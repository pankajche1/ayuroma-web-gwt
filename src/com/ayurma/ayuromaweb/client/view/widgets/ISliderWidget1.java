package com.ayurma.ayuromaweb.client.view.widgets;


import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.google.gwt.user.client.ui.Widget;

public interface ISliderWidget1 {
	public interface Presenter{
		boolean isFilmSet();
	}

	void setPresenter(Presenter presenter);

	Widget asWidget();

	void setSliderFilm(SliderFilmDTO sliderFilm);

	boolean isFilmSet();
}
