package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;

public interface IEditProductView {
	public interface Presenter{

		void onBtnSaveClick(String name, String description, Boolean isInStock);

		void getClipBoardText();

		void updateImage();

		void showImageBrowser();
		
	}
	void setPresenter(Presenter presenter);
	Widget asWidget();
	void reset();
	
	void info(String string, int id);

	void setImage(String url);
	void setDataToEdit(String name, String description, String strProductId,
			boolean isInStock);
	void setImageBrowser();
	void removeImageBrowser();
	AcceptsOneWidget getExtraPanel();

}
