package com.ayurma.ayuromaweb.client.view.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class CountryNamesView extends Composite {

	private static CountryNamesViewUiBinder uiBinder = GWT
			.create(CountryNamesViewUiBinder.class);

	interface CountryNamesViewUiBinder extends
			UiBinder<Widget, CountryNamesView> {
	}
	@UiField ListBox listBox;
	public CountryNamesView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public ListBox getListBox(){
		return this.listBox;
	}

}
