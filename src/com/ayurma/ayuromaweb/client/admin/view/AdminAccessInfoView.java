package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AdminAccessInfoView extends Composite {

	private static AdminAccessInfoViewUiBinder uiBinder = GWT
			.create(AdminAccessInfoViewUiBinder.class);

	interface AdminAccessInfoViewUiBinder extends
			UiBinder<Widget, AdminAccessInfoView> {
	}
	@UiField
	Label lblEmail;
	public AdminAccessInfoView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public void setEmail(String email){
		lblEmail.setText(email);
	}

}
