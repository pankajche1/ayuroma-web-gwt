package com.ayurma.ayuromaweb.client.admin.view.resource;



import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;


public interface AdminMainClientBundle extends ClientBundle{
	public static final AdminMainClientBundle INSTANCE =  GWT.create(AdminMainClientBundle.class);
	@Source("css/adminCssGlobal.css")
	GlobalStyle style();
	/*
	//gif image for showing suggest oracle ajax notification:
    @Source("images/ajax-loader-green-arrows.gif")
    ImageResource getRotatingArrow();
    @Source("images/searchIcon.png")
    ImageResource getSearchLense();
    @Source("images/ajax-loader.gif")
    ImageResource getLoadingAnimation();
    */
	 public interface GlobalStyle extends CssResource{
	  	String rootPanel();
	  	String mainContent();
	  	String leftBar();
	  	String heading1();
	  	String heading2();
	  	String sectionHeading();
	  	String fieldLabel();
	  	
	  	
	 }
}
