package com.ayurma.ayuromaweb.client.mobile.activity;

import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.mobile.ClientFactory;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.view.IProductMobileView;

import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class ProductActivity2 extends MGWTAbstractActivity{
	private final ClientFactory clientFactory;
	private Logger logger = Logger.getLogger("");
	private final DataServiceAsync rpcService;
	private String token = "";
	private IProductMobileView view;
	private ChemicalData targetProduct;
	//private List<ProductsGroupMenuItem> productsGroups;
	private final Cache cache;
	@Inject
	public ProductActivity2(ClientFactory clientFactory) {
		
		this.clientFactory = clientFactory;
		
		this.rpcService = this.clientFactory.getRpcService();
		//this.token = token;
		this.cache = this.clientFactory.getCache();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//view = clientFactory.getProductView();
		view.setHeaderTitle("Pankaj Product");
		//getting the user interface elements and adding the handlers:
	    addHandlerRegistration(view.getHeaderRightButton().addTapHandler(new TapHandler() {

	        @Override
	        public void onTap(TapEvent event) {
	        	//clientFactory.getPlaceController().goTo(new HomePlace());


	        }
	      }));
	    
		//panel.setWidget(view);	
		getProductFromData(token);
		  	    
	}
private void getProductFromData(String strKey){
		
		try{
			Long key = Long.valueOf(strKey);
			targetProduct=cache.getProductByKey(key);
			if(targetProduct!=null){
				//view.stopAjaxAnim();
				//view.showData(targetProduct);
			}else{
				//goto rpc service to get the product
				fetchProductFromServer(key);
			}
			
		}catch(NumberFormatException e){
			
		}
	}
public void fetchProductFromServer(Long key){
	rpcService.getProductByKey(key,new AsyncCallback<ChemicalData>(){

		@Override
		public void onFailure(Throwable caught) {
			if (caught instanceof InvocationException) { 
				//view.info("No internet connection.", 0,1);
			}else if(caught instanceof IncompatibleRemoteServiceException){
				//view.info("Error in sending message", 0,1);
			}else{
				//view.info("Error in sending message", 0,1);
			}
			//view.stopAjaxAnim();
			
		}

		@Override
		public void onSuccess(ChemicalData result) {
			
			if(result!=null){
				processDataFromServer(result);
			}
			else{
				//view.stopAjaxAnim();
			}
			
		}});
}
private void processDataFromServer(ChemicalData result){
	targetProduct=result;
	cache.putProduct(targetProduct);
	
	//Timer t = new Timer(){

		//@Override
		//public void run() {
			//view.stopAjaxAnim();
			view.showData(targetProduct);
			
		//}};
	//t.schedule(2000);

	
}
	 
}
