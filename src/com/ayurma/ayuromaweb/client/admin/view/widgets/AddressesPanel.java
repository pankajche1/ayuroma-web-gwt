package com.ayurma.ayuromaweb.client.admin.view.widgets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class AddressesPanel extends Composite implements IAddressesPanel{

	private static AddressesPanelUiBinder uiBinder = GWT
			.create(AddressesPanelUiBinder.class);

	interface AddressesPanelUiBinder extends UiBinder<Widget, AddressesPanel> {
	}
	@UiField AddressWidget addressWidget1;
	@UiField Button btnAddAddressPanel;
	@UiField HTMLPanel addressesPanel;
	
	private int idPanel = 0;
	private List<AddressWidget> addressWidgets = new ArrayList<AddressWidget>();
	public AddressesPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		//event handler class:
		
		
	}
	@UiHandler("btnAddAddressPanel")
	void onAddAddressPanelClick(ClickEvent e) {
		
		AddressWidget panel = new AddressWidget(idPanel, this);
		idPanel++;
		//add to the DOM:
		addressesPanel.add(panel);
		//add to the list
		addressWidgets.add(panel);
		
		
		
	}
	public void removePanel(int id){
		int index = -1;
		int i=0;
		for(AddressWidget panel : addressWidgets){
			if(id==panel.getId()){
				panel.removeFromParent();
				index = i;
				break;
			}
			i++;
			
		}
		if(index>=0){
			addressWidgets.remove(index);
			
		}
	}
	@Override
	public List<IAddressWidget> getPanels(){
		List<IAddressWidget> list = new ArrayList<IAddressWidget>();
		list.add(addressWidget1);//add the default widget
		//extra widgets:
		for(AddressWidget w: addressWidgets){
			list.add(w);
		}
		return list;
	}
	public void reset(){
		//reset the default widget:
		addressWidget1.reset();
		//and remove the remaining widgets:
		
		for(AddressWidget w:addressWidgets ){
			w.removeFromParent();//remove from DOM
			
		}
		//clear the list:
		addressWidgets.clear();
		
	}
	

}
