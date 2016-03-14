package com.ayurma.ayuromaweb.client.mobile.place;


import java.util.logging.Level;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.mobile.activity.ProductsGroupsMenuActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class ProductsGroupsMenuPlace extends ActivityPlace<ProductsGroupsMenuActivity>{
	//private Logger logger = Logger.getLogger("");
	//private String token;
	
	@Inject
	public ProductsGroupsMenuPlace(ProductsGroupsMenuActivity activity) {
		
		super(activity);
		//logger.log(Level.INFO, "ProductsPlace construct...");
	}
	@Override
	public ProductsGroupsMenuActivity getActivity(){
		activity.init(this);
		//logger.log(Level.INFO, "ProductsPlace getActivity...");
		return activity;
	}
/*
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	*/
	@Prefix("products-groups")
    public static class Tokenizer 
                    implements PlaceTokenizer<ProductsGroupsMenuPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
		//private Logger logger = Logger.getLogger("");
        //1
        private final Provider<ProductsGroupsMenuPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ProductsGroupsMenuPlace> placeProvider){
        	//logger.log(Level.INFO, "ProductsPlace Tokenizer()");
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(ProductsGroupsMenuPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public ProductsGroupsMenuPlace getPlace(String token) {
        	ProductsGroupsMenuPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

/*
	@Prefix("products-groups")
	public static class ProductsPlaceTokenizer implements PlaceTokenizer<ProductsPlace> {

		@Override
		public ProductsPlace getPlace(String token) {
			return new ProductsPlace(token);
		}

		@Override
		public String getToken(ProductsPlace place) {
			return place.getToken();
		}

	}
	*/
}
