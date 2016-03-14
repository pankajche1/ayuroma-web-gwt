package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.SliderViewActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SliderViewPlace extends ActivityPlace<SliderViewActivity>{
	@Inject
	public SliderViewPlace(SliderViewActivity activity) {
		super(activity);

	}
	@Override 
	public SliderViewActivity getActivity(){
		activity.init(this);
		return activity;
	}
	
	@Prefix("slider-view")
    public static class Tokenizer 
                    implements PlaceTokenizer<SliderViewPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<SliderViewPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<SliderViewPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(SliderViewPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public SliderViewPlace getPlace(String token) {
        	SliderViewPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }	

}
