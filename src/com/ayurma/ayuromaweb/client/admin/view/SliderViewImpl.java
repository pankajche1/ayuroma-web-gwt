package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;

import com.google.gwt.uibinder.client.UiHandler;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SliderViewImpl extends Composite implements ISliderView{

	private static SliderViewImplUiBinder uiBinder = GWT
			.create(SliderViewImplUiBinder.class);

	interface SliderViewImplUiBinder extends UiBinder<Widget, SliderViewImpl> {
	}
	private Presenter presenter;

	@UiHandler("btnCreateNew")
	public void createNew(ClickEvent event){
		presenter.createNewSlider();
	}
	@UiHandler("btnUploadSliderImages")
	public void uploadSliderImages(ClickEvent event){
		presenter.uploadSliderImage();
	}
	@UiHandler("btnBrowseSlider")
	public void browseSliders(ClickEvent event){
		presenter.browseSliders();
	}
	
	@UiHandler("btnBrowseSliderImages")
	public void browseSliderImages(ClickEvent event){
		presenter.browseSliderImages();
	}
	public SliderViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

}
