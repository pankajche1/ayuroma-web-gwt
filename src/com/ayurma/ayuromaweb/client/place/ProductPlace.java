package com.ayurma.ayuromaweb.client.place;


import com.ayurma.ayuromaweb.client.activity.ProductViewActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductPlace  extends ActivityPlace<ProductViewActivity>{
	private String productName;
	private String strKey;
	private boolean isFromSearch=false;
    @Inject
	public ProductPlace(ProductViewActivity activity) {
		super(activity);
		
	}
	@Override
    public ProductViewActivity getActivity() {
        activity.init(this);
        return activity;
    }
	
    public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStrKey() {
		return strKey;
	}
	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public boolean isFromSearch() {
		return isFromSearch;
	}
	public void setFromSearch(boolean isFromSearch) {
		this.isFromSearch = isFromSearch;
	}

	@Prefix("product-display")
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
