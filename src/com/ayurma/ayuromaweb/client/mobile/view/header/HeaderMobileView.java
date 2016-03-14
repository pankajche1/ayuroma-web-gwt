package com.ayurma.ayuromaweb.client.mobile.view.header;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;


public class HeaderMobileView extends Composite {

	private static HeaderMobileViewUiBinder uiBinder = GWT
			.create(HeaderMobileViewUiBinder.class);

	interface HeaderMobileViewUiBinder extends
			UiBinder<Widget, HeaderMobileView> {
	}
	@UiField protected Button btnRight;
	@UiField protected HeaderTitle headerTitle;
	public HeaderMobileView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}
	public void setButtonRightLabel(String label){
		btnRight.setText(label);
	}
	public void setHeaderTitleText(String title){
		headerTitle.setText(title);
	}
	public HasTapHandlers getButtonRight() {

		return btnRight;
	}
	public void setButtonRightVisible(boolean isVisible){
		btnRight.setVisible(isVisible);
	}

}
