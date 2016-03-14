package com.ayurma.ayuromaweb.client.admin.view;


import java.util.List;


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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class BrowseProductGroupViewImpl extends Composite 
		implements IBrowseProductGroupView{

	private static BrowseProductGroupViewImplUiBinder uiBinder = GWT
			.create(BrowseProductGroupViewImplUiBinder.class);

	interface BrowseProductGroupViewImplUiBinder extends
			UiBinder<Widget, BrowseProductGroupViewImpl> {
	}
	interface MyCss extends CssResource{
		String productGroup();
		String evenRow();
		String oddRow();
		String buttonsPanel();
	}
	private Presenter presenter;
	@UiField HTMLPanel dataPanel;
    @UiField
    Button btnLoadList;
    @UiField MyCss style;
    //@UiField
    //HTMLPanel contentPanel;
    @UiField
    TextBox txtNumPage;
	//@UiField
	//Style style;
    @UiField
    ListBox lbItemsPerPage;
    @UiField InlineLabel lblMainMessage;

	public BrowseProductGroupViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnLoadList")
	void onBtnLoadListClick(ClickEvent e){
		dataPanel.clear();
		//contentPanel.clear();
		//it will create the view if it is not already present:
		/*
		if(groupsListView==null){
			groupsListView=new ProductGroupsListView<T1,T2>();
		}
		groupsListView.setPresenter(presenter);
		
		contentPanel.add(groupsListView);
		groupsListView.clearData();//clear the already existing data
		*/
		//Then it will load the list:
		presenter.loadProductGroupsList(txtNumPage.getText(),lbItemsPerPage.getValue(lbItemsPerPage.getSelectedIndex()));
	}

	@Override
	public void info(String message, int id) {
		lblMainMessage.setText(message);
		
	}

	@Override
	public void showGroupsList(List<String> names, List<String> sn) {
		dataPanel.clear();
		int id=0;
		for(String name:names){
			ProductGroupItem group = new ProductGroupItem(name,id);
			if(id%2==0)group.addStyleName(style.evenRow());
			else group.addStyleName(style.oddRow());
			dataPanel.add(group);
			id++;
		}
		
	}
	private class ProductGroupItem extends HTMLPanel{
		private int id;
		private Button btnDelete,btnEdit,btnAddRemoveProducts;
		
		public ProductGroupItem(String html,int id) {
			super(html);
			this.id=id;
			this.setStyleName(style.productGroup());
			EventHandler eventHandler = new EventHandler();
			HTMLPanel btnsPanel=new HTMLPanel("");
			btnsPanel.setStyleName(style.buttonsPanel());
			btnDelete=new Button("Delete");
			btnDelete.addClickHandler(eventHandler);
			btnsPanel.add(btnDelete);
			btnEdit=new Button("Edit");
			btnEdit.addClickHandler(eventHandler);
			
			btnsPanel.add(btnEdit);
			btnAddRemoveProducts=new Button("Add/Romove Products");
			btnAddRemoveProducts.addClickHandler(eventHandler);
			btnsPanel.add(btnAddRemoveProducts);
			add(btnsPanel);
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
					presenter.editProductGroup(id);
				 }else if(sender==btnAddRemoveProducts){
					presenter.addRemProducts(id);
				 }
				
			}
			
		}
		
	}


	private void confirmDelete(int id){
		
		boolean option=Window.confirm("Do you want to delete?");
		if(option){
			//if(presenter!=null) presenter.deleteGroup(id);
		}
		//dialogBox.center();		
	}

	@Override
	public void reset() {
		dataPanel.clear();
		
	}


}
