package com.ayurma.ayuromaweb.client.view;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ayurma.ayuromaweb.client.layout.ProductDetailsLayout;
import com.ayurma.ayuromaweb.client.view.resource.MainClientBundle;
import com.ayurma.ayuromaweb.client.view.widgets.AyuromaAjaxMole;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
/*import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;*/
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
//import com.google.gwt.user.client.ui.DecoratorPanel;
//import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
//import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

import com.google.gwt.user.client.ui.Widget;

public class ProductDetailsViewImpl<T> extends Composite implements IProductDetailsView<T>{
	
	private static ProductDetailsViewImplUiBinder uiBinder = GWT
			.create(ProductDetailsViewImplUiBinder.class);

	@SuppressWarnings("rawtypes")
	interface ProductDetailsViewImplUiBinder extends
			UiBinder<Widget, ProductDetailsViewImpl> {
	}
	public interface MyStyle extends CssResource{
		//chemical-details table style
		String chemicalDatails();
		//product-img
		String imageProduct();
		//these two added on 131217
		String productImage();
		String imagePanel();
		String noImagePanel();
		//link-enquiry
		String linkEnquiry();
		//product-details-data (table)
		String productDetailsData();
		//value-name
		String valueName();
		//value
		String value();
		//heading2
		String heading2();
		//table cell style:
		String cell1();
		String oddRow();
		String evenRow();
		String inStock();
		String notInStock();
		String stockInfoPanel();
		String noImage();
		String withImage();
		String tableCaption();
	}
	//private Image ajaxAnim= new Image(MainClientBundle.INSTANCE.getLoadingAnimation());
	private String imageServletUrl="ayuromaweb3/serveImage?blob-key=";
	private Presenter<T> presenter;
	private AyuromaAjaxMole ajaxMole;
	private List<ProductDetailsLayout<T>> listLayouts; 
	@UiField HTMLPanel descriptionPanel,imagePanel,ajaxNotificationPanel,contentPanel;
	@UiField MyStyle style1;
	//@UiField HTML infoTable;
	//@UiField HTMLPanel infoTablePanel;
	@UiField HTMLPanel specificationsTablePanel;
	private HTMLPanel basicInfoPanel,specPanel,usesPanel;
	//@UiField Button btnSendEnquiry, btnGetReports;
	@UiField HTMLPanel btnsPanel;
	@UiField Label lblHeading;
	//@UiField Image image;
	@UiField TabPanel tabPanel;
	private Map<String,String> styles;
	//@UiField FlexTable tblSpecifications,tblBasicInfo;
	@UiHandler("sendEnquiry")
	public void gotoSendEnquiry(ClickEvent e){
		presenter.gotoEnquiry();
		
	}
	@UiHandler("getReports")
	public void gotoGetReporst(ClickEvent e){
		presenter.gotoGetReports();
	}
	public ProductDetailsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//the ajaxMole:
		ajaxMole = new AyuromaAjaxMole();
		//set the tabs panel data:
		basicInfoPanel=new HTMLPanel("");
		basicInfoPanel.setStyleName(style1.chemicalDatails());
		specPanel=new HTMLPanel("");
		specPanel.setStyleName(style1.chemicalDatails());
		usesPanel=new HTMLPanel("");
		tabPanel.setAnimationEnabled(true);
		
		tabPanel.add(basicInfoPanel,"Basic Info");
		tabPanel.add(specPanel,"Specifications");
		tabPanel.add(usesPanel,"Uses");
		tabPanel.selectTab(0);
		
