package com.ayurma.ayuromaweb.client.view;

import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import org.easymock.EasyMock;
public class Sidebar1ViewMock implements ISideBar1View{

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasWidgets get() {
		
		return new MyView();
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
