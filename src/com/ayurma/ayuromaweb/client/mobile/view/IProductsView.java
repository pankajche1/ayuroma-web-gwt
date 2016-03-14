package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.mobile.model.ProductsGroupMenuItem;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;



public interface IProductsView extends IsWidget{
	public void setHeaderTitle(String text);

	//public HasTapHandlers getAboutButton();

	public HasCellSelectedHandler getCellSelectedHandler();
	public void setGroups(List<ProductsGroupMenuItem> groups);

	public HasTapHandlers getHeaderRightButton();

}
