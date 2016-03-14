package com.ayurma.ayuromaweb.client.mobile.activity;


import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.activity.IProductViewActivity;
import com.ayurma.ayuromaweb.client.mobile.layout.ProductMobileViewLayoutImpl;
import com.ayurma.ayuromaweb.client.mobile.place.EnquiryMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductDetailsMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductPlace;
import com.ayurma.ayuromaweb.client.mobile.view.IProductMobileView;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.utils.ProductDataProvider;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;


public class ProductActivity extends MGWTAbstractActivity implements IProductViewActivity{
	//private final ClientFactory clientFactory;
	private Logger logger = Logger.getLogger("");
	private final DataServiceAsync rpcService;
	private PlaceController placeController;
	private ProductPlace place;
	private String strProductKey = "";
	private IProductMobileView<ChemicalData> view;
	private ChemicalData targetProduct;
	private Provider<HomePlace> homePlaceProvider;
	private final ProductDataProvider dataProvider;
	//private List<ProductsGroupMenuItem> productsGroups;
	private final Cache cache;
	private Provider<EnquiryMobilePlace> enquiryMobilePlaceProvider;
	private Provider<ProductDetailsMobilePlace> productDetailsMobilePlaceProvider;
	@Inject
	public ProductActivity(IProductMobileView<ChemicalData> view, DataServiceAsync rpcService,
			Cache cache,
			PlaceController placeController,
			Provider<HomePlace> homePlaceProvider,
			Provider<EnquiryMobilePlace> enquiryMobilePlaceProvider,
			Provider<ProductDetailsMobilePlace> productDetailsMobilePlaceProvider,
			ProductMobileViewLayoutImpl layouts) {
		this.view = view;
		//this.view.setPresenter(this);
		this.view.setLayouts(layouts);
		this.homePlaceProvider = homePlaceProvider;
		this.enquiryMobilePlaceProvider = enquiryMobilePlaceProvider;
		this.productDetailsMobilePlaceProvider = productDetailsMobilePlaceProvider;
		this.placeController=placeController;
		this.rpcService=rpcService;
		this.cache = cache;
		dataProvider = new ProductDataProvider(this.rpcService, this.cache, this);
		
	}
	public void init(ProductPlace place){
		//System.out.println("init");
		this.place = place;
		view.reset();
		view.showAjaxAnim();
		String token = place.getPlaceName();
		dataProvider.processToken(token);
		
	}
	/*
	@Inject
	public ProductActivity(ClientFactory clientFactory, String token) {
		
		this.clientFactory = clientFactory;
		
		this.rpcService = this.clientFactory.getRpcService();
		this.token = token;
		this.cache = this.clientFactory.getCache();
	}
	*/

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//System.out.println("start()");
		//view = clientFactory.getProductView();
		//view.setHeaderTitle("Product");
		//getting the user interface elements and adding the handlers:
	    addHandlerRegistration(view.getHeaderRightButton().addTapHandler(new TapHandler() {

	        @Override
	        public void onTap(TapEvent event) {
	        	HomePlace place= homePlaceProvider.get();
        		place.setPlaceName("home");
        		placeController.goTo(place);
	        }
	    }));
	   
	    //product enquiry button:
	    addHandlerRegistration(view.getButtonSendEnquiry().addTapHandler(new TapHandler() {

	        @Override
	        public void onTap(TapEvent event) {
	        	EnquiryMobilePlace place= enquiryMobilePlaceProvider.get();
	        	String queryString="source=product&key="+String.valueOf(targetProduct.getKey());
	    		place.setPlaceName(queryString);
        		
        		placeController.goTo(place);
	        }
	    }));
	    //get reports button:
	    addHandlerRegistration(view.getButtonGetReports().addTapHandler(new TapHandler() {

	        @Override
	        public void onTap(TapEvent event) {
	        	EnquiryMobilePlace place= enquiryMobilePlaceProvider.get();
	        	String queryString="source=product-report&key="+String.valueOf(targetProduct.getKey());
	    		place.setPlaceName(queryString);
	        	
        		placeController.goTo(place);
	        }
	    }));
	    //show details button:
	    addHandlerRegistration(view.getButtonShowDetails().addTapHandler(new TapHandler() {

	        @Override
	        public void onTap(TapEvent event) {
	        	ProductDetailsMobilePlace place = productDetailsMobilePlaceProvider.get();
	        	Long keyDetails = targetProduct.getDetailsId();
	   		 	String strKeyDetails=String.valueOf(keyDetails); 
	   		 	place.setPlaceName(strKeyDetails);
        		
        		placeController.goTo(place);
	        }
	    }));
	   
		panel.setWidget(view.asWidget());	
		
		  	    
	}

	@Override
	public void processDataFromServer(ChemicalData result){
		targetProduct=result;
		cache.putProduct(targetProduct);
	
		//Timer t = new Timer(){

			//@Override
			//public void run() {
				view.stopAjaxAnim();
				view.showData(targetProduct);
			
				//}};
				//t.schedule(2000);

	
	}
	@Override
	public void stopAjaxAnim() {
		view.stopAjaxAnim();
		
	}
	@Override
	public void startAjaxAnim() {
		view.showAjaxAnim();
		
	}
	@Override
	public void info(String msg, int id, int type) {
		view.info(msg, id, type);
		/*
		switch(type){
		case 0:	//success
			
			break;
		case 1://error rpc
			
			break;
		}
		*/
		
	}
	public ChemicalData getTargetProduct() {
		return targetProduct;
	}
	@Override
	public void showData(ChemicalData chemical) {
		targetProduct=chemical;
		view.showData(targetProduct);
		
	}
	 
	  
}
