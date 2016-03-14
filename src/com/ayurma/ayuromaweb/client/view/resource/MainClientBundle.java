package com.ayurma.ayuromaweb.client.view.resource;


import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;



public interface MainClientBundle extends ClientBundle  {
	public static final MainClientBundle INSTANCE =  GWT.create(MainClientBundle.class);
	
	@Source("css/cssGlobal.css")
	GlobalStyle style();
	//logo:
	@Source("images/logo140417_001.png")
	ImageResource logo1();
	//gif image for showing suggest oracle ajax notification:
    @Source("images/ajax-loader-green-arrows.gif")
    ImageResource getRotatingArrow();
    @Source("images/searchIcon.png")
    ImageResource getSearchLense();
    @Source("images/ajax-loader.gif")
    ImageResource getLoadingAnimation();
    //body background image:
	@Source("images/background/body-bg2.png")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource bgBody1();
	//blue strip background:
	@Source("images/background/blu_stripes.png")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource blu_stripes();
	//cartographer dark background:
	@Source("images/background/cartographer.png")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource cartographer();	
	//dark_Tire
	@Source("images/background/dark_Tire.png")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource dark_Tire();	
	//argyle
	@Source("images/background/argyle.png")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource argyle();	
	//green-fibers
	@Source("images/background/green-fibers.png")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource greenFibers();
	//blue leaves
	@Source("images/background/blue-leaves.jpg")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource blueLeaves();
	public interface GlobalStyle extends CssResource{
	  	String rootPanel();
	  	String contentPanel();
	  	String bgBody1();
	  	String bgBlueStripes();
	  	String cartographer();
	  	String darkTire();
	  	String argyle();
	  	String greenFibers();
	  	String blueLeaves();
	  	
	}
}
