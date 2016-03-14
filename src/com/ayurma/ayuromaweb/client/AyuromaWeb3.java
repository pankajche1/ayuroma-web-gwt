package com.ayurma.ayuromaweb.client;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.activity.FooterActivity;
import com.ayurma.ayuromaweb.client.activity.HeaderActivity;
import com.ayurma.ayuromaweb.client.activity.MenubarActivity;
import com.ayurma.ayuromaweb.client.gin.AyuromaGinjector;
import com.ayurma.ayuromaweb.client.json.IAppInitData;
import com.ayurma.ayuromaweb.client.json.JSAppInitData;
import com.ayurma.ayuromaweb.client.json.JSCompanyInfo;
import com.ayurma.ayuromaweb.client.json.JSProductGroup;
import com.ayurma.ayuromaweb.client.json.JSSliderFilm;
import com.ayurma.ayuromaweb.client.json.JSSliderImageItem;
import com.ayurma.ayuromaweb.client.json.JsAddress;
import com.ayurma.ayuromaweb.client.json.JsEmail;
import com.ayurma.ayuromaweb.client.json.JsMobile;
import com.ayurma.ayuromaweb.client.json.JsTelephone;
import com.ayurma.ayuromaweb.client.mvp.AppPlaceFactory;
import com.ayurma.ayuromaweb.client.mvp.AppPlaceHistoryMapper;
import com.ayurma.ayuromaweb.client.place.HomePlace;
import com.ayurma.ayuromaweb.client.view.resource.MainClientBundle;
import com.ayurma.ayuromaweb.shared.CompanyInfoDTO;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Overflow;
//import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
//import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTMLPanel;
//import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;






