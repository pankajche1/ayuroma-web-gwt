package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.SliderBrowserActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SliderBrowserPlace extends ActivityPlace<SliderBrowserActivity>{
	@Inject
	public SliderBrowserPlace(SliderBrowserActivity activity) {
		super(activity);
		
	}
	
	@Override
	public SliderBrowserActivity getActivity() {
		activity.init(this);
		return activity;
	}

	@Prefix("slider-browser")
    public static class Tokenizer 
                    implements PlaceTokenizer<SliderBrowserPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<SliderBrowserPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<SliderBrowserPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(SliderBrowserPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public SliderBrowserPlace getPlace(String token) {
        	SliderBrowserPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }	
}
