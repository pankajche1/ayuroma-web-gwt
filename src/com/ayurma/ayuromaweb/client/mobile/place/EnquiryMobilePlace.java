package com.ayurma.ayuromaweb.client.mobile.place;

import com.ayurma.ayuromaweb.client.mobile.activity.EnquiryMobileViewActivity;
import com.ayurma.ayuromaweb.client.mobile.activity.ProductActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class EnquiryMobilePlace extends ActivityPlace<EnquiryMobileViewActivity>{
	@Inject
	public EnquiryMobilePlace(EnquiryMobileViewActivity activity) {
		super(activity);
		
	}
	@Override
	public EnquiryMobileViewActivity getActivity(){
		activity.init(this);
		//logger.log(Level.INFO, "ProductsPlace getActivity...");
		return activity;
	}	
	@Prefix("enquiry")
    public static class Tokenizer 
                    implements PlaceTokenizer<EnquiryMobilePlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
		//private Logger logger = Logger.getLogger("");
        //1
        private final Provider<EnquiryMobilePlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<EnquiryMobilePlace> placeProvider){
        	//logger.log(Level.INFO, "ProductsPlace Tokenizer()");
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(EnquiryMobilePlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public EnquiryMobilePlace getPlace(String token) {
        	EnquiryMobilePlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
	


}
