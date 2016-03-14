package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddProductGroupViewImpl extends Composite implements IAddProductGroupView{

	private static AddProductGroupViewImplUiBinder uiBinder = GWT
			.create(AddProductGroupViewImplUiBinder.class);

	interface AddProductGroupViewImplUiBinder extends
			UiBinder<Widget, AddProductGroupViewImpl> {
	}
	@UiField
	Button btnSave;
	@UiField
	Button btnReset;
	@UiField
	TextBox txtName;
	@UiField
	TextArea taDescription;
	//@UiField
	//TextBox txtImageUrl;
	@UiField
	Label lblMessage;
	@UiField
	HTMLPanel rootPanel;
	private Presenter presenter;
	public AddProductGroupViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnSave")
	void onSaveButtonClick(ClickEvent e) {
		btnSave.setEnabled(false);
		if(presenter!=null) presenter.onAddProductGroupButtonClicked(
							txtName.getText(), taDescription.getText(),
							"");
	}

	@UiHandler("btnReset")
	void onResetButtonClick(ClickEvent e) {
		lblMessage.setText("");
		btnSave.setEnabled(true);
		txtName.setText("");
		taDescription.setText("");
	}
	@Override
	public void info(String message, int id) {
		lblMessage.setText(message);
		
	}

	@Override
	public void reset() {
		lblMessage.setText("");
		btnSave.setEnabled(true);
		txtName.setText("");
		taDescription.setText("");
		
		
	}

}
