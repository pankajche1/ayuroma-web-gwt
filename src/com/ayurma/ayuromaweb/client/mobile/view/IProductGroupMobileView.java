package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.mobile.model.ProductItem;
import com.ayurma.ayuromaweb.client.view.IProductGroupView;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;

public interface IProductGroupMobileView extends IProductGroupView{
	public void setTitle(String text);

	//public HasTapHandlers getAboutButton();

	public HasCellSelectedHandler getCellSelectedHandler();
	public void setProducts(List<ProductItem> list);
	public void clearList(List<ProductItem> list);
	public void showProgressIndicator();
	public void showListView();

	public HasTapHandlers getHeaderRightButton();
}
