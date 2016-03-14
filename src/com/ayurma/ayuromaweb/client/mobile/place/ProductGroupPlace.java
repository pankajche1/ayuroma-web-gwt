package com.ayurma.ayuromaweb.client.mobile.place;



import com.ayurma.ayuromaweb.client.mobile.activity.ProductGroupActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductGroupPlace extends ActivityPlace<ProductGroupActivity>{
	private String productGroupName;
	private String strKey;
	
	@Inject
	public ProductGroupPlace(ProductGroupActivity activity) {
	
		super(activity);
		//logger.log(Level.INFO, "ProductsPlace construct...");
	}
	@Override
	public ProductGroupActivity getActivity(){
		activity.init(this);
		//logger.log(Level.INFO, "ProductsPlace getActivity...");
		return activity;
	}	
	/*
	public ProductGroupPlace(String token) {
		
		this.token = token;
	}

	@Override
	public ProductGroupActivity getActivity(){
		//activity.init(this);
		return activity;
	}
	*/
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
		//private Logger logger = Logger.getLogger("");
        //1
        private final Provider<ProductGroupPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ProductGroupPlace> placeProvider){
        	//logger.log(Level.INFO, "ProductsPlace Tokenizer()");
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

/*
	@Prefix("products")
	public static class ProductGroupPlaceTokenizer implements PlaceTokenizer<ProductGroupPlace> {

		@Override
		public ProductGroupPlace getPlace(String token) {
			return new ProductGroupPlace(token);
		}

		@Override
		public String getToken(ProductGroupPlace place) {
			return place.getToken();
		}

	}
	*/
}
