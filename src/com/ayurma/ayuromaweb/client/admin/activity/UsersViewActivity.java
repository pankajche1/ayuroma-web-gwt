package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ayurma.ayuromaweb.client.admin.place.UsersPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.util.UserLevelData;
import com.ayurma.ayuromaweb.client.admin.util.UserSettings;
import com.ayurma.ayuromaweb.client.admin.view.IUsersView;
import com.ayurma.ayuromaweb.server.model.UserConstants;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class UsersViewActivity extends AbstractActivity implements IUsersView.Presenter{
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private UsersPlace place;
	private IUsersView view;
	private List<UserDTO> users=new ArrayList<UserDTO>();
	private List<User> usersInfo=new ArrayList<User>();
	private int iTargetUser=-1;
	private List<Integer> accessInfo;
	private List<Integer> adminLevels;
	private AdminCache cache;
	private UserDTO user;
	private Map<Integer,String> mapMenus;
	private List<String> menuNames;
	
	@Inject
	public UsersViewActivity(PlaceController placeController, AdminDataServiceAsync rpcService,
			IUsersView view,AdminCache cache){
		this.placeController=placeController;
		this.rpcService=rpcService;
		this.view=view;
		this.view.setPresenter(this);
		this.cache=cache;
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		System.out.println("start() called...");
		panel.setWidget(view.asWidget());
		
	}

	public void init(UsersPlace place) {
		
		this.place=place;
		//getting the current user:
		user=cache.getUser();
		//reset the view:
		view.init();
		if(user.getAdminLevel()>UserSettings.ADMIN_LEVEL_ONE){
			//user is not allowed to visit this section
			//TODO give message to the user about this
			return;
		}
		//initiating necessary data:
		initData();
		fetchUsers();
	}
	private void initData(){
		//names of the menu which are to be shown as the 
		//checkboxes names on the individuale items
		//mapMenus = UserSettings.getMapMenu();
		menuNames=UserSettings.getMenuNames();
		if(user.isAdmin()){
			//if sudo user is admin then:
			int[] accessAreas=new int[menuNames.size()];
		
			//setting full access to the admin sudo user
			
			for(int i=0;i<accessAreas.length;i++){
				accessAreas[i]=1;
			}
			user.setAccessSections(accessAreas);
		}

				
	}
	private void fetchUsers(){
		view.info("Loading, please wait...", 0);
		rpcService.getUsers("emailaddress", new AsyncCallback<List<UserDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("check your internet connection...",0);
				
			}

			@Override
			public void onSuccess(List<UserDTO> result) {
				view.info("", 0);
				processData(result);
				
			}} );
	}
	private void processData(List<UserDTO> result){
		users=new ArrayList<UserDTO>();
		
		for(UserDTO item:result){

			if(item.getAdminLevel()>user.getAdminLevel()||item.getAdminLevel()==UserSettings.ADMIN_LEVEL_NULL){
				if(!item.getEmail().equals(user.getEmail()))
					users.add(item);
			}
		}
		//System.out.println("UsersViewActivity::processData, size:"+users.size());
		for(UserDTO item:users){
			
			
				showUserInView(item);
		}
		
	}
	private void showUserInView(UserDTO item){
		List<String> chkBoxNames=new ArrayList<String>();
		List<Boolean> displayInfo = new ArrayList<Boolean>();
		List<Boolean> chkBoxValues = new ArrayList<Boolean>();
		//traversing the menu list:
		int i=0;
		//get the access area info from the sudo user:
		int[] accessAreas=user.getAccessSections();
		for(String menuName:menuNames){
			//put the name of the check box:
			chkBoxNames.add(menuName);
			

			
				if(accessAreas[i]==1){
					if(!user.isAdmin()){
						if(menuName.equals("Users")){
							displayInfo.add(false);
						}else displayInfo.add(true);
					}else displayInfo.add(true);
				}else{
					displayInfo.add(false);
				}
			
			i++;
		}
		//now setting the checkbox values:
		int[] accessAreasItem=item.getAccessSections();
		for(int code:accessAreasItem){
			if(code==1){
				chkBoxValues.add(true);
			}else chkBoxValues.add(false);
		}
		//TODO now give the above three lists to the view:
		//view.addUser(name,email,{menu access info list},{admin level info})
		//public void addUserData(String userName,String userEmail,List<String> areaNames,
				//List<Boolean> areaAccessInfo,List<Boolean> chkValues,List<String> userLevels)
		//setting the admin level list box data:
		int iMinAdminLevel=UserSettings.ADMIN_LEVEL_NULL;
		if(user.isAdmin())
			iMinAdminLevel=UserSettings.ADMIN_LEVEL_ROOT;
		else iMinAdminLevel=user.getAdminLevel()+1;
		UserLevelData levelData = UserSettings.getUserAdminLevels(iMinAdminLevel); 
		int indexSelectedLevel=levelData.getAdminLevelIndex(item.getAdminLevel());
		//System.out.println("User View Actvity:: iSelectedLevel:"+levelData.toString());
		view.addUserData(item.getName(),
				item.getEmail(),
				chkBoxNames,
				displayInfo,
				chkBoxValues, levelData,indexSelectedLevel);
		
	}
	private void processData2(List<UserDTO> result){
		users=result;
		usersInfo=new ArrayList<User>();
		List<String> names = new ArrayList<String>();
		List<String> emails = new ArrayList<String>();
		mapMenus = UserSettings.getMapMenu();
		if(user.isAdmin()){
			//process the info differentely to show all the things:
			processUsersForAdmin();
			return;
		}
		String email="";
		if(user!=null) email=user.getEmail();
		int i=0;
		for(UserDTO item:users){
			//do not show the current user in the user lists:
			if(!item.getEmail().equals(email)){
				names.add(item.getName());
				emails.add(item.getEmail());
				User userInfo = new User(i,item.getName(),item.getEmail());
				//feeding the access info:
				int[] access=item.getAccessSections();
				
				for(int code:access){
					if(code==1) userInfo.addAccessInfoValue(true);
					else userInfo.addAccessInfoValue(false);
				}
				usersInfo.add(userInfo );
				
			}
			i++;
		}
		//now show the users in the view:
		//resetting the view:
		view.init();
		i=0;
		for(User item:usersInfo){
			createUiData(user.getAccessSections(),i);
			i++;
		}
		//get the menus names mapped to some ids etc:
		//for example: UserSettings.HOME is mapped to the "Home" menu 
		
		
		//here it is to be decided which data is to be shown
		//from the UserDTO object you get [1,1,1,1,0,0,0] kind of array
		//i.e. at [0]:home, [1]:products, [2]:groups, [3]:sliders
		//make the accessAreas array in the UserDTO taking total number
		//of menus from the UserSettings: size=UserSetting.size
		int size = mapMenus.size();
		int[] accessAreas= new int[size];
		//same order will given in the user setting ui view:
		//chkNames=[home,products,,,,,] same order
		// int[] accessAreas = new int[size]
		List<String> chkNames=new ArrayList<String>();
		Set<Integer> keySet =mapMenus.keySet();
		
		Iterator<Integer> iterator = keySet.iterator();
		while(iterator.hasNext()){
			chkNames.add(mapMenus.get(iterator.next()));
		}
		accessInfo = new ArrayList<Integer>();
		
		/*
		// give the corresponding name to show on the view:
		accessInfo.add(UserConstants.HOME);
		chkNames.add("Home");
		accessInfo.add(UserConstants.COMPANY_DATA);
		chkNames.add("Company data");
		accessInfo.add(UserConstants.IMAGES);
		chkNames.add("Images");
		accessInfo.add(UserConstants.PRODUCT_GROUPS);
		chkNames.add("Product Groups");
		accessInfo.add(UserConstants.PRODUCTS);
		chkNames.add("Products");
		accessInfo.add(UserConstants.TOOLS);
		chkNames.add("Tools");
		accessInfo.add(UserConstants.SLIDERS);
		chkNames.add("Sliders");
		*/
		//now user level data:
		/*
		List<String> userLevels = new ArrayList<String>();
		adminLevels= new ArrayList<Integer>();
		adminLevels.add(UserConstants.ADMIN_LEVEL_ROOT);
		userLevels.add("Root");
		adminLevels.add(UserConstants.ADMIN_LEVEL_ONE);
		userLevels.add("Level 1");
		adminLevels.add(UserConstants.ADMIN_LEVEL_SECOND);
		userLevels.add("Level 2");
		adminLevels.add(UserConstants.ADMIN_LEVEL_THIRD);
		userLevels.add("Level 3");
		adminLevels.add(UserConstants.ADMIN_LEVEL_NULL);
		userLevels.add("No Access");
		*/
		//view.showData(names, emails,chkNames,userLevels);
	}
	private void processUsersForAdmin(){
		List<String> names = new ArrayList<String>();
		List<String> emails = new ArrayList<String>();
		int i=0;
		//traverse all the users loaded from the server:
		for(UserDTO item:users){
				
				names.add(item.getName());
				emails.add(item.getEmail());
				User userInfo = new User(i,item.getName(),item.getEmail());
				//feeding the access info:
				int[] access=item.getAccessSections();
				
				for(int code:access){
					if(code==1) userInfo.addAccessInfoValue(true);
					else userInfo.addAccessInfoValue(false);
				}
				usersInfo.add(userInfo );
				
			
			i++;
		}
		//resetting the view:
		view.init();
		//preparing the data to be shown on the view:
		int iUser=0;
		//creating the access data for the admin:
		int[] accessData=UserSettings.getMenuArray();
		for(int k=0;k<accessData.length;k++) accessData[k]=1;
		for(User item:usersInfo){
			
			Map<Integer,String> mapMenus1 = UserSettings.getMapMenu();
			//this will contain the names of the menus that will appear in the ui:
			List<String> menuNames=new ArrayList<String>();
			List<Boolean> accessInfo = new ArrayList<Boolean>();
			
			i=0;
			for(int info:accessData){
				if(info==1){
					menuNames.add(mapMenus1.get(i));
					usersInfo.get(iUser).addAccessInfo(menuNames.size()-1);
					// for information on the view check box true or false
					accessInfo.add(usersInfo.get(iUser).getAreaAccessInfo().get(i));
				}else{
					usersInfo.get(iUser).addAccessInfo(-1);
				}
				i++;
			}
			System.out.println("UserViewActivity::user data to be shown on the view:");
			System.out.println(usersInfo.get(iUser).toString());
			System.out.println("UserViewActivity::createUiData() adding the user to view:");
			System.out.println("-------------");
			view.addUserData(usersInfo.get(iUser).getName(),
					usersInfo.get(iUser).email,
					menuNames,null, accessInfo,null,0);
			i++;
			
		
			iUser++;
		}

	}
	public void createUiData(int[] accessData,int iUser){
		//get the menu array template:
		//int[] accessData = UserSettings.getMenuArray();
		//get the names of the menu mapped to their integer ids:
		Map<Integer,String> mapMenus1 = UserSettings.getMapMenu();
		//this will contain the names of the menus that will appear in the ui:
		List<String> menuNames=new ArrayList<String>();
		List<Boolean> accessInfo = new ArrayList<Boolean>();
		int i=0;
		for(int info:accessData){
			if(info==1){
				menuNames.add(mapMenus1.get(i));
				usersInfo.get(iUser).addAccessInfo(menuNames.size()-1);
				// for information on the view check box true or false
				accessInfo.add(usersInfo.get(iUser).getAreaAccessInfo().get(i));
			}else{
				usersInfo.get(iUser).addAccessInfo(-1);
			}
			i++;
		}
		System.out.println("UserViewActivity::user data to be shown on the view:");
		System.out.println(usersInfo.get(iUser).toString());
		System.out.println("UserViewActivity::createUiData() adding the user to view:");
		System.out.println("-------------");
		view.addUserData(usersInfo.get(iUser).getName(),
				usersInfo.get(iUser).email,
				menuNames,null ,accessInfo,null,0);
		System.out.println("-------------");
		
		
	}
	@Override
	public void addUserToAdminGroup(int iUser,List<Boolean> access,String strUserLevel){
		iTargetUser=iUser;
		view.userItemInfo("Registering, please wait...",  iTargetUser);
		//User targetUser = usersInfo.get(iUser);
		//List<Integer> list = targetUser.getAccessInfo();
		int[] accessData = new int[access.size()];
		/*
		int[] accessData = new int[mapMenus.size()];
		int j=0;
		int i=0;
		for(int item:list){
			if(item!=-1){
				if(access.get(i)) accessData[j]=1;
				else accessData[j]=0;
				i++;
			}else accessData[j]=0;
			j++;
		}
		*/
		int i=0;
		for(boolean val:access){
			//System.out.println("val:"+val);
			if(val)
				accessData[i]=1;
			else accessData[i]=0;
			i++;
		}
		
		//get the email of the user:
		//String email = users.get(iUser).getEmail();
		//System.out.println("Admin Level:"+ adminLevels.get(userLevel));
		//create the user:
		UserDTO user = users.get(iUser);
		//update the user:
		//access sections:
		user.setAccessSections(accessData);
		user.setAdminLevel(Integer.valueOf(strUserLevel));
		updateUser(user);
		
	}
	private void updateUser(UserDTO user){
		rpcService.updateUser(user, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				//view.info("check your internet connection...",0);
				view.userItemInfo("Please check your internet connection",  iTargetUser);
				
			}

			@Override
			public void onSuccess(String message) {
				//view.info(message, 0);
				view.userItemInfo(message,  iTargetUser);
				//processData(result);
				
			}} );
	}
	public List<UserDTO> getUsers() {
		return users;
	}
	public class User{
		private int id;
		private List<Integer> accessInfo=new ArrayList<Integer>();
		private List<Boolean> areaAccessInfo=new ArrayList<Boolean>();
		private String email="";
		private String name="";
		public User(int id,String name,String email){
			this.id=id;
			this.name=name;
			this.email=email;
		}
		public void addAccessInfo(int code){
			accessInfo.add(code);
		}
		public void addAccessInfoValue(boolean value){
			areaAccessInfo.add(value);
		}
		
		public List<Boolean> getAreaAccessInfo() {
			return areaAccessInfo;
		}
		public List<Integer> getAccessInfo() {
			return accessInfo;
		}
		
		@Override
		public String toString() {
			return "User [id=" + id + ", accessInfo=" + accessInfo
					+ ", areaAccessInfo=" + areaAccessInfo + ", email=" + email
					+ ", name=" + name + "]";
		}
		public String getEmail() {
			return email;
		}
		public String getName() {
			return name;
		}
		
	}
	public List<User> getUsersInfo() {
		return usersInfo;
	}
	public UserDTO getUser() {
		return user;
	}
	@Override
	public void deleteUser(int iUser) {
		iTargetUser=iUser;
		view.userItemInfo("Deleting, please wait...",  iTargetUser);
		UserDTO targetUser = users.get(iUser);
		
		if(targetUser==null){
			return;
		}
		rpcService.deleteUser(targetUser.getEmail(),
				user.getEmail(),
				new AsyncCallback<List<UserDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				//view.info("check your internet connection...",0);
				view.userItemInfo("Please check your internet connection",  iTargetUser);
				
			}

			@Override
			public void onSuccess(List<UserDTO> list) {
				
				showUpdatedUsersList(list);
			}} );
		
	}
	private void showUpdatedUsersList(List<UserDTO> result){
		view.init();
		processData(result);
	}
	

}
