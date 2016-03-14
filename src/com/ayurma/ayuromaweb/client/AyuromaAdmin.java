package com.ayurma.ayuromaweb.client;

import com.ayurma.ayuromaweb.client.admin.activity.HeaderActivity;
import com.ayurma.ayuromaweb.client.admin.activity.MenuActivity;
import com.ayurma.ayuromaweb.client.admin.activity.RegistrationViewPresenter;
import com.ayurma.ayuromaweb.client.admin.gin.AdminGinjector;
import com.ayurma.ayuromaweb.client.admin.mvp.AdminAppPlaceFactory;
import com.ayurma.ayuromaweb.client.admin.mvp.AdminAppPlaceHistoryMapper;
import com.ayurma.ayuromaweb.client.admin.place.HomePlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.util.UserSettings;
import com.ayurma.ayuromaweb.client.admin.view.AdminAccessInfoView;
import com.ayurma.ayuromaweb.client.admin.view.resource.AdminMainClientBundle;
//import com.ayurma.ayuromaweb.client.json.JSAppInitData;
//import com.ayurma.ayuromaweb.client.json.JSSliderImageItem;
import com.ayurma.ayuromaweb.client.json.JSUser;

import com.ayurma.ayuromaweb.shared.UserDTO;
//import com.ayurma.ayuromaweb.client.mvp.AppPlaceHistoryMapper;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
//import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
//import com.google.gwt.user.client.DOM;
//import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class AyuromaAdmin implements EntryPoint {
	private AdminGinjector injector = GWT.create(AdminGinjector.class);
	private SimplePanel headerWidget = new SimplePanel();
	private SimplePanel appWidget = new SimplePanel();
	private SimplePanel menuWidget = new SimplePanel();
	private EventBus eventBus ;
	@Override
	public void onModuleLoad() {
		// first make sure that the user is the administrator:
		UserDTO user=getUser();
		eventBus = injector.getEventBus();
		AdminCache cache = injector.getAdminCache();
		if(user!=null){
			//1 placing the header at the top cause logout will be created in all the cases:
			HeaderActivity headerActivity = injector.getHeaderActivity();
			RootPanel.get().add(headerWidget);
			headerActivity.init(user.getName(), user.getLogoutUri());
			headerActivity.start(headerWidget, eventBus);
			//putting the user in the cache:
			cache.setUser(user);
			//now deciding the privilego da la usero:
			//if the user is the creator of the site it self:
			if(user.isAdmin()){
				//creating the user interface:
				createUi(user);
				/*
				//System.out.println("value="+isUserAdmin());
				
				PlaceController placeController = injector.getPlaceController();

				ActivityMapper activityMapper = injector.getActivityMapper();
				ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);     
				activityManager.setDisplay(appWidget);
        
				AdminAppPlaceFactory factory = injector.getAppPlaceFactory();
				HomePlace defaultPlace = factory.getHomePlace();
				AdminAppPlaceHistoryMapper historyMapper = GWT.create(AdminAppPlaceHistoryMapper.class);
				historyMapper.setFactory(factory);
		
				PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
				historyHandler.register(placeController, eventBus, defaultPlace);
				//style
				AdminMainClientBundle bundle = GWT.create(AdminMainClientBundle.class);
				bundle.style().ensureInjected();

				//1 placing a menu at the left hand side:
				RootPanel.get().add(menuWidget);
				menuWidget.setStyleName(bundle.style().leftBar());
				injector.getMenuActivity().start(menuWidget, eventBus);
				//2 the main content widget:
				RootPanel.get().add(appWidget);   
				appWidget.setStyleName(bundle.style().mainContent());
				historyHandler.handleCurrentHistory();
			*/
			}else{
				//user is not the creator sed may havas adminitratan privilegon:
				int adminLevel=user.getAdminLevel();
				if(adminLevel==UserSettings.ADMIN_LEVEL_NULL){
					// check if the user exists in the company data base
					if(user.isExists()){
						//means user exists but no privilego
						showNoAdminAccessView(user);
					}else{
						//means user does not exists
						// but he is logged in to the site by his gmail id:
						//so may be he wants administrative privilago
						//show him the form to fill and submit:
						createUserRegView(user);
					}
				}else{
					//it means user has some administrative privilage:
					//decide which level he has:
					//0 1 2 or 3 or 4
					//and present the front end page accordingly
					createUi(user);
				}
				
				//System.out.println("value="+isUserAdmin());
				//here a request is to be send for adding the user to the
				// administration group
				//RootPanel.get().add(new HTML("<h2 style='color:red;font-family:arial;'>Sorry you are not the administrator!</h2>"));
			}
		}else{
			//user is null itself
		}

	}
	private void showNoAdminAccessView(UserDTO user){
		AdminMainClientBundle bundle = GWT.create(AdminMainClientBundle.class);
		bundle.style().ensureInjected();
		AdminAccessInfoView view = new AdminAccessInfoView();
		view.setEmail(user.getEmail());
		RootPanel.get().add(appWidget);   
		appWidget.setStyleName(bundle.style().mainContent());
		appWidget.setWidget(view);
	}
	private void createUserRegView(UserDTO user){
		//style
		AdminMainClientBundle bundle = GWT.create(AdminMainClientBundle.class);
		bundle.style().ensureInjected();
		RootPanel.get().add(appWidget);   
		appWidget.setStyleName(bundle.style().mainContent());
		RegistrationViewPresenter presenter = injector.getRegistrationViewPresenter();
		presenter.init(user);
		presenter.go(appWidget);
	}
	private void createUi(UserDTO user){
		
		PlaceController placeController = injector.getPlaceController();

		ActivityMapper activityMapper = injector.getActivityMapper();
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);     
		activityManager.setDisplay(appWidget);

		AdminAppPlaceFactory factory = injector.getAppPlaceFactory();
		HomePlace defaultPlace = factory.getHomePlace();
		AdminAppPlaceHistoryMapper historyMapper = GWT.create(AdminAppPlaceHistoryMapper.class);
		historyMapper.setFactory(factory);

		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);
		//style
		AdminMainClientBundle bundle = GWT.create(AdminMainClientBundle.class);
		bundle.style().ensureInjected();

		//1 placing a menu at the left hand side:
		RootPanel.get().add(menuWidget);
		menuWidget.setStyleName(bundle.style().leftBar());
		MenuActivity menuActivity=injector.getMenuActivity();
		menuActivity.init();
		menuActivity.start(menuWidget, eventBus);
		//2 the main content widget:
		RootPanel.get().add(appWidget);   
		appWidget.setStyleName(bundle.style().mainContent());
		historyHandler.handleCurrentHistory();
		
	}
	private UserDTO getUser(){
		
		UserDTO user=null;
		//System.out.println("Here ");
		JSUser jsUser = getUserInfo();
		if(jsUser==null) return null;
		
		//String userName=jsUser.getName();
		//String userEmail=jsUser.getEmail();
		user = new UserDTO();
		user.setEmail(jsUser.getEmail());
		user.setName(jsUser.getName());
		
		//System.out.println("isLoggedin:"+isLoggedin);
		if(Boolean.valueOf(jsUser.isLoggedin())){
			//user is loggedin
			//so logout button:
			//String logoutUri=jsUser.getLogoutUri();
			user.setAdmin(Boolean.valueOf(jsUser.isAdmin()));//the creater of the site
			user.setLogoutUri(jsUser.getLogoutUri());
			user.setAdminLevel(Integer.valueOf(jsUser.getAdminLevel()));
			user.setExists(Boolean.valueOf(jsUser.exists()));
			//System.out.println("LogoutUri="+logoutUri+", isAdmin="+jsUser.isAdmin());
			//access areas:
			@SuppressWarnings("rawtypes")
			String accessAreas=jsUser.getAccessAreas();
			
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
				


			}else System.out.println("area is null");
		}else{
			//user is not logged in
			//so create the log in button:
			//System.out.println("false="+", isAdmin="+jsUser.isAdmin());
			return null;
			
		}

		return user;
	}
	private native JSUser getUserInfo()/*-{
		return $wnd.userInfo;
	}-*/;
	/*
	private class User{
		private String name;
		private String email;
		private String logoutUri;
		//the creater of the site
		boolean isAdmin=false;
		boolean isLoggedin=false;
		boolean exists=false;
		private int AdminLevel=UserConstants.ADMIN_LEVEL_NULL;
		public boolean isLoggedin() {
			return isLoggedin;
		}
		public void setLoggedin(boolean isLoggedin) {
			this.isLoggedin = isLoggedin;
		}
		public String getLogoutUri() {
			return logoutUri;
		}
		public void setLogoutUri(String logoutUri) {
			this.logoutUri = logoutUri;
		}
		public boolean isAdmin() {
			return isAdmin;
		}
		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}
		public String getName() {
			return name;
		}
		public String getEmail() {
			return email;
		}
		public User(String name, String email) {
			
			this.name = name;
			this.email = email;
		}
		public boolean isExists() {
			return exists;
		}
		public void setExists(boolean exists) {
			this.exists = exists;
		}
		public int getAdminLevel() {
			return AdminLevel;
		}
		public void setAdminLevel(int adminLevel) {
			AdminLevel = adminLevel;
		}
		
		
	}
	*/
}
