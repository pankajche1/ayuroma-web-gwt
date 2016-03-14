package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.ProductsDetailsEditActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductsDetailsEditPlace extends ActivityPlace<ProductsDetailsEditActivity>{
	@Inject
	public ProductsDetailsEditPlace(ProductsDetailsEditActivity activity) {
		super(activity);
		
	}
	@Override
	public ProductsDetailsEditActivity getActivity(){
		activity.init(this);
		return activity;
		
	}
	@Prefix("products-details-edit")
    public static class Tokenizer 
                    implements PlaceTokenizer<ProductsDetailsEditPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<ProductsDetailsEditPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ProductsDetailsEditPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(ProductsDetailsEditPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public ProductsDetailsEditPlace getPlace(String token) {
        	ProductsDetailsEditPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
}
