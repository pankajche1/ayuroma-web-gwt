package com.ayurma.ayuromaweb.client.view;


import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public interface ILayout1View {
	Widget asWidget();

	HasWidgets get(String id);

	void scrollTo(int left, int top);
}
