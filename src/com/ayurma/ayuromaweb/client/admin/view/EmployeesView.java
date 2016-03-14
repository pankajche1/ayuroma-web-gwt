package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class EmployeesView extends Composite implements IEmployeesView{

	private static EmployeesViewUiBinder uiBinder = GWT
			.create(EmployeesViewUiBinder.class);

	interface EmployeesViewUiBinder extends UiBinder<Widget, EmployeesView> {
	}
	private Presenter presenter;
	public EmployeesView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnAddNewEmployee")
	public void addNewProduct(ClickEvent e){
		presenter.gotoPlace(0);
	}
	
	@UiHandler("btnBrowseEmployees")
	public void browseProducts(ClickEvent e){
		presenter.gotoPlace(1);
	}

	

}
