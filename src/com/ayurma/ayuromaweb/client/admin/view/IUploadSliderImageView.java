package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.Widget;

public interface IUploadSliderImageView {
	public interface Presenter{

		void getImageUploadUrl();
		
	}

	void setPresenter(Presenter presenter);
	void info(String message,int id);
	Widget asWidget();
	void setUploadForm(String url);
	void init();
}
