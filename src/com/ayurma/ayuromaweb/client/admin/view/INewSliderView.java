package com.ayurma.ayuromaweb.client.admin.view;


import java.util.List;

import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;

public interface INewSliderView {
	public interface Presenter{

		void startSliderImageBrowser();

		void removeSelectedImage(int id);

		void startProductsBrowser();

		void selectImage(int id);

		void saveFilm();
		
	}

	void setPresenter(Presenter presenter);
	AcceptsOneWidget getRightDataPanel();
	Widget asWidget();
	void showSelectedImages(List<SliderImageDTO> images);
	
	void showLinkedProduct(String productName, int idItem);
}
