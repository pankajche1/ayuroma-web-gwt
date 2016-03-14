package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProductsViewImpl extends Composite implements IProductsView{

	private static ProductsViewImplUiBinder uiBinder = GWT
			.create(ProductsViewImplUiBinder.class);

	interface ProductsViewImplUiBinder extends
			UiBinder<Widget, ProductsViewImpl> {
	}
	private Presenter presenter;
	public ProductsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnAddNewProduct")
	public void addNewProduct(ClickEvent e){
		presenter.gotoPlace(0);
	}
	
	@UiHandler("btnBrowseProducts")
	public void browseProducts(ClickEvent e){
		presenter.gotoPlace(1);
	}

}
