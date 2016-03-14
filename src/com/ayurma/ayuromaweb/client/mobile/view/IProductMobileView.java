package com.ayurma.ayuromaweb.client.mobile.view;



import com.ayurma.ayuromaweb.client.view.IProductView;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;


public interface IProductMobileView<T> extends IProductView<T>{

	public void setHeaderTitle(String text);

	public HasTapHandlers getHeaderRightButton();
	public HasTapHandlers getButtonSendEnquiry();
	public HasTapHandlers getButtonGetReports();
	public HasTapHandlers getButtonShowDetails();

	
}
