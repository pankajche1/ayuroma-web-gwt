package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.util.UserSettings;
import com.ayurma.ayuromaweb.client.admin.view.IRegistrationView;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class RegistrationViewPresenter implements IRegistrationView.Presenter{
	private final AdminDataServiceAsync rpcService;
	private final IRegistrationView view;
	private String email="";
	private UserDTO user;
	@Inject
	public RegistrationViewPresenter(AdminDataServiceAsync rpcService,IRegistrationView view){
		this.rpcService=rpcService;
		this.view=view;
		this.view.setPresenter(this);
		
	}
	public void init(UserDTO user){
		this.user=user;
		view.clear();
		view.showData(user.getEmail());
	}
	public void go(HasWidgets container){
		
		container.add(view.asWidget());
		
	}
	/*
	public void apply(){
		//UserDTO dto = new UserDTO();
		//dto.setEmail(email);
		rpcService.saveUser(user, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, 0);
				
			}});
	}
	*/

	@Override
	public void registerUser(String textHoneyPot) {
		view.info("Processing, please wait...", 0);
		UserDTO dto = new UserDTO();
		dto.setEmail(email);
		dto.textHoneyPot=textHoneyPot;
		//creating access areas:
		int[] accessData = UserSettings.getMenuArray();
		for(int i=0;i<accessData.length;i++){
			accessData[i]=0;
		}
		dto.setAccessSections(accessData);
		rpcService.saveUser(dto, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("Check your internet connection...", 0);
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, 0);
				
			}});
	}
		
	

}
