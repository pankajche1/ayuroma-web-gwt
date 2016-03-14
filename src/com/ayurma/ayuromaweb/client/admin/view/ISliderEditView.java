package com.ayurma.ayuromaweb.client.admin.view;


import java.util.List;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;

public interface ISliderEditView {
	public interface Presenter{

		void onDragEnd(int widgetIndex);

		void onDragStart(int widgetIndex);

		void updateFilm();

		void changeImage(int id);

		//void startSliderImageBrowser();

		//void startProductBrowser();

		void changeLink(int iSelectedItem);

		void onAddImagesClicked();

		void removeItem(int id);
		
	}
	void setPresenter(Presenter presenter);
	Widget asWidget();
	
	
	void info(String string, int id);
	void showFilmItems(List<String> imageUrls, List<String> names);
	void showImageBrowser();
	AcceptsOneWidget getExternalPanel();
	//void clearLeftDataPanel();
	void reset(int code);
	void clearExternalPanel();
	
}
