package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.util.UserLevelData;
import com.google.gwt.user.client.ui.Widget;

public class UsersViewMock implements IUsersView{
	private List<User> users=new ArrayList<User>();
	private Presenter presenter;
	public UsersViewMock(){
		System.out.println("UsersViewMock created...");
	}
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void info(String message, int id) {
		System.out.format("UsersViewMock::info, message:'%s', id:%d\n\n",message,id);
		
	}

	@Override
	public void setPresenter(Presenter presenter) {
		System.out.format("UsersViewMock::setPresenter()...\n\n");
		this.presenter=presenter;
	
		
	}

	@Override
	public void init() {
		users=new ArrayList<User>();
	}

	@Override
	public void showData(List<String> names, List<String> emails,
			List<String> chkNames, List<String> userLevels) {
		System.out.format("UsersViewMock::showData()...\n\n");
		int i=0;
		for(String name:names){
			System.out.format("\tName:'%s'\n",name);
			
			i++;
		}
		System.out.println("Checkboxes:");
		for(String chkName:chkNames){
			System.out.print(chkName+", ");
		}
		System.out.println();
		
	}

	@Override
	public void userItemInfo(String message, int iUserItem) {
		System.out.format("UsersViewMock::userItemInfo, message:'%s',for user with id:%d\n\n",message,iUserItem);
		
	}
	private class UserUi{
		private int id;
		private List<String> checkboxes= new ArrayList<String>();
		public UserUi(int id){
			this.id=id;
		}
		public void setCheckBoxesNames(List<String> checkboxes){
			this.checkboxes=checkboxes;
		}
	}
	@Override
	public void addUserData(String userName, String userEmail,
			List<String> areaNames, List<Boolean> areaAccessInfo,List<Boolean> chkValues,
			UserLevelData levelData,int iLevelSelected) {
		System.out.println("UsersViewMock::addUserData()");
		System.out.println("-----------------");
		System.out.println("\tthe checkbox name list:");
		for(String label:areaNames){
			System.out.format("%s,",label);
			
		}	
		System.out.println();
		User user = new User(users.size(),userName,userEmail);
		user.setCheckBox(areaNames,areaAccessInfo,chkValues);
		//admin level data:
		user.setAdminLevelData(levelData);
		user.setAdminLevel(iLevelSelected);
		user.createUserLevels(levelData, iLevelSelected);
		users.add(user);
		System.out.println(user.toString());
	
		
	}
	public void updateUser(int iUser){
		//the last parameter is for user level:
		if(iUser<users.size()){
			presenter.addUserToAdminGroup(iUser,
					users.get(iUser).getCheckboxValues(), 
					users.get(iUser).getAdminLevel());
		}else{
			System.out.println("UsersViewMock::updateUser() index > size");
		}
	}
	public void allowUserToAccessArea(int iUser,int iArea,boolean isAllowed){
		if(iUser<users.size()){
			users.get(iUser).setAccessToArea(iArea, isAllowed);
		}else System.out.println("Mock View:: index > size");
	}
	public void selectAdminLevel(int iUser,int indexListBox){
		 System.out.println("Mock View:: selecting admin level in the list box:"+indexListBox+", for user:"+iUser);
		users.get(iUser).selectAdminLevel(indexListBox);
	}
	public void printUsers(){
		for(User user:users){
			System.out.println(user.toString());
		}
	}
	private class User{
		private int id;
		private String name="";
		private String email="";
		private List<String> checkBoxLabels=new ArrayList<String>();
		private List<Boolean> checkboxValues=new ArrayList<Boolean>();
		private List<Boolean> checkDisplayInfo=new ArrayList<Boolean>();
		//admin level data:
		private UserLevelData adminLevelData;
		private int indexLevelSelected=-1;
		private int adminLevel=-1;
				
		public User(int id,String name,String email){
			this.id=id;
			this.name=name;
			this.email=email;
		}

		public void setCheckBox(List<String> checkBoxLabels,
				List<Boolean> chkDisplayInfo,
				List<Boolean> checkValue) {
			this.checkBoxLabels = checkBoxLabels;
			this.checkboxValues=checkValue;
			this.checkDisplayInfo=chkDisplayInfo;

		}

		public UserLevelData getAdminLevelData() {
			return adminLevelData;
		}

		public void setAdminLevelData(UserLevelData adminLevelData) {
			this.adminLevelData = adminLevelData;
		}

		public void selectAdminLevel(int index){
			System.out.println("MockView:user: admin level select index:"+index);
			this.indexLevelSelected=index;
		}

		public void setAdminLevel(int adminLevel) {
			this.adminLevel = adminLevel;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		public void setAccessToArea(int iArea,boolean isAllowed){
			if(iArea<checkBoxLabels.size()){
				checkboxValues.set(iArea, isAllowed);
			}else System.out.println("UserMock:: index>size");
		}

		public List<String> getCheckBoxLabels() {
			return checkBoxLabels;
		}

		public List<Boolean> getCheckboxValues() {
			return checkboxValues;
		}
		public String getAdminLevel(){
			System.out.println("MockView:user: admin level get admin level index:"+indexLevelSelected);
			
			List<Integer> values = adminLevelData.getLevelValues();
			System.out.println("MockView:user: admin level values:"+values.toString());
			return String.valueOf(values.get(indexLevelSelected)); 
		}
		public void createUserLevels(UserLevelData levelData,int iSelected){
			List<String> names = levelData.getLevelNames();
			List<Integer> values = levelData.getLevelValues();
			int i=0;
			System.out.println("UsersViewMock::User::createUserLevels()");
			for(String label:names){
				//lbUserLevel.addItem(label,String.valueOf(values.get(i)));
				System.out.println("list box names:"+label);
				i++;
			}
			//if(lbUserLevel.getItemCount()>iSelected){
				//lbUserLevel.setItemSelected(iSelected, true);
			//}	
			
		}
		@Override
		public String toString() {
			
			return "User [id=" + id + ", name=" + name + ", email=" + email
					+ ", checkBoxLabels=" + checkBoxLabels
					+ ", checkboxValues=" + checkboxValues
					+ ", checkDisplayInfo=" + checkDisplayInfo
					+ ", adminLevelData=" + adminLevelData.toString() + ", indexAdminLevelSelected="
					+ adminLevel + "]";
		}




		
		
	}

}
