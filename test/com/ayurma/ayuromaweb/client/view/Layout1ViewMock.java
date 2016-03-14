package com.ayurma.ayuromaweb.client.view;

import static org.easymock.EasyMock.*;

import java.util.Iterator;


import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class Layout1ViewMock implements ILayout1View{

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasWidgets get(String id) {
		// TODO Auto-generated method stub
		return new MyView();
	}

	@Override
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}
	private class MyView implements HasWidgets{

		@Override
		public void add(Widget w) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Iterator<Widget> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean remove(Widget w) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
