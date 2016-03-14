package com.ayurma.ayuromaweb.client.mobile.place;

import com.ayurma.ayuromaweb.client.mobile.activity.ProductActivity;
import com.ayurma.ayuromaweb.client.mobile.activity.ProductGroupActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductPlace extends ActivityPlace<ProductActivity>{
	@Inject
	public ProductPlace(ProductActivity activity) {
	
		super(activity);
		//logger.log(Level.INFO, "ProductsPlace construct...");
	}
	@Override
	public ProductActivity getActivity(){
		activity.init(this);
		//logger.log(Level.INFO, "ProductsPlace getActivity...");
		return activity;
	}	
	@Prefix("product-info")
    public static class Tokenizer 
                    implements PlaceTokenizer<ProductPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
		//private Logger logger = Logger.getLogger("");
        //1
        private final Provider<ProductPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ProductPlace> placeProvider){
        	//logger.log(Level.INFO, "ProductsPlace Tokenizer()");
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(ProductPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public ProductPlace getPlace(String token) {
        	ProductPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
	/*
	private String token;

	public ProductPlace(String token) {
		
		this.token = token;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	@Prefix("product-info")
	public static class ProductPlaceTokenizer implements PlaceTokenizer<ProductPlace> {

		@Override
		public ProductPlace getPlace(String token) {
			return new ProductPlace(token);
		}

		@Override
		public String getToken(ProductPlace place) {
			return place.getToken();
		}

	}
	*/
}
