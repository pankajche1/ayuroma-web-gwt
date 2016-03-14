package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.place.BrowseEmployeesPlace;
import com.ayurma.ayuromaweb.client.admin.place.EditEmployeePlace;

import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseEmployeesView;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.ayurma.ayuromaweb.shared.MobileDTO;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BrowseEmployeesActivity extends AbstractActivity implements IBrowseEmployeesView.Presenter{
	private PlaceController placeController;
	private AdminCache cache;
	private final AdminDataServiceAsync rpcService;
	private IBrowseEmployeesView view;
	private BrowseEmployeesPlace place;
	private int iPageEmployees=-1;
	private int nEmployeesPerPage=-1;
	private int displayMode=MULTI_ITEM_SELECTION_DIALOG;//default mode
	private Provider<EditEmployeePlace> editEmployeePlaceProvider; 
	public static final int STANDALONE_BROWSER=0;
	public static final int SINGLE_ITEM_SELECTION_DIALOG=3;
	public static final int MULTI_ITEM_SELECTION_DIALOG=1;
	public static final int MULTI_ITEM_SELECTION_STANDALONE=4;
	public static final int SINGLE_ITEM_SELECTION_STANDALONE=5;
	private List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
	@Inject
	public BrowseEmployeesActivity(PlaceController placeController,
			AdminCache cache, AdminDataServiceAsync rpcService,
			IBrowseEmployeesView view,
			Provider<EditEmployeePlace> editEmployeePlaceProvider) {
		
		this.placeController = placeController;
		this.cache = cache;
		this.rpcService = rpcService;
		this.view = view;
		this.editEmployeePlaceProvider = editEmployeePlaceProvider;
		this.view.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(BrowseEmployeesPlace place){
		this.place = place;
		//deciding which type of view is to be shown:
		displayMode = Integer.valueOf(place.getPlaceName());
		// initiation of the view to show the required interface
		// e.g. for a standalone browser it should not show the
		// cancel and ok buttons at the bottom:
		view.init();
		view.reset();// clear the view of earlier data items
		
	}

	@Override
	public void onLoadEmployeesButtonClicked(String strPageNumber,String strItemsPerPage,
			int sourceId) {
		view.info("Loading...", 0);
		String strIndexPage="0";//a zero base index
		try{
			iPageEmployees=Integer.valueOf(strPageNumber);//a 1 based index
			iPageEmployees--;//the number given by a common user is 1 based not zero based
			strIndexPage=String.valueOf(iPageEmployees);
			nEmployeesPerPage=Integer.valueOf(strItemsPerPage);
		}catch(NumberFormatException e){
			iPageEmployees=-1;
		}
		if(iPageEmployees<0){
			//view.info("No suce page number!", 2);
			view.info("", 0);
			return;
		}
		if(!getListFromCache(iPageEmployees,nEmployeesPerPage))
			fetchEmployeesInfoList(iPageEmployees,nEmployeesPerPage);
		
	}
	private void fetchEmployeesInfoList(int iPage,int itemsPerPage){
		//TODO at this time page index thing has not been put. in future think of it
		rpcService.getEmployees(new AsyncCallback<List<EmployeeDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending message", 0);
				}else{
					view.info("Error in sending message", 0);
				}
			}

			@Override
			public void onSuccess(List<EmployeeDTO> employees) {
				
				
				//if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);
				view.info("",0);
				processListFromDb(employees);
				
				
			}});
		
	}
	private void processListFromDb(List<EmployeeDTO> employees){
		this.employees = employees;
		//creating a list of of products names:
		List<String> names= new ArrayList<String>();
		List<String> snEmployees = new ArrayList<String>();//the serial number of the products
		int iEmployee=0;
		int sn = 0;
		for(int i=0;i<this.employees.size();i++){

			names.add(this.employees.get(i).getName());
			//iProduct=iPageProducts*nProductsPerPage+i+1;
			sn = iEmployee + 1;
			snEmployees.add(String.valueOf(sn));
			iEmployee++;
		}
		//calling the view to show this list:


		view.showEmployeesList(names,snEmployees,displayMode);
		
	}
	
	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	private boolean getListFromCache(int iPage,int nItemsPerPage){
		//TODO add the cache facility to store employees
		return false;
		
	}
	@Override
	public void onEditEmployeeClicked(int iEmployee) {
		//first get the product id from the data:
		int index=-1;
		try{
			index=Integer.valueOf(iEmployee);
		}catch(Exception e){
			return;
		}
		
		//getting the key of the target product:
		Long key = employees.get(index).getKey();
		EditEmployeePlace place = editEmployeePlaceProvider.get();
		place.setPlaceName(String.valueOf(key));
		placeController.goTo(place);

	}

	@Override
	public void onDeleteEmployeeClicked(int id) {
		view.info("deleting employee...", 0);
		
		//the id is the index of the employee in the employees list:
		int index=-1;
		try{
			index=Integer.valueOf(id);
		}catch(Exception e){
			view.info("", 0);
			return;
		}
		EmployeeDTO target = employees.get(index);
		
		if(target!=null){
			//create dto for data transfer and not unnecessary data:
			EmployeeDTO dto = new EmployeeDTO(target.getKey());
			//set the mobiles keys to be deleted:
			/*
			List<MobileDTO> mobilesToDelete = new ArrayList<MobileDTO>();
			for(MobileDTO m:target.getMobiles()){
				MobileDTO mdto = new MobileDTO(m.getKey());
				mobilesToDelete.add(mdto);
			}
			//set the mobiles to the dto:
			dto.setMobiles(mobilesToDelete);
			*/
			//now delete the employee:
			deleteEmployee(dto);
		}
		else view.info("",0);
		
	}

	@Override
	public void linkButtonClicked(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDisplayMode() {
		return this.displayMode;
	}

	@Override
	public void clearEmployeesCache() {
		// TODO Auto-generated method stub
		
	}
	public void deleteEmployee(EmployeeDTO dto){
		rpcService.deleteEmployee(dto, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending message", 0);
				}else{
					view.info("Error in sending message", 0);
				}
				
			}

			@Override
			public void onSuccess(String response) {
				
				
				System.out.println(response);
				if(response!=null) view.info(response,0);
				else view.info("May be some error. Please Check...",0);
				
				
			}});
	}

}