/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AyuromaWeb3 implements EntryPoint {
	private AyuromaGinjector injector = GWT.create(AyuromaGinjector.class);
	private SimplePanel appWidget = new SimplePanel();
	private SimplePanel headerWidget = new SimplePanel();
	private SimplePanel menubarWidget = new SimplePanel();
	private SimplePanel footerWidget = new SimplePanel();
	private HTMLPanel rootPanel = new HTMLPanel("");
	
	
	
	@SuppressWarnings("deprecation")
	public void onModuleLoad() {
		//setting the styling of the body element:
		//System.out.println("here");
		MainClientBundle.INSTANCE.style().ensureInjected();
		Element body =RootPanel.getBodyElement();
		//body.addClassName(MainClientBundle.INSTANCE.style().bgBlueStripes());
		//body.addClassName(MainClientBundle.INSTANCE.style().greenFibers());
		//body.addClassName(MainClientBundle.INSTANCE.style().argyle());
		body.addClassName(MainClientBundle.INSTANCE.style().blueLeaves());
		//body.
		//body.setClassName(MyClientBundle.INSTANCE.myStyle().bg1());
		//body.getStyle().setBorderWidth(2, Style.Unit.PX);
		//body.getStyle().setBorderColor("#f00");
		//body.getStyle().setBorderStyle(BorderStyle.SOLID);
		body.getStyle().setMargin(0, Style.Unit.PX);
		body.getStyle().setPadding(0,Style.Unit.PX);
		//body.getStyle().setOverflow(Overflow.VISIBLE);
		//body styling done here]
		EventBus eventBus = injector.getEventBus();
        PlaceController placeController = injector.getPlaceController();

        ActivityMapper activityMapper = injector.getActivityMapper();
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);     
        activityManager.setDisplay(appWidget);
        //getting the app init data from javascript:
        IAppInitData appInitDataJs = getAppInitData();
        //getting the product groups from the above:
        JsArray<JSProductGroup> jsProductGroupsArr=appInitDataJs.getProductGroups();
        AppInitData appInitData = injector.getAppInitData();
        for(int i=0;i<jsProductGroupsArr.length();i++){
        	appInitData.addProductGroup(jsProductGroupsArr.get(i).getName(),
        			jsProductGroupsArr.get(i).getGroupItemsId(),
        			jsProductGroupsArr.get(i).getImageUrl(),
        			jsProductGroupsArr.get(i).getGroupDisplayText());
        	//System.out.println("Name:"+jsProductGroupsArr.get(i).getName()
        			//+" Key:"+jsProductGroupsArr.get(i).getGroupItemsId());
        }
        //getting the slider film items:
        //JSSliderFilm jsSliderFilm = appInitDataJs.getSliderFilm();
        /*
        //JsArray<JSSliderImageItem> sliderFilmItems=appInitDataJs.getSliderFilmItems();
        JsArray<JSSliderImageItem> sliderFilmItems=jsSliderFilm.getSliderFilmItems();
        String filmKey = jsSliderFilm.getSliderKey();
        String totalItems=jsSliderFilm.getTotolItems();
        SliderFilmDTO sliderFilm= new SliderFilmDTO();
        //feeding the slider film item data:
        Long[] sliderImages = new Long[sliderFilmItems.length()];
        List<String> imageUrls = new ArrayList<String>();
        Long[] linkedProductsKeys= new Long[sliderFilmItems.length()];
        List<String> linkedProductsUrls= new ArrayList<String>();
        //System.out.println("Before loop length:"+sliderFilmItems.length());
        for(int i=0;i<sliderFilmItems.length();i++){
        	//System.out.println("Inside loop ayuroma3:");
        	imageUrls.add("ayuromaweb3/serveImage?blob-key="+sliderFilmItems.get(i).getImageUrl());
        	//linkedProductsKeys[i]=Long.valueOf(sliderFilmItems.get(i).getLinkedProduct());
        	linkedProductsUrls.add("#product-display:source=group&key="+sliderFilmItems.get(i).getLinkedProduct());
        	
        }
       
        sliderFilm.setImageUrls(imageUrls);
        sliderFilm.setLinkedItemsUrls(linkedProductsUrls);
        sliderFilm.setImageItems(sliderImages);
        sliderFilm.setProductIds(linkedProductsKeys);
        */
        appInitData.setSliderFilm(getSliderFilm(appInitDataJs.getSliderFilm()));
        //getting the company info address mobiles etc:
        JSCompanyInfo jsCompanyInfo = appInitDataJs.getCompanyInfo();
        CompanyInfoDTO companyInfo = new CompanyInfoDTO(jsCompanyInfo.getAddressHead(),
        		jsCompanyInfo.getPin(), jsCompanyInfo.getCity(),jsCompanyInfo.getCountry());
        JsArray<JsMobile> jsMobArr=jsCompanyInfo.getMobiles();
        JsArray<JsTelephone> jsTelPhoneArr=jsCompanyInfo.getTelephones();
        JsArray<JsAddress> jsAddressLinesArr=jsCompanyInfo.getAddressLines();
        JsArray<JsEmail> jsEmailsArr=jsCompanyInfo.getEmails();
        for(int i=0;i<jsAddressLinesArr.length();i++){
        	companyInfo.addAddressLine(jsAddressLinesArr.get(i).getLine());
        	
        }      
        for(int i=0;i<jsMobArr.length();i++){
        	companyInfo.addMobile(jsMobArr.get(i).getNumber());
        	
        }
        for(int i=0;i<jsTelPhoneArr.length();i++){
        	companyInfo.addTelephone(jsTelPhoneArr.get(i).getNumber());
        	
        }
        for(int i=0;i<jsEmailsArr.length();i++){
        	companyInfo.addEmail(jsEmailsArr.get(i).getEmail());
        	
        }
        appInitData.setCompanyInfo(companyInfo);
        
        //now creating the place:
        AppPlaceFactory factory = injector.getAppPlaceFactory();
        //the default page of the site:
		HomePlace defaultPlace = factory.getHomePlace();
		//defaultPlace.setPlaceName("Product1");
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		historyMapper.setFactory(factory);
		
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);
		//now the layout:
		MainClientBundle bundle = GWT.create(MainClientBundle.class);
		bundle.style().ensureInjected();
		rootPanel.setStyleName(bundle.style().rootPanel());
		RootPanel.get().add(rootPanel);
		//putting the header:
		rootPanel.add(headerWidget);
		HeaderActivity headerActivity =injector.getHeaderActivity(); 
		headerActivity.start(headerWidget, eventBus);
		
		//search box:
		SimplePanel suggestBoxWidget = new SimplePanel();
		//adding the search box at the right corner:
		headerActivity.get("side-panel").add(suggestBoxWidget);
		injector.getSuggestBoxActivity().start(suggestBoxWidget, eventBus);
		//menu bar:
		MenubarActivity menubarActivity = injector.getMenubarActivity();
		menubarActivity.setAppInitData(appInitData);
		rootPanel.add(menubarWidget);
		menubarActivity.start(menubarWidget, eventBus);
		//this contains the main content of the application:
		appWidget.setStyleName(bundle.style().contentPanel());
		rootPanel.add(appWidget);  
		//setting the footer on the view:
		FooterActivity footerActivity = injector.getFooterActivity();
		
		rootPanel.add(footerWidget);
		footerActivity.start(footerWidget, eventBus);
		footerActivity.init();
		//setting the current history:
        historyHandler.handleCurrentHistory();
	}
	private SliderFilmDTO getSliderFilm(JSSliderFilm jsSliderFilm){
		SliderFilmDTO sliderFilm= new SliderFilmDTO();
		 JsArray<JSSliderImageItem> sliderFilmItems=jsSliderFilm.getSliderFilmItems();
		 try{
	        Long filmKey = Long.valueOf(jsSliderFilm.getSliderKey());
	        int totalItems=Integer.valueOf(jsSliderFilm.getTotolItems());
	        sliderFilm.setKey(filmKey);
	        sliderFilm.setTotalItems(totalItems);
		 }catch(NumberFormatException e){
			 
		 }
	        //feeding the slider film item data:
	        Long[] sliderImages = new Long[sliderFilmItems.length()];
	        List<String> imageUrls = new ArrayList<String>();
	        Long[] linkedProductsKeys= new Long[sliderFilmItems.length()];
	        List<String> linkedProductsUrls= new ArrayList<String>();
	        //System.out.println("Before loop length:"+sliderFilmItems.length());
	        for(int i=0;i<sliderFilmItems.length();i++){
	        	//System.out.println("Inside loop ayuroma3:");
	        	imageUrls.add("ayuromaweb3/serveImage?blob-key="+sliderFilmItems.get(i).getImageUrl());
	        	//linkedProductsKeys[i]=Long.valueOf(sliderFilmItems.get(i).getLinkedProduct());
	        	linkedProductsUrls.add("#product-display:source=group&key="+sliderFilmItems.get(i).getLinkedProduct());
	        	
	        }
	       
	        sliderFilm.setImageUrls(imageUrls);
	        sliderFilm.setLinkedItemsUrls(linkedProductsUrls);
	        sliderFilm.setImageItems(sliderImages);
	        sliderFilm.setProductIds(linkedProductsKeys);
		return sliderFilm;
	}
	private native JSAppInitData getAppInitData()/*-{
		return $wnd.appInitData;
	}-*/;
}
