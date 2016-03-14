package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.Widget;

public interface IToolsView {
	public interface Presenter{

		void createAppData();
		
	}
	void setPresenter(Presenter presenter);
	Widget asWidget();
	
	
	void info(String string, int id);
}
