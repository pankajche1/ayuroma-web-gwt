package com.ayurma.ayuromaweb.client.mobile.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.mobile.model.HomeMenuItem;
import com.ayurma.ayuromaweb.client.mobile.place.AboutUsMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.EnquiryMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductsGroupsMenuPlace;
import com.ayurma.ayuromaweb.client.mobile.view.IHomeView;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;



public class HomeActivity extends MGWTAbstractActivity{

	private Logger logger = Logger.getLogger("");
	//private  ClientFactory clientFactory;
	private HomePlace place;
	private IHomeView view;
	private DataServiceAsync rpcService;
	private PlaceController placeController;
	private Provider<ProductsGroupsMenuPlace> productsPlaceProvider;
	private Provider<AboutUsMobilePlace> aboutUsMobilePlaceProvider;
	private Provider<EnquiryMobilePlace> enquiryMobilePlaceProvider;
	/*
	@Inject
	public HomeActivity(ClientFactory clientFactory) {
		
		this.clientFactory = clientFactory;
	}
	*/
	@Inject
	public HomeActivity(IHomeView view, DataServiceAsync rpcService,
			PlaceController placeController,
			Provider<ProductsGroupsMenuPlace> productsPlaceProvider,
			Provider<AboutUsMobilePlace> aboutUsMobilePlaceProvider,
			Provider<EnquiryMobilePlace> enquiryMobilePlaceProvider) {
		this.view = view;
		this.rpcService=rpcService;
		this.placeController=placeController;
		this.productsPlaceProvider = productsPlaceProvider;
		this.aboutUsMobilePlaceProvider = aboutUsMobilePlaceProvider;
		this.enquiryMobilePlaceProvider = enquiryMobilePlaceProvider;
		
	}
	public void init(HomePlace place){
		this.place=place;
	}
	  @Override
	  public void start(AcceptsOneWidget panel, EventBus eventBus) {
		  //IHomeView view = clientFactory.getHomeView();

		    view.setTitle("Ayuroma Centre");
		    view.setMenuItems(createMenuItemsList());
		   
		    addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(
		            new CellSelectedHandler() {

		              @Override
		              public void onCellSelected(CellSelectedEvent event) {
		                int index = event.getIndex();
		                if (index == 0) {
		                  //clientFactory.getPlaceController().goTo(new AboutUsPlace("about-us"));
		                	AboutUsMobilePlace place= aboutUsMobilePlaceProvider.get();
		            		
		            		//place.setPlaceName("my-place");
		            		placeController.goTo(place);
		                  return;
		                }
		                //products menu
		                if (index == 1) {
		                	 //logger.log(Level.INFO, "before creating products place...");
		                	ProductsGroupsMenuPlace place= productsPlaceProvider.get();
		            		
		            		//place.setPlaceName("my-place");
		            		placeController.goTo(place);
		            		//logger.log(Level.INFO, "after creating products place...");
		                  //clientFactory.getPlaceController().goTo(new ProductsPlace("my-place"));

		                  return;
		                }
		                //enquiry menu
		                if (index == 2) {
		                	EnquiryMobilePlace place = enquiryMobilePlaceProvider.get();
		                	place.setPlaceName("source=contact-us");
		                	placeController.goTo(place);
		                  //clientFactory.getPlaceController().goTo(new ContactUsPlace("contact-us"));

		                  return;
		                }

		              }
		            }));

		    panel.setWidget(view);	    
	  }
	  
	  private List<HomeMenuItem> createMenuItemsList() {
		    ArrayList<HomeMenuItem> list = new ArrayList<HomeMenuItem>();
		    list.add(new HomeMenuItem("About Us", 5));
		    list.add(new HomeMenuItem("Products", 5));
		    list.add(new HomeMenuItem("Contact Us", 5));
		    list.add(new HomeMenuItem("Location", 5));

		    return list;
		  }
		

}
