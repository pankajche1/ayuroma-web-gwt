package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.ProductsViewActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class ProductPlace extends ActivityPlace<ProductsViewActivity>{
	@Inject
	public ProductPlace(ProductsViewActivity activity) {
		super(activity);
		
	}
	@Override
    public ProductsViewActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("products")
    public static class Tokenizer 
                    implements PlaceTokenizer<ProductPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<ProductPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ProductPlace> placeProvider){
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
}
