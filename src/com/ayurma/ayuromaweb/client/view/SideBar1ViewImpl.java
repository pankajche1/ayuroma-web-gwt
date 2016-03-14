package com.ayurma.ayuromaweb.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class SideBar1ViewImpl extends Composite implements ISideBar1View{

	private static SideBar1ViewImplUiBinder uiBinder = GWT
			.create(SideBar1ViewImplUiBinder.class);

	interface SideBar1ViewImplUiBinder extends
			UiBinder<Widget, SideBar1ViewImpl> {
	}
	@UiField
	HTMLPanel rootPanel;
	public SideBar1ViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	private Presenter presenter;
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@Override
	public HasWidgets get() {

		return rootPanel;
	}

}
