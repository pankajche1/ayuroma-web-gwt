package com.ayurma.ayuromaweb.client.service;




import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ContactUsDTO;
import com.ayurma.ayuromaweb.shared.NameSuggestDTO;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.ayurma.ayuromaweb.shared.ProductEnquiryDTO;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SliderFilmItemsDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataServiceAsync {
	//suggest box oracle:
	void suggest(String queryString,AsyncCallback<NameSuggestDTO> callback);
	//Product get:
	void getProductByKey(Long key,AsyncCallback<ChemicalData> callback);
	void getProductByName(String name,AsyncCallback<ChemicalData> callback);
	//Product Group:
	void getProductGroupsItemsData(Long key,AsyncCallback<ProductGroupItemsData> callback);
	//contact us enquiry:
	void submitContactEnquiry(ContactUsDTO data,AsyncCallback<String> callback);
	//enquiry about a product:
	void submitProductEnquiry(ProductEnquiryDTO data,AsyncCallback<String> callback);
	//request for getting the product reports:
	void submitProductReportsRequest(ProductEnquiryDTO data,AsyncCallback<String> callback);
	void getProductDetails(Long key, AsyncCallback<ProductDetails> callback );
	void getSliderFilmItems(Long key,int iStart,int nItems,AsyncCallback<SliderFilmItemsDTO> callback);

}
