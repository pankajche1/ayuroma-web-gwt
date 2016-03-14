package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProductGroupViewImpl extends Composite implements IProductGroupView{

	private static ProductGroupViewImplUiBinder uiBinder = GWT
			.create(ProductGroupViewImplUiBinder.class);

	interface ProductGroupViewImplUiBinder extends
			UiBinder<Widget, ProductGroupViewImpl> {
	}
	private Presenter presenter;
	public ProductGroupViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnAddGroup")
	public void addGroup(ClickEvent e){
		presenter.gotoPlace(0);
	}
	
	@UiHandler("btnBrowseGroup")
	public void browseGroup(ClickEvent e){
		presenter.gotoPlace(1);
	}

}
