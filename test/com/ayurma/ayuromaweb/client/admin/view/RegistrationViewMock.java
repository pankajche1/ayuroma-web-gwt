package com.ayurma.ayuromaweb.client.admin.view;

import com.ayurma.ayuromaweb.client.admin.view.IRegistrationView;
import com.google.gwt.user.client.ui.Widget;

public class RegistrationViewMock implements IRegistrationView{

	@Override
	public void setPresenter(Presenter presenter) {
		System.out.println("RegistrationViewMock::setPresenter()...");
		
	}

	@Override
	public void info(String message, int id) {
		System.out.println("RegistrationViewMock::info() message:"+message+", id:"+id);
		
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		System.out.println("RegistrationViewMock::clear()...");
		
	}

	@Override
	public void showData(String email) {
		// TODO Auto-generated method stub
		
	}

}
