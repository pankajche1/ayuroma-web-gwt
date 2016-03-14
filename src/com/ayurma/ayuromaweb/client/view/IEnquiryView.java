package com.ayurma.ayuromaweb.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface IEnquiryView {
	public interface Presenter{

		void submitEnquiry(String senderName,String senderEmail,String companyName,String city,
				String country,String countryCallingCode,String isdCode,String areaCallingCode,
				String mobile,String phone,String message,String txtHoneyPot);
		void submitProductEnquiry(String senderName,String senderEmail,String companyName,String city,
				String country,String countryCallingCode,String isdCode,String areaCallingCode,
				String mobile,String phone,String message,String txtHoneyPot);
		
		void submitProductReportsRequest(String senderName,String senderEmail,String companyName,String city,
				String country,String countryCallingCode,String isdCode,String areaCallingCode,
				String mobile,String phone,boolean isRoport1,boolean isRoport2,boolean isRoport3,String txtHoneyPot);
		
	}

	void setPresenter(Presenter presenter);
	//void showAjaxAnim();
	//void stopAjaxAnim();
	Widget asWidget();
	void showProductEnquiryView(String productName);
	void showGetProductReportsView(String name);
	void showContactUsView();
	void reset();
	void info(String msg,int id,int type);
	void scrollTo(int left, int top);
	void setCompanyInfo(String companyHead, List<String> addressLines,
			String city, String pin, String country, List<String> mobiles,
			List<String> phones, List<String> emails);
	
	


}
