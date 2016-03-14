package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.mobile.model.ProductsGroupMenuItem;
import com.ayurma.ayuromaweb.client.mobile.view.header.HeaderMobileView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;

public class ProductsView extends Composite implements IProductsView{

	private static ProductsViewUiBinder uiBinder = GWT
			.create(ProductsViewUiBinder.class);

	interface ProductsViewUiBinder extends UiBinder<Widget, ProductsView> {
	}
	@UiField protected HeaderMobileView headerView;
	@UiField protected FlowPanel container;
	private CellList<ProductsGroupMenuItem> list;
	public ProductsView() {
		
		
		initWidget(uiBinder.createAndBindUi(this));
		list = new CellList<ProductsGroupMenuItem>(new BasicCell<ProductsGroupMenuItem>() {

			@Override
			public String getDisplayString(ProductsGroupMenuItem model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(ProductsGroupMenuItem model) {
				return true;
			}
		});

		
		container.add(list);
		headerView.setButtonRightLabel("Home");
		
		headerView.setHeaderTitleText("Ayuroma Centre");
		//headerView.setHeaderTitleText("Our Products");
	}

	@Override
	public HasTapHandlers getHeaderRightButton() {

		return headerView.getButtonRight();
	}
	@Override
	public HasCellSelectedHandler getCellSelectedHandler() {
		
		return list;
	}
	@Override
	public void setGroups(List<ProductsGroupMenuItem> groups) {
		list.render(groups);
		
	}
	@Override
	public void setHeaderTitle(String text) {
		headerView.setHeaderTitleText(text);
		
	}

}
