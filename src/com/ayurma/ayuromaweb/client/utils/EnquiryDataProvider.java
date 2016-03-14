package com.ayurma.ayuromaweb.client.utils;


import com.ayurma.ayuromaweb.client.activity.IEnquiryActivity;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ContactUsDTO;
import com.ayurma.ayuromaweb.shared.ProductEnquiryDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class EnquiryDataProvider {
	private final DataServiceAsync rpcService;
	private final Cache cache;
	private IEnquiryActivity activity;
	private ChemicalData targetProduct;//used for the Product Enquiry view
	public EnquiryDataProvider(DataServiceAsync rpcService, Cache cache, IEnquiryActivity activity) {
		
		this.rpcService = rpcService;
		this.cache = cache;
		this.activity = activity;
	}
	public void processToken(String token){
		String[] nameValuesPairs=token.split("[&]");
		String source="";
		if(nameValuesPairs.length>0){
			source=nameValuesPairs[0].split("[=]")[1];
		}
		//now the flow can go in three directions:
		if(source.equals("product")){//1 product enquiry
			showProductEnquiryView(nameValuesPairs[1].split("[=]")[1]);
		}else if(source.equals("product-report")){//2 product report enquiry
			showGetProductReportsView(nameValuesPairs[1].split("[=]")[1]);
		}else if(source.equals("contact-us")){//3 contact us enquiry
			showContactUsView();
		}
	}
	private void showContactUsView(){
		activity.setViewMode(1);
		activity.showContactUsView();
		
	}
	//which type of view 0 Product Enquiry 1 Contact us 2 product reports
	private void showProductEnquiryView(String strKey){
		
		//get the product key:
		try{
			Long key = Long.valueOf(strKey);
			targetProduct = cache.getProductByKey(key);
			if(targetProduct!=null){
				activity.setViewMode(0);
				activity.showProductEnquiryView(targetProduct.getName());
			}
		}catch(NumberFormatException e){
			
		}
	}
	
	private void showGetProductReportsView(String strKey){
		Long key = Long.valueOf(strKey);
		targetProduct = cache.getProductByKey(key);
		if(targetProduct!=null){
			activity.setViewMode(2);
			activity.showGetProductReportsView(targetProduct.getName());
		}
	}
	public void submitProductEnquiry(String senderName, String senderEmail,String companyName,
			String city, String country, String countryCallingCode,
			String isdCode, String areaCallingCode, String mobile,
			String phone, String message,String txtHoneyPot) {
		ProductEnquiryDTO data = new ProductEnquiryDTO();
		data.setNameUser(senderName);//0 name
		data.setEmail(senderEmail);//1 email
		data.setCompanyName(companyName);
		data.setCityName(city);
		data.setCountry(country);
		data.setIsdCode(isdCode);
		data.setAreaCodePhone(areaCallingCode);
		data.setPhone(phone);
		data.setCountryCallingCode(countryCallingCode);
		data.setMobile(mobile);
		data.setMessage(message);
		data.setTextHidden(txtHoneyPot.trim());
		if(targetProduct!=null) data.setProductName(targetProduct.getName());
		//sending rpc:
		rpcService.submitProductEnquiry(data, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				activity.stopAjaxAnim();
				if (caught instanceof InvocationException) { 
					activity.info("No internet connection.", 0,1);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					activity.info("Error in sending enquiry", 0,1);
				}else{
					activity.info("Error in sending enquiry", 0,1);
				}
				
			}

			@Override
			public void onSuccess(String result) {
				activity.stopAjaxAnim();
				activity.info(result, 0,0);
				
			}});
		
	}
	public void submitEnquiry(String senderName, String senderEmail,String companyName,
			String city, String country, String countryCallingCode,
			String isdCode, String areaCallingCode, String mobile,
			String phone, String message,String txtHoneyPot) {
		ContactUsDTO data = new ContactUsDTO();
		data.setNameUser(senderName);//0 name
		data.setEmail(senderEmail);//1 email
		data.setCompanyName(companyName);
		data.setCityName(city);
		data.setCountry(country);
		data.setIsdCode(isdCode);
		data.setAreaCodePhone(areaCallingCode);
		data.setPhone(phone);
		data.setCountryCallingCode(countryCallingCode);
		data.setMobile(mobile);
		data.setMessage(message);
		data.setTextHidden(txtHoneyPot.trim());
		//sending rpc:
		rpcService.submitContactEnquiry(data, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				activity.stopAjaxAnim();
				if (caught instanceof InvocationException) { 
					
					activity.info("No internet connection.", 0,1);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					activity.info("Error in sending enquiry", 0,1);
				}else{
					activity.info("Error in sending enquiry", 0,1);
				}
				
			}

			@Override
			public void onSuccess(String result) {
				activity.stopAjaxAnim();
				activity.info(result, 0,0);
				
			}});
		
	}
	public void submitProductReportsRequest(String senderName,
			String senderEmail, String companyName, String city,
			String country, String countryCallingCode, String isdCode,
			String areaCallingCode, String mobile, String phone,
			boolean isRoport1, boolean isRoport2, boolean isRoport3,
			String txtHoneyPot) {
		ProductEnquiryDTO data = new ProductEnquiryDTO();
		data.setNameUser(senderName);//0 name
		data.setEmail(senderEmail);//1 email
		data.setCompanyName(companyName);
		data.setCityName(city);
		data.setCountry(country);
		data.setIsdCode(isdCode);
		data.setAreaCodePhone(areaCallingCode);
		data.setPhone(phone);
		data.setCountryCallingCode(countryCallingCode);
		data.setMobile(mobile);
		//reports:
		String message=(isRoport1)?"COA ":"";
		
		message+=(isRoport2)?"MSDS ":"";
		
		message+=(isRoport3)?"GCMS ":"";

		data.setMessage(message);
		data.setTextHidden(txtHoneyPot.trim());
		if(targetProduct!=null) data.setProductName(targetProduct.getName());
		//sending rpc:
		rpcService.submitProductReportsRequest(data, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				activity.stopAjaxAnim();
				if (caught instanceof InvocationException) { 
					activity.info("No internet connection.", 0,1);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					activity.info("Error in sending message", 0,1);
				}else{
					activity.info("Error in sending message", 0,1);
				}
				
			}

			@Override
			public void onSuccess(String result) {
				activity.stopAjaxAnim();
				activity.info(result, 0,0);
				
			}});	
		
	}

}
