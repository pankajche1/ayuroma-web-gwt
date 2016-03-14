package com.ayurma.ayuromaweb.client.admin.view.user;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.util.UserLevelData;
import com.ayurma.ayuromaweb.client.admin.view.IUsersView.Presenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class UserInfo extends Composite {

	private static UserInfoUiBinder uiBinder = GWT
			.create(UserInfoUiBinder.class);

	interface UserInfoUiBinder extends UiBinder<Widget, UserInfo> {
	}
	@UiField
	InlineLabel lblUserName,lblUserEmail;
	@UiField HTMLPanel accessDataPanel;
	@UiField ListBox lbUserLevel;
	@UiField InlineLabel lblMessage;
	private List<CheckBox> chks;
	private Presenter presenter;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo() {
		initWidget(uiBinder.createAndBindUi(this));
		//creating the ui:
		chks = new ArrayList<CheckBox>();
		//putting the check boxes:
		
	}
	public void createAccessData(List<String> checkNames,List<Boolean> areaAccessInfo,
			List<Boolean> checkboxesValues){
		CheckBox chk;
		int i=0;
		for(String label:checkNames){
			chk = new CheckBox(label);
			chk.setValue(checkboxesValues.get(i));
			//add the check box to the interface only if admin wants
			if(areaAccessInfo.get(i)) accessDataPanel.add(chk);//this is DOM addition
			//all the checkboxes will be put in a java List:
			chks.add(chk);
			
			i++;
		}
	}
	public void createUserLevels(UserLevelData levelData,int iSelected){
		List<String> names = levelData.getLevelNames();
		List<Integer> values = levelData.getLevelValues();
		int i=0;
		//System.out.println("UserInfo::createUserLevels, names size:"+names.size());
		for(String label:names){
			lbUserLevel.addItem(label,String.valueOf(values.get(i)));
			i++;
		}
		//System.out.println("    item counts::"+lbUserLevel.getItemCount()+
				//", iSelected:"+iSelected);
		try{
		if(lbUserLevel.getItemCount()>iSelected)
			lbUserLevel.setItemSelected(iSelected, true);
		}catch(Exception e){
			//System.out.println("Exception, e="+e.getLocalizedMessage());
		}
		
	}
	public List<Boolean> getCheckValues(){
		List<Boolean> vals = new ArrayList<Boolean>();
		for(CheckBox chk:chks){
			vals.add(chk.getValue());
		}
		return vals;
	}
	public int getUserLevel(){
		
		return lbUserLevel.getSelectedIndex();
	}

	public void setUserName(String name){
		lblUserName.setText(name);
	}
	public void setUserEmail(String email){
		lblUserEmail.setText(email);
	}
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	@UiHandler("btnUpdateUser")
	public void onBtnRegisterClick(ClickEvent event){
		presenter.addUserToAdminGroup(id, getCheckValues(),
				lbUserLevel.getValue(lbUserLevel.getSelectedIndex()));
	}
	@UiHandler("btnDeleteUser")
	public void onBtnDeleteUserClick(ClickEvent event){
		confirmDelete();
	}
	private void confirmDelete(){
		boolean option=Window.confirm("Do you want to delete the User?");
		if(option){
			//presenter.deleteFilm(id);
			presenter.deleteUser(id);
			
		}
	}
	public void info(String message,int id){
		lblMessage.setText(message);
	}
	

}
