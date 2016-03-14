package com.ayurma.ayuromaweb.client.view;


import java.util.List;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public interface IHomeView {
	public interface Presenter{
		void gotoProductGroup(String keyGroup);
	}

	void setPresenter(Presenter presenter);
	//void showAjaxAnim();
	//void stopAjaxAnim();
	Widget asWidget();
	HasWidgets get(String id);
	boolean isContentSliderPut();
	void setContentSliderPut(boolean isContentSliderSet);
	void setProductGroupData(List<String> headTexts, List<String> bodyTexts,
			List<String> imageUrls, List<String> linkUrls);
	void scrollTo(int left, int top);
	
}
