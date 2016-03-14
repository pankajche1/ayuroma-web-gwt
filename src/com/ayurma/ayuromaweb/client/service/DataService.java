package com.ayurma.ayuromaweb.client.service;





import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ContactUsDTO;
import com.ayurma.ayuromaweb.shared.NameSuggestDTO;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.ayurma.ayuromaweb.shared.ProductEnquiryDTO;
import com.ayurma.ayuromaweb.shared.SliderFilmItemsDTO;

import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataservice")
public interface DataService extends RemoteService {
	//suggest box oracle:
	NameSuggestDTO suggest(String queryString);
	//Product get:
	ChemicalData getProductByKey(Long key);
	ChemicalData getProductByName(String name);
	//Product Groups:
	ProductGroupItemsData getProductGroupsItemsData(Long key);
	//contact us:
	String submitContactEnquiry(ContactUsDTO data);
	//enquiry about the product:
	String submitProductEnquiry(ProductEnquiryDTO data);
	//request for getting the product reports:
	String submitProductReportsRequest(ProductEnquiryDTO data);
	ProductDetails getProductDetails(Long key);
	SliderFilmItemsDTO getSliderFilmItems(Long key,int iStart,int nItems);
}
