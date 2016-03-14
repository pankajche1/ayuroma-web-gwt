package com.ayurma.ayuromaweb.client.utils;


import com.ayurma.ayuromaweb.client.activity.IProductDetailsActivity;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ProductDetailsDataProvider {
	private final DataServiceAsync rpcService;
	private final Cache cache;
	
	private final IProductDetailsActivity activity;
	private ProductDetails targetData;

	public ProductDetailsDataProvider(DataServiceAsync rpcService, Cache cache,
			IProductDetailsActivity activity) {
		
		this.rpcService = rpcService;
		this.cache = cache;
		this.activity = activity;
	}
	public void processToken(String token){
		try{
			Long key = Long.valueOf(token);
			fetchProductDetails(key);
		}catch(NumberFormatException e){
			
		}
	}
	private void fetchProductDetails(Long key){
		//search the cache:
		ProductDetails data = cache.getProductDetails(key);
		if(data!=null){
			processResult(data);
			return;
		}
		rpcService.getProductDetails(key,new AsyncCallback<ProductDetails>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ProductDetails result) {
				cache.putProductDetails(result);

					processResult(result);	
				
			}});
	}
	private void processResult(ProductDetails result){
		targetData=result;
		activity.setTargetData(result);
		
		//Timer t = new Timer(){

			//@Override
			//public void run() {
				activity.stopAjaxLoading();
				activity.setData(targetData);
				
			//}};
		//t.schedule(2000);
	}
	

}
