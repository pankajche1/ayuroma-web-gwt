package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.mobile.model.HomeMenuItem;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;


public interface IHomeView  extends IsWidget{
	public void setTitle(String text);

	public HasTapHandlers getAboutButton();

	public HasCellSelectedHandler getCellSelectedHandler();
	public void setMenuItems(List<HomeMenuItem> createTopicsList);

}
