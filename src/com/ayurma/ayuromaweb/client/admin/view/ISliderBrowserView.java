package com.ayurma.ayuromaweb.client.admin.view;


import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface ISliderBrowserView {
	public interface Presenter{

		void loadFilms(int nPage, int itemsPerPage);

		void deleteFilm(int iFilm);

		void displayFilm(int id);

		

		void showEditSliderView(int idSlider);
		
	}

	void setPresenter(Presenter presenter);
	Widget asWidget();
	
	void setFilms(List<String> titles, List<String> datesCreation,
			List<String> datesEdit);
	void clear();
	void info(String message, int type);
}
