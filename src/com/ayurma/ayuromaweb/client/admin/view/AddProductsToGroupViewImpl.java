package com.ayurma.ayuromaweb.client.admin.view;




import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class AddProductsToGroupViewImpl extends Composite implements IAddProductsToGroupView,ClickHandler {

	private static AddProductsToGroupViewImplUiBinder uiBinder = GWT
			.create(AddProductsToGroupViewImplUiBinder.class);

	interface AddProductsToGroupViewImplUiBinder extends
			UiBinder<Widget, AddProductsToGroupViewImpl> {
	}
	//remove the product from the list of the proposed products to be included:
	private String DESELECT_PRODUCT_MESSAGE="Do you want to deselect the product?";
	//remove from the product list which is presently included in the group:
	private String REMOVE_PRODUCT_MESSAGE="Do you want to remove the product?";
	private final int INCLUDED=0;
	private final int TO_BE_INCLUDED=1;
	
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
		
	}
	private Presenter presenter;
	@UiField
	HTMLPanel curProductsPanel;
	@UiField SimplePanel rightPanel;
	@UiField InlineLabel lblMainMessage;
    @UiField
    Label lblGroupName;//,lblProductsMessage,lblMessageGroup;
	public AddProductsToGroupViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		lblMainMessage.setText("");
		btnAddSelectedProducts.addClickHandler(this);
		btnSave.addClickHandler(this);
	}
	@UiField Button btnAddSelectedProducts,btnSave;
	@UiField Style style;
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	//@Override
	//public void setGroupData(String name,String[] namesProducts,String[] strSn){
		//dataPanel.clear();
		//dataPanel.add(new HTMLPanel(name));
	//}
	@Override
	public void setSelectedProductData(String name,String[] namesProducts,String[] strSn){
		//selectedProductsPanel.clear();
		
		boolean isEven=true;
		ProductItem item;
		int id=0;
		for(String productName:namesProducts){
			item = new ProductItem(strSn[id]+" ",productName,TO_BE_INCLUDED,id);
			if(isEven) item.addStyleName(style.evenRow());
			else item.addStyleName(style.oddRow());
			if(isEven) isEven = false;
			else isEven =true;			
			//selectedProductsPanel.add(item);
			id++;

		}	
	}
	@Override
	public void setGroupData(String name,String[] namesProducts,String[] strSn){
		lblGroupName.setText(name);
		//showing the current products on the current group:
		curProductsPanel.clear();
		//selected products panel:
		//selectedProductsPanel.clear();
		//HTMLPanel panel;
		//HTMLPanel clearPanel;
		//Button btn;
		//RemoveProductFromGroupHandler rmHandler = new RemoveProductFromGroupHandler();
		int id=0;
		boolean isEven=true;
		ProductItem item;
		for(String productName:namesProducts){
			//pankaj note: 14 02 06: Now there is no need of this INCLUDED thing:
			//so think of remove this:
			item = new ProductItem(strSn[id]+" ",productName,INCLUDED,id);
			if(isEven) item.addStyleName(style.evenRow());
			else item.addStyleName(style.oddRow());
			//serial number:
			//panel = new HTMLPanel(strSn[id]+" ");
			//panel.setStyleName(style.curProduct());
			//if(isEven) panel.addStyleName(style.evenRow());
			//else panel.addStyleName(style.oddRow());
			// product name:
			//panel.add(new InlineLabel(productName));
			// remove button:
			//btn=new Button("Remove");
			//btn.addStyleName(style.floatRight());
			//btn.getElement().setId(String.valueOf(id));
			//btn.addClickHandler(rmHandler);
			//panel.add(btn);
			//clearPanel=new HTMLPanel("");
			//clearPanel.setStyleName(style.clearBoth());
			//panel.add(clearPanel);
			if(isEven) isEven = false;
			else isEven =true;			
			curProductsPanel.add(item);
			id++;
		}
	}
	@Override
	public AcceptsOneWidget get(String id){
		if(id.equals("right-panel")) return rightPanel;
		else return null;
	}
	@Override
	public void onClick(ClickEvent event) {
		 Widget sender = (Widget) event.getSource();
		 if(sender==btnAddSelectedProducts){
			 presenter.includeProducts();
		 }else if(sender==btnSave){
			 presenter.save();
		 }
		
	}
	
	private class ProductItem extends HTMLPanel implements ClickHandler{
		private int id;
		private int type;
		public ProductItem(String sn,String productName,int type,int id) {
			//serial number
			super(sn);
			this.type=type;
			setStyleName(style.curProduct());
			this.id=id;
			//adding the product name:
			add(new InlineLabel(productName));
			// adding the remove button:
			Button btn=new Button("Remove");
			btn.addStyleName(style.floatRight());
			btn.addClickHandler(this);
			add(btn);
			
			
		}
		@Override
		public void onClick(ClickEvent event) {
			Button btn=(Button) event.getSource();
			//confirmProductDelete(DESELECT_PRODUCT_MESSAGE);
			if(btn.getText().equals("Remove")){
				confirmProductDelete(type,id,DESELECT_PRODUCT_MESSAGE);
			}
			
		}
		
	}
	
	private void confirmProductDelete(int type,int id,String message){
		
		boolean option=Window.confirm(message);
		if(option){
			switch(type){
			case INCLUDED:
				//user has chosen to delete product that is
				presenter.removeProduct(id);
				//in the group
				break;
			case TO_BE_INCLUDED:
				//user wants to deselect a product in the 
				//tobe included list:
				presenter.removeProductFromProposed(id);
				break;
			}
			//if(presenter!=null) presenter.deleteProductFromGroup(strId);
		}
		//dialogBox.center();		
	}
	//for deleting a product from a group:
	private class ProductDelFrmGroupClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Button btn=(Button) event.getSource();
			//this is some error prone area
			try{
				String strId=btn.getElement().getId();
				confirmProductDelete(0,0,strId);
				//System.out.println("id:"+strId);
				
			}catch(Exception e){
				//attachedProductsPanel.clear();
				//attachedProductsPanel.add(new HTML("Error in processing!"));
			}
			
		}
		
	}
	@Override
	public void info(String message,int code){
		switch(code){
		case 0://a message that is shown on the main message area
			lblMainMessage.setText(message);
			break;
		}
	}
}
