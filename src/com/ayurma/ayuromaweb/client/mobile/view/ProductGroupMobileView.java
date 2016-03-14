package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.mobile.model.ProductItem;
import com.ayurma.ayuromaweb.client.mobile.view.header.HeaderMobileView;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
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
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;

public class ProductGroupMobileView extends Composite implements IProductGroupMobileView{

	private static ProductGroupMobileViewUiBinder uiBinder = GWT
			.create(ProductGroupMobileViewUiBinder.class);

	interface ProductGroupMobileViewUiBinder extends
			UiBinder<Widget, ProductGroupMobileView> {
	}
	private Logger logger = Logger.getLogger("");
	private CellList<ProductItem> list;
	@UiField protected HeaderMobileView headerView;
	@UiField protected FlowPanel container;
	@UiField protected ScrollPanel scrollPanel;
	private ProgressIndicator progressIndicator;
	public ProductGroupMobileView() {
		initWidget(uiBinder.createAndBindUi(this));
		progressIndicator = new ProgressIndicator();
		progressIndicator.getElement().setAttribute("style", "margin:auto; margin-top: 50px");
		list = new CellList<ProductItem>(new BasicCell<ProductItem>() {

			@Override
			public String getDisplayString(ProductItem model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(ProductItem model) {
				return true;
			}
		});
		headerView.setButtonRightLabel("Home");
		
		headerView.setHeaderTitleText("Ayuroma Centre");
	}
	@Override
	public HasTapHandlers getHeaderRightButton() {

		return headerView.getButtonRight();
	}
	@Override
	public void setTitle(String text) {
		headerView.setHeaderTitleText(text);

	}

	@Override
	public HasCellSelectedHandler getCellSelectedHandler() {
		return list;
	}

	@Override
	public void setProducts(List<ProductItem> listProductItems) {
		
		list.render(listProductItems);
		scrollPanel.refresh();
		

	}

	@Override
	public void clearList(List<ProductItem> listProductItems) {
		list.render(listProductItems);
		
	}

	@Override
	public void showProgressIndicator() {
		
		showAjaxAnim();
		
	}
	
	@Override
	public void showListView() {
		container.clear();
		container.add(list);
		
		
		
		
	}
	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showAjaxAnim() {
		container.clear();
		container.add(progressIndicator);
		scrollPanel.refresh();
		
	}
	@Override
	public void stopAjaxAnim() {
		container.clear();
		
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showData(ProductGroupItemsData data) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}

}
