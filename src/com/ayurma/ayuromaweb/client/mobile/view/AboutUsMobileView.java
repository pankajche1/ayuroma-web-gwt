package com.ayurma.ayuromaweb.client.mobile.view;

import com.ayurma.ayuromaweb.client.mobile.view.header.HeaderMobileView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;

public class AboutUsMobileView extends Composite implements IAboutUsMobileView{

	private static AboutUsViewUiBinder uiBinder = GWT
			.create(AboutUsViewUiBinder.class);

	interface AboutUsViewUiBinder extends UiBinder<Widget, AboutUsMobileView> {
	}
	
	
	@UiField protected HeaderMobileView headerView;
	
	public AboutUsMobileView() {
		initWidget(uiBinder.createAndBindUi(this));
		headerView.setButtonRightLabel("Home");
		headerView.setHeaderTitleText("Ayuroma Centre");
	}

	@Override
	public void info(String msg, int id, int type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showProgressIndicator(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HasTapHandlers getHeaderRightButton() {

		return headerView.getButtonRight();
	}

}
