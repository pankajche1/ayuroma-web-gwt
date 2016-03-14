package com.ayurma.ayuromaweb.client;

//import com.ayurma.ayuromaweb.client.gin.AyuromaGinjector;
import com.ayurma.ayuromaweb.client.mobile.AppHistoryObserver;
import com.ayurma.ayuromaweb.client.mobile.AppPlaceHistoryMapper;
import com.ayurma.ayuromaweb.client.mobile.ClientFactory;
import com.ayurma.ayuromaweb.client.mobile.ClientFactoryImpl;
import com.ayurma.ayuromaweb.client.mobile.PhoneActivityMapper;
import com.ayurma.ayuromaweb.client.mobile.PhoneAnimationMapper;
import com.ayurma.ayuromaweb.client.mobile.gin.MobileGinjector;
import com.ayurma.ayuromaweb.client.mobile.mvp.MobileAppPlaceFactory;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.view.resource.MainClientBundleMobile;



//import com.ayurma.ayuromaweb.client.service.DataService;
//import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort;
import com.googlecode.mgwt.ui.client.util.SuperDevModeUtil;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.googlecode.mgwt.ui.client.widget.menu.overlay.OverlayMenu;



public class AyuromaMobile implements EntryPoint {
	private MobileGinjector injector = GWT.create(MobileGinjector.class);
	 
