package com.ayurma.ayuromaweb.client.mobile.view;


import com.ayurma.ayuromaweb.client.view.IEnquiryView;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface IEnquiryMobileView extends IEnquiryView{
	public void setTitle(String text);

	public HasTapHandlers getSendButton();

	public boolean validate();

	//public HasText getNameTextBox();

	public String getNameUser();

	
	public String getEmailUser() ;

	public String getNameCompany(); 


	public String getNameCity(); 

	public String getNameCountry(); 
	public String getPhoneNumber(); 

	public String getMessageText();

	public String getHoneyPotText();

	public void info(String msg, int id, int type);

	public void showProgressIndicator(boolean isVisible);

	HasTapHandlers getHeaderRightButton();

	boolean getChkCOAReportValue();

	boolean getChkMSDSReportValue();

	boolean getChkGCMSReportValue();
	

}
