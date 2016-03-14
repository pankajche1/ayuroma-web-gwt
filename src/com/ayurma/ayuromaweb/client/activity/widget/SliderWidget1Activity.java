package com.ayurma.ayuromaweb.client.activity.widget;

import com.ayurma.ayuromaweb.client.view.widgets.ISliderWidget1;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class SliderWidget1Activity implements ISliderWidget1.Presenter{
  private ISliderWidget1 view;
  @Inject
  public SliderWidget1Activity(ISliderWidget1 view){
	  this.view=view;
	  this.view.setPresenter(this);
  }
 
  public void setSliderFilm(SliderFilmDTO sliderFilm) {
	view.setSliderFilm(sliderFilm);
	
  }
  public void go(HasWidgets container){
	  container.add(view.asWidget());
  }

@Override
public boolean isFilmSet() {
	
	return view.isFilmSet();
}
}
