package com.ayurma.ayuromaweb.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

public class ActivityPlace <T extends Activity> extends Place {
	 protected T activity;

	    public ActivityPlace(T activity) {
	        this.activity = activity;
	        setPlaceName("");
	    }

	    public T getActivity() {
	        return activity;
	    }

	    private String placeName;

	    public void setPlaceName(String token) {
	        this.placeName = token;
	    }

	    public String getPlaceName() {
	        return placeName;
	    }
}
