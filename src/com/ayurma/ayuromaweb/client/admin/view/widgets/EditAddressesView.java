package com.ayurma.ayuromaweb.client.admin.view.widgets;

import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;



import com.ayurma.ayuromaweb.client.admin.view.widgets.IEditAddressView.Presenter;
import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class EditAddressesView extends Composite implements IEditAddressesView{

	private static EditAddressesViewUiBinder uiBinder = GWT
			.create(EditAddressesViewUiBinder.class);

	interface EditAddressesViewUiBinder extends
			UiBinder<Widget, EditAddressesView> {
	}

	public EditAddressesView() {
		initWidget(uiBinder.createAndBindUi(this));
		lstAddressViews  = new ArrayList<EditAddressView>();
	}
	private List<EditAddressView> lstAddressViews;
	private Presenter presenter;
	@UiField HTMLPanel dataPanel;
	@UiField Button btnAddAddress;
	//private Logger logger = Logger.getLogger("Edit Addresses View Logger");
	public void showAddresses(List<AddressDTO> list){
		//logger.log(Level.INFO, "showAddresses() list.size()="+list.size());
		//list of addresses will come here.. one address will have
		// line 1, line 2, line 3 and so on
		// city
		//state
		//country
		//clear the datapanel:
		dataPanel.clear();
		//clear the list:
		lstAddressViews.clear();
		
		for(AddressDTO dto:list){
			//create an address view:
			EditAddressView view = new EditAddressView();
			lstAddressViews.add(view);
			//add to DOM:
			
			dataPanel.add(view);
			//showing the data on the view:
			view.setData(dto);
			view.setPresenter(presenter);
			
			
		}
	}
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}
	@UiHandler("btnAddAddress")
	void onBtnAddAddressClick(ClickEvent e) {
		EditAddressView view = new EditAddressView();
		lstAddressViews.add(view);
		//add to DOM:
		dataPanel.add(view);
		view.setPresenter(presenter);
	}

}
