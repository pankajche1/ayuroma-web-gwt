package com.ayurma.ayuromaweb.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class HeaderViewImpl extends Composite implements HeaderView{

	private static HeaderViewImplUiBinder uiBinder = GWT
			.create(HeaderViewImplUiBinder.class);

	interface HeaderViewImplUiBinder extends UiBinder<Widget, HeaderViewImpl> {
	}
	@UiField
	HTMLPanel sidePanel;
	public HeaderViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public HTMLPanel getSidePanel(){
		return sidePanel;
	}
	@Override
	public HasWidgets get(String id) {
		if(id.equals("side-panel")) return sidePanel;
		return null;
	}


}
