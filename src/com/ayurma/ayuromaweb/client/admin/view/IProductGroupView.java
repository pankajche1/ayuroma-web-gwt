package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.Widget;

public interface IProductGroupView {
	public interface Presenter{
		void gotoPlace(int id);
		
	}

	void setPresenter(Presenter presenter);
	Widget asWidget();

}
