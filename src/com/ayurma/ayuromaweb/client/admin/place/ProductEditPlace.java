package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.ProductEditActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductEditPlace  extends ActivityPlace<ProductEditActivity>{
	@Inject
	public ProductEditPlace(ProductEditActivity activity) {
		super(activity);
		
	}
	@Override
	public ProductEditActivity getActivity(){
		activity.init(this);
		return activity;
	}
	@Prefix("products-edit")
    public static class Tokenizer 
                    implements PlaceTokenizer<ProductEditPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<ProductEditPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ProductEditPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(ProductEditPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public ProductEditPlace getPlace(String token) {
        	ProductEditPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

}
