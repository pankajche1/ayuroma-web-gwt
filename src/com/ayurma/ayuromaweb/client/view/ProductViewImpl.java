package com.ayurma.ayuromaweb.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import com.ayurma.ayuromaweb.client.layout.ProductViewLayout;
import com.ayurma.ayuromaweb.client.view.widgets.AyuromaAjaxMole;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
//import com.google.gwt.user.client.ui.Image;
//import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;


public class ProductViewImpl<T> extends Composite implements IProductView<T>{

	private static ProductViewImplUiBinder uiBinder = GWT
			.create(ProductViewImplUiBinder.class);

	
	@SuppressWarnings("rawtypes")
	interface ProductViewImplUiBinder extends UiBinder<Widget, ProductViewImpl> {
	}
	private AyuromaAjaxMole ajaxMole;
	//private String imageServletUrl="ayuromaweb3/serveImage?blob-key=";
	private Map<String,String> styles;
	interface Styles extends CssResource{
		
		String rootPanel();
	
		/*
		String sosRequestControls();
		String myMole();
		String header();
		String mainContainer();
		String requestHeading();
		String reportPanel();
		String btnPanel();
		String floatRight();
		String clearBoth();
		*/
		//product body:
		String productBody();
		String productImageBox();//0
		String productImage();//1
		String linkEnquiry();//2
		String linkMore();//3
		//animated panel:
		//String sosPanelHidden();
		//String sosPanelStart();
		//String sosPanelFinal(); 
		//error:
		String errorMessage();
		//simple message:
		//String message();
		//table style:
		String tableStyle();//4
	  
	}
	
	private Presenter<T> presenter;
	private List<ProductViewLayout<T>> layouts;
	@UiField HTMLPanel dataPanel,btnsPanel,ajaxPanel;
    @UiField
    Styles style;
	@UiField
	Label lblProductName;
	@UiField
	HTML productBody;
	@UiField Button btnSendEnquiry, btnGetReports, btnShowDetails;
	//@UiField 
	//HTML footerChild;
	public ProductViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//setting the style list:
		styles = new HashMap<String,String>();
		styles.put("image-box",style.productImageBox());//0
		styles.put("image",style.productImage());//1
		styles.put("link-enquiry",style.linkEnquiry());//2
		styles.put("link-more",style.linkMore());//3
		styles.put("table",style.tableStyle());//4
		//image-box,image,link-enquiry,link-more,table
		//creating the ajax mole:
		 ajaxMole = new AyuromaAjaxMole();
		//adding handlers to the buttons:
		btnShowDetails.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				presenter.goToDetails();
				
				
			}});
		btnSendEnquiry.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				presenter.goToEnquiry();
				
				
			}});
		btnGetReports.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				presenter.getReports();
				
				
			}});

	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter=presenter;
		
	}
	@Override
	public void setLayouts(List<ProductViewLayout<T>> layouts) {
		this.layouts = layouts;
	}

	@Override
	public void showData(T data) {
		/*
		List<String> styles=new ArrayList<String>();
		styles.add(style.productImageBox());//0
		styles.add(style.productImage());//1
		styles.add(style.linkEnquiry());//2
		styles.add(style.linkMore());//3
		styles.add(style.tableStyle());//4
		*/
		StringBuilder sb = new StringBuilder();
		layouts.get(0).render(data,sb,styles);
		lblProductName.setText(sb.toString());
		sb = new StringBuilder();
		layouts.get(1).render(data,sb,styles);
		productBody.setHTML(sb.toString());
		//rendering the footer:
		//sb = new StringBuilder();
		//renderFooter(data,sb,styles);
		//footerChild.setHTML(sb.toString());
		//buttons at the bottom to do various activities:
		/*
		btnsPanel.clear();
		Button btn = new Button("Details");
		btn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				presenter.goToDetails();
				
				
			}});
		btnsPanel.add(btn);
		btn = new Button("Enquiry");
		btn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				presenter.goToEnquiry();
				
				
			}});
		btnsPanel.add(btn);
		btnsPanel.add(new InlineLabel("COA, MSDS, GCMS reports"));
		btn = new Button("Get Reports");
		btn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				presenter.getReports();
				
				
			}});
		btnsPanel.add(btn);
		*/
		/*
		if(data!=null){
			dataPanel.getElement().setInnerHTML("<h1>"+data.getName()+"</h1>");
		}else{
			dataPanel.getElement().setInnerHTML("<h1> No Product found</h1>");
		}
		*/
		dataPanel.setVisible(true);
	}
	@Override
	public void reset(){
		dataPanel.setVisible(false);
		//the name of the product:
		lblProductName.setText("");
		productBody.setHTML("");
	}
	/*
	public void render(ChemicalData c, StringBuilder sb,
			List<String> styles) {
		 //styles 0:productImageBox 1:productImage
	      String description=c.getDescription();

	      String strHtml="<table class='"+styles.get(4)+"'><tbody><tr>";
	     // strHtml+="<div class='chemical-list-item'>"
	    		 // strHtml +="<div class='product-wrapper'>";
    			  // +"<h2>"+c.getName()+"</h2>";
	    //don't put this div if there is no image specified:
	      if(!c.getImageUrl().equals("")){
	    	  strHtml+="<td style='vertical-align:top;'>";
	    	  strHtml+= "<div class='"+styles.get(0)+"'><img class='"+styles.get(1)+"' src='"+imageServletUrl+c.getImageUrl()+"' alt='image'></img></div>";
	    	  strHtml+="</td>";
	      }else{
	    	  
	      }
	      //info about in the stock:
	      
	      strHtml+="<td style='vertical-align:top;'>"
    		       
    		        +description
    		        +"<p> In Stock: "
	      			+"<span style='color:"+(c.getIsInStock()?"rgb(57, 114, 73)":"rgb(150, 57, 73)")+";font-weight:bold;'>"
	      			+(c.getIsInStock()?"Yes":"No")
	      			+"</span></p></td>"   		    
    		        +"</tr></tbody></table>";
    		     //the next line is commented on 131028 1218
    		       /// +" <div class='clear'></div>";
    		       // +"<a class='link-more' href='#products/productDetails/source-list/"+c.getStrIdDetailsInfo()+"'>more ></a>"
    		       // +"<a class='link-enquiry' href='#products/enquiry/chemicals/list&"+c.getStrId()+"'>Enquiry</a>"
    		       //  +" <div class='clear'>" ;
    		         //"</div>";
    		       // +"</div>";
	     
    	  sb.append(strHtml);  
		
	}
	*/
	public void renderFooter(ChemicalData c, StringBuilder sb,
			List<String> styles){
  	  sb.append("<a class='"+styles.get(3)+"' href='#products/productDetails/source-list/"+c.getStrIdDetailsInfo()+"'>more ></a>"
		        +"<a class='"+styles.get(2)+"' href='#products/enquiry/chemicals/list&"+c.getStrId()+"'>Enquiry</a>");

	}

	@Override
	public void showAjaxAnim() {
		ajaxPanel.add(ajaxMole);
		
	}

	@Override
	public void stopAjaxAnim() {
		ajaxMole.removeFromParent();
		
	}
	@Override
	public void scrollTo(int left,int top){
		Window.scrollTo(left, top);
	}

	@Override
	public void info(String msg, int id, int type) {
		// TODO Auto-generated method stub
		
	}

}
