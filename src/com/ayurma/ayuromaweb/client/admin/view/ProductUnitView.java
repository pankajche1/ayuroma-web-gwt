package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProductUnitView extends Composite {

	private static ProductUnitViewUiBinder uiBinder = GWT
			.create(ProductUnitViewUiBinder.class);

	interface ProductUnitViewUiBinder extends UiBinder<Widget, ProductUnitView> {
	}
	private int id;
	@UiField FocusPanel rootPanel;
	public ProductUnitView(int displayMode) {
		initWidget(uiBinder.createAndBindUi(this));
		//for single selection mode:
	}
	public HasClickHandlers getSelectablePanel(){
		return rootPanel;
	}

}
