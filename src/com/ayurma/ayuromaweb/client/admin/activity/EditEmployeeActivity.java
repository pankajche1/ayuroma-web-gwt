package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.place.EditEmployeePlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IEditEmployeeView;
import com.ayurma.ayuromaweb.client.admin.view.widgets.IEditAddressView;
import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class EditEmployeeActivity extends AbstractActivity implements IEditEmployeeView.Presenter,
			IEditAddressView.Presenter{
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private final IEditEmployeeView view;
	private EditEmployeePlace place;
	private EmployeeDTO targetEmployee;
	/*
	 * codeEdit = 1 for city in address update
	 * 2 for state update
	 * 3 for country update
	 * 4 for address lines update
	 * 5 for address removal
	 */
	private int codeEdit = 0;//this is edit code that goes to the server
	@Inject
	public EditEmployeeActivity(PlaceController placeController,
			AdminDataServiceAsync rpcService, IEditEmployeeView view) {
		
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.view = view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(EditEmployeePlace place){
		this.place=place;
		view.reset();
		
		view.info("Loading data. Pleasse wait...",0);
		try{
			String strKey = place.getPlaceName();
			fetchEmployeeData(Long.valueOf(strKey));
		}catch(NumberFormatException e){
			view.info("Some Error! try again.",0);
		}
	}
	private void fetchEmployeeData(Long key) {
		rpcService.getEmployeeByKey(key, new AsyncCallback<EmployeeDTO>(){

			@Override
			public void onFailure(Throwable caught) {
				
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("IncompatibleRemoteServiceException", 0);
				}else{
					view.info("Error in getting data from server.", 0);
				}
				
			}

			@Override
			public void onSuccess(EmployeeDTO employee) {
				
				
				//if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);
				processEmployeeFromDb(employee);
				
				
			}});
	}
	public void processEmployeeFromDb(EmployeeDTO targetEmployee){
		if(targetEmployee==null){
			view.info("Data was not found on the server", 0);
			return;
		}
		view.info("", 0);//main message at the top
		this.targetEmployee = targetEmployee;
		view.setData(this.targetEmployee);
		
		
	}

	@Override
	public void deleteAddress(Long keyAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCity(String city, Long keyAddress) {
		codeEdit = 1;
		AddressDTO dto = new AddressDTO();
		dto.setKey(keyAddress);
		dto.setCity(city);
		updateEmployeeAddress(dto, codeEdit);
		
		
	}

	@Override
	public void updateState(String state, Long keyAddress) {
		codeEdit = 2;
		AddressDTO dto = new AddressDTO();
		dto.setKey(keyAddress);
		dto.setState(state);
		updateEmployeeAddress(dto, codeEdit);
		
	}

	@Override
	public void updateCountry(String country, Long keyAddress) {
		codeEdit = 3;
		AddressDTO dto = new AddressDTO();
		dto.setKey(keyAddress);
		dto.setCountry(country);
		updateEmployeeAddress(dto, codeEdit);
		
	}
	@Override
	public void updateAddressLines(List<String> lines, Long keyAddress){
		codeEdit = 5;
		List<String> lines2 = new ArrayList<String>();
		for(String line:lines){
			String l = line.trim();
			if(!l.isEmpty()) lines2.add(l);
		}
		AddressDTO dto = new AddressDTO();
		dto.setKey(keyAddress);
		dto.setAddressLines(lines2);
		//now adding the address:
		//but if there are no address lines don't add it to the addresses:
		if(!lines2.isEmpty()) updateEmployeeAddress(dto, codeEdit);
		
	}
	public void updateEmployeeName(String name){
		targetEmployee.setName(name);
		//code is 1 for updating only the name:
		updateEmployee(targetEmployee.getKey(),targetEmployee,1);
		
	}
	public void updateEmployeeDesignation(String designation){
		targetEmployee.setDesignation(designation);
		//code is 1 for updating only the name:
		updateEmployee(targetEmployee.getKey(),targetEmployee,2);
		
	}
	
	public void updateEmployee(Long key, EmployeeDTO updatedEmployee, int code){
		rpcService.updateEmployee(key,updatedEmployee,code, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				
				//view.info("RPC failed!",0);
				
			}

			@Override
			public void onSuccess(String response) {
				
				
				//if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);
				System.out.println(response);
				
				
			}});
	}
	public void updateEmployeeAddress(AddressDTO dto, int code){
		rpcService.updateEmployeeAddress(dto ,code, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("IncompatibleRemoteServiceException", 0);
				}else{
					view.info("Error in getting data from server.", 0);
				}
				
			}

			@Override
			public void onSuccess(String response) {
				
				
				processAddressUpdateResponse(response);
				
				
				
				
				
			}});
	}
	private void processAddressUpdateResponse(String response){
		if(response==null){
			view.info("Some error",0);return;
		}
		if(response.equals("202")){
			switch(codeEdit){
			case 0:
			case 1://update city successs:
				view.info("City updated successfully", 0);break;
			case 2://update city successs:
				view.info("State updated successfully", 0);break;
			case 3://update country successs:
				view.info("Country updated successfully", 0);break;
			}
		}else if(response.equals("303")){
			view.info("Key is null", 0);
		}
		
	}

	public EmployeeDTO getTargetEmployee() {
		return targetEmployee;
	}

}
