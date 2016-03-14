package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;

public interface IEditProductGroupView {
	public interface Presenter{
		void gotoPlace(int id);

		void updateProductGroup(String text, String html, int i);

		void showImageBrowser();
		
	}

	void setPresenter(Presenter presenter);
	Widget asWidget();
	void reset();
	void setData(String name, String dscrp);
	void info(String string, int id);
	void setProductGroupImage(String string);
	AcceptsOneWidget getExtraPanel();
	void setImageBrowser();
	void removeImageBrowser();


}
