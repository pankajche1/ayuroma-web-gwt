package com.ayurma.ayuromaweb.client.admin.view;

//import java.util.logging.Level;
//import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.admin.view.widgets.EditAddressesView;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditEmployeeView extends Composite implements IEditEmployeeView {

	private static EditEmployeeViewUiBinder uiBinder = GWT
			.create(EditEmployeeViewUiBinder.class);

	interface EditEmployeeViewUiBinder extends
			UiBinder<Widget, EditEmployeeView> {
	}
	private Presenter presenter;
	@UiField Label lblMainMessage;
	@UiField TextBox tbName, tbDesignation;
	@UiField EditAddressesView addressesView;
	//private Logger logger = Logger.getLogger("Edit Employee View Logger");
	public EditEmployeeView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		//addressesView.setPresenter(presenter);
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String message, int id) {
		switch(id){
		case 0:
			lblMainMessage.setText(message);
			break;
		case 1:
			//lblMessage.setText(message);
			break;
		case 2:
			//lblImageMessage.setText(message);
			break;
		}
		
	}
	@Override
	public void setData(EmployeeDTO dto){
		//set the name:
		tbName.setText(dto.getName());
		//set the designation:
		tbDesignation.setText(dto.getDesignation());
		//logger.log(Level.INFO, "EditEmployeeView::setData() ");
		//set the address:
		addressesView.showAddresses(dto.getAddresses());
		
	}

	@Override
	public void setAddressPresenter(
			com.ayurma.ayuromaweb.client.admin.view.widgets.IEditAddressView.Presenter presenter) {
		addressesView.setPresenter(presenter);
		
	}

}
