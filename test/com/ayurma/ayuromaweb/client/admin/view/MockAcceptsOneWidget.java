package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public class MockAcceptsOneWidget implements AcceptsOneWidget{

	@Override
	public void setWidget(IsWidget w) {
		System.out.println("Widget set on the container...");
		
		
	}

}
