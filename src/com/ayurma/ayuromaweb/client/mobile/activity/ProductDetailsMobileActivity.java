package com.ayurma.ayuromaweb.client.mobile.activity;

import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.activity.IProductDetailsActivity;

import com.ayurma.ayuromaweb.client.mobile.layout.ProductDetailsMobileLayoutImpl;
import com.ayurma.ayuromaweb.client.mobile.place.EnquiryMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductDetailsMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.view.IProductDetailsMobileView;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.utils.ProductDetailsDataProvider;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class ProductDetailsMobileActivity extends MGWTAbstractActivity implements IProductDetailsActivity{
	private Logger logger = Logger.getLogger("");
	private final DataServiceAsync rpcService;
	private PlaceController placeController;
	private Provider<HomePlace> homePlaceProvider;
	private final Cache cache;
	private IProductDetailsMobileView<ProductDetails> view;
	private ProductDetailsMobilePlace place;
	private  ProductDetailsDataProvider dataProvider;
	private ProductDetails targetData;
	private Provider<EnquiryMobilePlace> enquiryMobilePlaceProvider;
	@Inject
	public ProductDetailsMobileActivity(IProductDetailsMobileView<ProductDetails> view,
			DataServiceAsync rpcService,
			Cache cache,
			PlaceController placeController,
			Provider<HomePlace> homePlaceProvider,
			Provider<EnquiryMobilePlace> enquiryMobilePlaceProvider,
			ProductDetailsMobileLayoutImpl layouts
			) {
		this.view = view;
		//this.view.setPresenter(this);
		this.view.setListLayouts(layouts);
		this.homePlaceProvider = homePlaceProvider;
		this.enquiryMobilePlaceProvider = enquiryMobilePlaceProvider;
		this.placeController=placeController;
		this.rpcService=rpcService;
		this.cache = cache;
		dataProvider = new ProductDetailsDataProvider(this.rpcService, this.cache, this);
		
	}
	public void init(ProductDetailsMobilePlace place){
		//System.out.println("init");
		this.place = place;
		view.reset();
		view.showAjaxLoading();
		dataProvider.processToken(place.getPlaceName());
		
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
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
		        	//String queryString="source=product&key="+String.valueOf(targetProduct.getKey());
		        	String queryString="source=product&key="+String.valueOf(targetData.getKeyChemical());
		    		place.setPlaceName(queryString);
	        		
	        		placeController.goTo(place);
		        }
		    }));
		    //get reports button:
		    addHandlerRegistration(view.getButtonGetReports().addTapHandler(new TapHandler() {

		        @Override
		        public void onTap(TapEvent event) {
		        	EnquiryMobilePlace place= enquiryMobilePlaceProvider.get();
		        	//String queryString="source=product-report&key="+String.valueOf(targetProduct.getKey());
		        	String queryString="source=product-report&key="+String.valueOf(targetData.getKeyChemical());
		    		place.setPlaceName(queryString);
		        	
	        		placeController.goTo(place);
		        }
		    }));
		panel.setWidget(view.asWidget());	
	}
	@Override
	public void setData(ProductDetails data) {
		view.setData(data);
		
	}
	@Override
	public void showAjaxLoading() {
		view.showAjaxLoading();
		
	}
	@Override
	public void stopAjaxLoading() {
		view.stopAjaxLoading();
		
	}
	@Override
	public ProductDetails getTargetData() {
		
		return targetData;
	}
	@Override
	public void setTargetData(ProductDetails targetData) {
		this.targetData = targetData;
		
	}

}
