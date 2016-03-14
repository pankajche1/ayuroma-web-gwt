package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.Widget;

public interface IHomeView {
	public interface Presenter{
		
	}

	void setPresenter(Presenter presenter);
	//void showAjaxAnim();
	//void stopAjaxAnim();
	Widget asWidget();
}
