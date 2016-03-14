package com.ayurma.ayuromaweb.client.place;

import com.ayurma.ayuromaweb.client.activity.EnquiryViewActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class EnquiryPlace extends ActivityPlace<EnquiryViewActivity>{
	@Inject
	public EnquiryPlace(EnquiryViewActivity activity) {
		super(activity);
		
	}
	@Override
	public EnquiryViewActivity getActivity(){
		activity.init(this);
		return activity;
	}
	@Prefix("enquiry")
    public static class Tokenizer 
                    implements PlaceTokenizer<EnquiryPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<EnquiryPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<EnquiryPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(EnquiryPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public EnquiryPlace getPlace(String token) {
        	EnquiryPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

}
