package com.ayurma.ayuromaweb.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


import com.ayurma.ayuromaweb.client.view.widgets.CountryNamesView;
import com.ayurma.ayuromaweb.shared.validation.ContactUsEnquiry;
import com.ayurma.ayuromaweb.shared.validation.EnquiryData;
import com.ayurma.ayuromaweb.shared.validation.ProductReportsData;
import com.ayurma.ayuromaweb.shared.validation.SenderData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.Validation;

public class EnquiryViewImpl extends Composite implements IEnquiryView{

	private static EnquiryViewImplUiBinder uiBinder = GWT
			.create(EnquiryViewImplUiBinder.class);

	interface EnquiryViewImplUiBinder extends UiBinder<Widget, EnquiryViewImpl> {
	}
	public interface MyStyle extends CssResource{
		String ajaxMessage();
		String ajaxMessageOff();
		
	}
	private Presenter presenter;
	
	public EnquiryViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//country data for phone calling code data:
		countriesData=new CountriesData() ;
		//set the country calling codes etc:
		countryListBox=countryList.getListBox();
		countryListBox.getElement().getStyle().setWidth(408, Style.Unit.PX);
		countryListBox.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				ListBox lb = (ListBox)event.getSource();
				String code = countriesData.getCode(lb.getValue(lb.getSelectedIndex()));
				if(code!=null){
					tbCallCodePhone.setText("+"+code);
					tbCallCode.setText("+"+code);
				}else{
					tbCallCodePhone.setText("");
					tbCallCode.setText("");
				}
				//System.out.println("selected index:"+lb.getSelectedIndex()+", value:"+lb.getValue(lb.getSelectedIndex()));
				
				
			}});
		for(int i=0;i<countryListBox.getItemCount();i++){
			if(countryListBox.getValue(i).equals("India")){
				countryListBox.setSelectedIndex(i);
				break;
			}
		}
		String code = countriesData.getCode(countryListBox.getValue(countryListBox.getSelectedIndex()));
		if(code!=null){
			tbCallCodePhone.setText("+"+code);
			tbCallCode.setText("+"+code);
		}else{
			tbCallCodePhone.setText("");
			tbCallCode.setText("");
		}
		//enable the buttons:
		btnSubmit.setEnabled(true);
		btnResetForm.setEnabled(true);

	}
	@UiField Label lblHeading,lblSubject;
	@UiField
	HTMLPanel messagePanel;
	@UiField
	HTMLPanel form;
	@UiField
	Button btnSubmit,btnResetForm;
	@UiField
	HTMLPanel productReportsPanel, messageTextAreaPanel;
	@UiField
	TextBox tbName;
	@UiField TextBox tbHidden;//for preventing spam bot
	@UiField
	TextBox tbEmail,tbCompanyName, tbCity ,tbTelephone, tbMobile,tbCallCode,tbCallCodePhone,
							  tbAreaCodePhone;
	@UiField
	TextArea taMessage;
	//@UiField
	//InlineLabel lblCaptcha;
	//@UiField
	//TextBox tbCode;
	
	@UiField
	InlineLabel lblNameMsg;
	@UiField
	InlineLabel lblEmailMsg;
	@UiField
	InlineLabel lblMessageMsg;
	//@UiField
	//InlineLabel lblCodMsg;
	@UiField
	InlineLabel lblCompanyNameMessage,lblCityMessage,lblPhoneMessage,lblMobileMessage;
	@UiField InlineLabel lblReportsMessage;
	@UiField
	InlineLabel ajaxMessage;
	//@UiField
	//Button btnResetCaptcha;
	String nameProduct;
	ListBox countryListBox;
	@UiField
	CountryNamesView countryList;
	//@UiField HTMLPanel recaptchaPanel;
	CountriesData countriesData;
	@UiField MyStyle style;
	@UiField CheckBox chk0,chk1,chk2;
	@UiField CompanyInfoViewImpl companyInfo;
	private int viewId;//which type of view 0 Product Enquiry 1 Contact us 2 product reports
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@Override
	public void setCompanyInfo(String companyHead,List<String> addressLines,
			String city, String pin, String country,
			List<String> mobiles, List<String> phones,List<String> emails){
		//setting the company info:
		companyInfo.setCompanyInfo(companyHead, addressLines, city, pin, country, mobiles, phones, emails);
		
	}
	@Override 
	public void showProductEnquiryView(String productName){

		lblHeading.setText("Product Enquiry Form");
		lblSubject.setText("Subject: Enquiry for the product "+productName);
		//show the message text area
		messageTextAreaPanel.setVisible(true);
		productReportsPanel.setVisible(false);
		viewId=0;
	}
	@Override
	public void showGetProductReportsView(String productName) {
		lblHeading.setText("Product Report Request Form");
		lblSubject.setText("Subject: Request for the reports of the product "+productName);
		//show the check boxes for the three reports
		messageTextAreaPanel.setVisible(false);
		productReportsPanel.setVisible(true);
		viewId=2;
		
	}
	@Override
	public void showContactUsView(){
		lblHeading.setText("Enquiry Form");
		lblSubject.setText("Subject: Enquiry");
		//show the message text area
		messageTextAreaPanel.setVisible(true);
		productReportsPanel.setVisible(false);
		viewId=1;
	}
	@Override
	public void scrollTo(int left,int top){
		Window.scrollTo(left, top);
	}
	private void validate(){
		btnSubmit.setEnabled(false);
		//btnResetCaptcha.setEnabled(false);
		btnResetForm.setEnabled(false);
		
		//lblCaptchaAjax.setStyleName(style.ajaxMessageOff());
		//lblCaptchaAjax.setText("");				
		ajaxMessage.setStyleName(style.ajaxMessageOff());
		ajaxMessage.setText("");

		lblNameMsg.setText("");//1
		lblEmailMsg.setText("");//2
		lblMessageMsg.setText("");//3
		//lblCodMsg.setText("");//4
		lblCompanyNameMessage.setText("");//5A
		lblCityMessage.setText("");//5B
		
		//lblCountryNameMessage.setText("");//6
		lblPhoneMessage.setText("");//7
		lblMobileMessage.setText("");//8
		//product reports message:
		lblReportsMessage.setText("");
		//to give the data to the presenter:
		List<String> dataList = new ArrayList<String>();
		//validation:
		ListBox lb;
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
		String countryName=countryListBox.getItemText(countryListBox.getSelectedIndex());
		String telephone=tbTelephone.getText();
		String mobile=tbMobile.getText();
		String mobileCountryCode=tbCallCode.getText();
		String isdCode=tbCallCodePhone.getText();
		String phoneAreaCode= tbAreaCodePhone.getText();
		//response:
		///String response =captchaWidget.getResponse();
				
				 
		//System.out.println("country:"+countryName+"\ntelephone:"+telephone+"\n");
		
		//filling the sender data:
		sender.setName(name);
		sender.setEmail(email);
		sender.setCompanyName(companyName);
		sender.setCityName(cityName);
		//sender.setCountry(countryName);
		sender.setPhone(telephone);
		sender.setMobile(mobile);
		sender.setAreaCodeMobile(mobileCountryCode);
		sender.setAreaCodePhone(phoneAreaCode);
		sender.setIsdCode(isdCode);
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
			if(chk0.getValue()) reportsData.setIsReportSelected("yes");
			if(chk1.getValue()) reportsData.setIsReportSelected("yes");
			if(chk2.getValue()) reportsData.setIsReportSelected("yes");
			isValid2=isProductReportsValid(reportsData);
			
		} 
		if(!isValid || !isValid2){
		     ajaxMessage.setStyleName(style.ajaxMessage());
			 ajaxMessage.setText("Wrong entry in the form");
		     btnSubmit.setEnabled(true);
		    // btnResetCaptcha.setEnabled(true);
		     btnResetForm.setEnabled(true);

			return;
		}
		
		ajaxMessage.setStyleName(style.ajaxMessage());
		ajaxMessage.setText("Please wait...");
		if(viewId==0){
			//product enquiry:
			presenter.submitProductEnquiry(name, email, companyName,
					cityName, countryName, 
					mobileCountryCode, isdCode, phoneAreaCode,
					mobile, telephone, message,tbHidden.getText());
		}else if(viewId==1){
			//contact us general enquiry:
			presenter.submitEnquiry(name, email, companyName,
					cityName, countryName, 
					mobileCountryCode, isdCode, phoneAreaCode,
					mobile, telephone, message,tbHidden.getText());			
		}else if(viewId==2){
			//product reports request:
			presenter.submitProductReportsRequest(name, email, companyName,
					cityName, countryName, 
					mobileCountryCode, isdCode, phoneAreaCode,
					mobile, telephone,
					chk0.getValue(),chk1.getValue(),chk2.getValue(),tbHidden.getText());
		}
		
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
		        	lblNameMsg.setText(errorMessage);
		        }else if(fieldName.equals("email")){
		        	lblEmailMsg.setText(errorMessage);
		        }else if(fieldName.equals("companyName")){
		        	lblCompanyNameMessage.setText(errorMessage);
		        }else if(fieldName.equals("cityName")){
		        	lblCityMessage.setText(errorMessage);
		        }else if(fieldName.equals("mobile")){
		        	lblMobileMessage.setText(errorMessage);
		        }else if(fieldName.equals("phone")){
		        	lblPhoneMessage.setText(errorMessage);
		        }else if(fieldName.equals("areaCodeMobile")){
		        	lblMobileMessage.setText(errorMessage); 
		        }else if(fieldName.equals("areaCodePhone")){
		        	lblPhoneMessage.setText(errorMessage);
		        }else if(fieldName.equals("isdCode")){
		        	lblPhoneMessage.setText(errorMessage);
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
			    	 lblReportsMessage.setText(errorMessage);
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
			 String errorMessage="";
			 for (ConstraintViolation<EnquiryData> constraintViolation : violations) {
				 fieldName=constraintViolation.getPropertyPath().toString();
			     errorMessage = constraintViolation.getMessage();
			     if(fieldName.equals("message")){
			    	 lblMessageMsg.setText(errorMessage);
			     }
			 }
			 return false;
		}
		return true;		
	}
	@UiHandler("btnSubmit")
	public void onSubmitButtonClick(ClickEvent e) {
		validate();
	}
	@UiHandler("btnResetForm")
	public void onResetButtonClick(ClickEvent e) {
		resetForm(true);
		
		
	}
	@Override
	public void reset(){
		//reset the controls:
		btnSubmit.setEnabled(false);
		//btnResetCaptcha.setEnabled(false);
		btnResetForm.setEnabled(false);
		resetForm(false);
		
		//setting a captcha:
		//setCaptcha();
		//now make the submit button enabled
		btnSubmit.setEnabled(true);
		//btnResetCaptcha.setEnabled(true);
		btnResetForm.setEnabled(true);
	}
	private void resetForm(boolean isClearAll){
		//lblCaptchaAjax.setStyleName(style.ajaxMessageOff());
		//lblCaptchaAjax.setText("");				
		ajaxMessage.setStyleName(style.ajaxMessageOff());
		ajaxMessage.setText("");

		lblNameMsg.setText("");//1
		lblEmailMsg.setText("");//2
		lblMessageMsg.setText("");//3
		//lblCodMsg.setText("");//4
		lblCompanyNameMessage.setText("");//5A
		lblCityMessage.setText("");//5B
		
		//lblCountryNameMessage.setText("");//6
		lblPhoneMessage.setText("");//7
		lblMobileMessage.setText("");//8
		//sending reports checkboxes message;
		
		//now text boxes:
		if(isClearAll){
			tbName.setText("");//1
			tbEmail.setText("");//2
			taMessage.setText("");//3
			//tbCode.setText("");//4
			tbCompanyName.setText("");//5
			tbCity.setText("");//6 
			tbTelephone.setText("");//7
			tbMobile.setText("");//8
			//tbCallCode.setText("");//9
			//tbCallCodePhone.setText("");//10
			tbAreaCodePhone.setText("");//11
		}
		tbName.setFocus(true);
		if(viewId==0){
			taMessage.setText("");
		}else if(viewId==1){
			taMessage.setText("");
		}else if(viewId==2){
			 lblReportsMessage.setText("");
			//uncheck the check boxes:
			chk0.setValue(false);
			chk1.setValue(false);
			chk2.setValue(false);
		}
	}
	@Override
	public void info(String msg, int id, int type) {
		switch(type){
		case 0:	//success
			//here we get the messages from the server:
			ajaxMessage.setStyleName(style.ajaxMessage());
			//on success the server sends the success message: 'enquiry sent'
			ajaxMessage.setText(msg);
			break;
		case 1://error rpc
			ajaxMessage.setStyleName(style.ajaxMessage());
			ajaxMessage.setText(msg);

			break;
		}
		btnSubmit.setEnabled(true);
		btnResetForm.setEnabled(true);
		
	}

}
