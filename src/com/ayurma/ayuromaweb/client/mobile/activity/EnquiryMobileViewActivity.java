package com.ayurma.ayuromaweb.client.mobile.activity;


import java.util.logging.Level;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.activity.IEnquiryActivity;
import com.ayurma.ayuromaweb.client.mobile.place.EnquiryMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.view.IEnquiryMobileView;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.utils.EnquiryDataProvider;
import com.ayurma.ayuromaweb.shared.ContactUsDTO;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class EnquiryMobileViewActivity extends MGWTAbstractActivity implements IEnquiryActivity{
	//private final ClientFactory clientFactory;
	private Logger logger = Logger.getLogger("");
	private String token = "";
	private DataServiceAsync rpcService;
	
	private PlaceController placeController;
	private final Cache cache;
	private Provider<HomePlace> homePlaceProvider;
	
	private IEnquiryMobileView view;
	private EnquiryMobilePlace place;
	private EnquiryDataProvider dataProvider;
	private int viewMode;//which type of view 0 Product Enquiry 1 Contact us 2 product reports
	@Inject
	public EnquiryMobileViewActivity(IEnquiryMobileView view,
			DataServiceAsync rpcService,
			Cache cache,
			PlaceController placeController,
			Provider<HomePlace> homePlaceProvider) {
		
		this.view = view;
		this.homePlaceProvider = homePlaceProvider;
		this.placeController=placeController;
		this.rpcService=rpcService;
		this.cache = cache;
		dataProvider = new EnquiryDataProvider(this.rpcService, this.cache, this); 
		
	}
	public void init(EnquiryMobilePlace place){
		//System.out.println("init");
		this.place = place;
		String token = place.getPlaceName();
		dataProvider.processToken(token);
		
		
	}
	/*
	public EnquiryMobileViewActivity(ClientFactory clientFactory,String token) {
		
		this.clientFactory = clientFactory;
		this.rpcService = this.clientFactory.getRpcService();
		this.token = token;
		this.cache = this.clientFactory.getCache();
		
	}
	*/
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//view = clientFactory.getContactUsView();
		//getting the user interface elements and adding the handlers:
	    addHandlerRegistration(view.getSendButton().addTapHandler(new TapHandler() {

	        @Override
	        public void onTap(TapEvent event) {
	          boolean isValid = view.validate();
	          if(isValid){
	        	  if(viewMode==0){
	        		  submitProductEnquiry();//enquiry about a particular product
	        	  }else if(viewMode==1){
	        		  submitEnquiry(); //contact us enquiry 
	        	  }else if(viewMode==2){
	        		  submitReportRequest();//request to get reports of a product
	        	  }
	          }

	        }
	      }));
	  //getting the user interface elements and adding the handlers:
	    addHandlerRegistration(view.getHeaderRightButton().addTapHandler(new TapHandler() {

	        @Override
	        public void onTap(TapEvent event) {
	        	//clientFactory.getPlaceController().goTo(new HomePlace());
	        	HomePlace place= homePlaceProvider.get();
        		
        		place.setPlaceName("home");
        		placeController.goTo(place);


	        }
	      }));


		panel.setWidget(view.asWidget());
			  
	}
	private void submitProductEnquiry(){
		view.showProgressIndicator(true);
		/*
		1.String senderName, (2) String senderEmail, (3)String companyName,
		(4) String city, (5) String country, (6) String countryCallingCode,
		(7) String isdCode, (8) String areaCallingCode, (9) String mobile,
		(10) String phone, (11) String message, (12)String txtHoneyPot
		*/
		// there are 12 data:
		String senderName = view.getNameUser();//1
		String senderEmail = view.getEmailUser();//2
		String companyName = view.getNameCompany();//3
		String nameCity = view.getNameCity();//4
		String nameCountry = view.getNameCountry();//5
		String countryCallingCode = "";//6
		String isdCode = "";//7
		String areaCallingCode = "";//8
		String mobile = "";//9
		String phoneNumber = view.getPhoneNumber();//10 phone number (not mobile)
		String message = view.getMessageText();//11 message
		String strHoneyPot = view.getHoneyPotText();//12
		dataProvider.submitProductEnquiry(senderName, senderEmail, companyName, nameCity, nameCountry,
				countryCallingCode, isdCode, areaCallingCode, mobile, 
				phoneNumber, message, strHoneyPot);
	}
	private void submitReportRequest(){
		view.showProgressIndicator(true);
		/*
		1.String senderName, (2) String senderEmail, (3)String companyName,
		(4) String city, (5) String country, (6) String countryCallingCode,
		(7) String isdCode, (8) String areaCallingCode, (9) String mobile,
		(10) String phone, (11) String message, (12)String txtHoneyPot
		*/
		// there are 12 data:
		String senderName = view.getNameUser();//1
		String senderEmail = view.getEmailUser();//2
		String companyName = view.getNameCompany();//3
		String nameCity = view.getNameCity();//4
		String nameCountry = view.getNameCountry();//5
		String countryCallingCode = "";//6
		String isdCode = "";//7
		String areaCallingCode = "";//8
		String mobile = "";//9
		String phoneNumber = view.getPhoneNumber();//10 phone number (not mobile)
		//String message = view.getMessageText();//11 message
		String strHoneyPot = view.getHoneyPotText();//12
		dataProvider.submitProductReportsRequest(senderName, senderEmail, companyName, nameCity, nameCountry,
				countryCallingCode, isdCode, areaCallingCode, mobile, 
				phoneNumber, view.getChkCOAReportValue(),view.getChkMSDSReportValue(),
				view.getChkMSDSReportValue(), strHoneyPot);
	}
	private void submitEnquiry(){
		view.showProgressIndicator(true);
		
		/*
		1.String senderName, (2) String senderEmail, (3)String companyName,
		(4) String city, (5) String country, (6) String countryCallingCode,
		(7) String isdCode, (8) String areaCallingCode, (9) String mobile,
		(10) String phone, (11) String message, (12)String txtHoneyPot
		*/
		// there are 12 data:
		String senderName = view.getNameUser();//1
		String senderEmail = view.getEmailUser();//2
		String companyName = view.getNameCompany();//3
		String nameCity = view.getNameCity();//4
		String nameCountry = view.getNameCountry();//5
		String countryCallingCode = "";//6
		String isdCode = "";//7
		String areaCallingCode = "";//8
		String mobile = "";//9
		String phoneNumber = view.getPhoneNumber();//10 phone number (not mobile)
		String message = view.getMessageText();//11 message
		String strHoneyPot = view.getHoneyPotText();//12
		dataProvider.submitEnquiry(senderName, senderEmail, companyName, nameCity, nameCountry,
				countryCallingCode, isdCode, areaCallingCode, mobile, 
				phoneNumber, message, strHoneyPot);
		
		
	}
	@Override
	public void info(String msg, int id, int type) {
		view.info(msg, id, type);
		
	}
	@Override
	public void stopAjaxAnim() {
		view.showProgressIndicator(false);
		
	}
	@Override
	public void startAjaxAnim() {
		view.showProgressIndicator(true);
		
	}
	@Override
	public void showContactUsView() {
		view.showContactUsView();
		
	}
	@Override
	public void showProductEnquiryView(String productName) {
		view.showProductEnquiryView(productName);
		
	}
	@Override
	public void showGetProductReportsView(String productName) {
		view.showGetProductReportsView(productName);
		
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
