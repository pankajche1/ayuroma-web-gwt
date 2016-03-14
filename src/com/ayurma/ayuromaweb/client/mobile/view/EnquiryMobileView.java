package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.ayurma.ayuromaweb.client.mobile.view.header.HeaderMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.widgets.CountryNamesMobileView;
import com.ayurma.ayuromaweb.shared.validation.ContactUsEnquiry;
import com.ayurma.ayuromaweb.shared.validation.EnquiryData;
import com.ayurma.ayuromaweb.shared.validation.ProductReportsData;
import com.ayurma.ayuromaweb.shared.validation.SenderData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.Validation;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MPhoneNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextArea;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.checkbox.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;

public class EnquiryMobileView extends Composite implements IEnquiryMobileView {
	
	private static EnquiryMobileViewUiBinder uiBinder = GWT
			.create(EnquiryMobileViewUiBinder.class);

	interface EnquiryMobileViewUiBinder extends UiBinder<Widget, EnquiryMobileView> {
	}
	private Logger logger = Logger.getLogger("");
	@UiField protected Button btnSend, btnReset;
	@UiField protected MEmailTextBox tbEmail;
	@UiField protected MTextBox tbName,tbCompanyName, tbCity, tbHidden;
	@UiField protected MPhoneNumberTextBox tbPhone;
	@UiField protected MTextArea taMessage;
	@UiField protected ScrollPanel scrollPanel;
	@UiField protected ProgressIndicator progressIndicator;
	@UiField protected CountryNamesMobileView listBoxCountries;
	@UiField protected MCheckBox chkCOAReport, chkMSDSReport, chkGCMSReport;
	@UiField protected Form form;
	@UiField protected HeaderMobileView headerView;
	@UiField protected FormEntry frmEntryMessage, frmEntryGCMSReport, frmEntryMSDSReport, frmEntryCOAReport;
	@UiField Label lblHeading,lblSubject;
	private MListBox countryListBox;
	private Label lblFormTitle;
	private int viewId;//which type of view 0 Product Enquiry 1 Contact us 2 product reports
	
	
	
	public EnquiryMobileView() {
		
		//s.
		initWidget(uiBinder.createAndBindUi(this));
		//tbEmail.setText("Pankaj:");
		//email text box
		//TODO use this property to c if this solves the problem of text area going up suddenly:
		//scrollPanel.setScrollLock(true);
		//scrollPanel.setBounce(false);
		// workaround for android formfields jumping around when using
		// -webkit-transform
		lblFormTitle = new Label("Contact Us");
		form.setHeader(lblFormTitle);
		//scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
		tbEmail.addFocusHandler(new FocusHandler(){

			@Override
			public void onFocus(FocusEvent event) {
				tbEmail.setInvalid(false);
				
			}});
		//phone text box:
		tbPhone.addFocusHandler(new FocusHandler(){

			@Override
			public void onFocus(FocusEvent event) {
				tbPhone.setInvalid(false);
				
			}});
		/*
		btnSend.addTapHandler(new TapHandler(){

			@Override
			public void onTap(TapEvent event) {
				validate();
				//logger.log(Level.INFO, "send button clicked");
				
			}});
		*/
		btnReset.addTapHandler(new TapHandler(){

			@Override
			public void onTap(TapEvent event) {
				resetForm(true);
				
			}});
		countryListBox = listBoxCountries.getListBox();
		for(int i=0;i<countryListBox.getItemCount();i++){
			if(countryListBox.getValue(i).equals("India")){
				countryListBox.setSelectedIndex(i);
				break;
			}
		}
		headerView.setButtonRightLabel("Home");
		headerView.setHeaderTitleText("Ayuroma Centre");
		scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

		//chkCOAReport.setTitle("check");
		//progressIndicator.setVisible(true);
	}
	/*
	@UiHandler("sendButton")
	public void onSendButtonClick(ClickEvent e) {
		
		logger.log(Level.INFO, "send button clicked");
	}
	*/

