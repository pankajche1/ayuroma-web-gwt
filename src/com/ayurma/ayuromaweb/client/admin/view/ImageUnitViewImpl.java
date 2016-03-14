package com.ayurma.ayuromaweb.client.admin.view;



import com.ayurma.ayuromaweb.client.admin.activity.BrowseImagesActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImageUnitViewImpl extends Composite implements IImageUnitView{

	private static ImageUnitViewImplUiBinder uiBinder = GWT
			.create(ImageUnitViewImplUiBinder.class);

	interface ImageUnitViewImplUiBinder extends
			UiBinder<Widget, ImageUnitViewImpl> {
	}
	private Presenter presenter;
	private IBrowseImagesView.Presenter mainPresenter;
	@UiField Button btnDelete;
	@UiField HTMLPanel imagePanel,bottomPanel;
	@UiField Label lblImageInfo;
	@UiField FocusPanel rootPanel;
	private String imageUrl;
	private Long imageInfoKey;
	
	public ImageUnitViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public ImageUnitViewImpl(int displayMode) {
		initWidget(uiBinder.createAndBindUi(this));
		switch(displayMode){
		case BrowseImagesActivity.STANDALONE:
			
			break;
		case BrowseImagesActivity.SINGLESELECTION_DIALOG://for single image selection dialog box mode
			//the bottom panel contains the delete button
			bottomPanel.setVisible(false);
			break;
		case BrowseImagesActivity.MULTISELECTION_DIALOG:
			break;
		}
	}
	@Override
	public void info(String message, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@Override
	public void setMainPresenter(IBrowseImagesView.Presenter mainPresenter){
		this.mainPresenter=mainPresenter;
	}
	public void setImageUrl(String servletUrl,String ImageUrl){
		imageUrl=ImageUrl;
		imagePanel.getElement().setInnerHTML("<img src='"+servletUrl+ImageUrl+"' alt='image' width='200px'></img>");
	}
	/*
	@UiHandler("btnCopy")
	void onCopy(ClickEvent event){
		mainPresenter.copyImageUrl(imageUrl);
	}
	*/
	@UiHandler("btnDelete")
	void onDelete(ClickEvent event){
		confirmDelete();
	}
	private void confirmDelete(){
		boolean option=Window.confirm("Do you want to delete the image?");
		if(option){
			mainPresenter.deleteImage(imageInfoKey);
			
		}
	}
	public void setImageInfoKey(Long imageInfoKey) {
		this.imageInfoKey = imageInfoKey;
	}
	public void setImageInfo(String info){
		lblImageInfo.setText(info);
	}
	public HasClickHandlers getSelectablePanel(){
		return rootPanel;
	}


}
