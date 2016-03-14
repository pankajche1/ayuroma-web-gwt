package com.ayurma.ayuromaweb.client.activity;



import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.activity.widget.SliderWidget1Activity;
import com.ayurma.ayuromaweb.client.place.HomePlace;
import com.ayurma.ayuromaweb.client.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.view.IHomeView;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderFilmItemsDTO;
import com.gmail.pankajche1.contentslider.client.slider4.ContentSlider4;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
//import com.google.inject.Provider;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class HomeViewActivity extends AbstractActivity implements IHomeView.Presenter,ContentSlider4.IAjaxLoader{
	private final IHomeView view;
	private SliderWidget1Activity sliderPresenter;
	private ContentSlider4 slider4Presenter;
	private HomePlace place;
	private AppInitData appInitData;
	private Provider<ProductGroupPlace> productGroupPlaceProvider;
	private PlaceController placeController;
	private final DataServiceAsync rpcService;
	private final String productLinkFromGroupUrl;
	private final String imageServletUrl;
	//private Provider<SliderWidget1Activity> sliderActivity;
	@Inject
	public HomeViewActivity(IHomeView view, SliderWidget1Activity sliderPresenter,
			DataServiceAsync rpcService,
			AppInitData appInitData,
			Provider<ProductGroupPlace> productGroupPlaceProvider,
			PlaceController placeController, @Named("productLinkFromGroupUrl") String productLinkFromGroupUrl,
			@Named("imageServletUrl") String imageServletUrl) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.sliderPresenter=sliderPresenter;
		this.rpcService=rpcService;
		//this.slider4Presenter=slider4Presenter;
		this.appInitData=appInitData;
		this.productGroupPlaceProvider=productGroupPlaceProvider;
		this.placeController=placeController;
		this.productLinkFromGroupUrl=productLinkFromGroupUrl;
		this.imageServletUrl=imageServletUrl;
		
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(HomePlace place){
		this.place=place;
		//now put the components on the view:
		view.scrollTo(0, 0);
		//1 putting the content slider widget:
		if(!view.isContentSliderPut()){
			//the last value is for the total items on the server data store:
			slider4Presenter= new ContentSlider4(980,220,42,4,2,2,1000,2000,100);
			SliderFilmDTO sliderFilm = appInitData.getSliderFilm();
			//System.out.println("Home, slider key:"+sliderFilm.getKey()+", total items:"+sliderFilm.getTotalItems());
			//putting a content slider on the home page:
			slider4Presenter.go(view.get("slider"));
			slider4Presenter.setSliderFilmKey(sliderFilm.getKey());
			slider4Presenter.setTotalItems(sliderFilm.getTotalItems());
			slider4Presenter.setAjaxLoader(this);
			//sliderPresenter.setSliderFilm(sliderFilm);
			setSliderItems(sliderFilm);
			slider4Presenter.start();
			view.setContentSliderPut(true);
		 
		}
		//2 putting the product group entry links
		List<String> headTexts=new ArrayList<String>();
		List<String> bodyTexts=new ArrayList<String>();
		List<String> imageUrls=new ArrayList<String>();
		List<String> linkUrls=new ArrayList<String>();
		int nGroups=appInitData.getProductGroupSize();
		for(int i=0;i<nGroups;i++){
			if(appInitData.getProductGroupName(i).equals("All Products")) continue;
				
			headTexts.add(appInitData.getProductGroupName(i));
			bodyTexts.add(appInitData.getProductGroupText(i));
			imageUrls.add(appInitData.getProductGroupImageUrl(i));
			linkUrls.add(appInitData.getProductGroupKey(i));
			
		}
		view.setProductGroupData(headTexts, bodyTexts, imageUrls, linkUrls);
	}

	@Override
	public void gotoProductGroup(String strKey) {
		ProductGroupPlace place= productGroupPlaceProvider.get();
		
		place.setPlaceName(strKey);
		placeController.goTo(place);
		
	}
	private void setSliderItems(SliderFilmDTO sliderFilm){
		List<String> imgUrls=sliderFilm.getImageUrls();
		List<String> links=sliderFilm.getLinkedItemsUrls();
		int i=0;
		for(String img:imgUrls){
			slider4Presenter.addItem(img, links.get(i));
			i++;
		}
	}

	@Override
	public void load(long sliderKey,int iStart, int nItemsPerAjax) {
		rpcService.getSliderFilmItems(sliderKey, iStart, 20,new AsyncCallback<SliderFilmItemsDTO>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(SliderFilmItemsDTO result) {
				if(result==null) System.out.println("Item got is null");
				else processSliderItems(result);
				//displayItems(result);
				
			}});
		
	}
	private void processSliderItems(SliderFilmItemsDTO dto){

		List<Long> linkedProductsKeys=dto.getProductKeys();
        List<String> linkedProductsUrls= new ArrayList<String>();
        List<String> imageUrls = new ArrayList<String>();
        List<String> images = dto.getImageUrls();
        for(String image:images){
        	imageUrls.add(imageServletUrl+image);
        }
       for(Long key:linkedProductsKeys){
    	   linkedProductsUrls.add(productLinkFromGroupUrl+key);
       }
       /*
       for(String imgUrl:imageUrls){
    	   System.out.println("image-url="+imgUrl);
       }
       for(String url:linkedProductsUrls){
    	   System.out.println("url="+url);
       }*/
       slider4Presenter.setAjaxData(imageUrls, linkedProductsUrls, dto.getnTotalItems());
	}

}
