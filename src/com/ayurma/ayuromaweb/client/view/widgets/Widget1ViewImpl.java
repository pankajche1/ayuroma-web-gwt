package com.ayurma.ayuromaweb.client.view.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Widget1ViewImpl extends Composite implements IWidget1View{

	private static Widget1ViewImplUiBinder uiBinder = GWT
			.create(Widget1ViewImplUiBinder.class);

	interface Widget1ViewImplUiBinder extends UiBinder<Widget, Widget1ViewImpl> {
	}

	public Widget1ViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	private Presenter presenter;
	@UiField
	Label lblHeading;
	@UiField HTMLPanel contentPanel;
	@UiHandler("linkVisit")
	public void onVisitClick(ClickEvent e){
		presenter.visit();
	}
	@Override
	public void setPresenter(Presenter presenter) {
        this.presenter=presenter;
		
	}
	@Override
	public void setHeading(String heading) {
		lblHeading.setText(heading);
		
	}
	@Override
	public void setContentText(String text){
		contentPanel.getElement().setInnerHTML(text);
	}

}
