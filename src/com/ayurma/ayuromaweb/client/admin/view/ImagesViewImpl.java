package com.ayurma.ayuromaweb.client.admin.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.admin.activity.BrowseImagesActivity;
import com.ayurma.ayuromaweb.client.admin.activity.ImagesViewActivity;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ImagesViewImpl extends Composite implements IImagesView{

	private static ImagesViewImplUiBinder uiBinder = GWT
			.create(ImagesViewImplUiBinder.class);

	interface ImagesViewImplUiBinder extends UiBinder<Widget, ImagesViewImpl> {
	}
	private Presenter presenter;
	public ImagesViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	@UiField SimplePanel contentPanel;
	@UiField HTMLPanel mainPanel;
	//@UiField Label lblSectionHead;
	@Override
	public void init(){
		
	}
	@Override
	public void updateLayout(int displayMode){
		switch(displayMode){
		case ImagesViewActivity.IMAGEBROWSER_STANDALONE:
			
			mainPanel.setVisible(false);
			break;
		case ImagesViewActivity.UPLOADIMAGE_LAYOUT:
			mainPanel.setVisible(false);
			break;
		case ImagesViewActivity.DEFAULT_LAYOUT:
			//removing if some images browser view is present:
			contentPanel.clear();
			mainPanel.setVisible(true);
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
	@UiHandler("btnUploadImage")
	public void showUploadImageView(ClickEvent e){
		presenter.showView(0);
	}
	
	@UiHandler("btnBrowseImages")
	public void showBrowseImageView(ClickEvent e){
		presenter.showView(1);
	}
	@Override
	public void setImageData(List<ImageDataDTO> images) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setImageUploadUrl(String url) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public AcceptsOneWidget get(String id){
		if(id.equals("content-panel")) return contentPanel;
		else return null;
	}

}
