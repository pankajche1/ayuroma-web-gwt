package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class NewSliderViewImpl extends Composite implements INewSliderView{

	private static NewSliderViewImplUiBinder uiBinder = GWT
			.create(NewSliderViewImplUiBinder.class);

	interface NewSliderViewImplUiBinder extends
			UiBinder<Widget, NewSliderViewImpl> {
	}
	interface MyCss extends CssResource{
		String selectedImage();
		String normalImage();
	}
	
	private Presenter presenter;
	private SliderImageViewImpl sliderImageBrowser;
	@UiField
	HTMLPanel leftPanel,rightPanel,selectedImagesPanel;
	@UiField
	SimplePanel rightDataPanel;
	@UiField MyCss style;
	
	private List<ImageUnit> imageUnits;
	private ImageUnit selectedImageUnit;
	public NewSliderViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnSaveFilm")
	public void saveFilm(ClickEvent e){
		presenter.saveFilm();
	}
	@UiHandler("btnRemoveImage")
	public void removeImage(ClickEvent e){
		if(selectedImageUnit!=null){
			presenter.removeSelectedImage(selectedImageUnit.getId());
		}
	}
	@UiHandler("btnShowImagesBrowser")
	public void showImageBrowser(ClickEvent e){
		rightDataPanel.clear();
		presenter.startSliderImageBrowser();
		/*
		//clear the already existing data on the panel:
		rightDataPanel.clear();
		//show the image browser:
		if(sliderImageBrowser==null){
			sliderImageBrowser = new SliderImageViewImpl();
		}
		rightDataPanel.add(sliderImageBrowser);
		*/
	}
	
	@UiHandler("btnShowProductsBrowser")
	public void showProductsBrowser(ClickEvent e){
		rightDataPanel.clear();
		presenter.startProductsBrowser();
	}	
	@Override
	public AcceptsOneWidget getRightDataPanel() {
		
		return rightDataPanel;
	}
	@Override
	public void showSelectedImages(List<SliderImageDTO> images){
		//SliderImageUnitImpl imagePanel;
		selectedImagesPanel.clear();
		imageUnits=new ArrayList<ImageUnit>();
		ImageUnit imageUnit;
		for(int i=0;i<images.size();i++){
			imageUnit=new ImageUnit(i,"ayuromaweb3/serveImage?blob-key="+images.get(i).getImageKey());
			imageUnits.add(imageUnit);
			//imagePanel.setPresenter(presenter);
			//imagePanel.setImageUrl("ayuromaweb3/serveImage?blob-key=",images.get(i).getImageKey());
			//imagePanel.setImageInfo(images.get(i).getImageInfo());
			//imagePanel.setImageInfoKey(images.get(i).getKey());
			selectedImagesPanel.add(imageUnit);
			
			
		}	
	}
	@Override
	public void showLinkedProduct(String productName,int idItem){
		if(imageUnits.size()>idItem){
			imageUnits.get(idItem).setLinkedProductName(productName);
		}
	}
	private class ImageUnit extends FocusPanel implements ClickHandler{
		private String imageUrl;
		private int id;
		private HTMLPanel rootPanel;
		private Label lblLinkedProduct;
		public ImageUnit(int id,String imageUrl){
			
			this.imageUrl=imageUrl;
			this.id=id;
			Image img = new Image(imageUrl);
			img.setAltText("Image");
			img.setWidth("200px");
			rootPanel=new HTMLPanel("");
			rootPanel.add(img);
			//label for showing the name of the linked product:
			lblLinkedProduct = new Label();
			rootPanel.add(lblLinkedProduct);
			add(rootPanel);
			this.setStyleName(style.normalImage());
			this.addClickHandler(this);
		}
		@Override
		public void onClick(ClickEvent event) {
			
			for(ImageUnit item:imageUnits){
				item.setStyleName(style.normalImage());
			}
			this.setStyleName(style.selectedImage());
			selectedImageUnit=this;
			presenter.selectImage(id);
		}
		public int getId(){
			return this.id;
		}
		public void setLinkedProductName(String name){
			lblLinkedProduct.setText(name);
		}
	}

}
