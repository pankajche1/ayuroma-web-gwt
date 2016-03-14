package com.ayurma.ayuromaweb.client.view.widgets;

import com.ayurma.ayuromaweb.client.view.resource.MainClientBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class AyuromaAjaxMole extends Composite {

	private static AyuromaAjaxMoleUiBinder uiBinder = GWT
			.create(AyuromaAjaxMoleUiBinder.class);

	interface AyuromaAjaxMoleUiBinder extends UiBinder<Widget, AyuromaAjaxMole> {
	}
	
	@UiField Image image;
	//private Image ajaxAnim= new Image(MainClientBundle.INSTANCE.getLoadingAnimation());
	public AyuromaAjaxMole() {
		initWidget(uiBinder.createAndBindUi(this));
		image.setResource(MainClientBundle.INSTANCE.getLoadingAnimation());
		
	}

}
