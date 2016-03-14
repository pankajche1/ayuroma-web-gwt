package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
//import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddNewProductViewImpl extends Composite implements IAddNewProductView{

	private static AddNewProductViewImplUiBinder uiBinder = GWT
			.create(AddNewProductViewImplUiBinder.class);

	interface AddNewProductViewImplUiBinder extends
			UiBinder<Widget, AddNewProductViewImpl> {
	}
	private Presenter presenter;
	@UiField
	Button btnAdd,btnReset;
	@UiField
	HTMLPanel rootPanel;
	@UiField
	TextBox txtName;
	@UiField
	TextArea taDescription;
	@UiField
	InlineLabel lblMessage;

	public AddNewProductViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnAdd")
	void onClick(ClickEvent e) {
		btnAdd.setEnabled(false);
		if(presenter!=null) presenter.onAddProductButtonClicked(txtName.getText(),
				taDescription.getText());
		//addProduct();
	}
	@UiHandler("btnReset")
	void onClickReset(ClickEvent e) {
		
		reset();
		
	}
	@Override
	public void showMessage(String message) {
		//rootPanel.clear();
		//rootPanel.add(new HTML("<h2 style='font-family:arial;'>"+message+"</h2>"));
		lblMessage.setText(message);
	}
	@Override
	public void showAjaxLoading() {
		//rootPanel.add(imgLoading);
		
	}
	@Override
	public void stopAjaxLoading() {
		//imgLoading.removeFromParent();
		
	}
	//@Override
	//public void setUploadImageFormAction(String url) {
		//formImageUpload.setAction(url);
		//formImageUpload.setMethod(FormPanel.METHOD_POST);
		//formImageUpload.setEncoding(FormPanel.ENCODING_MULTIPART);

	@Override
	public void info(String message, int id) {
		lblMessage.setText(message);
		
	}

	@Override
	public void reset() {
		btnAdd.setEnabled(true);
		txtName.setText("");
		taDescription.setText("");
		lblMessage.setText("");
		
	}


}
