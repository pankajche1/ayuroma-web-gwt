package com.ayurma.ayuromaweb.client.mobile.factory;

import com.ayurma.ayuromaweb.client.mobile.ClientFactory;
import com.ayurma.ayuromaweb.client.mobile.view.HomeView;
import com.ayurma.ayuromaweb.client.mobile.view.IAboutUsView;
import com.ayurma.ayuromaweb.client.mobile.view.IContactUsView;
import com.ayurma.ayuromaweb.client.mobile.view.IHomeView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductGroupView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductsView;
import com.ayurma.ayuromaweb.client.mobile.view.ProductsView;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataService;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientFactoryImplMock implements ClientFactory {
	private EventBus eventBus;
	private PlaceController placeController;
	private HomeView homeView;
	private ProductsView productsView;
	private IProductGroupView productGroupView;
	private IContactUsView contactUsView;
	private IAboutUsView aboutUsView;
	private IProductView productView;
	private final DataServiceAsync rpcService;
	private final Cache cache;
	
	public ClientFactoryImplMock() {
		eventBus = new SimpleEventBus();
		rpcService = GWT.create(DataService.class);
		placeController = new PlaceController(eventBus);
		cache = new Cache();
	}

	@Override
	public EventBus getEventBus() {
		
		return eventBus;
	}

	@Override
	public DataServiceAsync getRpcService() {
		
		return rpcService;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public IHomeView getHomeView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProductsView getProductsView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProductGroupView getProductGroupView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContactUsView getContactUsView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAboutUsView getAboutUsView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProductView getProductView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cache getCache() {
		// TODO Auto-generated method stub
		return null;
	}

}
