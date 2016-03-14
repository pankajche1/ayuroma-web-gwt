package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.Widget;

public interface ISliderView {
	public interface Presenter{
		void createNewSlider();
		void browseSliders();
		void uploadSliderImage();
		void browseSliderImages();
		void showEditSliderView();
	}

	void setPresenter(Presenter presenter);
	Widget asWidget();
}
