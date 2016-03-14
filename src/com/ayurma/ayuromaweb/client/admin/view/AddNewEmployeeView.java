package com.ayurma.ayuromaweb.client.admin.view;

import com.ayurma.ayuromaweb.client.admin.view.widgets.AddTextBoxWidget;
import com.ayurma.ayuromaweb.client.admin.view.widgets.AddressesPanel;
import com.ayurma.ayuromaweb.client.admin.view.widgets.IAddressesPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddNewEmployeeView extends Composite implements IAddNewEmployeeView {

	private static AddNewEmployeeViewUiBinder uiBinder = GWT
			.create(AddNewEmployeeViewUiBinder.class);
	private IAddNewEmployeeView.Presenter presenter;
	@UiField
	Button btnAdd,btnReset;
	@UiField
	TextBox txtName;
	@UiField
	TextBox txtDesignation;
	@UiField
	InlineLabel lblMessage;
	@UiField
	AddTextBoxWidget mobilesWidget, phonesWidget, emailsWidget;
	@UiField
	AddressesPanel addressesPanel;
	interface AddNewEmployeeViewUiBinder extends
			UiBinder<Widget, AddNewEmployeeView> {
	}

	public AddNewEmployeeView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void showAjaxLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAjaxLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String message, int id) {
		lblMessage.setText(message);
		
	}

	@Override
	public void reset() {
		btnAdd.setEnabled(true);
		txtName.setText("");
		txtDesignation.setText("");
		lblMessage.setText("");
		mobilesWidget.reset();
		phonesWidget.reset();
		emailsWidget.reset();
		addressesPanel.reset();
		
		
	}
	@UiHandler("btnAdd")
	void onClick(ClickEvent e) {
		btnAdd.setEnabled(false);
		if(presenter!=null) presenter.onAddEmployeeButtonClicked(txtName.getText(),
				txtDesignation.getText(), mobilesWidget.getValues(),
				phonesWidget.getValues(),
				emailsWidget.getValues());
		//addProduct();
	}
	@UiHandler("btnReset")
	void onClickReset(ClickEvent e) {
		
		reset();
		
	}

	@Override
	public IAddressesPanel getAddressesPanel() {
		
		return addressesPanel;
	}

	
}
