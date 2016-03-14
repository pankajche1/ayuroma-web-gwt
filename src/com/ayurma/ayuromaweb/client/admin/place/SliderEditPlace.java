package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.SliderEditViewActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SliderEditPlace extends ActivityPlace<SliderEditViewActivity>{
	@Inject
	public SliderEditPlace(SliderEditViewActivity activity) {
		super(activity);
		
	}
	
	@Override
	public SliderEditViewActivity getActivity() {
		activity.init(this);
		return activity;
	}

	@Prefix("slider-edit")
    public static class Tokenizer 
                    implements PlaceTokenizer<SliderEditPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<SliderEditPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<SliderEditPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(SliderEditPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public SliderEditPlace getPlace(String token) {
        	SliderEditPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }	
}
