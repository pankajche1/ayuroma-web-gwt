package com.ayurma.ayuromaweb.client.utils;

import com.ayurma.ayuromaweb.client.activity.IProductViewActivity;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class ProductDataProvider {
	private final DataServiceAsync rpcService;
	private final Cache cache;
	private ChemicalData targetProduct = null;
	private final IProductViewActivity activity;

	public ProductDataProvider(DataServiceAsync rpcService, Cache cache, IProductViewActivity activity) {
		
		this.rpcService = rpcService;
		this.cache = cache;
		this.activity = activity;
	}
	public void processToken(String token){
		String source="";
		String query;
		String[] nameValuesPairs=token.split("[&]");
		//System.out.println(token);
		if(nameValuesPairs.length>0){
			String[] children=nameValuesPairs[0].split("[=]");
			if(children.length>1) source=children[1];
			
		}
		//System.out.println(source);
		if(source.equals("search")){
			String[] queryNameValuePairs=nameValuesPairs[1].split("[=]");
			query=queryNameValuePairs[1];
			getProductFromSearch(query);
		}else if(source.equals("group")){
			getProductFromData(nameValuesPairs[1].split("[=]")[1]);
		}
	}
	public void getProductFromSearch(String query){
		String productName = query;
		//search in the cache if this name exists
		//if yes then get its key
		//and call the rpc to get this product by this key:
		Long key = cache.getKeySearchedProduct(productName);
		if(key!=null){
			//now see if in the cache the product is alerady present
			ChemicalData dataFromCache = cache.getProductByKey(key);
			if(dataFromCache==null){
				//call the rpc
				fetchProductFromServer(key);
			}else{
				//show the product in the view
				activity.processDataFromServer(dataFromCache);
				//view.showData(dataFromCache);
			}	
			//if yes then don't go to the server
		}else{
			//call the rpc to get the product by Product name
			fetchProductByName(productName);
		}
	}
	private void fetchProductByName(String name){
		rpcService.getProductByName(name, new AsyncCallback<ChemicalData>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ChemicalData result) {
				activity.processDataFromServer(result);
				
			}});
	}
	public void getProductFromData(String strKey){
		
		try{
			Long key = Long.valueOf(strKey);
			targetProduct=cache.getProductByKey(key);
			if(targetProduct!=null){
				activity.stopAjaxAnim();
				activity.showData(targetProduct);
			}else{
				//goto rpc service to get the product
				fetchProductFromServer(key);
			}
			
		}catch(NumberFormatException e){
			//System.out.println("ProductActivity::getProductFromData() NumberFormatException.");
			activity.info("Number Format Error", 0, 1);//type 1 = Error, 0 = success
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
				activity.stopAjaxAnim();
			
			}

			@Override
			public void onSuccess(ChemicalData result) {
				
				if(result!=null){
					activity.processDataFromServer(result);
				}
				else{
					activity.stopAjaxAnim();
				}
			
		}});
	}
	
}
