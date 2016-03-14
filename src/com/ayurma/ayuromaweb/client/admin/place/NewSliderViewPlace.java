package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.NewSliderViewActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class NewSliderViewPlace extends ActivityPlace<NewSliderViewActivity>{
	@Inject
	public NewSliderViewPlace(NewSliderViewActivity activity) {
		super(activity);

	}
	
	@Override
	public NewSliderViewActivity getActivity() {
		activity.init(this);
		return activity;
	}

	@Prefix("new-slider")
    public static class Tokenizer 
                    implements PlaceTokenizer<NewSliderViewPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<NewSliderViewPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<NewSliderViewPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(NewSliderViewPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public NewSliderViewPlace getPlace(String token) {
        	NewSliderViewPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
}
