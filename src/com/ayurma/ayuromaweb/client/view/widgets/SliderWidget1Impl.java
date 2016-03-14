package com.ayurma.ayuromaweb.client.view.widgets;

import java.util.List;

import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.gmail.pankajche1.contentslider.client.ContentSlider2;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class SliderWidget1Impl extends Composite implements ISliderWidget1{

	private static SliderWidget1ImplUiBinder uiBinder = GWT
			.create(SliderWidget1ImplUiBinder.class);

	interface SliderWidget1ImplUiBinder extends
			UiBinder<Widget, SliderWidget1Impl> {
	}
	private Presenter presenter;
	@UiField HTMLPanel rootPanel;
	private ContentSlider2 slider;
	public SliderWidget1Impl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@Override
	public void setSliderFilm(SliderFilmDTO sliderFilm){
		List<String> imgUrls=sliderFilm.getImageUrls();
		//System.out.println("key link inside widget:");
		//List<String> linkedItemsUrls=sliderFilm.getLinkedItemsUrls();
		//for(String str:linkedItemsUrls){
			//System.out.println("key link:"+str);
		//}
		slider = new ContentSlider2(980,215,215,215,8,imgUrls,sliderFilm.getLinkedItemsUrls());
		rootPanel.add(slider);
		slider.start();
			
	}
	@Override
	public boolean isFilmSet(){
		if(slider==null) return false;
		else return true;
	}
	@Override
	public void onLoad(){
		//slider.start();
	}
	@Override
	public void onUnload(){
		//slider.
	}

}
