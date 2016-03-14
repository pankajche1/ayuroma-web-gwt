package com.ayurma.ayuromaweb.client.mobile;

//import com.ayurma.ayuromaweb.client.mobile.view.AboutUsView;

import com.ayurma.ayuromaweb.client.mobile.view.HomeView;
//import com.ayurma.ayuromaweb.client.mobile.view.IAboutUsView;

import com.ayurma.ayuromaweb.client.mobile.view.IHomeView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductGroupMobileView;

import com.ayurma.ayuromaweb.client.mobile.view.IProductsView;
import com.ayurma.ayuromaweb.client.mobile.view.ProductGroupMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.ProductsView;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataService;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;


public class ClientFactoryImpl implements ClientFactory{
	private EventBus eventBus;
	private PlaceController placeController;
	private HomeView homeView;
	private ProductsView productsView;
	private IProductGroupMobileView productGroupView;
	//private IContactUsView contactUsView;
	//private IAboutUsView aboutUsView;
	//private IProductView productView;
	private final DataServiceAsync rpcService;
	private final Cache cache;
	public ClientFactoryImpl(){
		eventBus = new SimpleEventBus();
		rpcService = GWT.create(DataService.class);

		placeController = new PlaceController(eventBus);

		homeView = new HomeView();
		cache = new Cache();
		
	}

	@Override
	public Cache getCache() {
		return cache;
	}


	@Override
	public EventBus getEventBus() {
		
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		
		return placeController;
	}

	@Override
	public IHomeView getHomeView() {
		if (homeView == null) {
			homeView = new HomeView();
		}
		return homeView;
	}


	@Override
	public IProductsView getProductsView() {
		if (productsView == null) {
			productsView = new ProductsView();
		}
		return productsView;
	}


	@Override
	public IProductGroupMobileView getProductGroupView() {
		if (productGroupView  == null) {
			productGroupView = new ProductGroupMobileView();
		}
		return productGroupView ;
	}


	@Override
	public DataServiceAsync getRpcService() {
		
		return rpcService;
	}
	/*
	@Override
	public IContactUsView getContactUsView() {
		if (contactUsView  == null) {
			contactUsView = new ContactUsView();
		}
		return contactUsView ;
	}

	@Override
	public IAboutUsView getAboutUsView() {
		if (aboutUsView  == null) {
			aboutUsView = new AboutUsView();
		}
		return aboutUsView ;
	}
	
	*/




}
