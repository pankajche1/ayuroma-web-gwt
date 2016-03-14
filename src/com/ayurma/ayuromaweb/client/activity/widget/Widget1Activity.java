package com.ayurma.ayuromaweb.client.activity.widget;

import com.ayurma.ayuromaweb.client.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.view.widgets.IWidget1View;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class Widget1Activity implements IWidget1View.Presenter{
	private IWidget1View view;
	private PlaceController placeController;
	private Provider<ProductGroupPlace> productGroupPlaceProvider;
	private String key;
	@Inject
	public Widget1Activity(IWidget1View view,PlaceController placeController,
			Provider<ProductGroupPlace> productGroupPlaceProvider){
		
		this.placeController=placeController;
		this.view=view;
		this.view.setPresenter(this);
		this.productGroupPlaceProvider=productGroupPlaceProvider;
	}
	public void go(HasWidgets containter){
		containter.add(view.asWidget());
	}
	@Override
	public void setHeading(String heading) {
		view.setHeading(heading);
		
	}
	@Override
	public void setBody(String body) {
		view.setContentText(body);
		
	}
	@Override
	public void setKey(String key) {
		this.key=key;
		
	}
	@Override
	public void visit() {
		ProductGroupPlace place= productGroupPlaceProvider.get();
		
		place.setPlaceName(key);
		placeController.goTo(place);
		
	}
}
