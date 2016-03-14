package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.Widget;

public interface IAddNewProductView {
	public interface Presenter{
		void gotoPlace(int id);
		void onAddProductButtonClicked(String name,String description);
	}

	void setPresenter(Presenter presenter);
	Widget asWidget();
	void showMessage(String message);
	void showAjaxLoading();
	void stopAjaxLoading();
	void info(String message,int id);
	void reset();
}
