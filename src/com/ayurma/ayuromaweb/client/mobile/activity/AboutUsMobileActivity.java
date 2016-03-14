package com.ayurma.ayuromaweb.client.mobile.activity;

import java.util.logging.Logger;



import com.ayurma.ayuromaweb.client.mobile.place.AboutUsMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;

import com.ayurma.ayuromaweb.client.mobile.view.IAboutUsMobileView;

import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class AboutUsMobileActivity extends MGWTAbstractActivity{
	//private final ClientFactory clientFactory;
	private Logger logger = Logger.getLogger("");
	private String token = "";
	private final DataServiceAsync rpcService;
	
	private final Cache cache;
	
	private IAboutUsMobileView view;
	private Provider<HomePlace> homePlaceProvider;
	private PlaceController placeController;
	private AboutUsMobilePlace place;
	
	@Inject
	public AboutUsMobileActivity(IAboutUsMobileView view, DataServiceAsync rpcService,
			Cache cache,
			PlaceController placeController,
			Provider<HomePlace> homePlaceProvider) {
		this.view = view;
		this.cache = cache;
		this.rpcService = rpcService;
		this.homePlaceProvider = homePlaceProvider;
		this.placeController = placeController;
		
	}
	public void init(AboutUsMobilePlace place){

		this.place = place;
		
	}
	/*
	public AboutUsMobileActivity(ClientFactory clientFactory,String token) {
		
		this.clientFactory = clientFactory;
		this.rpcService = this.clientFactory.getRpcService();
		this.token = token;
		this.cache = this.clientFactory.getCache();
		
	}
	*/
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//view = clientFactory.getAboutUsView();
		//getting the user interface elements and adding the handlers:
	    addHandlerRegistration(view.getHeaderRightButton().addTapHandler(new TapHandler() {

	        @Override
	        public void onTap(TapEvent event) {
	        	HomePlace place= homePlaceProvider.get();
        		place.setPlaceName("home");
        		placeController.goTo(place);


	        }
	      }));


		panel.setWidget(view);
			  
	}
	
}
