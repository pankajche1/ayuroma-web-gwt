package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.place.AddNewEmployeePlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IAddNewEmployeeView;
import com.ayurma.ayuromaweb.client.admin.view.widgets.IAddressWidget;
import com.ayurma.ayuromaweb.client.admin.view.widgets.IAddressesPanel;
import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.ayurma.ayuromaweb.shared.EmployeeEmailDTO;
import com.ayurma.ayuromaweb.shared.EmployeePhoneDTO;
import com.ayurma.ayuromaweb.shared.MobileDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class AddNewEmployeeActivity extends AbstractActivity implements IAddNewEmployeeView.Presenter{
	private IAddNewEmployeeView view;
	private final PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private AddNewEmployeePlace place;
	@Inject
	public AddNewEmployeeActivity(IAddNewEmployeeView view,
			PlaceController placeController, AdminDataServiceAsync rpcService) {
		super();
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
	}
	public void init(AddNewEmployeePlace place){
		this.place=place;
		//clearing the already exiting data on the view:
		view.reset();
		
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}

	@Override
	public void gotoPlace(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAddEmployeeButtonClicked(String name, String designation,
			List<String> lstMobileNumbers,
			List<String> lstPhones,
			List<String> lstEmails	) {
		view.info("Saving the data...",0);
		EmployeeDTO employee = new EmployeeDTO(name, designation);
		List<MobileDTO> lstMobiles = new ArrayList<MobileDTO>();
		for(String number:lstMobileNumbers){
			MobileDTO dto = new MobileDTO();
			dto.setNumber(number);
			lstMobiles.add(dto);
		}
		employee.setMobiles(lstMobiles);
		//put the phones:
		List<EmployeePhoneDTO> phonesDto = new ArrayList<EmployeePhoneDTO>();
		for(String number:lstPhones){
			EmployeePhoneDTO dto = new EmployeePhoneDTO("","",number);//empty area code and country code
			phonesDto.add(dto);
		}
		employee.setPhones(phonesDto);
		//emails:
		List<EmployeeEmailDTO> emailsDto = new ArrayList<EmployeeEmailDTO>();
		for(String address:lstEmails){
			EmployeeEmailDTO dto = new EmployeeEmailDTO(address);
			emailsDto.add(dto);
		}
		employee.setEmails(emailsDto);
		//get the addresses:
		IAddressesPanel addressesPanel = view.getAddressesPanel();
		
		employee.setAddresses(processAddresses(addressesPanel));
		
		saveEmployee(employee);
		
	}
	private List<AddressDTO> processAddresses(IAddressesPanel addressesPanel){
		//now get the addresses from this panel:
		List<IAddressWidget> listAddressWidgets = addressesPanel.getPanels();
		//now create the data for address:
		List<AddressDTO> addresses = new ArrayList<AddressDTO>();
		for(IAddressWidget w:listAddressWidgets ){
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setCity(w.getTbCity().getText().trim());
			addressDTO.setState(w.getTbState().getText().trim());
			addressDTO.setCountry(w.getTbCountry().getText().trim());
			List<String> lines = w.getLines();
			List<String> lines2 = new ArrayList<String>();
			for(String line:lines){
				String l = line.trim();
				if(!l.isEmpty()) lines2.add(l);
			}
			addressDTO.setAddressLines(lines2);
			//now adding the address:
			//but if there are no address lines don't add it to the addresses:
			if(!lines2.isEmpty()) addresses.add(addressDTO);
			
		}
		return addresses;
		
	}
	private void saveEmployee(EmployeeDTO employee){
		rpcService.saveEmployee(employee, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("IncompatibleRemoteServiceException: "+caught.toString(), 0);
				}else{
					
					view.info("RPC failure: "+caught.toString(), 0);
				}
				
				
			}

			@Override
			public void onSuccess(String result) {
				
				//System.out.println(result);
				if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);

				
				
			}});
	}

}
