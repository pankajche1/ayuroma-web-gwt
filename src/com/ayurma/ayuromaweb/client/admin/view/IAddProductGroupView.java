package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.Widget;

public interface IAddProductGroupView {
	public interface Presenter{
		void gotoPlace(int id);
		void onAddProductGroupButtonClicked(String name,String description,String imageUrl);
		
	}
	void setPresenter(Presenter presenter);
	Widget asWidget();
	void info(String message,int id);
	void reset();

}
