package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.Widget;

public interface IRegistrationView {
	public interface Presenter{
		void registerUser(String textHoneyPot);
	}
	void setPresenter(Presenter presenter);
	void info(String message,int id);
	
	Widget asWidget();
	void clear();
	void showData(String email);

}
