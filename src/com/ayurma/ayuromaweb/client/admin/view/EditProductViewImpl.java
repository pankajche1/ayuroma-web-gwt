package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.pankajche1.rtt.client.RichTextEditor;

public class EditProductViewImpl extends Composite implements IEditProductView{

	private static EditProductViewImplUiBinder uiBinder = GWT
			.create(EditProductViewImplUiBinder.class);

	interface EditProductViewImplUiBinder extends
			UiBinder<Widget, EditProductViewImpl> {
	}
	private Presenter presenter;

	@UiField
	HTMLPanel dataPanel,rta1,defaultPanel;
	@UiField SimplePanel extraPanel;
	@UiField HTML noImagePanel;
	@UiField
	Button btnSave;
	@UiField Button btnLinkImage,btnUpdateImage;
	//@UiField
	//TextArea taDescription;
	@UiField
	TextBox txtName;
	@UiField
	CheckBox chk0;
	@UiField
	InlineLabel lblMessage;
	@UiField Label lblImageMessage;
	@UiField
	Label lblMainMessage;//this is main message at the top of the content
	RichTextEditor dscrpEditor;
    @UiField
    HTMLPanel imagePanel;
	public EditProductViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		 dscrpEditor=new RichTextEditor("100%","500px");
		 rta1.add(dscrpEditor);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnSave")
	void onBtnSaveClick(ClickEvent e){
		if(presenter!=null) presenter.onBtnSaveClick(txtName.getText(),
				dscrpEditor.getHtml(),chk0.getValue());

	}
	@UiHandler("btnUpdateImage")
	void onBtnUpdateImageClick(ClickEvent e){
       //presenter.onBtnSaveClick("", "", true,1);
       presenter.updateImage();
	}
	@Override
	public void reset() {
		//showing the default panel:
		defaultPanel.setVisible(true);
		extraPanel.clear();
		//reset the view to default:
		btnLinkImage.setText("Link Image");
		
		//clear the image panel
		imagePanel.clear();
		//adding a no image indicator panel:
		imagePanel.add(noImagePanel);
		//resetting all the message labels:
		lblMainMessage.setText("");
		lblMessage.setText("");
		lblImageMessage.setText("");
		//reset the text data:
		txtName.setText("Loading...");
		dscrpEditor.setHtml("");
		
	}

	@Override
	public void info(String message, int id) {
		switch(id){
		case 0:
			lblMainMessage.setText(message);
			break;
		case 1:
			lblMessage.setText(message);
			break;
		case 2:
			lblImageMessage.setText(message);
			break;
		}
	}
	@Override
	public void setDataToEdit(String name, String description,
			String strKeyProduct,boolean isInStock) {
		//upto now the datapanel was not visible so make it visible:
		dataPanel.setVisible(true);
		
		//now set the data:
		txtName.setText(name);
		//taDescription.setText(description);
		//rta0.setHTML(description);
		dscrpEditor.setHtml(description);
		//System.out.println("view isInStock:"+isInStock);
		chk0.setValue(isInStock);
		//this.strKeyProduct=strKeyProduct;
		/*
		if(imageUrl.equals("")){
			btnLinkImage.setText("Link Image");
			//it means there is no image attached to this product
		}else{
			btnLinkImage.setText("Change Image");
			//imgProduct.setUrl("ayuromaweb/serveImage?blob-key="+imageUrl);
		}
		*/
		//txtImageUrl.setText(imageUrl);
		
	}
	@Override
	public void setImage(String url){
		btnLinkImage.setText("Change Image");
		imagePanel.clear();
		//noImagePanel.removeFromParent();
		Image img = new Image(url);
		
		imagePanel.add(img);

		//imagePanel.getElement().setInnerHTML("<img src='"+url+"' alt='image' width='200px'></img>");
	}
	@UiHandler("btnLinkImage")
	void onBtnLinkImageClick(ClickEvent e){
     	presenter.showImageBrowser();
	}
	@Override
	public void setImageBrowser() {
		defaultPanel.setVisible(false);
		
	}
	@Override
	public void removeImageBrowser() {
		defaultPanel.setVisible(true);
		extraPanel.clear();
		
	}
	@Override
	public AcceptsOneWidget getExtraPanel(){
		return extraPanel;
	}

}
