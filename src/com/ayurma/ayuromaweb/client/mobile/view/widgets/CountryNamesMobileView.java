package com.ayurma.ayuromaweb.client.mobile.view.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;

public class CountryNamesMobileView extends Composite  {

	private static CountryNamesMobileViewUiBinder uiBinder = GWT
			.create(CountryNamesMobileViewUiBinder.class);

	interface CountryNamesMobileViewUiBinder extends
			UiBinder<Widget, CountryNamesMobileView> {
	}
	@UiField MListBox listBox;
	public CountryNamesMobileView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	

	public CountryNamesMobileView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}



	public MListBox getListBox() {
		return listBox;
	}

	
}
