package com.ayurma.ayuromaweb.client.admin.view;


import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface IMenu {
	public interface Presenter{
		void gotoPlace(int id);
	}

	void setPresenter(Presenter presenter);
	Widget asWidget();
	void createMenu(List<String> menus);
}
