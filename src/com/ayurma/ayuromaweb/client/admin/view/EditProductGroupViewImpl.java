package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.pankajche1.rtt.client.RichTextEditor;

public class EditProductGroupViewImpl extends Composite 
	implements IEditProductGroupView{

	private static EditProductGroupViewImplUiBinder uiBinder = GWT
			.create(EditProductGroupViewImplUiBinder.class);

	interface EditProductGroupViewImplUiBinder extends
			UiBinder<Widget, EditProductGroupViewImpl> {
	}
	private Presenter presenter;
    @UiField
    TextBox txtName;
   // @UiField
   // TextArea taDescription;
    @UiField
    Button btnSave,btnUpdateImage,btnLinkImage;
    @UiField
    InlineLabel lblAjaxMessage,lblImageMessage;
    @UiField
    HTMLPanel imagePanel,imageRootPanel,mainPanel,rta;//rta means richTextEditor panel
    @UiField HTMLPanel dataPanel;
    @UiField SimplePanel panel1;
	private RichTextEditor editor;
	public EditProductGroupViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		editor=new RichTextEditor("100%","500px");
		 rta.add(editor);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@Override
	public void setData(String name,String dscrp){
		mainPanel.setVisible(true);
		//dataPanel.setVisible(true);
		panel1.clear();
		txtName.setText(name);
		//taDescription.setText(dscrp);
		editor.setHtml(dscrp);
	}
	@UiHandler("btnSave")
	void onBtnUpdateClick(ClickEvent e){
       presenter.updateProductGroup(txtName.getText(),
    		   editor.getHtml(),0);//id is 0 to update name and description
	}
	/*
	@UiHandler("btnPasteImage")
	void onBtnPasteImageClick(ClickEvent e){
       //presenter.getClipBoardText();
	}
	*/
	@UiHandler("btnUpdateImage")
	void onBtnUpdateImageClick(ClickEvent e){
       presenter.updateProductGroup("", "", 1);
	}
	@UiHandler("btnLinkImage")
	void onBtnLinkImageClick(ClickEvent e){
      presenter.showImageBrowser();
	}
	public void info(String message,int id){
		
		switch(id){
		case 0:lblAjaxMessage.setText(message);
			break;
		case 1:lblImageMessage.setText(message);
			break;
		
		}
	}
	@Override
	public void reset(){
		txtName.setText("");
		//taDescription.setText("");
		editor.setHtml("");
		lblAjaxMessage.setText("");
	}
	public void setImage(String url){
		
		imagePanel.clear();
		imagePanel.getElement().setInnerHTML("<img src='"+url+"' alt='image' width='200px'></img>");
	}

	@Override
	public void setProductGroupImage(String url) {
		imagePanel.clear();
		Image image = new Image(url);
		imagePanel.add(image);
		//cause an image has been set to change the button's text:
		btnLinkImage.setText("Change Image");
	}
	@Override
	public AcceptsOneWidget getExtraPanel(){
		return panel1;
	}

	@Override
	public void setImageBrowser() {
		mainPanel.setVisible(false);
		
		//imageRootPanel.setVisible(false);
		//dataPanel.setVisible(false);
		
	}

	@Override
	public void removeImageBrowser() {
		mainPanel.setVisible(true);
		//dataPanel.setVisible(true);
		//imageRootPanel.setVisible(true);
		panel1.clear();
		
	}
}
