package com.ayurma.ayuromaweb.client.mobile.view.header;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;

public class HeaderMobileView2 extends Composite {

	private static HeaderMobileView2UiBinder uiBinder = GWT
			.create(HeaderMobileView2UiBinder.class);

	interface HeaderMobileView2UiBinder extends
			UiBinder<Widget, HeaderMobileView2> {
	}
	//@UiField protected Button btnRight;
	//@UiField protected HeaderTitle headerTitle;
	public HeaderMobileView2() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public void setButtonRightLabel(String label){
		//btnRight.setText(label);
	}
	public void setHeaderTitleText(String title){
		//headerTitle.setText(title);
	}
	public HasTapHandlers getButtonRight() {

		//return btnRight;
		return new btnRightMock();
	}
	public void setButtonRightVisible(boolean isVisible){
		//btnRight.setVisible(isVisible);
	}
	private class btnRightMock implements HasTapHandlers{

		@Override
		public HandlerRegistration addTapHandler(TapHandler handler) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
