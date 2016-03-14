package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.Widget;

public interface IEmployeesView {
	public interface Presenter{
		void gotoPlace(int id);
	}

	void setPresenter(Presenter presenter);
	//void showAjaxAnim();
	//void stopAjaxAnim();
	Widget asWidget();

}
