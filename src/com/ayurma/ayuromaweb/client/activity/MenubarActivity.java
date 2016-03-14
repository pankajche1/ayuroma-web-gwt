package com.ayurma.ayuromaweb.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.place.EnquiryPlace;
import com.ayurma.ayuromaweb.client.place.HomePlace;
import com.ayurma.ayuromaweb.client.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.place.ProductPlace;
import com.gmail.pankajche1.client.MenuBar3;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;




public class MenubarActivity extends AbstractActivity{
	private MenuBar3 menubar = new MenuBar3(false);
	 private PlaceController placeController;
	 private Provider<ProductPlace> productPlaceProvider;
	 private Provider<ProductGroupPlace> productGroupPlaceProvider;
	 private Provider<HomePlace> homePlaceProvider;
	 Provider<EnquiryPlace> enquiryPlaceProvider;
	 private List<ProductGroupPlace> places;
	 private ProductGroupPlace place;
	 private Command command;
	 private AppInitData appInitData;
	 @Inject
	public MenubarActivity(PlaceController placeController,
			Provider<HomePlace> homePlaceProvider,
			Provider<ProductPlace> productPlaceProvider,
			Provider<ProductGroupPlace> productGroupPlaceProvider,
			Provider<EnquiryPlace> enquiryPlaceProvider) {
		this.placeController=placeController;
		this.productPlaceProvider=productPlaceProvider;
		this.homePlaceProvider=homePlaceProvider;
		this.productGroupPlaceProvider=productGroupPlaceProvider;
		this.enquiryPlaceProvider=enquiryPlaceProvider;
	}
	 
	public void setAppInitData(AppInitData appInitData) {
		this.appInitData = appInitData;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		menubar.setZIndex(100);
		
		HomePlace homePlace = homePlaceProvider.get();
		homePlace.setPlaceName("My Home Page");
		command = new MyCommand(homePlace);
		menubar.addMenuItem("Home", command);
		//product section:
		menubar.addMenuItem("Products", null);
		int size = appInitData.getProductGroupSize();
		//final String[] tokens = {"All Products","Natural Essential Oils","Aromatic Chemicals","Carrier Oils"};
		//final String[] keys = {"1","3","2003","2004"};
		places = new ArrayList<ProductGroupPlace>();
		MyCommand cmd;
		for(int i=0;i<size;i++){
			
			place = productGroupPlaceProvider.get();
			place.setPlaceName(appInitData.getProductGroupKey(i));
			place.setProductGroupName(appInitData.getProductGroupName(i));
			place.setStrKey(appInitData.getProductGroupKey(i));
			places.add(place);
			cmd = new MyCommand(place);
			menubar.addSubMenuItem(1,appInitData.getProductGroupName(i),cmd);
		}
		//3 contact us section:
		EnquiryPlace enquiryPlace = enquiryPlaceProvider.get();
		enquiryPlace.setPlaceName("source=contact-us");
		command = new MyCommand(enquiryPlace);
		menubar.addMenuItem("Contact Us", command);
		panel.setWidget(menubar);
		
	}
	private class MyCommand implements Command{
		private Place place;
		//private ActivityPlace<T> activityPlace;
		public MyCommand(Place place){
			this.place=place;
		}
		@Override
		public void execute() {
			//System.out.println("MenubarActivity command execute()...");
			placeController.goTo(place);
			
		}
		
	}

}
