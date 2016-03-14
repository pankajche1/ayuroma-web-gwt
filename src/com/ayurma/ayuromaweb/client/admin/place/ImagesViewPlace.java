package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.ImagesViewActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ImagesViewPlace extends ActivityPlace<ImagesViewActivity>{
	@Inject
	public ImagesViewPlace(ImagesViewActivity activity) {
		super(activity);
		
	}
	@Override
	public ImagesViewActivity getActivity(){
		activity.init(this);
		return activity;
	}
	@Prefix("images")
    public static class Tokenizer 
                    implements PlaceTokenizer<ImagesViewPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<ImagesViewPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ImagesViewPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(ImagesViewPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public ImagesViewPlace getPlace(String token) {
        	ImagesViewPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
}
