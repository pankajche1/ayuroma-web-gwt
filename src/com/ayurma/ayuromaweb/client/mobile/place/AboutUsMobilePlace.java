package com.ayurma.ayuromaweb.client.mobile.place;

import com.ayurma.ayuromaweb.client.mobile.activity.AboutUsMobileActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AboutUsMobilePlace extends ActivityPlace<AboutUsMobileActivity>{
	private String token;
	@Inject
	public AboutUsMobilePlace(AboutUsMobileActivity activity) {
	
		super(activity);

	}
	@Override
	public AboutUsMobileActivity getActivity(){
		activity.init(this);

		return activity;
	}	
	@Prefix("about-us")
    public static class Tokenizer 
                    implements PlaceTokenizer<AboutUsMobilePlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
		//private Logger logger = Logger.getLogger("");
        //1
        private final Provider<AboutUsMobilePlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<AboutUsMobilePlace> placeProvider){
        	//logger.log(Level.INFO, "ProductsPlace Tokenizer()");
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(AboutUsMobilePlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public AboutUsMobilePlace getPlace(String token) {
        	AboutUsMobilePlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }	


}
