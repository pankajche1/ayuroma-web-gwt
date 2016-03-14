package com.ayurma.ayuromaweb.client.mobile.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface IAboutUsMobileView extends IsWidget{
	public void setTitle(String text);
	public void info(String msg, int id, int type);
	public void showProgressIndicator(boolean isVisible);
	
	public HasTapHandlers getHeaderRightButton();

}
