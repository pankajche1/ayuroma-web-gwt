package com.ayurma.ayuromaweb.client.mobile.view.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProgressIndicatorWidget extends Composite {

	private static ProgressIndicatorWidgetUiBinder uiBinder = GWT
			.create(ProgressIndicatorWidgetUiBinder.class);

	interface ProgressIndicatorWidgetUiBinder extends
			UiBinder<Widget, ProgressIndicatorWidget> {
	}

	public ProgressIndicatorWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
