package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.view.widgets.IAddressWidget;
import com.ayurma.ayuromaweb.client.admin.view.widgets.IAddressesPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class AddNewEmployeeViewMock implements IAddNewEmployeeView{

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
	public void showMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAjaxLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAjaxLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String message, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IAddressesPanel getAddressesPanel() {
		IAddressesPanel addressPanel = new AddressesPanelMock();
		return addressPanel;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
		
	}
	private class AddressesPanelMock implements IAddressesPanel{

		@Override
		public List<IAddressWidget> getPanels() {
			String[] cities = {"Kanpur", "Delhi", "Mumbai"};
			String[] states = {"UP", "Delhi", "Maharastra"};
			String[] countries = {"India", "India", "India"};
			String[] lines = {"Line1", "Line2", "Line3"};
			List<IAddressWidget> list = new ArrayList<IAddressWidget>();
			for(int i=0;i<cities.length;i++){
				AddressWidgetMock panel = new AddressWidgetMock(cities[i],states[i],
						countries[i]);
				list.add(panel);	
			}
			//create 2 address panel:
			//AddressWidgetMock panel1 = new AddressWidgetMock();
			//list.add(panel1);
			//AddressWidgetMock panel2 = new AddressWidgetMock();
			//list.add(panel2);
			return list;
		}
		
	}
	private class AddressWidgetMock implements IAddressWidget{
		private String city, state, country;
		public AddressWidgetMock(String city, String state, String country){
			this.city = city;
			this.state = state;
			this.country = country;
			
		}
		@Override
		public HasText getTbCity() {
			
			return new HasTextImpl(city);
		}

		@Override
		public HasText getTbState() {
			
			return new HasTextImpl(state);
		}

		@Override
		public HasText getTbCountry() {
			
			return new HasTextImpl(country);
		}

		@Override
		public List<String> getLines() {
			List<String> list = new ArrayList<String>();
			list.add("Line1");
			list.add("   ");//empty line
			list.add(" Line2");//leading space
			list.add("Line3");
			list.add("Line4 ");
			return list;
		}
		
	}
	private class HasTextImpl implements HasText{
		private String text;
		public HasTextImpl(String text){
			this.text = text;
		}
		@Override
		public String getText() {
			
			return text;
		}

		@Override
		public void setText(String text) {
			this.text = text;
			
		}
		
	}

}
