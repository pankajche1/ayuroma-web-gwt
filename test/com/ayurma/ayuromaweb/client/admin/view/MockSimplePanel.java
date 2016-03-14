package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public class MockSimplePanel implements AcceptsOneWidget{

	@Override
	public void setWidget(IsWidget w) {
		System.out.println("Some widget is added to this mock SimplePanel");
		
	}

}
