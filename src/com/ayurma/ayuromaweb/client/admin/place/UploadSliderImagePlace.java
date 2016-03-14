package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.UploadSliderImageViewActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class UploadSliderImagePlace extends ActivityPlace<UploadSliderImageViewActivity>{
	@Inject
	public UploadSliderImagePlace(UploadSliderImageViewActivity activity) {
		super(activity);
		
	}
	
	@Override
	public UploadSliderImageViewActivity getActivity() {
		activity.init(this);
		return activity;
	}

	@Prefix("upload-slider-images")
    public static class Tokenizer 
                    implements PlaceTokenizer<UploadSliderImagePlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<UploadSliderImagePlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<UploadSliderImagePlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(UploadSliderImagePlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public UploadSliderImagePlace getPlace(String token) {
        	UploadSliderImagePlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }	

}
