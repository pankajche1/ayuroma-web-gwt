package com.ayurma.ayuromaweb.client.place;

import com.ayurma.ayuromaweb.client.activity.ProductGroupActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductGroupPlace  extends ActivityPlace<ProductGroupActivity>{
	private String productGroupName;
	private String strKey;

	@Inject
	public ProductGroupPlace(ProductGroupActivity activity) {
		super(activity);
		
	}
	@Override
    public ProductGroupActivity getActivity() {
        activity.init(this);
        return activity;
    }

	public String getProductGroupName() {
		return productGroupName;
	}
	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}
	public String getStrKey() {
		return strKey;
	}
	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	@Prefix("products")
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
