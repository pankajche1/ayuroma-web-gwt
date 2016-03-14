package com.ayurma.ayuromaweb.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ayurma.ayuromaweb.client.mobile.model.ProductsGroupMenuItem;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SearchResultData;
import com.google.gwt.user.client.ui.SuggestOracle.Response;

public class Cache {
	//put the products in the cache:
	private Map<Long,ChemicalData> products=new HashMap<Long,ChemicalData>(); 
	//the product details data:
	
	private Map<Long,ProductDetails> productDetails=new HashMap<Long,ProductDetails>();
	//private Map<Long,ChemicalData> SearchedProducts=new HashMap<Long,ChemicalData>(); 
	private Map<String, Response> searchResponses=new HashMap<String,Response>();
	private Map<String, Long> searchedNames = new HashMap<String,Long>();
	
	//product group items data:
	private Map<Long,ProductGroupItemsData> groupItemsData= new HashMap<Long,ProductGroupItemsData>();
	//mobile data for product groups menu item:
	private Map<Long, ProductsGroupMenuItem> mapMobileProductsGroup= new HashMap<Long,ProductsGroupMenuItem>();

	public void addSearchResultData(SearchResultData data){
		ChemicalData[] productsFromServer = data.getProducts();
		for(ChemicalData item:productsFromServer){
			//put them in the cache only if they are not there:
			if(products.get(item.getKey())==null)
				products.put(item.getKey(), item);
		}
	}
	public Response getSearchResponse(String query){
		return searchResponses.get(query);
		
	}
	public void addSearchResponse(String query,Response response){
		searchResponses.put(query, response);
	}
	public void putProduct(ChemicalData data){
		products.put(data.getKey(), data);
		
	}
	public ChemicalData getProductByKey(Long key){
		return products.get(key);
	}
	public void putSearchedName(String name,Long productKey){
		searchedNames.put(name, productKey);
	}
	public Long getKeySearchedProduct(String name){
		return searchedNames.get(name); 
	}
	//product group:
	//group items data:
	public ProductGroupItemsData getGroupsItems(Long key){
		if(key==null) return null;
		else return groupItemsData.get(key);
	}
	public void addGroupItemsData(ProductGroupItemsData data){
		groupItemsData.put(data.getKey(), data);
	}
	public void putProductDetails(ProductDetails data){
		try{
			Long key = Long.valueOf(data.getStrId());
			productDetails.put(key, data);
		}catch(NumberFormatException e){
			
		}
	}
	public ProductDetails getProductDetails(Long key){
		return productDetails.get(key);
	}
	//mobile related data product group menu item:
	public ProductsGroupMenuItem getMobileProductGroupsItem(Long key){
		if(key==null) return null;
		else return mapMobileProductsGroup.get(key);
	}
	public void addMobileProductGroupsItem(ProductsGroupMenuItem data){
		mapMobileProductsGroup.put(data.getKey(), data);
	}
	

}
