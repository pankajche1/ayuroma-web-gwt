package com.ayurma.ayuromaweb.client.admin.view.widgets;

import java.util.List;



public interface IEditAddressView {
	public interface Presenter{
		void deleteAddress(Long keyAddress);
		void updateCity(String city, Long keyAddress);
		void updateState(String state, Long keyAddress);
		void updateCountry(String country, Long keyAddress);
		
		void updateAddressLines(List<String> lines, Long keyAddress);
	}
	void setPresenter(Presenter presenter);
	void info(String message,int id);
	void reset();
	
}
