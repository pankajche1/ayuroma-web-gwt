package com.ayurma.ayuromaweb.client.activity;

public interface IEnquiryActivity {
	void info(String msg, int id, int type);
	void stopAjaxAnim();
	void startAjaxAnim();
	void showContactUsView();
	void showProductEnquiryView(String strKey);
	void showGetProductReportsView(String strKey);
	int getViewMode();
	void setViewMode(int viewMode);

}
