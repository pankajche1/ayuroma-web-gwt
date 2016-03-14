package com.ayurma.ayuromaweb.client.mobile.place;



import com.ayurma.ayuromaweb.client.mobile.activity.ProductDetailsMobileActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductDetailsMobilePlace extends ActivityPlace<ProductDetailsMobileActivity>{
	@Inject
	public ProductDetailsMobilePlace(ProductDetailsMobileActivity activity) {
		super(activity);
		
	}
	@Override
	public ProductDetailsMobileActivity getActivity(){
		activity.init(this);
		//logger.log(Level.INFO, "ProductsPlace getActivity...");
		return activity;
	}	
	@Prefix("product-details")
    public static class Tokenizer 
                    implements PlaceTokenizer<ProductDetailsMobilePlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
		//private Logger logger = Logger.getLogger("");
        //1
        private final Provider<ProductDetailsMobilePlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ProductDetailsMobilePlace> placeProvider){
        	//logger.log(Level.INFO, "ProductsPlace Tokenizer()");
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(ProductDetailsMobilePlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public ProductDetailsMobilePlace getPlace(String token) {
        	ProductDetailsMobilePlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

}
