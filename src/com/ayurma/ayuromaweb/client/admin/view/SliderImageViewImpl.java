package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.activity.ISliderImageBrowserConnector;
import com.ayurma.ayuromaweb.client.admin.activity.SliderImagesBrowseActivity;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SliderImageViewImpl extends Composite implements ISliderImageView{

	private static SliderImageViewImplUiBinder uiBinder = GWT
			.create(SliderImageViewImplUiBinder.class);

	interface SliderImageViewImplUiBinder extends
			UiBinder<Widget, SliderImageViewImpl> {
	}
	private Presenter presenter;
	private ISliderImageBrowserConnector imgDataReceiver;
	private int displayMode=0;
	@UiField Button  btnLoadImages;
	@UiField Button btnExportImages;
	@UiField TextBox txtNumPage,txtItemsPerPage;
	@UiField InlineLabel lblMessage;
	@UiField HTMLPanel dataPanel;
	@UiField HTMLPanel exportButtonPanel;
	@UiField HTMLPanel bottomBtnsPanel;
	@UiField Button btnCancelBrowser,btnTakeImage;
	private List<SliderImageUnitImpl> items=new ArrayList<SliderImageUnitImpl>();
	private List<HasClickHandlers> items2=new ArrayList<HasClickHandlers>();
	private int iSelected=-1;
	List<CheckBox> checkBoxesAdd=new ArrayList<CheckBox>();
	
	public SliderImageViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	@Override
	public void init(int displayMode) {
		//here we need to set the desired layout of the browser:
		this.displayMode=displayMode;
		switch(displayMode){
		case SliderImagesBrowseActivity.STANDALONE:
			bottomBtnsPanel.setVisible(false);
			break;
		case SliderImagesBrowseActivity.MULTISELECTION_DIALOG:
			bottomBtnsPanel.setVisible(true);
			break;
		case SliderImagesBrowseActivity.MOUSECLICK_SINGLESELECTION_DIALOG:
			bottomBtnsPanel.setVisible(true);
			break;
		}
		
	}
	@Override
	public void info(String message, int id) {
		lblMessage.setText(message);
		
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@Override
	public void setDataPresenter(ISliderImageBrowserConnector imgDataReceiver){
		this.imgDataReceiver=imgDataReceiver;
	}
	@UiHandler("btnLoadImages")
	void onLoadImagesClick(ClickEvent e){
		presenter.loadSliderImages(txtNumPage.getText(), txtItemsPerPage.getText());
	}
	@UiHandler("btnCancelBrowser")
	void onCancelBrowser(ClickEvent e){
		imgDataReceiver.cancelImageBrowser();
	}
	@UiHandler("btnClearCacheImages")
	void onClearCacheBtnClick(ClickEvent e){
		presenter.clearImageCacheData();
		
	}
	/*
	 * this button is the ok button at the bottom
	 * of this image browser
	 */
	@UiHandler("btnTakeImage")
	void onTakeImageClick(ClickEvent e){
		
		switch(displayMode){
		case SliderImagesBrowseActivity.MOUSECLICK_SINGLESELECTION_DIALOG:
			imgDataReceiver.getSelectedImage(iSelected);
			break;
		case SliderImagesBrowseActivity.MULTISELECTION_DIALOG:
			
			imgDataReceiver.onImageBrowserOkClick();
			break;
		}
		
	}
	@Override
	public void clear(){
		dataPanel.clear();
		lblMessage.setText("");
	}
	@Override
	public void showData(List<SliderImageDTO> images,int displayMode){
		switch(displayMode){
		case SliderImagesBrowseActivity.STANDALONE:
			//here a delete button will be shown at the bottom of an image unit:
			displayImages1(images,displayMode);
			break;
		case SliderImagesBrowseActivity.MULTISELECTION_DIALOG://with a check on the image
			//here a check box will be shown on every image unit but no delete button will be shown
			displayImages4(images,displayMode);
			//displayImages2(images);
			break;
		case SliderImagesBrowseActivity.MOUSECLICK_SINGLESELECTION_DIALOG://for creating single selection image browser
			//for selecting the image by mouse click
			displayImages3(images,displayMode);
			break;
		//case SliderImagesBrowseActivity.MULTISELECTION_DIALOG://for creating a multiselect image browser:
			//displayImages4(images,displayMode);
		//	break;
		}

		
	}
	/*
	 * shows 
	 */
	private void displayImages1(List<SliderImageDTO> images,int displayMode){
		dataPanel.clear();
		lblMessage.setText("");
		SliderImageUnitImpl imagePanel;
		exportButtonPanel.setVisible(false);
		for(int i=0;i<images.size();i++){
			imagePanel=new SliderImageUnitImpl();
			dataPanel.add(imagePanel);
			imagePanel.setDisplayMode(displayMode);
			imagePanel.setPresenter(presenter);
			imagePanel.setImageUrl("ayuromaweb3/serveImage?blob-key=",images.get(i).getImageKey());
			imagePanel.setImageInfo(images.get(i).getImageInfo());
			imagePanel.setImageInfoKey(images.get(i).getKey());
		}		
	}
	/*
	 * putting a check box against each image unit:
	 * On the imageContainer:HTMLPanel two objects are added 
	 * (1) the image unit
	 * (2) the check box
	 */
	private void displayImages2(List<SliderImageDTO> images){
		dataPanel.clear();
		lblMessage.setText("");
		SliderImageUnitImpl imagePanel;
		checkBoxesAdd=new ArrayList<CheckBox>();
		CheckBox chk;
		HTMLPanel imageContainer;
		exportButtonPanel.setVisible(true);
		for(int i=0;i<images.size();i++){
			imageContainer=new HTMLPanel("");
			imagePanel=new SliderImageUnitImpl();
			imageContainer.add(imagePanel);
			
			imagePanel.setPresenter(presenter);
			imagePanel.setImageUrl("ayuromaweb3/serveImage?blob-key=",images.get(i).getImageKey());
			imagePanel.setImageInfo(images.get(i).getImageInfo());
			imagePanel.setImageInfoKey(images.get(i).getKey());
			chk=new CheckBox();
			checkBoxesAdd.add(chk);
			imageContainer.add(chk);
			dataPanel.add(imageContainer);
		}		
	}
	/*
	 * for creating single selection image browser
	 * by mouse click selection:
	 */
	private void displayImages3(List<SliderImageDTO> images,int displayMode){
		dataPanel.clear();
		SliderImageUnitImpl imagePanel;
		exportButtonPanel.setVisible(false);
		items=new ArrayList<SliderImageUnitImpl>();
		items2=new ArrayList<HasClickHandlers>();
		SelectionHandler selectionHandler = new SelectionHandler();
		for(int i=0;i<images.size();i++){
			imagePanel=new SliderImageUnitImpl();
			dataPanel.add(imagePanel);
			items.add(imagePanel);
			
			imagePanel.setPresenter(presenter);
			imagePanel.setDisplayMode(displayMode);
			imagePanel.setImageUrl("ayuromaweb3/serveImage?blob-key=",images.get(i).getImageKey());
			imagePanel.setImageInfo(images.get(i).getImageInfo());
			imagePanel.setImageInfoKey(images.get(i).getKey());
			items2.add(imagePanel.getSelectionPanel());
			imagePanel.getSelectionPanel().addClickHandler(selectionHandler);
		}	
	}
	/*
	 * this methods presenters the images in multi select browser mode
	 * with a check box at the bottom of every image
	 */
	private void displayImages4(List<SliderImageDTO> images,int displayMode){
		dataPanel.clear();
		SliderImageUnitImpl imagePanel;
		exportButtonPanel.setVisible(false);
		items=new ArrayList<SliderImageUnitImpl>();
		//items2=new ArrayList<HasClickHandlers>();
		//SelectionHandler selectionHandler = new SelectionHandler();
		for(int i=0;i<images.size();i++){
			///System.out.println("SliderImageView:: displayImage4()... displayMode="+displayMode);
			imagePanel=new SliderImageUnitImpl(i);
			dataPanel.add(imagePanel);
			items.add(imagePanel);
			
			imagePanel.setPresenter(presenter);
			imagePanel.setDisplayMode(displayMode);
			imagePanel.setImageUrl("ayuromaweb3/serveImage?blob-key=",images.get(i).getImageKey());
			imagePanel.setImageInfo(images.get(i).getImageInfo());
			imagePanel.setImageInfoKey(images.get(i).getKey());
			//items2.add(imagePanel.getSelectionPanel());
			//imagePanel.getSelectionPanel().addClickHandler(selectionHandler);
		}	
	}
	@Override
	public HasClickHandlers getExportButton() {
	   return btnExportImages;
	}
	@Override
	public List<Boolean> getSelectedImages(){
		List<Boolean> listValues = new ArrayList<Boolean>();
		for(CheckBox chk:checkBoxesAdd){
			listValues.add(chk.getValue());
		}
		return listValues;
	}
	@Override
	public void setDisplayMode(int displayMode){
		this.displayMode=displayMode;
		switch(this.displayMode){
		case 0:
			
			break;
		case 1:
			
			break;
		case 2://present the view in form of a dialog box for selecting the image:
			
			break;
		}		
	}
	private class SelectionHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			HasClickHandlers item = (HasClickHandlers) event.getSource();
			if(iSelected>=0) items.get(iSelected).setSelected(false);
			iSelected=items2.indexOf(item);
			items.get(iSelected).setSelected(true);
			
			
		}
		
	}

	

}
