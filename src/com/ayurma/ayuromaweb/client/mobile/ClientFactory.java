package com.ayurma.ayuromaweb.client.mobile;

//import com.ayurma.ayuromaweb.client.mobile.view.IAboutUsView;

import com.ayurma.ayuromaweb.client.mobile.view.IHomeView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductGroupMobileView;

import com.ayurma.ayuromaweb.client.mobile.view.IProductsView;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;


public interface ClientFactory {
	//1
	public EventBus getEventBus();
	public DataServiceAsync getRpcService();
	//2
	public PlaceController getPlaceController();
	// 3 home view:
	public IHomeView getHomeView();
	//4 products view:
	public IProductsView getProductsView();
	public IProductGroupMobileView getProductGroupView();
	//5 contact us view:
	//public IContactUsView getContactUsView();
	//6 about us view:
	//public IAboutUsView getAboutUsView();
	//7 product info view:
	//public IProductView getProductView();
	public Cache getCache();
}
