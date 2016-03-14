package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;


import com.ayurma.ayuromaweb.client.admin.activity.BrowseImagesActivity;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class BrowseImagesViewImpl extends Composite implements IBrowseImagesView{
	
	private static BrowseImagesViewImplUiBinder uiBinder = GWT
			.create(BrowseImagesViewImplUiBinder.class);

	interface BrowseImagesViewImplUiBinder extends
			UiBinder<Widget, BrowseImagesViewImpl> {
	}
	interface MyCss extends CssResource{
		String selectedItem();
		String defaultItem();
	}
	private Presenter presenter;
	private IDataLinker dataLinker;
	//private int displayMode=0;//0 for independent browser, 1 for select one image dialog box
	@UiField Button  btnLoadImages;
	@UiField TextBox txtNumPage,txtItemsPerPage;
	@UiField InlineLabel lblMessage;
	@UiField HTMLPanel dataPanel,noImagesToShowPanel,bottomPanel;
	@UiField MyCss style;
	private List<HasClickHandlers> listSelectables;
	private List<ImageUnitViewImpl> listWidgets;
	private int iSelected=-1;
	public BrowseImagesViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	@Override
	public void info(String message, int id) {
		lblMessage.setText(message);
		
	}
	@UiHandler("btnLoadImages")
	void onLoadImagesClick(ClickEvent e){
		presenter.loadImages(txtNumPage.getText(), txtItemsPerPage.getText());
	}
	@UiHandler("btnOk")
	void onOkBtnClick(ClickEvent e){
		if(iSelected>=0) dataLinker.onOkClick(iSelected);

	}
	@UiHandler("btnCancel")
	void onCancelBtnClick(ClickEvent e){
		dataLinker.onCancelClick();
	}
	
	@UiHandler("btnClearCacheImages")
	void onClearCacheBtnClick(ClickEvent e){
		presenter.clearImageCacheData();
		
	}
	@Override
	public void showData(List<ImageDataDTO> images,int displayMode){
		dataPanel.clear();
		noImagesToShowPanel.setVisible(false);
		lblMessage.setText("");
		switch(displayMode){
		case BrowseImagesActivity.STANDALONE://show default view
			showDefaultView(images);
			break;
		case BrowseImagesActivity.SINGLESELECTION_DIALOG://show single image seletable dialog box view
			showSingleImageSelectionView(images);
			break;
		}
		


		
	}
	private void showDefaultView(List<ImageDataDTO> images){
		if(images.isEmpty()) noImagesToShowPanel.setVisible(true);
		for(int i=0;i<images.size();i++){
			ImageUnitViewImpl imagePanel=new ImageUnitViewImpl(presenter.getDisplayMode());
			dataPanel.add(imagePanel);
			imagePanel.setMainPresenter(presenter);
			imagePanel.setImageUrl("ayuromaweb3/serveImage?blob-key=",images.get(i).getImageKey());
			imagePanel.setImageInfo(images.get(i).getImageInfo());
			imagePanel.setImageInfoKey(images.get(i).getKey());
		}		
	}
	private void showSingleImageSelectionView(List<ImageDataDTO> images){
		listSelectables = new ArrayList<HasClickHandlers>() ;
		listWidgets = new ArrayList<ImageUnitViewImpl>(); 
		MyClickHandler hl = new MyClickHandler();
		if(images.isEmpty()) noImagesToShowPanel.setVisible(true);
		for(int i=0;i<images.size();i++){
			ImageUnitViewImpl imagePanel=new ImageUnitViewImpl(presenter.getDisplayMode());
			HasClickHandlers h = imagePanel.getSelectablePanel();
			listSelectables.add(h);
			listWidgets.add(imagePanel);
			h.addClickHandler(hl);
			dataPanel.add(imagePanel);
			imagePanel.setMainPresenter(presenter);
			imagePanel.setImageUrl("ayuromaweb3/serveImage?blob-key=",images.get(i).getImageKey());
			imagePanel.setImageInfo(images.get(i).getImageInfo());
			imagePanel.setImageInfoKey(images.get(i).getKey());
		}			
	}
	
	@Override
	public void setDisplayMode(int displayMode) {
		//this.displayMode = displayMode;
	}
	private class MyClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			HasClickHandlers hl = (HasClickHandlers) event.getSource();
			//deselecting the present item:
			if(iSelected>=0){
				listWidgets.get(iSelected).removeStyleName(style.selectedItem());
			}	
			iSelected = listSelectables.indexOf(hl);
			listWidgets.get(iSelected).addStyleName(style.selectedItem());
			
		}
		
	}
	@Override
	public void setDataLinker(IDataLinker linker) {
		this.dataLinker=linker;
		
	}
	@Override
	public void clear(){
		noImagesToShowPanel.setVisible(true);
		dataPanel.clear();
	}

	@Override
	public void init(int displayMode) {
		switch(displayMode){
		case BrowseImagesActivity.STANDALONE:
			bottomPanel.setVisible(false);
			break;
		case BrowseImagesActivity.MULTISELECTION_DIALOG:
			bottomPanel.setVisible(true);
			break;
		case BrowseImagesActivity.SINGLESELECTION_DIALOG:
			bottomPanel.setVisible(true);
			break;
		}
		
	}



}