	@Override
	public void onModuleLoad() {
		
		startNew();
	}
	private void startNew(){
	    SuperDevModeUtil.showDevMode();

	    ViewPort viewPort = new MGWTSettings.ViewPort();
	    viewPort.setUserScaleAble(false).setMinimumScale(1.0).setMinimumScale(1.0).setMaximumScale(1.0);

	    MGWTSettings settings = new MGWTSettings();
	    settings.setViewPort(viewPort);
	    settings.setIconUrl("logo.png");
	    settings.setFullscreen(true);
	    settings.setFixIOS71BodyBug(true);
	    settings.setPreventScrolling(true);

	    MGWT.applySettings(settings);
	    //pankaj style global:
	    MainClientBundleMobile.INSTANCE.style().ensureInjected();
	    MainClientBundleMobile.INSTANCE.productStyle().ensureInjected();
	    Element body =RootPanel.getBodyElement();
		body.addClassName(MainClientBundleMobile.INSTANCE.style().global());
		EventBus eventBus = injector.getEventBus();
        PlaceController placeController = injector.getPlaceController();
      //3 history mapper:
	    // Start PlaceHistoryHandler with our PlaceHistoryMapper
	    AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
	    //4 find if desktop of android:
	    if (MGWT.getFormFactor().isTablet() || MGWT.getFormFactor().isDesktop()) {
	      //createTabletDisplay(clientFactory);
	    } else {
	      //createPhoneDisplay(clientFactory);
	    }
	    
	    createPhoneDisplayNew(eventBus);
        //now creating the place:
        MobileAppPlaceFactory factory = injector.getAppPlaceFactory();
        historyMapper.setFactory(factory);
        //the default page of the site:
		HomePlace defaultPlace = factory.getHomePlace();
	    //5 all the eventbus event fired go to this. this is the appcontroller thing:
	    AppHistoryObserver historyObserver = new AppHistoryObserver();
	    MGWTPlaceHistoryHandler historyHandler = new MGWTPlaceHistoryHandler(historyMapper, historyObserver);
	    //6 now the default view when the user loads the index page:
	    historyHandler.register(placeController, eventBus, defaultPlace);
	    //7 start the history:
	    historyHandler.handleCurrentHistory();
		
	}//startNew()
	/*
	private void start() {
		 //1 do the initial settings:
		    SuperDevModeUtil.showDevMode();

		    ViewPort viewPort = new MGWTSettings.ViewPort();
		    viewPort.setUserScaleAble(false).setMinimumScale(1.0).setMinimumScale(1.0).setMaximumScale(1.0);

		    MGWTSettings settings = new MGWTSettings();
		    settings.setViewPort(viewPort);
		    settings.setIconUrl("logo.png");
		    settings.setFullscreen(true);
		    settings.setFixIOS71BodyBug(true);
		    settings.setPreventScrolling(true);

		    MGWT.applySettings(settings);
		    //pankaj style global:
		    MainClientBundleMobile.INSTANCE.style().ensureInjected();
		    Element body =RootPanel.getBodyElement();
			body.addClassName(MainClientBundleMobile.INSTANCE.style().global());
			
		    //2 now the client factory that will give you the resources on demand:
		    final ClientFactory clientFactory = new ClientFactoryImpl();
		    //3 history mapper:
		    // Start PlaceHistoryHandler with our PlaceHistoryMapper
		    AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		    //4 find if desktop of android:
		    if (MGWT.getFormFactor().isTablet() || MGWT.getFormFactor().isDesktop()) {
		      //createTabletDisplay(clientFactory);
		    } else {
		      //createPhoneDisplay(clientFactory);
		    }
		    
		    createPhoneDisplay(clientFactory);
		    //5 all the eventbus event fired go to this. this is the appcontroller thing:
		    AppHistoryObserver historyObserver = new AppHistoryObserver();
		    MGWTPlaceHistoryHandler historyHandler = new MGWTPlaceHistoryHandler(historyMapper, historyObserver);
		    //6 now the default view when the user loads the index page:
		    historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new HomePlace());
		    //7 start the history:
		    historyHandler.handleCurrentHistory();
		  }//start()
	*/
	/*
	  private void createPhoneDisplay(ClientFactory clientFactory) {
		  //1 basic root panel which will have all the view:
		    AnimationWidget display = new AnimationWidget();
		    //2 activities are mapped here: first you goto a place then the place takes its activity
		    //+ from this mapper and with the activity the corresponding view also comes:
		    PhoneActivityMapper appActivityMapper = new PhoneActivityMapper(clientFactory);
		    PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();
		    AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper, clientFactory.getEventBus());
		    activityManager.setDisplay(display);
		    RootPanel.get().add(display);
		  }//createPhoneDisplay()
	*/
	  private void createPhoneDisplayNew(EventBus eventBus) {
		  //1 basic root panel which will have all the view:
		    AnimationWidget display = new AnimationWidget();
		    //2 activities are mapped here: first you goto a place then the place takes its activity
		    //+ from this mapper and with the activity the corresponding view also comes:
		    ActivityMapper appActivityMapper = injector.getActivityMapper();
		    
		    PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();
		    AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper, eventBus);
		    activityManager.setDisplay(display);
		    RootPanel.get().add(display);
		  }//createPhoneDisplay()
	  /*
	  private void createTabletDisplay(ClientFactory clientFactory) {
		    OverlayMenu overlayMenu = new OverlayMenu();
		    AnimationWidget navDisplay = new AnimationWidget();
		    ActivityMapper navActivityMapper = new TabletNavActivityMapper(clientFactory);
		    AnimationMapper navAnimationMapper = new TabletNavAnimationMapper();
		    AnimatingActivityManager navActivityManager = new AnimatingActivityManager(navActivityMapper, navAnimationMapper, clientFactory.getEventBus());
		    navActivityManager.setDisplay(navDisplay);
		    overlayMenu.setMaster(navDisplay);
		    AnimationWidget mainDisplay = new AnimationWidget();
		    TabletMainActivityMapper tabletMainActivityMapper = new TabletMainActivityMapper(clientFactory);
		    AnimationMapper tabletMainAnimationMapper = new TabletMainAnimationMapper();
		    AnimatingActivityManager mainActivityManager = new AnimatingActivityManager(tabletMainActivityMapper, tabletMainAnimationMapper, clientFactory.getEventBus());
		    mainActivityManager.setDisplay(mainDisplay);
		    overlayMenu.setDetail(mainDisplay);
		    RootPanel.get().add(overlayMenu);
		  }
		  */

}
