package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.ProductGroupActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductGroupPlace extends ActivityPlace<ProductGroupActivity> {
	@Inject
	public ProductGroupPlace(ProductGroupActivity activity) {
		super(activity);
		
	}
	@Override
    public ProductGroupActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("products-groups")
    public static class Tokenizer 
                    implements PlaceTokenizer<ProductGroupPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<ProductGroupPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ProductGroupPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(ProductGroupPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public ProductGroupPlace getPlace(String token) {
        	ProductGroupPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
}
