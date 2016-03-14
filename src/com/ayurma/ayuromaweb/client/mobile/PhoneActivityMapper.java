package com.ayurma.ayuromaweb.client.mobile;


import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;




public class PhoneActivityMapper implements ActivityMapper{
	private ClientFactory clientFactory;
	@SuppressWarnings("unused")
	private EventBus eventBus;
    @SuppressWarnings("unused")
	private PlaceController placeController;
    @Inject
	public PhoneActivityMapper(EventBus eventBus,
            PlaceController placeController) {
		this.eventBus = eventBus;
        this.placeController = placeController;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Activity getActivity(Place place) {
		 if (place instanceof ActivityPlace) {
	            Activity activity = 
	                ((ActivityPlace) place).getActivity();
	            return activity;
	        }
		return null;
	}
	/*
	@Override
	public Activity getActivity(Place place) {
		//1 home place:
		if (place instanceof HomePlace) {
			//return new HomeActivity(clientFactory);
		}
		//2 products place:
		if (place instanceof ProductsPlace) {
			return new ProductsActivity(clientFactory);
		}
		//3 product group place:
		if (place instanceof ProductGroupPlace) {
			ProductGroupPlace myPlace = (ProductGroupPlace) place;
			return new ProductGroupActivity(clientFactory, myPlace.getToken());
		}
		// contact us place:
		if (place instanceof ContactUsPlace) {
			ContactUsPlace myPlace = (ContactUsPlace) place;
			return new ContactUsActivity(clientFactory, myPlace.getToken());
		}
		// about us place:
		if (place instanceof AboutUsPlace) {
			AboutUsPlace myPlace = (AboutUsPlace) place;
			return new AboutUsActivity(clientFactory, myPlace.getToken());
		}
		// product place:
		if (place instanceof ProductPlace) {
			ProductPlace myPlace = (ProductPlace) place;
			return new ProductActivity(clientFactory, myPlace.getToken());
		}
		//default return:
		return new HomeActivity();

		
	}//getActivity()
	*/
}
