package com.ayurma.ayuromaweb.client.view;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.view.resource.MainClientBundle;
import com.ayurma.ayuromaweb.client.view.widgets.AyuromaAjaxMole;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.ProductGrpItemsNamedGroup;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProductGroupViewImpl extends Composite implements IProductGroupView{

	private static ProductGroupViewImplUiBinder uiBinder = GWT
			.create(ProductGroupViewImplUiBinder.class);

	interface ProductGroupViewImplUiBinder extends
			UiBinder<Widget, ProductGroupViewImpl> {
	}
	
	interface MyStyles extends CssResource{
		String item();
		String detailsLink();
		String moreLink();
		String productsTable();
		String contentTitle();
		String imagePanel();
		String groupHead();
	}
	private Presenter presenter;
	private AyuromaAjaxMole ajaxMole;
	@UiField HTMLPanel dataPanel,contentPanel,ajaxNotificationPanel;
    @UiField
    Label contentTitle;
    @UiField MyStyles style;
	//private Image ajaxAnim= new Image(MainClientBundle.INSTANCE.getLoadingAnimation());
	public ProductGroupViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//the ajaxMole:
		ajaxMole = new AyuromaAjaxMole();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	@Override
	public void showAjaxAnim() {
		contentPanel.setVisible(false);
		ajaxNotificationPanel.add(ajaxMole);
		
	}

	@Override
	public void stopAjaxAnim() {
		ajaxMole.removeFromParent();
		
	}

	@Override
	public void showData(ProductGroupItemsData data) {
		//dataPanel.clear();
		contentPanel.setVisible(true);
		//1 set the title of the content:
		contentTitle.setText(data.getName());
		String[] styles = new String[3];
		styles[0]=style.productsTable();
		styles[1]=style.imagePanel();
		styles[2]=style.groupHead();
		StringBuilder sb = new StringBuilder();
		render(data,sb,styles);
		dataPanel.add(new HTMLPanel(sb.toString()));
		//dataPanel.getElement().setInnerHTML("<h2>"+data.getName()+"</h2>");
		
		
	}
	/*
	 * reset the view to default:
	 */
	@Override
	public void reset() {
		contentPanel.setVisible(false);
		contentTitle.setText("");
		dataPanel.clear();
		
	}
	public void render(ProductGroupItemsData t, StringBuilder sb,String[] styles) {
		String str="";
		if(!t.getImageUrl().equals(""))
			str+="<div class='"+styles[1]+"'><img src='ayuromaweb3/serveImage?blob-key="+t.getImageUrl()+"' alt='image'></img></div>";
		str+="<div>"+t.getDescription()+"</div>";
		//get the data in the ProductGroupItemsData object:
		String[] itemsNames = t.getItemsNames();
		//Long[] keys = t.getItemsIds();
		//Long[] keysDetails = t.getDetailsKeys();
		//these styles will come in:
		//[0] table style [1] link style 
		if(itemsNames.length==0){
			sb.append(str);
			return;
		}
		if(t.getListGroups()!=null){
			//if the names have been grouped by initial letters
			//the table will be made based on this group
			//get the groups out:
			List<ProductGrpItemsNamedGroup> list=t.getListGroups();
			ProductGrpItemsNamedGroup groupATOF = list.get(0);
			ProductGrpItemsNamedGroup groupGTOL = list.get(1);
			ProductGrpItemsNamedGroup groupMTOR = list.get(2);
			ProductGrpItemsNamedGroup groupSTOZ = list.get(3);
			ProductGrpItemsNamedGroup groupOthers = list.get(4);
			//items from A-F:
			List<String> names=groupATOF.getItemsNames();
			
			if(names.size()>0){
				str+="<h2 class='"+styles[2]+"'>A-F</h2>";
				str+=buildTable(groupATOF.getItemsNames(),groupATOF.getItemsIds(),
						groupATOF.getDetailsKeys(),styles);
				
		    }
			//items from G-L:
			names=groupGTOL.getItemsNames();
			//System.out.println("G-L");
			if(names.size()>0){
				str+="<h2 class='"+styles[2]+"'>G-L</h2>";
				str+=buildTable(groupGTOL.getItemsNames(),groupGTOL.getItemsIds(),
						groupGTOL.getDetailsKeys(),styles);
				
		    }
			//items from M-R:
			names=groupMTOR.getItemsNames();
			//System.out.println("M-R");
			if(names.size()>0){
				str+="<h2 class='"+styles[2]+"'>M-R</h2>";
				str+=buildTable(groupMTOR.getItemsNames(),groupMTOR.getItemsIds(),
						groupMTOR.getDetailsKeys(),styles);
				
		    }
			//items from S-Z:
			names=groupSTOZ.getItemsNames();
			//System.out.println("S-Z");
			if(names.size()>0){
				str+="<h2 class='"+styles[2]+"'>S-Z</h2>";
				str+=buildTable(groupSTOZ.getItemsNames(),groupSTOZ.getItemsIds(),
						groupSTOZ.getDetailsKeys(),styles);
				
		    }
			//items having names: like alpha, beta etc:
			names=groupOthers.getItemsNames();
			//System.out.println("Others");
			if(names.size()>0){
				str+="<h2 class='"+styles[2]+"'>Others</h2>";
				str+=buildTable(groupOthers.getItemsNames(),groupOthers.getItemsIds(),
						groupOthers.getDetailsKeys(),styles);
				
		    }

		}else{
			//if names have not been grouped by names:
			str+=buildTable(t.getItemsNames(),t.getItemsIds(),
					t.getDetailsKeys(),styles);
			
			
		}
		/*
		str+="<table class='"+styles[0]+"'><tbody>";
		int iItem=0;
		int nRows=itemsNames.length/3;
		
		for(int iRow=0;iRow<nRows;iRow++){
			//creating a row:
			str+="<tr>";
			//first column:
			str+="<td><a href='#products/product/"+keys[iItem]+"'>"+itemsNames[iItem]+"</a></td>";
			//second column
			str+="<td><a href='#products/product/"+keys[iItem+1]+"'>"+itemsNames[iItem+1]+"</a></td>";
			//third column
			str+="<td><a href='#products/product/"+keys[iItem+2]+"'>"+itemsNames[iItem+2]+"</a></td>";
			//ending the row:
			str+="</tr>";
			iItem+=3;
		}
		if(iItem<itemsNames.length) str+="<tr>";
		for(int i=iItem;i<itemsNames.length;i++){
			str+="<td><a href='#products/product/"+keys[i]+"'>"+itemsNames[i]+"</a></td>";
		}
		if(iItem<itemsNames.length) str+="</tr>";
		str+="</tbody></table>";
		*/
		sb.append(str);
	}
	/**
	 * The purpose of this function is only to create List objects from array [] objects:
	 * 
	 * @param names
	 * @param keys
	 * @param datailsKeys
	 * @param styles
	 * @return
	 */
	private String buildTable(String[] names,Long[] keys,Long[] datailsKeys,String[] styles){
		List<String> nameList=new ArrayList<String>();
		List<Long> idList = new ArrayList<Long>();
		List<Long> detailsKeysList = new ArrayList<Long>();
		int i=0;
		for(String name:names){
			nameList.add(name);
			idList.add(keys[i]);
			detailsKeysList.add(datailsKeys[i]);
			i++;
		}
		
		return buildTable(nameList,idList,detailsKeysList,styles);
	}
	/**
	 * this is the main function to create html rendering string to put it on the page
	 * @param names
	 * @param keys
	 * @param datailsKeys
	 * @param styles
	 * @return
	 */
	private String buildTable(List<String> names,List<Long> keys,List<Long> datailsKeys,String[] styles){
		String str="";
		str+="<table class='"+styles[0]+"'><tbody>";
		int iItem=0;
		int nRows=names.size()/3;
		
		for(int iRow=0;iRow<nRows;iRow++){
			//creating a row:
			str+="<tr>";
			//first column:
			str+="<td><a href='#product-display:source=group&key="+keys.get(iItem)+"'>"+names.get(iItem)+"</a></td>";
			//second column
			str+="<td><a href='#product-display:source=group&key="+keys.get(iItem+1)+"'>"+names.get(iItem+1)+"</a></td>";
			//third column
			str+="<td><a href='#product-display:source=group&key="+keys.get(iItem+2)+"'>"+names.get(iItem+2)+"</a></td>";
			//ending the row:
			str+="</tr>";
			iItem+=3;
		}
		if(iItem<names.size()) str+="<tr>";
		for(int i=iItem;i<names.size();i++){
			str+="<td><a href='#product-display:source=group&key="+keys.get(i)+"'>"+names.get(i)+"</a></td>";
		}
		if(iItem<names.size()) str+="</tr>";
		str+="</tbody></table>";	
		return str;
	}
	@Override
	public void scrollTo(int left,int top){
		Window.scrollTo(left, top);
	}

}
