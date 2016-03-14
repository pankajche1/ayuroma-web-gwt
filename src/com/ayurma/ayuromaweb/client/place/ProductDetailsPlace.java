package com.ayurma.ayuromaweb.client.place;

import com.ayurma.ayuromaweb.client.activity.ProductDetailsActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductDetailsPlace extends ActivityPlace<ProductDetailsActivity>{
	@Inject
	public ProductDetailsPlace(ProductDetailsActivity activity) {
		super(activity);
		
	}
	@Override
	public ProductDetailsActivity getActivity(){
		activity.init(this);
		return activity;
	}
	@Prefix("product-details")
    public static class Tokenizer 
                    implements PlaceTokenizer<ProductDetailsPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<ProductDetailsPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ProductDetailsPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(ProductDetailsPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public ProductDetailsPlace getPlace(String token) {
        	ProductDetailsPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

}
