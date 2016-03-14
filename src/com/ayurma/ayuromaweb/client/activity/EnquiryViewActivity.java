package com.ayurma.ayuromaweb.client.activity;

import java.util.List;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.place.EnquiryPlace;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.utils.EnquiryDataProvider;
import com.ayurma.ayuromaweb.client.view.IEnquiryView;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.CompanyInfoDTO;
import com.ayurma.ayuromaweb.shared.ContactUsDTO;
import com.ayurma.ayuromaweb.shared.ProductEnquiryDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class EnquiryViewActivity extends AbstractActivity implements IEnquiryView.Presenter,
										IEnquiryActivity{
	private IEnquiryView view;
	private final DataServiceAsync rpcService;
	private EnquiryPlace place;
	private final Cache cache;
	private ChemicalData targetProduct;//used for the Product Enquiry view
	private AppInitData appInitData;
	private CompanyInfoDTO companyInfo;
	private EnquiryDataProvider dataProvider;
	private int viewMode;//which type of view 0 Product Enquiry 1 Contact us 2 product reports
	@Inject
	public EnquiryViewActivity(IEnquiryView view, DataServiceAsync rpcService,
			Cache cache, AppInitData appInitData) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.cache=cache;
		this.rpcService = rpcService;
		this.appInitData=appInitData;
		companyInfo= appInitData.getCompanyInfo();
		dataProvider = new EnquiryDataProvider(this.rpcService, this.cache, this); 
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(EnquiryPlace place){
		this.place=place;
		view.scrollTo(0, 0);
		setCompanyInfoView();//setting the address of the company
		String token = place.getPlaceName();
		dataProvider.processToken(token);
		/*
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
		*/
		this.view.reset();
	}
	private void setCompanyInfoView(){

		view.setCompanyInfo(companyInfo.getCompanyHead(), companyInfo.getAddress(), companyInfo.getCity(), companyInfo.getPin(), companyInfo.getCountry(),
				companyInfo.getMobiles(), companyInfo.getTelephones(), companyInfo.getEmails());

		
	}
	@Override
	public void showContactUsView(){
		
		view.showContactUsView();
		
	}
	@Override
	public void showProductEnquiryView(String nameTargetProduct){
		view.showProductEnquiryView(nameTargetProduct);
		/*
		
		//get the product key:
		try{
			Long key = Long.valueOf(strKey);
			targetProduct = cache.getProductByKey(key);
			if(targetProduct!=null){
				view.showProductEnquiryView(targetProduct.getName());
			}
		}catch(NumberFormatException e){
			
		}
		*/
	}
	@Override
	public void showGetProductReportsView(String nameTargetProduct){
		view.showGetProductReportsView(nameTargetProduct);
		/*
		Long key = Long.valueOf(strKey);
		targetProduct = cache.getProductByKey(key);
		if(targetProduct!=null){
			view.showGetProductReportsView(targetProduct.getName());
		}
		*/
	}
/*
	public void submitEnquirys(List<String> list) {
		List<String> dataList=list;
		if(dataList.isEmpty()) return;//no data was got from the view
		//save the data to the server:
		ContactUsDTO data = new ContactUsDTO();
		data.setNameUser(dataList.get(0));//0 name
		data.setEmail(dataList.get(1));//1 email
		data.setCompanyName(dataList.get(2));
		data.setCityName(dataList.get(3));
		data.setCountry(dataList.get(4));
		data.setIsdCode(dataList.get(5));
		data.setAreaCodePhone(dataList.get(6));
		data.setPhone(dataList.get(7));
		data.setCountryCallingCode(dataList.get(8));
		data.setMobile(dataList.get(9));
		data.setMessage(dataList.get(10));
		//11 reCaptcha challenge
		///data.setChallenge(dataList.get(12));
		//12 reCaptcha response
		///data.setResponse(dataList.get(13));
		sendEnquiry(data);
		
		
	}
	private void sendEnquiry(ContactUsDTO data){
		
	}
*/
	@Override
	public void submitProductEnquiry(String senderName, String senderEmail,String companyName,
			String city, String country, String countryCallingCode,
			String isdCode, String areaCallingCode, String mobile,
			String phone, String message,String txtHoneyPot) {
		//giving this task to the dataProvider:
		dataProvider.submitProductEnquiry(senderName, senderEmail, companyName, city, 
				country, countryCallingCode, isdCode, areaCallingCode, 
				mobile, phone, message, txtHoneyPot);
		/*
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
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0,1);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending enquiry", 0,1);
				}else{
					view.info("Error in sending enquiry", 0,1);
				}
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, 0,0);
				
			}});
			*/
		
	}

	@Override
	public void submitEnquiry(String senderName, String senderEmail,String companyName,
			String city, String country, String countryCallingCode,
			String isdCode, String areaCallingCode, String mobile,
			String phone, String message,String txtHoneyPot) {
		
		dataProvider.submitEnquiry(senderName, senderEmail, companyName, city, country,
				countryCallingCode, isdCode, areaCallingCode, mobile, 
				phone, message, txtHoneyPot);
		/*
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
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0,1);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending enquiry", 0,1);
				}else{
					view.info("Error in sending enquiry", 0,1);
				}
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, 0,0);
				
			}});
			*/
		
	}

	@Override
	public void submitProductReportsRequest(String senderName,
			String senderEmail, String companyName, String city,
			String country, String countryCallingCode, String isdCode,
			String areaCallingCode, String mobile, String phone,
			boolean isRoport1, boolean isRoport2, boolean isRoport3,
			String txtHoneyPot) {
		
		dataProvider.submitProductReportsRequest(senderName, senderEmail, companyName, 
				city, country, countryCallingCode, isdCode, areaCallingCode, mobile, 
				phone, isRoport1, isRoport2, isRoport3, txtHoneyPot);
		/*
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
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0,1);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending message", 0,1);
				}else{
					view.info("Error in sending message", 0,1);
				}
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, 0,0);
				
			}});	
			*/
		
	}

	@Override
	public void info(String msg, int id, int type) {
		view.info(msg, id, type);
		
	}

	@Override
	public void stopAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getViewMode() {
		
		return viewMode;
	}

	@Override
	public void setViewMode(int viewMode) {
		this.viewMode = viewMode;
		
	}

}
