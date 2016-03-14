package com.ayurma.ayuromaweb.client.admin.mvp;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class AdminAppActivityMapper implements ActivityMapper {
    private EventBus eventBus;
    private PlaceController placeController;
    @Inject
    public AdminAppActivityMapper(EventBus eventBus,
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

}
