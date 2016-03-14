package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.SliderImagesBrowseActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SliderImagesPlace  extends ActivityPlace<SliderImagesBrowseActivity>{
	@Inject
	public SliderImagesPlace(SliderImagesBrowseActivity activity) {
		super(activity);
		
	}
	
	@Override
	public SliderImagesBrowseActivity getActivity() {
		this.activity.init(this);
		return this.activity;
	}

	@Prefix("slider-images")
    public static class Tokenizer 
                    implements PlaceTokenizer<SliderImagesPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<SliderImagesPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<SliderImagesPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(SliderImagesPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public SliderImagesPlace getPlace(String token) {
        	SliderImagesPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
}
