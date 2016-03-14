package com.ayurma.ayuromaweb.client.mobile.view;

import com.ayurma.ayuromaweb.client.view.IProductDetailsView;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface IProductDetailsMobileView<T> extends IProductDetailsView<T> {

	HasTapHandlers getHeaderRightButton();

	void setHeaderTitle(String text);

	HasTapHandlers getButtonSendEnquiry();

	HasTapHandlers getButtonGetReports();

}
