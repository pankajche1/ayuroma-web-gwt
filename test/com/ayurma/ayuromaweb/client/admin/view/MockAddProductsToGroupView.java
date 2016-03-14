package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MockAddProductsToGroupView implements IAddProductsToGroupView{

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IsWidget asWidget() {
		System.out.println("MockView::asWidget()");
		return new MockWidget();
	}

	@Override
	public void setGroupData(String name, String[] namesProducts, String[] strSn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AcceptsOneWidget get(String id) {
		
		return new MockSimplePanel();
	}

	@Override
	public void setSelectedProductData(String name, String[] namesProducts,
			String[] strSn) {
		System.out.println("MockAddRemoveProductsView setSelectedProductData()...");
		System.out.printf("\tName=", name);
		int i=0;
		for(String productName:namesProducts){
			System.out.printf("\n\t\t[%s] %s",strSn[i],productName );
			i++;
			
		}
		System.out.println();
		
	}

	@Override
	public void info(String message, int code) {
		// TODO Auto-generated method stub
		
	}

}
