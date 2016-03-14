package com.ayurma.ayuromaweb.client.admin.view;


import java.util.List;

import com.ayurma.ayuromaweb.client.admin.view.widgets.IAddressesPanel;
import com.google.gwt.user.client.ui.Widget;

public interface IAddNewEmployeeView {
	public interface Presenter{
		void gotoPlace(int id);
		void onAddEmployeeButtonClicked(String name,String designation,
				List<String> lstMobiles,
				List<String> lstPhones,
				List<String> lstEmails);
	}

	void setPresenter(Presenter presenter);
	Widget asWidget();
	void showMessage(String message);
	void showAjaxLoading();
	void stopAjaxLoading();
	void info(String message,int id);
	IAddressesPanel getAddressesPanel();
	void reset();
}
