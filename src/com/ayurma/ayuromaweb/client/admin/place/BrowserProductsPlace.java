package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.BrowseProductsActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BrowserProductsPlace extends ActivityPlace<BrowseProductsActivity>{
	@Inject
	public BrowserProductsPlace(BrowseProductsActivity activity) {
		super(activity);
		
	}
	@Override
	public BrowseProductsActivity getActivity(){
		activity.init(this);
		return activity;
	}
	@Prefix("products-browser")
    public static class Tokenizer 
                    implements PlaceTokenizer<BrowserProductsPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<BrowserProductsPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<BrowserProductsPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(BrowserProductsPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public BrowserProductsPlace getPlace(String token) {
        	BrowserProductsPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
	
}
