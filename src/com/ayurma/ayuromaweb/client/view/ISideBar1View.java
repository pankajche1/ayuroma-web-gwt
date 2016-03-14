package com.ayurma.ayuromaweb.client.view;


import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public interface ISideBar1View {
	public interface Presenter{
		HasWidgets get();
	}

	void setPresenter(Presenter presenter);
	
	Widget asWidget();
	HasWidgets get();
}
