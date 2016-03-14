package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.place.EmployeePlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.ayurma.ayuromaweb.shared.MobileDTO;
import com.ayurma.ayuromaweb.shared.MobileUpdateDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class EmployeeActivity extends AbstractActivity{
	private final AdminDataServiceAsync rpcService;
	private final EventBus eventBus;
	private EmployeePlace place;
	private List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
	private EmployeeDTO targetEmployee;
	private MobileDTO mobileToAdd;
	private MobileDTO targetMobile;
	int codeUpdate = 0;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// TODO Auto-generated method stub
		
	}
	@Inject
	public EmployeeActivity(AdminDataServiceAsync rpcService, EventBus eventBus) {

		this.rpcService = rpcService;
		this.eventBus = eventBus;
	}
	public void init(EmployeePlace place) {
		this.place = place;
		
	}
	public void addEmployee(String name, String designation){
		EmployeeDTO employee = new EmployeeDTO(name, designation);
		saveEmployee(employee);
	}
	private void saveEmployee(EmployeeDTO employee){
		rpcService.saveEmployee(employee, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				
				//view.info("RPC failed!",0);
				
			}

			@Override
			public void onSuccess(String result) {
				
				System.out.println(result);
				//if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);

				
				
			}});
	}
	public void getEmployeesFromDb() {
		rpcService.getEmployees(new AsyncCallback<List<EmployeeDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				
				//view.info("RPC failed!",0);
				
			}

			@Override
			public void onSuccess(List<EmployeeDTO> employees) {
				
				
				//if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);
				processListFromDb(employees);
				
				
			}});
	}
	private void processListFromDb(List<EmployeeDTO> employees){
		this.employees = employees;
		
	}
	public List<EmployeeDTO> getEmployees() {
		return employees;
	}
	public void getEmployeeByKey(Long key){
		rpcService.getEmployeeByKey(key, new AsyncCallback<EmployeeDTO>(){

			@Override
			public void onFailure(Throwable caught) {
				
				//view.info("RPC failed!",0);
				
			}

			@Override
			public void onSuccess(EmployeeDTO employee) {
				
				
				//if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);
				processEmployeeFromDb(employee);
				
				
			}});
	}
	public EmployeeDTO getTargetEmployee() {
		return targetEmployee;
	}
	public void processEmployeeFromDb(EmployeeDTO targetEmployee){
		this.targetEmployee = targetEmployee;
		
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
	public void deleteEmployee(Long key){
		EmployeeDTO dto = new EmployeeDTO();
		dto.setKey(key);
		rpcService.deleteEmployee(dto, new AsyncCallback<String>(){

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
	public void getEmployeeMobilesFromDb(Long key){
		rpcService.getEmployeeMobiles(key, new AsyncCallback<List<MobileDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				
				//view.info("RPC failed!",0);
				
			}

			@Override
			public void onSuccess(List<MobileDTO> mobiles) {
				
				
				//if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);
				processEmployeeMobiles(mobiles);
				
				
			}});
	}
	private void processEmployeeMobiles(List<MobileDTO> mobiles){
		//put these mobiles in the target employee data:
		if(targetEmployee!=null){
			targetEmployee.setMobiles(mobiles);
		}
		
	}
	public void addEmployeeMobile(Long key, String mobile){
		mobileToAdd = new MobileDTO(mobile, key);
		
		rpcService.addEmployeeMobile(key, mobile, new AsyncCallback<MobileUpdateDTO>(){

			@Override
			public void onFailure(Throwable caught) {
				
				//view.info("RPC failed!",0);
				
			}

			@Override
			public void onSuccess(MobileUpdateDTO response) {
				
				
				//if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);
				System.out.println(response);
				processAddEmployeeMobile(response);
				
				
			}});
		
	}
	public void updateMobile(MobileDTO dto, int code){
		codeUpdate = code;
		targetMobile = dto;
		rpcService.updateMobile(dto, code, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				
				//view.info("RPC failed!",0);
				
			}

			@Override
			public void onSuccess(String response) {
				
				
				//if(result!=null) view.info(result,0);
				//else view.info("result string from the server is NULL",0);
				processUpdateEmployeeMobile(response);
				
				
			}});
	}
	private void processUpdateEmployeeMobile(String response){
		
		switch(codeUpdate){
		case 0:
		case 1:
		case 2:
			if(response.equals("Mobile-update-success")){
				//add the new mobile to the target employee:
				if(targetEmployee!=null){
					targetEmployee.addMobile(mobileToAdd);
				}
				
			}else if(response.equals("mobile-key-null")){
				System.out.println("Mobile key is NULL.");
			}
			break;
		case  3://here comes after deleting the mobile
			if(response.equals("Mobile-delete-success")){
				//remove the deleted mobile from the mobile list:
				//System.out.println("came here");
				if(targetEmployee!=null){
					targetEmployee.deleteMobile(targetMobile);
				}
				targetMobile = null;
				
				
			}
			
			break;
		}
		
	}
	private void processAddEmployeeMobile(MobileUpdateDTO response){
		if(response.getResponse().equals("mobile-add-success")){
			//add the new mobile to the target employee:
			mobileToAdd.setKey(response.getUpdatedMobile().getKey());
			if(targetEmployee!=null){
				targetEmployee.addMobile(mobileToAdd);
			}
			
		}
		
	}
	
	
	

}
