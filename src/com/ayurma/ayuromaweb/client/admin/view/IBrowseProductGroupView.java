package com.ayurma.ayuromaweb.client.admin.view;


import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface IBrowseProductGroupView {
	public interface Presenter{
		void gotoPlace(int id);
		void loadProductGroupsList(String strIndexPg,String strItemsPerPage);
		void editProductGroup(int id);
		void addRemProducts(int id);
	}

	void setPresenter(Presenter presenter);
	void info(String message,int id);
	void showGroupsList(List<String> names,List<String> sn);
	void reset();
	//void showAjaxAnim();
	//void stopAjaxAnim();
	Widget asWidget();

}
