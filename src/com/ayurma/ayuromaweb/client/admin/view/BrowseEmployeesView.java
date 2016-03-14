package com.ayurma.ayuromaweb.client.admin.view;


import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.activity.BrowseEmployeesActivity;
import com.ayurma.ayuromaweb.client.admin.activity.BrowseProductsActivity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class BrowseEmployeesView extends Composite implements IBrowseEmployeesView {

	private static BrowserEmployeesViewUiBinder uiBinder = GWT
			.create(BrowserEmployeesViewUiBinder.class);
	private IBrowseEmployeesView.Presenter presenter;
	interface Style extends CssResource{
		String rootPanel();
		String leftPanel();
		String rightPanel();
		//left panel styles:
		String leftPanelButtonsPanel();
		//right panel styles:
		String rightPanelButtonsPanel1();
		String rightPanelButtonsPanel2();
		String dataPanel();
		String productUnit();
		//the current products that are in the current group:
		String curProduct();
		//for floating purposes:
		String floatRight();
		String clearBoth();
		String evenRow();
		String oddRow();
		String heading1();
		String heading2();
		String textMessage();
		//buttons panel that cotains delete,edit, edit details button on each product unit:
		String buttonsPanel();
		
	}
	@UiField
    HTMLPanel dataPanel,noDataNotePanal;
    @UiField
    TextBox txtPageNumber;
    @UiField
    ListBox lbItemsPerPage;
	@UiField
	Style style;
	@UiField HTMLPanel bottomBtnsPanel;
	@UiField Button btnCancelBrowser,btnOk;
	@UiField Label lblMessage;
    //group of checkboxes for selecting products to be added
    List<CheckBox> checkBoxesAdd=new ArrayList<CheckBox>();
    private int iSelected=-1;
    //private int displayMode=0;
	interface BrowserEmployeesViewUiBinder extends
			UiBinder<Widget, BrowseEmployeesView> {
	}

	public BrowseEmployeesView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
	
		this.presenter = presenter;
	}

	@Override
	public void reset() {
		dataPanel.clear();
		noDataNotePanal.setVisible(true);
		lblMessage.setText("");
		
	}

	@Override
	public void info(String message, int code) {
		lblMessage.setText(message);
		
	}

	@Override
	public void clear() {
		dataPanel.clear();	
		
	}

	@Override
	public void init() {
		// here the view will initiate itself for showing the 
		// proper inter face:
		int displayMode = presenter.getDisplayMode();
		switch (displayMode) {
		case BrowseEmployeesActivity.STANDALONE_BROWSER:// shows an independent
														// product browser:
			bottomBtnsPanel.setVisible(false);

			break;
		case BrowseEmployeesActivity.MULTI_ITEM_SELECTION_DIALOG:
			bottomBtnsPanel.setVisible(true);

			break;
		case 2:
			bottomBtnsPanel.setVisible(false);

			break;
		case BrowseEmployeesActivity.SINGLE_ITEM_SELECTION_DIALOG:// single
																	// product
																	// selection
																	// mode:
			bottomBtnsPanel.setVisible(true);

			break;
		case BrowseEmployeesActivity.MULTI_ITEM_SELECTION_STANDALONE:
			bottomBtnsPanel.setVisible(false);
			break;
		}
		
	}
	@UiHandler("btnLoadEmployees")
	void onLoadEmployeesClick(ClickEvent e) {
		
		//clearing the already existing data:
		dataPanel.clear();
		if(presenter!=null) presenter.onLoadEmployeesButtonClicked(txtPageNumber.getText(),
				lbItemsPerPage.getValue(lbItemsPerPage.getSelectedIndex()),0);
	}
	@UiHandler("btnClearCacheEmployees")
	void onClearCacheBtnClick(ClickEvent e){
		presenter.clearEmployeesCache();
		
	}

	@Override
	public void showEmployeesList(List<String> names, List<String> sn,
			int displayMode) {
		if(names.isEmpty()){
			noDataNotePanal.setVisible(true);
			info("", 0);
		}
		else noDataNotePanal.setVisible(false);
		switch(displayMode){
		case BrowseProductsActivity.STANDALONE_BROWSER://shows an independent product browser:
			bottomBtnsPanel.setVisible(false);
			showEmployeesDisplay0(names,sn);
			break;
		case BrowseProductsActivity.MULTI_ITEM_SELECTION_DIALOG:
			bottomBtnsPanel.setVisible(true);
			//showProductsDisplay1(names,sn);
			break;
		case 2:
			bottomBtnsPanel.setVisible(false);
			//showProductsDisplay2(names,sn);
			break;
		case BrowseProductsActivity.SINGLE_ITEM_SELECTION_DIALOG://single product selection mode:
			bottomBtnsPanel.setVisible(true);
			//showProductsDisplay3(names,sn);
			break;
		case BrowseProductsActivity.MULTI_ITEM_SELECTION_STANDALONE:
			//showProductsDisplay1(names,sn);
			break;			
		}
		
	}
	/**
	 * stand alone browser
	 * @param names
	 * @param sn
	 */
	private void showEmployeesDisplay0(List<String> names,List<String> sn){
		
		dataPanel.clear();//clear the data panel:
		
		int i=0;
		
		for(String name:names){
			//sets the id of the employee as in the activity list of employees:
			EmployeeItem employeePanel = new EmployeeItem("("+sn.get(i)+") "+name,i,0);
			if(i%2==0) employeePanel.addStyleName(style.evenRow());
			else employeePanel.addStyleName(style.oddRow());
			dataPanel.add(employeePanel);
			i++;
		}
	}
	private class EmployeeItem extends HTMLPanel{
		private int id;
		private Button btnDelete,btnEdit,btnEditDetails,btnLink,btnSelect;
		//private int displayMode=0;
		
		public EmployeeItem(String html,int id,int displayMode) {
			super(html);
			this.id=id;
			EventHandler eventHandler = new EventHandler();
			setStyleName(style.productUnit());
			HTMLPanel buttonsPanel = new HTMLPanel("");
			
			buttonsPanel = new HTMLPanel("");
			buttonsPanel.setStyleName(style.buttonsPanel());
			switch(displayMode){
			case 0:

				btnDelete=new Button("Delete");
				btnDelete.addClickHandler(eventHandler);
				buttonsPanel.add(btnDelete);
				btnEdit=new Button("Edit");
				btnEdit.addClickHandler(eventHandler);
			
				buttonsPanel.add(btnEdit);
				btnEditDetails=new Button("Edit Details");
				btnEditDetails.addClickHandler(eventHandler);
				buttonsPanel.add(btnEditDetails);
				add(buttonsPanel);
			break;
			case 1:
				break;
			case 2://shows a link button against each product
				btnLink=new Button("Link");
				btnLink.addClickHandler(eventHandler);
				add(btnLink);
				break;
			case BrowseProductsActivity.SINGLE_ITEM_SELECTION_DIALOG://single product selection mode
				if(id%2==0){
					addStyleName(style.oddRow());
				}else addStyleName(style.evenRow());
				btnSelect=new Button("Select");
				btnSelect.addClickHandler(eventHandler);
				buttonsPanel.add(btnSelect);
				add(buttonsPanel);
				break;
			}
		}
		private class EventHandler implements ClickHandler, KeyUpHandler{

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onClick(final ClickEvent event) {
				 Widget sender = (Widget) event.getSource();
				 if(sender==btnDelete){
					 confirmDelete(id);		
				 }else if(sender==btnEdit){
					presenter.onEditEmployeeClicked(id);
				 }else if(sender==btnEditDetails){
					//presenter.onEditProductDetailsClicked(id);
				 }else if(sender==btnLink){
					presenter.linkButtonClicked(id); 
				 }else if(sender==btnSelect){
					 iSelected=id;
				 }
				
			}
			
		}
		
	}//private class ends here

	private void confirmDelete(int id){
		
		boolean option=Window.confirm("Do you want to delete this Employee?");
		if(option){
			presenter.onDeleteEmployeeClicked(id);
		}
				
	}
	

}
