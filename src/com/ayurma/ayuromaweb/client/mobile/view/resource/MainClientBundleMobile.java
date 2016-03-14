package com.ayurma.ayuromaweb.client.mobile.view.resource;



import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;


public interface MainClientBundleMobile extends ClientBundle {
	public static final MainClientBundleMobile INSTANCE =  GWT.create(MainClientBundleMobile.class);
	@Source({"css/cssGlobalMobile.css"})
	GlobalStyle style();
	@Source({"css/product.css"})
	ProductStyle productStyle();
	@Source({"css/productDetails.css"})
	ProductDetailsStyle productDetailsStyle();
	@Source("com/ayurma/ayuromaweb/client/view/resource/header/logo140420_005_74px.png")
    ImageResource logo();
	 public interface GlobalStyle extends CssResource{
		 String rootPanel();
		 String global();
		 String logo();
		 /*
		  	String rootPanel();
		  	String contentPanel();
		  	String bgBody1();
		  	String bgBlueStripes();
		  	String cartographer();
		  	String darkTire();
		  	String argyle();
		  	String greenFibers();
		  	*/
		  	
		 }
	 public interface ProductStyle extends CssResource{
		 String productImageBox();
		 String productImage();
		 String productDescription();
		 String buttonEnquiry();
		 String spacer();
		 
	 }//Product
	 public interface ProductDetailsStyle extends CssResource{
		//chemical-details table style
			String alignCenter();
			String width_100();/*width 100% */
			String width_50();/*width 50% */
			String spacer();
		 	String rootPanel();
			String imagePanel();
			String usesPanel();
			String productName();
			String productImage();
			String productImageBox();
			
			//heading2
			String heading1();
			String heading2();
			String h2();
			String h3();
		//description:
			String productDescription();
			//basic info 
			//table
			String tableTh1();
			String tableCaption();
			String value();
			String valueName();
			//button
			String button();
		
	 }//ProductDetailsStyle interface

}