	@Override
	public boolean validate(){
		btnSend.setDisabled(true);
		btnReset.setDisabled(true);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		//create the request:
		ContactUsEnquiry enquiryData = new ContactUsEnquiry();
		
		//Data of the sender:
		SenderData sender = new SenderData();
		//set the data:
		String name=tbName.getText();
		String email=tbEmail.getText();
		
		
		
		String message=taMessage.getText();
		
		//String code=tbCode.getText();
		String companyName=tbCompanyName.getText();
		
		String cityName=tbCity.getText();
		
		//String countryName=countryListBox.getItemText(countryListBox.getSelectedIndex());
		String telephone=tbPhone.getText();
		removeFocus();
		
		//String mobile=tbMobile.getText();
		//String mobileCountryCode=tbCallCode.getText();
		//String isdCode=tbCallCodePhone.getText();
		//String phoneAreaCode= tbAreaCodePhone.getText();
		//response:
		///String response =captchaWidget.getResponse();
		//filling the sender data:
		sender.setName(name);
		sender.setEmail(email);
		sender.setCompanyName(companyName);
		sender.setCityName(cityName);
		//sender.setCountry(countryName);
		sender.setPhone(telephone);
		//sender.setMobile(phone);
		//sender.setAreaCodeMobile(mobileCountryCode);
		//sender.setAreaCodePhone(phoneAreaCode);
		//sender.setIsdCode(isdCode);
		//STEP 1: validating the sender:
		boolean isValid=isSenderValid(sender);
		boolean isValid2=false;
		//STEP 2: validating the request:
		//this can be of three types:

		//viewId 0 productEnquiry, 1 contact us enquiry, 2 product reports
		if(viewId==0){
			EnquiryData data = new EnquiryData();
			data.setMessage(message);
			isValid2=isEnquiryValid(data);
		}else if(viewId==1){
			EnquiryData data = new EnquiryData();
			data.setMessage(message);
			isValid2=isEnquiryValid(data);
					
		}else if(viewId==2){
			ProductReportsData reportsData = new ProductReportsData() ;
			
			if(chkCOAReport.getValue()) reportsData.setIsReportSelected("yes");
			if(chkMSDSReport.getValue()) reportsData.setIsReportSelected("yes");
			if(chkGCMSReport.getValue()) reportsData.setIsReportSelected("yes");
			isValid2=isProductReportsValid(reportsData);
					
		} 
	
		if(!isValid || !isValid2){
			btnSend.setDisabled(false);
			btnReset.setDisabled(false);
			return false;
		}
		return true;
	}
	private boolean isSenderValid(SenderData sender){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<SenderData>> violations = validator.validate(sender);
		if (!violations.isEmpty()) {
			// StringBuffer errorMessage = new StringBuffer();
			 String fieldName="";
			 String errorMessage="";
		      for (ConstraintViolation<SenderData> constraintViolation : violations) {
		       // if (errorMessage.length() == 0) {
		          //errorMessage.append('\n');
		        //}
		        //errorMessage.append(constraintViolation.getPropertyPath().toString());
		       
		        //errorMessage.append(":");
		       // errorMessage.append(constraintViolation.getMessage());
		        //errorMessage.append('\n');
		        fieldName=constraintViolation.getPropertyPath().toString();
		        errorMessage = constraintViolation.getMessage();
		        if(fieldName.equals("name")){
		        	//lblNameMsg.setText(errorMessage);
		        }else if(fieldName.equals("email")){
		        	tbEmail.setInvalid(true);
		        	//lblEmailMsg.setText(errorMessage);
		        }else if(fieldName.equals("companyName")){
		        	//lblCompanyNameMessage.setText(errorMessage);
		        }else if(fieldName.equals("cityName")){
		        	//lblCityMessage.setText(errorMessage);
		        }else if(fieldName.equals("mobile")){
		        	//lblMobileMessage.setText(errorMessage);
		        }else if(fieldName.equals("phone")){
		        	tbPhone.setInvalid(true);
		        	//lblPhoneMessage.setText(errorMessage);
		        }else if(fieldName.equals("areaCodeMobile")){
		        	//lblMobileMessage.setText(errorMessage); 
		        }else if(fieldName.equals("areaCodePhone")){
		        	//lblPhoneMessage.setText(errorMessage);
		        }else if(fieldName.equals("isdCode")){
		        	//lblPhoneMessage.setText(errorMessage);
		        }else{
		        	//System.out.println("error field:"+fieldName+", val:"+errorMessage);
		        }
		        
		        //lblCompanyNameMessage,lblCountryNameMessage,lblPhoneMessage,lblMobileMessage		        
		      }
		    // System.out.println(errorMessage.toString());
		    return false;
			 
		}
		return true;
	}
	private boolean isProductReportsValid(ProductReportsData reportsData){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ProductReportsData>> violations = validator.validate(reportsData);
		if (!violations.isEmpty()) {
			 String fieldName="";
			 String errorMessage="";
			 for (ConstraintViolation<ProductReportsData> constraintViolation : violations) {
				 fieldName=constraintViolation.getPropertyPath().toString();
			     errorMessage = constraintViolation.getMessage();
			     if(fieldName.equals("isReportSelected")){
			    	 //lblReportsMessage.setText(errorMessage);
			     }
			 }
			 return false;
		}
		return true;
	}
	private boolean isEnquiryValid(EnquiryData data){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<EnquiryData>> violations = validator.validate(data);
		if (!violations.isEmpty()) {
			 String fieldName="";
			 //String errorMessage="";
			 for (ConstraintViolation<EnquiryData> constraintViolation : violations) {
				 fieldName=constraintViolation.getPropertyPath().toString();
			     //errorMessage = constraintViolation.getMessage();
			     if(fieldName.equals("message")){
			    	 //lblMessageMsg.setText(errorMessage);
			     }
			 }
			 return false;
		}
		return true;		
	}
	@Override
	public HasTapHandlers getSendButton() {

		return btnSend;
	}
	@Override
	public String getNameUser() {

		return tbName.getText();
	}
	@Override
	public String getEmailUser() {

		return tbEmail.getText();
	}
	@Override
	public String getNameCompany() {

		return tbCompanyName.getText();
	}
	@Override
	public String getNameCity() {

		return tbCity.getText();
	}
	@Override
	public String getNameCountry() {
		String countryName=countryListBox.getItemText(countryListBox.getSelectedIndex());
		return countryName;
	}
	@Override
	public String getPhoneNumber() {

		return tbPhone.getText();
	}
	@Override
	public String getMessageText() {
		
		return taMessage.getText();
	}
	@Override
	public String getHoneyPotText() {

		return tbHidden.getText();
	}
	@Override
	public boolean getChkCOAReportValue(){
		return chkCOAReport.getValue();
	}
	@Override
	public boolean getChkMSDSReportValue(){
		
		return chkMSDSReport.getValue();
	}
	