		//setting the style list:
		styles = new HashMap<String,String>();
		styles.put("chemical-details",style1.chemicalDatails());//0
		styles.put("image-product",style1.imageProduct());//1
		styles.put("link-enquiry",style1.linkEnquiry());//2
		styles.put("productDetailData",style1.productDetailsData());//3
		styles.put("valueName",style1.valueName());//4
		styles.put("value",style1.value());//5
		styles.put("heading2",style1.heading2());//6
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter=presenter;
		
	}
	@Override
	public void setData(T data){
		contentPanel.setVisible(true);
		/*
		StringBuilder sb = new StringBuilder();
		List<String> styles = new ArrayList<String>();
		styles.add(style1.chemicalDatails());//0
		styles.add(style1.imageProduct());//1
		styles.add(style1.linkEnquiry());//2
		styles.add(style1.productDetailsData());//3
		styles.add(style1.valueName());//4
		styles.add(style1.value());//5
		styles.add(style1.heading2());//6
		render(data,sb,styles);
		*/
		
		
		StringBuilder sb = new StringBuilder();
		//0 get the name:
		listLayouts.get(0).render(data, sb,styles);
		lblHeading.setText(sb.toString());
		//1 get the image url:
		sb = new StringBuilder();
		listLayouts.get(1).render(data, sb,styles);
		String imageUrl=sb.toString();
		if(!imageUrl.equals("")){
			Image image = new Image(imageUrl);
			image.setAltText("Image");
			image.setStyleName(style1.productImage());
			imagePanel.add(image);
			imagePanel.setStyleName(style1.imagePanel());
		}else imagePanel.setStyleName(style1.noImagePanel());
		//2 setting the decription:
		sb = new StringBuilder();
		listLayouts.get(2).render(data, sb,styles);
		descriptionPanel.getElement().setInnerHTML(sb.toString());
		//3 setting the basic info (common name, bot name, country of origin etc)
		sb = new StringBuilder();
		listLayouts.get(3).render(data, sb,styles);
		basicInfoPanel.getElement().setInnerHTML(sb.toString());
		int h1=basicInfoPanel.getOffsetHeight();

		//4 getting the specification table which contains refractive index data etc.
		sb = new StringBuilder();
		listLayouts.get(4).render(data, sb,styles);
		specPanel.getElement().setInnerHTML(sb.toString());
		int h2=specPanel.getOffsetHeight();

		//5 getting the uses of the product:
		sb = new StringBuilder();
		listLayouts.get(5).render(data, sb,styles);
		usesPanel.getElement().setInnerHTML(sb.toString());
		int h3=usesPanel.getOffsetHeight();

		int hMax=0;
		if(h1>h2){
			if(h1>h3)hMax=h1;
			else hMax=h3;
		}else{
			if(h2>h3) hMax=h2;
			else hMax=h3;
		}
		//setting the height in the tabs panel:
		/*
		basicInfoPanel.getElement().getStyle().setHeight(hMax, Style.Unit.PX);
		specPanel.getElement().getStyle().setHeight(hMax, Style.Unit.PX);
		usesPanel.getElement().getStyle().setHeight(hMax, Style.Unit.PX);
		*/
		/*
		//tables:
		//tblSpecifications.removeAllRows();
		//tblBasicInfo.removeAllRows();
		
		//dataPanel.add(new HTML(sb.toString()));
		//product name as main heading of the page
		lblHeading.setText(data.getName());
		//product description and image of the product
		dataPanel.add(getBasicInfoPanel(data));
		//adding the general information table about the product like common name etc:
		Label lblBasicInfoCaption = new Label("Basic Information");
		lblBasicInfoCaption.setStyleName(style1.tableCaption());
		infoTablePanel.add(lblBasicInfoCaption);
		infoTablePanel.add(getProductInfoTable(data));
		//the data like refractive index data containing table:
		if(data.getIsSection2Displayed()==1){
			//add the specification table
			Label lblTableCaption = new Label("Specifications");
			lblTableCaption.setStyleName(style1.tableCaption());
			specificationsTablePanel.add(lblTableCaption);
			specificationsTablePanel.add(getProductSpecificationsTable(data));
		}
		*/

	}
	/*
	private HTMLPanel getBasicInfoPanel(ProductDetails data){
		HTMLPanel panel = new HTMLPanel("");
		HTMLPanel container = new HTMLPanel("");
		//add the image of the product:
		if(!data.getImageUrl().equals("")){
			Image productImage = new Image(imageServletUrl+data.getImageUrl());
			productImage.setStyleName(style1.imageProduct());
			panel.add(productImage);
			container.setStyleName(style1.withImage());
		}else container.setStyleName(style1.noImage());
		
		//description of the product:
		HTML description = new HTML();
		description.setHTML(data.getDescription());
		container.add(description);
		HTMLPanel inStockPanel = new HTMLPanel("In Stock:");
		inStockPanel.setStyleName(style1.stockInfoPanel());
		InlineLabel lblInStock = new InlineLabel();
		lblInStock.setText(data.getIsInStock()?"Yes":"No");
		lblInStock.setStyleName(data.getIsInStock()?style1.inStock():style1.notInStock());
		inStockPanel.add(lblInStock);
		container.add(inStockPanel);
		container.add(btnsPanel);
		panel.add(container);
		return panel;
	}
	*/
	/*
	private FlexTable getProductInfoTable(ProductDetails data){
		//DecoratorPanel contentTableDecorator = new DecoratorPanel();
		
		String[] rowStyles={style1.oddRow(),style1.evenRow()};
		int iStyle=0;
		int[] listDisplay=data.getListDisplay();
		//table for the Basic Information section:
		//*
		 //TableElement table = Document.get().createTableElement();
		 //TableSectionElement tbody;
		 //table.appendChild(tbody = Document.get().createTBodyElement());
		 //TableRowElement row;
		 //TableCellElement cell; 
		// 
		 //Grid grid = new Grid();
		FlexTable flexTable= new FlexTable();
		
		//grid.getCellFormatter().setWidth(0, 0, "100%");
		
		 int iRow=0;
		 //creating a row
		 //1 common name
		 if(listDisplay[0]==1){
			//System.out.println(data.getCommonName());
			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 flexTable.setHTML(iRow, 0, "Common Name");
			 flexTable.setHTML(iRow, 1, data.getCommonName());
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
		 }
		 //2 bot name
		 if(listDisplay[1]==1){
			 //System.out.println(data.getBotanicalName());
			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 
			 flexTable.setHTML(iRow, 0, "Botanical Name");
			 flexTable.setHTML(iRow, 1, data.getBotanicalName());
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
		 }
		 //3 country of origin
		 if(listDisplay[2]==1){
			 //System.out.println(data.getCountryOfOrigin());
			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 flexTable.setHTML(iRow, 0, "Country Of Origin");
			 flexTable.setHTML(iRow, 1, data.getCountryOfOrigin());
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
		 }
		 //4 chemical constituents
		 if(listDisplay[3]==1){

			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 flexTable.setHTML(iRow, 0, "Chemical Constituents");
			 flexTable.setHTML(iRow, 1, data.getChemicalConstituent());
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
			 
		 }
		 //5 source
		 if(listDisplay[4]==1){

			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 flexTable.setHTML(iRow, 0, "Source");
			 flexTable.setHTML(iRow, 1, data.getPlantPart());
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
			 
		 }
		 //6 separation method
		 if(listDisplay[5]==1){

			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 flexTable.setHTML(iRow, 0, "Separation methods");
			 flexTable.setHTML(iRow, 1, data.getSaparationMethod());
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
			 
		 }
		 //7 color appearance
		 if(listDisplay[6]==1){

			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 flexTable.setHTML(iRow, 0, "Color and Appearance");
			 flexTable.setHTML(iRow, 1, data.getColorAndAppearance());
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
			 
		 }
		 //8 solubility
		 if(listDisplay[7]==1){

			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 flexTable.setHTML(iRow, 0, "Solubility");
			 flexTable.setHTML(iRow, 1, data.getSolubility());
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
			 
		 }
		
		 return flexTable;
		 //infoTable.setHTML(table.getInnerHTML());
		
		 
		 
		
		
	}
*/
	/*
	private FlexTable getProductSpecificationsTable(ProductDetails data){
		String[] rowStyles={style1.oddRow(),style1.evenRow()};
		int iStyle=0;
		//int[] listDisplay=data.getListDisplay();
		String[] strValues=data.getStrValues();//these are the values of sp grav,refrec index, opt rot and flash point each two values;
		FlexTable flexTable= new FlexTable();
		
		 int iRow=0;
		
		 //1 refractive index
		 
		 if(data.getIsRfrIndexDspl()==1){

			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 String cellText="Refractive Index at "+data.getTempRefracIndex();
			 if(data.getTempUnitRfrIndex()==0) cellText+="&#176;C";
			 else cellText+="&#176;F";
			 flexTable.setHTML(iRow, 0, cellText);
			 if(data.getIsRfrIndexRange()==0){
				 	//str+=t.getRefractiveIndex();
				 	cellText=strValues[2];
				 }else{
				 	//str+=t.getRefractiveIndex1()+" to "+t.getRefractiveIndex2();
					 cellText=strValues[2]+" to "+strValues[3];
				 }

			 flexTable.setHTML(iRow, 1, cellText);
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
		}
		 //2 optical rotation
		 if(data.getIsOptRotDspl()==1){
			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 String cellText="Optical Rotation";
			 flexTable.setHTML(iRow, 0, cellText);
			 if(data.getIsOptRotRange()==0){
				 cellText=strValues[4]+"&#176;";
			 }else{
				 cellText=strValues[4]+"&#176; to "+strValues[5]+"&#176;";
			 }
			 //setting the value:
			 flexTable.setHTML(iRow, 1, cellText);
			
			 //style of the current row
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
			 }
		 //3 specific gravity
		 if(data.getIsSpGravDspl()==1){
			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 flexTable.setHTML(iRow, 0, "Specific Gravity at "+data.getTempSpGrav()+
					 ((data.getTempUnitSpGrav()==0)?"&#176;C":"&#176;F"));
			 flexTable.setHTML(iRow, 1, ((data.getIsSpGravRange()==0)?strValues[0]:strValues[0]+" to "+strValues[1]));
			 //style of the current row
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 iRow++;
			 iStyle=(iStyle>0)?0:1;
			 }
		 //4 flash point
		 if(data.getIsFlashPointDspl()==1){
			 flexTable.getCellFormatter().addStyleName(iRow, 0, style1.cell1());
			 flexTable.getFlexCellFormatter().setVerticalAlignment(iRow,0,HasVerticalAlignment.ALIGN_MIDDLE);
			 String cellText="Flash Point ("+((data.getTempUnitFlashPoint()==0)?"&#176;C":"&#176;F")+")";
			 
			
				 
			 //if(data.getTempUnitFlashPoint()==0) cellText+="&#176;C)";
			// else cellText+="&#176;F)";
			 flexTable.setHTML(iRow, 0, cellText);
			
			 if(data.getIsFlshPntRange()==0){
			 //str+=t.getFlashPoint();
				 cellText=strValues[6];
			 }else{
				 cellText=strValues[6]+" to "+strValues[7];
			 
			 }

			 flexTable.setHTML(iRow, 1, cellText);
			 //style of the current row
			 flexTable.getRowFormatter().addStyleName(iRow, rowStyles[iStyle]);
			 
			 iRow++;
			 iStyle=(iStyle>0)?0:1;

			 }
		 return flexTable;
	}
	*/
	/*
	private void render(ProductDetails t, StringBuilder sb,List<String> styles) {
	      String[] subStr=null;
	      String description=t.getDescription();
	      /*
			///String chemicalDatails(); [0]
			//product-img
			///String imageProduct(); [1]
			//link-enquiry
			///String linkEnquiry(); [2]
			//product-details-data (table)
			///String productDetailsData(); [3]
			//value-name
			///String valueName(); [4]
			//value
			///String value(); [5]
			
			
		String str="";
String imgUrl=t.getImageUrl();
int[] listDisplay=t.getListDisplay();
String[] strValues=t.getStrValues();//these are the values of sp grav,refrec index, opt rot and flash point each two values;
String[] rowStyleName={"row-odd","row-even"};
String[] rowStyles={"background-color:rgb(238, 255, 238);","background-color:rgb(230, 250, 230);"};

int iStyle=0;

//YOU CAN USE THIS WAY TOO:
//sb.append("<div class='chemical-details'>"+"<h2>"+t.getName()+"</h2>");
str="<div class='"+styles.get(6)+"'>"+t.getName()+"</div>";//heading2
str+="<table  class='"+styles.get(0)+"'><tbody><tr>";
// str="<div class='chemical-details'>"
// +"<h2>"+t.getName()+"</h2>";
if(imgUrl==null||imgUrl.equals("")){
	 str+="<td></td>";
}else{
	
	 str+="<td style='vertical-align:top;'><img style='border: 1px solid rgb(204, 204, 204);" +
	 		//"padding:10px;' class='product-img' src='ayuromaweb/serveImage?blob-key="+t.getImageUrl()+"'></img></td>";
	 "padding:10px;' class='"+styles.get(1)+"' src='"+imageServletUrl+t.getImageUrl()+"'></img></td>";
	// str+="='ayuromaweb/serveImage?blob-key="+t.getImageUrl()+"' alt='image here'></img>";
}
//<td>
//sb.append("<div class='prouduct-info-container'>");
//str+="<div class='prouduct-info-container'>";

//<!-- decription of the product -->
str+="<td  style='padding-left:10px;padding-top:0;vertical-align:top;'>";
str+=description;
str+="</td>";
//</td></tr>
//product in stock info:
//<tr>
//<td></td><td>
str+="</tr><tr><td></td><td style='padding:10px;background-color:rgb(230, 250, 230);'>";
str+="In Stock: ";
str+="<span style='color:"+(t.getIsInStock()?"rgb(57, 114, 73)":"rgb(150, 57, 73)")+";font-weight:bold;'>";
str+=(t.getIsInStock()?"Yes":"No");
str+="</span>";
str+="</td></tr>";
//product enquiry information:
//<tr>
str+="<tr><td></td><td style='padding:10px;'>";
//PANKAJ NOTE: The one line was commented 130922 17:06 hrs
//str+="<a class='link-enquiry' href='#products/enquiry/chemicals/details&"+source+"&"+t.getKeyChemical()+"'>Enquiry</a>";
str+="<a class='"+styles.get(2)+"' href='#products/enquiry/chemicals/details&"+t.getKeyChemical()+"'>Enquiry</a>";
str+="</td></tr>";
//table for basic info:
str+="<tr><td></td><td style='padding-left:10px;'>" +
"<table  class='"+styles.get(3)+"' style='font-family:arial;'>"
+"<caption style='text-align:left;margin-bottom:0px;padding:0px;'><h3>Basic Information</h3></caption>"
+"<tbody>";
//0 common name
iStyle=0;
if(listDisplay[0]==1){//common name
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Common Name </td><td  class='"+styles.get(5)+"'>"+t.getCommonName()+"</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//1 bot name:
if(listDisplay[1]==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Botanical Name: </td><td  class='"+styles.get(5)+"' style='font-style:italic;'>"+t.getBotanicalName()+"</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//2 country of origin
if(listDisplay[2]==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Country of Origin: </td><td  class='"+styles.get(5)+"'>"+t.getCountryOfOrigin()+"</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//3 chemical constituents
if(listDisplay[3]==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Major Chemical constituents:</td><td  class='"+styles.get(5)+"'>"+t.getChemicalConstituent()+"</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//4 source
if(listDisplay[4]==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Obtained from:</td><td  class='"+styles.get(5)+"'>"+t.getPlantPart()+"</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//5 separation method
if(listDisplay[5]==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Method of separation:</td><td  class='"+styles.get(5)+"'>"+t.getSaparationMethod()+"</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//6 color and appearance
if(listDisplay[6]==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Color and appearance:</td><td class='"+styles.get(5)+"'>"+t.getColorAndAppearance()+"</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//7 order and smell
if(listDisplay[7]==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Order </td><td  class='"+styles.get(5)+"'>"+t.getOrder()+"</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//8 solubility
if(listDisplay[8]==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Solubility</td><td  class='"+styles.get(5)+"'>"+t.getSolubility()+"</td></tr>";;
iStyle=(iStyle>0)?0:1;
}

str+="</tbody>"
//+"<tfoot>"
//+"<tr><td colspan='2'>Info: no info here to be shown in this way</td></tr>"
//+"</tfoot>"
+"</table>";
//up to here the table for the basic information data>
if(t.getIsSection2Displayed()==1){

iStyle=0;
str+="<table  class='"+styles.get(3)+"'>"
	+"<caption style='text-align:left;margin-bottom:0px;padding:0px;'><h3>Specification</h3></caption>";

//numerical data:
//if refractive index is single value or it is in a range:
if(t.getIsRfrIndexDspl()==1){


str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Refractive Index at "+t.getTempRefracIndex();

if(t.getTempUnitRfrIndex()==0) str+="&#176;C";
else str+="&#176;F";
str+="</td><td  class='"+styles.get(5)+"'>";

if(t.getIsRfrIndexRange()==0){
	//str+=t.getRefractiveIndex();
	str+=strValues[2];
}else{
	//str+=t.getRefractiveIndex1()+" to "+t.getRefractiveIndex2();
	str+=strValues[2]+" to "+strValues[3];
}
str+="</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//optical rotation:
if(t.getIsOptRotDspl()==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Optical Rotation</td><td  class='value'>";
if(t.getIsOptRotRange()==0){
	str+=strValues[4]+"&#176;";
	//if(t.getOpticalRotation()>0){
		//str+="+"+t.getOpticalRotation()+"&#176;";
		//str+="+"+strValues[4]+"&#176;";
	//}else{ 
		//str+=t.getOpticalRotation()+"&#176;";
		//str+="-"+strValues[4]+"&#176;";
	//}
}else{
	str+=strValues[4]+"&#176;";
	//if(t.getOpticalRotation1()>0){
		//str+="+"+t.getOpticalRotation1()+"&#176;";
	//}else{ str+=t.getOpticalRotation1()+"&#176;";}
	str+=" to ";
	str+=strValues[5]+"&#176;";
	//if(t.getOpticalRotation2()>0){
		//str+="+"+t.getOpticalRotation2()+"&#176;";
	//}else{ str+=t.getOpticalRotation2()+"&#176;";}
}

str+="</td></tr>";
iStyle=(iStyle>0)?0:1;
}
//specific gravity:
if(t.getIsSpGravDspl()==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Specific Gravity at "+t.getTempSpGrav();
if(t.getTempUnitSpGrav()==0) str+="&#176;C";
else str+="&#176;F";
str+="</td><td  class='"+styles.get(5)+"'>";
if(t.getIsSpGravRange()==0){
//str+=t.getSpecificGravity();
str+=strValues[0];
}else{
str+=strValues[0];
//str+=t.getSpecificGravity1();
str+=" to ";
str+=strValues[1];
//str+=t.getSpecificGravity2();
}
str+="</td></tr>";
iStyle=(iStyle>0)?0:1;
}

//flash point:
if(t.getIsFlashPointDspl()==1){
str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get(4)+"'>Flash Point (";
if(t.getTempUnitFlashPoint()==0) str+="&#176;C";
else str+="&#176;F";
str+=")</td><td  class='"+styles.get(5)+"'>";
//System.out.println("isFlashPointRange="+t.getIsFlshPntRange());
if(t.getIsFlshPntRange()==0){
//str+=t.getFlashPoint();
str+=strValues[6];
}else{
str+=strValues[6];
//str+=t.getFlashPoint1();
str+=" to ";
//str+=t.getFlashPoint2();
str+=strValues[7];
}

str+="</td></tr>";
iStyle=(iStyle>0)?0:1;
}

str+="</table>";
}//if property data table is to be shown
//upto here section 2 (at this Property data) table
//uses section:
if(!t.getUses().equals("")){
str+="<h3>Uses</h3>";
str+=t.getUses();
}//uses section ends here>
//str+="</div>"

str+="</td></tr></tbody></table>";



		sb.append(str);
		
	}
	*/
	@Override
	public void scrollTo(int left,int top){
		Window.scrollTo(left, top);
	}
	@Override
	public void setListLayouts(List<ProductDetailsLayout<T>> listLayouts) {
		this.listLayouts = listLayouts;
	}
	@Override
	public void showAjaxLoading() {
		ajaxNotificationPanel.add(ajaxMole);
		
	}
	@Override
	public void stopAjaxLoading(){
		ajaxMole.removeFromParent();
	}
	/*
	 * resets the view to default:
	 */
	@Override
	public void reset(){
		contentPanel.setVisible(false);
		//clearing the already existing data:
		imagePanel.clear();
		//dataPanel.clear();
		//infoTablePanel.clear();
		specificationsTablePanel.clear();
		//clearing the tab panel:
		basicInfoPanel.clear();
		specPanel.clear();
		usesPanel.clear();
		//default height:
		/*
		basicInfoPanel.getElement().getStyle().setHeight(200, Style.Unit.PX);
		specPanel.getElement().getStyle().setHeight(200, Style.Unit.PX);
		usesPanel.getElement().getStyle().setHeight(200, Style.Unit.PX);
		*/
		//setting the tab at the first tab button:
		tabPanel.selectTab(0);
	}
	

}
