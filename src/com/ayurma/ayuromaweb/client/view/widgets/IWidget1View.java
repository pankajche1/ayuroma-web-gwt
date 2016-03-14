package com.ayurma.ayuromaweb.client.view.widgets;


import com.google.gwt.user.client.ui.Widget;

public interface IWidget1View {
	public interface Presenter{
		void setHeading(String heading);

		void setBody(String body);

		

		void visit();

		void setKey(String key);
	}

	void setPresenter(Presenter presenter);
	//void showAjaxAnim();
	//void stopAjaxAnim();
	Widget asWidget();
	void setHeading(String heading);
	void setContentText(String text);
}
