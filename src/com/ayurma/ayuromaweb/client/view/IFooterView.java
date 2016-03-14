package com.ayurma.ayuromaweb.client.view;


import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface IFooterView {
	public interface Presenter{

		

		void gotoPlace(String token);
		
	}
	void setPresenter(Presenter presenter);
	Widget asWidget();

	

	void setAddress(String addressHead, List<String> addressLines,
			List<String> mobiles, List<String> phones, List<String> emails,
			String city, String pin, String country);
}
