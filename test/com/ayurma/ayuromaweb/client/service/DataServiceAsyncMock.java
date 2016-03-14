package com.ayurma.ayuromaweb.client.service;

import java.util.HashMap;
import java.util.Map;

import com.ayurma.ayuromaweb.server.dao.DAO;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ContactUsDTO;
import com.ayurma.ayuromaweb.shared.NameSuggestDTO;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.ayurma.ayuromaweb.shared.ProductEnquiryDTO;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SliderFilmItemsDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DataServiceAsyncMock implements DataServiceAsync{
	private Map<Long, ChemicalData> products = new HashMap<Long,ChemicalData>();
	public DataServiceAsyncMock(){
		products.put(100L, new ChemicalData("Product001","Description of Product001"));
		products.put(200L, new ChemicalData("Product002","Description of Product002"));
		products.put(300L, new ChemicalData("Product003","Description of Product003"));
		products.put(400L, new ChemicalData("Product004","Description of Product004"));
		products.put(500L, new ChemicalData("Product005","Description of Product005"));
		products.put(600L, new ChemicalData("Product006","Description of Product006"));
	}
	@Override
	public void suggest(String queryString,
			AsyncCallback<NameSuggestDTO> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getProductByKey(Long key, AsyncCallback<ChemicalData> callback) {
		System.out.println("DataServiceAsyncMock::getProductByKey().. key="+key);
		ChemicalData data = DAO.get().getProduct(key);
		callback.onSuccess(data);
		
	}

	@Override
	public void getProductByName(String name,
			AsyncCallback<ChemicalData> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getProductGroupsItemsData(Long key,
			AsyncCallback<ProductGroupItemsData> callback) {
		System.out.println("DataServiceAsyncMock::getProductGroupsItemsData() key="+key);
		
		callback.onSuccess(DAO.get().getProductGroupsItemsData(key));
		
	}

	@Override
	public void submitContactEnquiry(ContactUsDTO data,
			AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitProductEnquiry(ProductEnquiryDTO data,
			AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitProductReportsRequest(ProductEnquiryDTO data,
			AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getProductDetails(Long key,
			AsyncCallback<ProductDetails> callback) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getSliderFilmItems(Long key, int iStart,int nItems,
			AsyncCallback<SliderFilmItemsDTO> callback) {
		SliderFilmItemsDTO dto = DAO.get().getSliderFilmItems(key, iStart,nItems);
		callback.onSuccess(dto);
		
	}

}
