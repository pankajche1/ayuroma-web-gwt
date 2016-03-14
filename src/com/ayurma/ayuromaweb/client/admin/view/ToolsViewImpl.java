package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ToolsViewImpl extends Composite implements IToolsView{

	private static ToolsViewImplUiBinder uiBinder = GWT
			.create(ToolsViewImplUiBinder.class);

	interface ToolsViewImplUiBinder extends UiBinder<Widget, ToolsViewImpl> {
	}
	private Presenter presenter;
	public ToolsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	@Override
	public void info(String string, int id) {
		// TODO Auto-generated method stub
		
	}
	@UiHandler("btnCreateAppData")
	public void createAppData(ClickEvent e){
		presenter.createAppData();
	}

}
