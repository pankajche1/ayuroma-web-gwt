package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;

public class UploadImageViewImpl extends Composite implements IUploadImageView {

	private static UploadImageViewImplUiBinder uiBinder = GWT
			.create(UploadImageViewImplUiBinder.class);

	interface UploadImageViewImplUiBinder extends
			UiBinder<Widget, UploadImageViewImpl> {
	}
	private Presenter presenter;
	@UiField
	Button btnGetImageUploadUrl;
	@UiField
	FormPanel formImageUpload;
	@UiField
	SubmitButton btnUploadImage;
	@UiField
	Label lblMessageImage;
	@UiField
	TextBox txtImageInfo,txtFolderName;
	@UiField FileUpload fileField;
	private final String IMAGE_FOLDER="image";

	public UploadImageViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//this is when the form submit is complete:
		formImageUpload.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler()
		  {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				lblMessageImage.setText("");
				String str=event.getResults();
				if(str!=null){
				}else{
				}
				btnGetImageUploadUrl.setVisible(true);
				formImageUpload.setVisible(false);
				//btnGetImageUploadUrl.setText("Change Image");
			}
		  });	
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	@Override
	public void info(String message, int id) {
		lblMessageImage.setText(message);
		
	}
	@Override
	public void setUploadForm(String url){

		formImageUpload.setAction(url);
		formImageUpload.setMethod(FormPanel.METHOD_POST);
		formImageUpload.setEncoding(FormPanel.ENCODING_MULTIPART);
		formImageUpload.setVisible(true);
		txtFolderName.setText(IMAGE_FOLDER);
		txtImageInfo.setName("imageInfo");
		btnGetImageUploadUrl.setVisible(false);
	}
	@UiHandler("btnUploadImage")
	void onClickUploadImage(ClickEvent e) {
		if(fileField.getFilename().isEmpty()){
			Window.alert("no file to upload!");
			
		}else{
			lblMessageImage.setText("Uploading image...");
			txtImageInfo.setValue(txtImageInfo.getText());
			formImageUpload.submit();
		}
		
	}
	/*
	 * this the new image button on the interface
	 */
	@UiHandler("btnGetImageUploadUrl")
	void onGetUploadImageUrl(ClickEvent e) {
		presenter.getImageUploadUrl();
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.ayurma.ayuromaweb.client.admin.view.IUploadImageView#setDefaultLayout()
	 * this method is for setting the default layout i.e. showing
	 * the new image button on the interface and no form for uploading the image
	 */
	@Override
	public void setDefaultLayout(){
		lblMessageImage.setText("");
		btnGetImageUploadUrl.setVisible(true);
		formImageUpload.setVisible(false);
		//txtImageInfo.setText("");
		formImageUpload.reset();
	}



}
