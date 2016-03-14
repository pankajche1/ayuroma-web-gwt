package com.ayurma.ayuromaweb.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Layout1View extends Composite implements ILayout1View{

	private static Layout1ViewUiBinder uiBinder = GWT
			.create(Layout1ViewUiBinder.class);

	interface Layout1ViewUiBinder extends UiBinder<Widget, Layout1View> {
	}

	public Layout1View() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	@UiField
	HTMLPanel mainContent,sideBar;
	
	@Override
	public HasWidgets get(String id){
		if(id.equals("mainContent")) return mainContent;
		else if(id.equals("sidebar")) return sideBar;
		return null;
	}
	@Override
	public void scrollTo(int left,int top){
		Window.scrollTo(left, top);
	}
}