	@Override
	public boolean getChkGCMSReportValue(){
		return chkGCMSReport.getValue();
	}
	@Override
	public void info(String msg, int id, int type) {
		switch(type){
		case 0:	//success
			//here we get the messages from the server:
			////ajaxMessage.setStyleName(style.ajaxMessage());
			//on success the server sends the success message: 'enquiry sent'
			//ajaxMessage.setText(msg);
			break;
		case 1://error rpc
			//ajaxMessage.setStyleName(style.ajaxMessage());
			//ajaxMessage.setText(msg);

			break;
		}
		btnSend.setDisabled(false);
		btnReset.setDisabled(false);
		
	}
	private void resetForm(boolean isClearAll){
					
		removeFocus();
		//now text boxes:
		if(isClearAll){
			tbName.setText("");//1
			tbEmail.setText("");//2
			taMessage.setText("");//3
			//tbCode.setText("");//4
			tbCompanyName.setText("");//5
			tbCity.setText("");//6 
			tbPhone.setText("");//7
		}
		//tbName.setFocus(true);
		
	}
	private void removeFocus(){
		tbName.setFocus(false);
		tbEmail.setFocus(false);
		taMessage.setFocus(false);
		tbCompanyName.setFocus(false);
		tbCity.setFocus(false);
		tbPhone.setFocus(false);
	}//removeFocus()
	@Override
	public HasTapHandlers getHeaderRightButton() {

		return headerView.getButtonRight();
	}
	@Override
	public void showProgressIndicator(boolean isVisible){
		progressIndicator.setVisible(isVisible);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showProductEnquiryView(String productName) {
		lblHeading.setText("Product Enquiry Form");
		lblSubject.setText("Subject: Enquiry for the product "+productName);
		lblFormTitle.setText("Enquiry Form");
		//show the message text area
		frmEntryMessage.setVisible(true);
		
		frmEntryGCMSReport.setVisible(false);
		frmEntryMSDSReport.setVisible(false);
		frmEntryCOAReport.setVisible(false);
		viewId=0;
		
	}

	@Override
	public void showGetProductReportsView(String productName) {
		lblHeading.setText("Product Report Request Form");
		lblSubject.setText("Subject: Request for the reports of the product "+productName);
		//show the check boxes for the three reports
		frmEntryMessage.setVisible(false);
		
		frmEntryGCMSReport.setVisible(true);
		frmEntryMSDSReport.setVisible(true);
		frmEntryCOAReport.setVisible(true);
		viewId=2;
	}

	@Override
	public void showContactUsView() {
		lblHeading.setText("");
		lblSubject.setText("");
		lblFormTitle.setText("Enquiry Form");
		//lblSubject.setText("Subject: Enquiry");
		//show the message text area
		frmEntryMessage.setVisible(true);
		
		frmEntryGCMSReport.setVisible(false);
		frmEntryMSDSReport.setVisible(false);
		frmEntryCOAReport.setVisible(false);
		viewId=1;
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCompanyInfo(String companyHead, List<String> addressLines,
			String city, String pin, String country, List<String> mobiles,
			List<String> phones, List<String> emails) {
		// TODO Auto-generated method stub
		
	}



}
