package com.ayurma.ayuromaweb.client.mobile.view.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProductInfoWidget extends Composite {

	private static ProductInfoWidgetUiBinder uiBinder = GWT
			.create(ProductInfoWidgetUiBinder.class);

	interface ProductInfoWidgetUiBinder extends
			UiBinder<Widget, ProductInfoWidget> {
	}
	@UiField
	Label lblProductName;
	@UiField
	HTML productBody;
	//@UiField protected ProgressIndicator progressIndicator;
	//@UiField protected HTMLPanel dataPanel;
	public ProductInfoWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public void setProductName(String productName){
		lblProductName.setText(productName);
		
	}
	public void setProductBody(String htmlBody){
		productBody.setHTML(htmlBody);
	}

}
