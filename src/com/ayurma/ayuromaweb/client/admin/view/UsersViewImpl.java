package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.util.UserLevelData;
import com.ayurma.ayuromaweb.client.admin.view.user.UserInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class UsersViewImpl extends Composite implements IUsersView {

	private static UsersViewImplUiBinder uiBinder = GWT
			.create(UsersViewImplUiBinder.class);

	interface UsersViewImplUiBinder extends UiBinder<Widget, UsersViewImpl> {
	}
	private Presenter presenter;
	@UiField HTMLPanel dataPanel;
	@UiField Label lblMessage;
	private List<UserInfo> listUserUi;
	public UsersViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void info(String message, int id) {
		lblMessage.setText(message);
		
		
	}
	@Override
	public void userItemInfo(String message,int iUserItem){
		if(listUserUi!=null){
			if(iUserItem<listUserUi.size()){
				listUserUi.get(iUserItem).info(message, 0);
			}
		}
	}
	@Override
	public void addUserData(String userName,String userEmail,List<String> areaNames,
			List<Boolean> areaAccessInfo,List<Boolean> chkValues,UserLevelData levelData,
			int iLevelSelected){
		UserInfo item = new UserInfo();
		//item.setUserName(names.get(i));
		item.setUserEmail(userEmail);
		item.setPresenter(presenter);
		item.setId(listUserUi.size());
		item.createAccessData(areaNames,areaAccessInfo,chkValues);
		item.createUserLevels(levelData,iLevelSelected);
		listUserUi.add(item);
		dataPanel.add(item);
	}
	@Override
	public void showData(List<String> names,List<String> emails,List<String> chkNames,
			List<String> adminLevels){
		int i=0;
		dataPanel.clear();
		listUserUi = new ArrayList<UserInfo>(); 
		for(i=0;i<names.size();i++){
			UserInfo item = new UserInfo();
			//item.setUserName(names.get(i));
			item.setUserEmail(emails.get(i));
			item.setPresenter(presenter);
			item.setId(i);
			//item.createAccessData(chkNames,new ArrayList<Boolean>());
			//item.createUserLevels(adminLevels,0);
			listUserUi.add(item);
			dataPanel.add(item);
		}
	}
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	@Override
	public void init() {
		dataPanel.clear();
		listUserUi = new ArrayList<UserInfo>(); 
		
	}

}
