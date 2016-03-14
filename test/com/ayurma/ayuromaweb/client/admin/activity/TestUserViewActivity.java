package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.client.admin.juice.AdminJuiceModule;
import com.ayurma.ayuromaweb.client.admin.place.UsersPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.util.UserSettings;
import com.ayurma.ayuromaweb.client.admin.view.UsersViewMock;
import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
//import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class TestUserViewActivity {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private UsersViewActivity activity;
	private UsersViewMock mockView;
	private RegistrationViewPresenter regPresenter;
	private AdminCache cache;
	private DatabaseManager db;
	private Provider<UsersPlace> usersPlaceProvider;
	private Injector injector;
	String adminEmail="ayuroma-kanpur@gmail.com";
	String userEmail1="hemant1@gmail.com";
	//user data:
	String email="test@gmail.com";
	String userName="Pankaj";
	boolean isAdmin=true;
	String logoutUri="what is the logout uri?";
	int adminLevel=0;
	boolean exists=true;
	String accessAreas="1,1,1,1,0,0,0,1,";
	@Before
	public void setUp(){
		helper.setUp();
		injector = Guice.createInjector(new AdminJuiceModule());
		cache=injector.getInstance(AdminCache.class);
		db = new DatabaseManager();
		String userName="Ayuroma-Kanpur";
		String userEmail=adminEmail;
		boolean isAdmin=true;
		int adminLevel=UserSettings.ADMIN_LEVEL_ROOT;
		
		//save the admin [The admin is not saved in the database]:
		//System.out.println(db.saveUser(userEmail, userName,
				//isAdmin, adminLevel,false));
		//saving some users:
		// saving first user:
		userName="Hemant 1";
		userEmail=userEmail1;
		isAdmin=false;
		adminLevel=UserSettings.ADMIN_LEVEL_ONE;
		//adminLevel=UserSettings.ADMIN_LEVEL_SECOND;
		System.out.println(db.saveUser(userEmail, userName,isAdmin,adminLevel,DatabaseManager.ACCESS_TYPE3));
		// saving the second user:
		userName="User 1";
		userEmail="user1@gmail.com";
		isAdmin=false;
		adminLevel=UserSettings.ADMIN_LEVEL_NULL;
		System.out.println(db.saveUser(userEmail, userName,isAdmin,adminLevel,DatabaseManager.ACCESS_TYPE2));
		//saving the third user:
		userName="User 2";
		userEmail="user2@gmail.com";
		isAdmin=false;
		adminLevel=UserSettings.ADMIN_LEVEL_NULL;
		System.out.println(db.saveUser(userEmail, userName,isAdmin,adminLevel,DatabaseManager.ACCESS_TYPE3));
		//regPresenter=injector.getInstance(RegistrationViewPresenter.class);
		mockView=injector.getInstance(UsersViewMock.class);
		//regPresenter.registerUser("");
		//cache.setUser(getUser());
		//activity = injector.getInstance(UsersViewActivity.class);
		
		//putting the user in the cache:
		
		//initiate the activity:
		//activity.init(place);
	}
	@After
	public void tearDown(){
		helper.tearDown();
	}
	@Test
	public void test(){
		//1 the admin logs in:
		//simulateAdminLogIn();
		//2 user 2 logs in:
		simulateUser1LogIn();
		//testUsersViewActivity1();
	}
	private void simulateAdminLogIn(){
		//1 the admin logs in with his email to admin.jsp
			//so the user will be cached in the cache:
		cache.setUser(db.getAdmin());
		//2 now the user goes to 'users' menu area:
		usersPlaceProvider= injector.getProvider(UsersPlace.class);
		UsersPlace place = usersPlaceProvider.get();
		activity=place.getActivity();
		//now check the user of the acivity:
		UserDTO user=activity.getUser();
		System.out.println("-------- getting the user from the activity --------");
		System.out.println(user.toString());
		System.out.println("---------------------------------------------------");
		System.out.println("-------- getting the USERS from the activity --------");
		List<UserDTO> users = activity.getUsers();
		for(UserDTO item:users){
			System.out.println(item.toString());
		}

		// now setting the user 1 access areas:
		//user index, area index, access
		mockView.allowUserToAccessArea(1, 0, false);
		mockView.allowUserToAccessArea(1, 1, false);
		mockView.allowUserToAccessArea(1, 2, false);
		//selecting the admin level from the list box:
		int indexAdminLevel=2;
		mockView.selectAdminLevel(1, indexAdminLevel);
		//now clicking the save user button on the view:
		mockView.updateUser(1);
	}
	private void simulateUser1LogIn(){
		//1 logged in user is in the cache:
		System.out.println("----hemant1 logs in ------");
		cache.setUser(db.getUser(userEmail1));
		//2 now this user goes to the Users menu:
		usersPlaceProvider= injector.getProvider(UsersPlace.class);
		UsersPlace place = usersPlaceProvider.get();
		activity=place.getActivity();
		//now check the current user of the activity:
		//UserDTO user=activity.getUser();
		//System.out.println("-------- getting the user from the activity --------");
		//System.out.println(user.toString());
		//System.out.println("---------------------------------------------------");
		//now updating the user:
		//user index, area index, access
		mockView.allowUserToAccessArea(1, 0, false);
		mockView.allowUserToAccessArea(1, 1, false);
		mockView.allowUserToAccessArea(1, 2, false);
		//selecting the admin level from the list box:
		int indexAdminLevel=1;
		mockView.selectAdminLevel(1, indexAdminLevel);
		//now clicking the save user button on the view:
		mockView.updateUser(1);
		
	}
	private void testUsersViewActivity1(){
		//getting the user:
		List<UserDTO> users = activity.getUsers();
		System.out.println("These are the users got from the server by the activity:");
		System.out.println("----------------------");
		printUsers(users);
		System.out.println("----------------------\n");
		/*
		//get the menu template interger array:
		int[] accessData = UserSettings.getMenuArray();
		//change some data of the above:
		
		for(int i=0;i<accessData.length;i++){
			if(i%2==0) accessData[i]=0;
			else accessData[i]=1;
		}

		int iUser=0;//creating ui view for the first user:
		activity.createUiData(accessData, iUser);
		*/
		//now get what the activity has has created:
		List<UsersViewActivity.User> userInfos = activity.getUsersInfo();
		System.out.println("These are the users Infos created by the activity:");
		System.out.println("----------------------");
		for(UsersViewActivity.User item:userInfos ){
			System.out.println(item.toString());
		}
		
		System.out.println("----------------------\n");
		//now updating the above user with some specific data[simulating the check box ticking]:
		mockView.allowUserToAccessArea(0, 0, true);
		//check the view after setting the above:
		System.out.println("This is the user state in the Mock View:");
		System.out.println("----------------------");		
		mockView.printUsers();
		System.out.println("----------------------\n");
		//now updating the above user with some specific data:
		mockView.allowUserToAccessArea(0, 2, true);
		//check the view after setting the above:
		System.out.println("This is the user state in the Mock View:");
		System.out.println("----------------------");	
		mockView.printUsers();
		System.out.println("----------------------\n");
		//now updating the above user with some specific data:
		mockView.allowUserToAccessArea(0, 2, false);
		//check the view after setting the above:
		System.out.println("This is the user state in the Mock View:");
		System.out.println("----------------------");	
		mockView.printUsers();
		System.out.println("----------------------\n");
		//now updating the user:
		mockView.updateUser(0);
		//now againg changing the check box status:
		mockView.allowUserToAccessArea(0, 3, true);
		System.out.println("This is the user state in the Mock View:");
		System.out.println("----------------------");	
		mockView.printUsers();
		System.out.println("----------------------\n");
		//now updating the user:
		mockView.updateUser(0);
		
	}
	private void printUsers(List<UserDTO> users ){
		if(users.isEmpty()){
			System.out.println("Users list is empty.");
		}
		for(UserDTO user:users){
			System.out.println(user.toString());
		}
		
	}
	private UserDTO getUser(){
		
		UserDTO user = new UserDTO();


		user.setEmail(email);
		user.setName(userName);
		user.setAdmin(isAdmin);//the creater of the site
		user.setLogoutUri(logoutUri);
		user.setAdminLevel(adminLevel);
		user.setExists(exists);
		
		if(accessAreas!=null){
			String[] areas=accessAreas.split("[,]");
			int[] intVals=new int[areas.length];
			int i=0;
			for(String val:areas){
				
				try{
					intVals[i]=Integer.valueOf(val);
					
				}catch(NumberFormatException e){
					
					intVals[i]=0;
				}
				i++;
			}
			user.setAccessSections(intVals);
			


		}
		

		return user;
	}
}
