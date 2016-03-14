package com.ayurma.ayuromaweb.client.view;



import com.google.gwt.user.client.ui.Widget;

public interface ISearchView {
	public interface Presenter{
		
	}

	void setPresenter(Presenter presenter);
	void showAjaxAnim();
	void stopAjaxAnim();
	Widget asWidget();
}
