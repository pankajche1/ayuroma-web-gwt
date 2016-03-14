package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;



import com.ayurma.ayuromaweb.client.admin.activity.BrowseProductsActivity;
import com.ayurma.ayuromaweb.client.admin.activity.IProductBrowserConnector;
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
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class BrowseProductsViewImpl extends Composite implements IBrowseProductsView {

	private static BrowseProductsViewImplUiBinder uiBinder = GWT
			.create(BrowseProductsViewImplUiBinder.class);

	interface BrowseProductsViewImplUiBinder extends
			UiBinder<Widget, BrowseProductsViewImpl> {
	}
	interface Style extends CssResource{
		String rootPanel();
		String leftPanel();
		String rightPanel();
		//left panel styles:
		String leftPanelButtonsPanel();
		//right panel styles:
		String rightPanelButtonsPanel1();
		String rightPanelButtonsPanel2();
		String productsDataPanel();
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
	private Presenter presenter;
	private IProductBrowserConnector dataReceiver;
    @UiField
    Button btnLoadProducts;//,btnAddProducts,btnRefreshProducts;
    @UiField
    HTMLPanel productsDataPanel,noProductNotePanal;
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
	public BrowseProductsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnLoadProducts")
	void onLoadProductsClick(ClickEvent e) {
		//btnSave.setEnabled(false);
		//clearing the already existing data:
		productsDataPanel.clear();
		if(presenter!=null) presenter.onLoadProductsButtonClicked(txtPageNumber.getText(),
				lbItemsPerPage.getValue(lbItemsPerPage.getSelectedIndex()),0);
	}
	@UiHandler("btnClearCacheProducts")
	void onClearCacheBtnClick(ClickEvent e){
		presenter.clearProductsCache();
		
	}
	@Override
	public void init(){
		// here the view will initiate itself for showing the 
		//proper inter face:
		int displayMode=presenter.getDisplayMode();
		switch(displayMode){
		case BrowseProductsActivity.STANDALONE_BROWSER://shows an independent product browser:
			bottomBtnsPanel.setVisible(false);
		
			break;
		case BrowseProductsActivity.MULTI_ITEM_SELECTION_DIALOG:
			bottomBtnsPanel.setVisible(true);
		
			break;
		case 2:
			bottomBtnsPanel.setVisible(false);
			
			break;
		case BrowseProductsActivity.SINGLE_ITEM_SELECTION_DIALOG://single product selection mode:
			bottomBtnsPanel.setVisible(true);
			
			break;
		case BrowseProductsActivity.MULTI_ITEM_SELECTION_STANDALONE:
			bottomBtnsPanel.setVisible(false);
			break;
		}
	}
	@Override
	public void reset(){
		productsDataPanel.clear();
		noProductNotePanal.setVisible(true);
		lblMessage.setText("");
	}
	@Override
	public void showProductsList(List<String> names,List<String> sn,
			int displayMode){//sn is for serial number
		if(names.isEmpty()) noProductNotePanal.setVisible(true);
		else noProductNotePanal.setVisible(false);
		switch(displayMode){
		case BrowseProductsActivity.STANDALONE_BROWSER://shows an independent product browser:
			bottomBtnsPanel.setVisible(false);
			showProductsDisplay0(names,sn);
			break;
		case BrowseProductsActivity.MULTI_ITEM_SELECTION_DIALOG:
			bottomBtnsPanel.setVisible(true);
			showProductsDisplay1(names,sn);
			break;
		case 2:
			bottomBtnsPanel.setVisible(false);
			showProductsDisplay2(names,sn);
			break;
		case BrowseProductsActivity.SINGLE_ITEM_SELECTION_DIALOG://single product selection mode:
			bottomBtnsPanel.setVisible(true);
			showProductsDisplay3(names,sn);
			break;
		case BrowseProductsActivity.MULTI_ITEM_SELECTION_STANDALONE:
			showProductsDisplay1(names,sn);
			break;			
		}
	}
	/**
	 * stand alone browser
	 * @param names
	 * @param sn
	 */
	private void showProductsDisplay0(List<String> names,List<String> sn){
		HTMLPanel panel;
		productsDataPanel.clear();
		boolean isEven=true;
		int i=0;
		Button btn;
		for(String name:names){
			/*
			panel=new HTMLPanel("");
			
			panel.setStyleName(style.productUnit());
			if(isEven) panel.addStyleName(style.evenRow());
			else panel.addStyleName(style.oddRow());
			//adding the serial number:
			panel.add(new InlineLabel("("+sn.get(i)+") "));
			panel.add(new InlineLabel(name));
			btn = new Button("Edit");
			panel.add(btn);
			btn = new Button("Edit Details");
			panel.add(btn);
			btn = new Button("Delete");
			panel.add(btn);

			*/
			ProductItem product = new ProductItem("("+sn.get(i)+") "+name,i,0);
			if(i%2==0) product.addStyleName(style.evenRow());
			else product.addStyleName(style.oddRow());
			//productsDataPanel.add(panel);
			//if(isEven) isEven = false;
			//else isEven =true;
			productsDataPanel.add(product);
			i++;
		}
	}
	/**
	 * multi item selection dialog type view
	 * @param names
	 * @param sn
	 */
	private void showProductsDisplay1(List<String> names,List<String> sn){
		HTMLPanel panel;
		productsDataPanel.clear();
		CheckBox chk;
		checkBoxesAdd=new ArrayList<CheckBox>();
		boolean isEven=true;
		int i=0;
		for(String name:names){
			panel=new HTMLPanel("");
			
			panel.setStyleName(style.productUnit());
			if(isEven) panel.addStyleName(style.evenRow());
			else panel.addStyleName(style.oddRow());
			//adding the serial number:
			panel.add(new InlineLabel(sn.get(i)));
			chk=new CheckBox();
			checkBoxesAdd.add(chk);
			panel.add(chk);
			chk.setText(name);
			//panel.add(new InlineLabel(name));
			productsDataPanel.add(panel);
			if(isEven) isEven = false;
			else isEven =true;
			i++;
		}
	}
	private void showProductsDisplay2(List<String> names,List<String> sn){
		HTMLPanel panel;
		productsDataPanel.clear();		
		boolean isEven=true;
		int i=0;
		Button btn;
		for(String name:names){
			panel=new HTMLPanel("");
			
			panel.setStyleName(style.productUnit());
			if(isEven) panel.addStyleName(style.evenRow());
			else panel.addStyleName(style.oddRow());
			//adding the serial number:
			panel.add(new InlineLabel(sn.get(i)));
			btn=new Button("Link");
			
			panel.add(btn);
			productsDataPanel.add(new ProductItem("("+sn.get(i)+") "+name,i,2));
			//panel.add(new InlineLabel(name));
			//productsDataPanel.add(panel);
			if(isEven) isEven = false;
			else isEven =true;
			i++;
		}
	}
	/*
	 * single product selection dialog type mode:
	 */
	private void showProductsDisplay3(List<String> names,List<String> sn){
		//HTMLPanel panel;
		productsDataPanel.clear();		
		boolean isEven=true;
		int i=0;
		Button btn;
		for(String name:names){
			//panel=new HTMLPanel("");
			
			//panel.setStyleName(style.productUnit());
			//if(isEven) panel.addStyleName(style.evenRow());
			//else panel.addStyleName(style.oddRow());
			//adding the serial number:
			//panel.add(new InlineLabel(sn.get(i)));
			//btn=new Button("Link");
			
			//panel.add(btn);
			productsDataPanel.add(new ProductItem("("+sn.get(i)+") "+name,i,BrowseProductsActivity.SINGLE_ITEM_SELECTION_DIALOG));
			//panel.add(new InlineLabel(name));
			//productsDataPanel.add(panel);
			isEven=(isEven)?false:true;
			if(isEven) isEven = false;
			else isEven =true;
			i++;
		}
	}
	@Override
	public List<Boolean> getSelectedProducts(){
		List<Boolean> listValues = new ArrayList<Boolean>();
		for(CheckBox chk:checkBoxesAdd){
			listValues.add(chk.getValue());
		}
		return listValues;
	}

	private class ProductItem extends HTMLPanel{
		private int id;
		private Button btnDelete,btnEdit,btnEditDetails,btnLink,btnSelect;
		//private int displayMode=0;
		
		public ProductItem(String html,int id,int displayMode) {
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
					presenter.onEditProductClicked(id);
				 }else if(sender==btnEditDetails){
					presenter.onEditProductDetailsClicked(id);
				 }else if(sender==btnLink){
					presenter.linkButtonClicked(id); 
				 }else if(sender==btnSelect){
					 iSelected=id;
				 }
				
			}
			
		}
		
	}//private class ends here
	private void confirmDelete(int id){
		
		boolean option=Window.confirm("Do you want to delete?");
		if(option){
			presenter.onDeleteProductClicked(id);
		}
		//dialogBox.center();		
	}
	@UiHandler("btnCancelBrowser")
	void onCancelBrowser(ClickEvent e){
		dataReceiver.cancelProductBrowser();
	}
	@UiHandler("btnOk")
	void onConfirmationClick(ClickEvent e){
		dataReceiver.setProduct(iSelected);
	}
	@Override
	public void setDataReceiver(IProductBrowserConnector dataReceiver){
		this.dataReceiver=dataReceiver;
	}


	@Override
	public void info(String message,int code){
		lblMessage.setText(message);
	}
	@Override
	public void clear(){
		productsDataPanel.clear();		
	}
}
