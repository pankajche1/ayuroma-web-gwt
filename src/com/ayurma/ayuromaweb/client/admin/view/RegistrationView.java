package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RegistrationView extends Composite implements IRegistrationView{

	private static RegistrationViewUiBinder uiBinder = GWT
			.create(RegistrationViewUiBinder.class);

	interface RegistrationViewUiBinder extends
			UiBinder<Widget, RegistrationView> {
	}
	private Presenter presenter;
	@UiField
	InlineLabel lblMessage;
	@UiField
	Label lblEmail;
	@UiField TextBox tbHidden;//for preventing spam bot
	public RegistrationView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	@UiHandler("btnRegister")
	public void onBtnRegisterClick(ClickEvent event){
		presenter.registerUser(tbHidden.getText());
	}
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	@Override
	public void info(String message, int id) {
		lblMessage.setText(message);
		
	}
	@Override
	public void showData(String email){
		lblEmail.setText(email);
	}
	@Override
	public void clear(){
		lblEmail.setText("");
		lblMessage.setText("");
	}

}
