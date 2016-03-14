package com.ayurma.ayuromaweb.client.activity;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.activity.widget.Widget1Activity;
import com.ayurma.ayuromaweb.client.layout.ProductDetailsLayoutImpl;
import com.ayurma.ayuromaweb.client.place.EnquiryPlace;
import com.ayurma.ayuromaweb.client.place.ProductDetailsPlace;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.utils.ProductDetailsDataProvider;
import com.ayurma.ayuromaweb.client.view.ILayout1View;
import com.ayurma.ayuromaweb.client.view.IProductDetailsView;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
//import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class ProductDetailsActivity extends AbstractActivity implements IProductDetailsView.Presenter<ProductDetails>,
											IProductDetailsActivity{
	private IProductDetailsView<ProductDetails> view;
	private ILayout1View rootPanel;
	
	private Cache cache;
	private final PlaceController placeController;
	private ProductDetailsPlace place;
	private final DataServiceAsync rpcService;
	private Provider<EnquiryPlace> enquiryPlaceProvider;
	private ProductDetails targetData;
	
	private SideBar1Activity sidebar1Activity;
	
	private Provider<Widget1Activity> widgetActivity1Provider;
	
	private AppInitData appInitData;
	private String imageServletUrl;
	private  ProductDetailsDataProvider dataProvider;
	
	
	@Inject
	public ProductDetailsActivity(ILayout1View rootPanel,
			IProductDetailsView<ProductDetails> view, Cache cache,
			DataServiceAsync rpcService,
			Provider<EnquiryPlace> enquiryPlaceProvider,
			PlaceController placeController,
			SideBar1Activity sidebar1Activity,
			
			Provider<Widget1Activity> widgetActivity1Provider,
			AppInitData appInitData,
			@Named("imageServletUrl") String imageServletUrl,
			ProductDetailsLayoutImpl layouts) {
	    this.rootPanel=rootPanel;
		this.view = view;
		this.view.setPresenter(this);
		this.view.setListLayouts(layouts);
		this.cache = cache;
		this.rpcService = rpcService;
		this.enquiryPlaceProvider=enquiryPlaceProvider;
		this.placeController=placeController;
		this.sidebar1Activity=sidebar1Activity;
		this.appInitData=appInitData;
		this.widgetActivity1Provider=widgetActivity1Provider;
		this.imageServletUrl=imageServletUrl;
		dataProvider = new ProductDetailsDataProvider(this.rpcService, this.cache, this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(rootPanel.asWidget());
		
		//panel.setWidget(view.asWidget());
		
	}
	public void init(ProductDetailsPlace place){
		//1 adding the main content:
		//clear the already exiting content:
		rootPanel.scrollTo(0, 0);
		rootPanel.get("mainContent").clear();
		//reset the view to default and show ajax loading:
		view.reset();
		view.showAjaxLoading();
		rootPanel.get("mainContent").add(view.asWidget());
		
		//2 adding the side bar content:
		rootPanel.get("sidebar").clear();
		sidebar1Activity.get().clear();
		sidebar1Activity.go(rootPanel.get("sidebar"));
		int nGroups=appInitData.getProductGroupSize();
		for(int i=0;i<nGroups;i++){
			if(appInitData.getProductGroupName(i).equals("All Products")) continue;
			Widget1Activity act1 = widgetActivity1Provider.get();
			act1.setHeading(appInitData.getProductGroupName(i));
			act1.setBody(appInitData.getProductGroupText(i));
			act1.setKey(appInitData.getProductGroupKey(i));
			act1.go(sidebar1Activity.get());	
			//headTexts.add(appInitData.getProductGroupName(i));
			//bodyTexts.add(appInitData.getProductGroupText(i));
			//imageUrls.add(appInitData.getProductGroupImageUrl(i));
			//linkUrls.add(appInitData.getProductGroupKey(i));
		}
		//getting an activity:

		//setting up place:
		this.place=place;
		dataProvider.processToken(place.getPlaceName());
		/*
		try{
			Long key = Long.valueOf(place.getPlaceName());
			fetchProductDetails(key);
		}catch(NumberFormatException e){
			
		}
		*/
	}
	/*
	private void fetchProductDetails(Long key){
		//search the cache:
		ProductDetails data = cache.getProductDetails(key);
		if(data!=null){
			processResult(data);
			return;
		}
		rpcService.getProductDetails(key,new AsyncCallback<ProductDetails>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ProductDetails result) {
				cache.putProductDetails(result);

					processResult(result);	
				
			}});
	}
	*/
	/*
	private void processResult(ProductDetails result){
		targetData=result;
		
		//Timer t = new Timer(){

			//@Override
			//public void run() {
				view.stopAjaxLoading();
				view.setData(targetData);
				
			//}};
		//t.schedule(2000);
	}
	*/
	@Override
	public void gotoEnquiry(){
		EnquiryPlace place = enquiryPlaceProvider.get();
		String queryString="source=product&key="+String.valueOf(targetData.getKeyChemical());
		place.setPlaceName(queryString);
		placeController.goTo(place);

	}

	@Override
	public void gotoGetReports() {
	
		EnquiryPlace place = enquiryPlaceProvider.get();
		String queryString="source=product-report&key="+String.valueOf(targetData.getKeyChemical());
		place.setPlaceName(queryString);
		placeController.goTo(place);
	}

	@Override
	public void setData(ProductDetails data) {
		view.setData(data);
		
	}

	@Override
	public void showAjaxLoading() {
		view.showAjaxLoading();
		
	}

	@Override
	public void stopAjaxLoading() {
		view.stopAjaxLoading();
		
	}

	public ProductDetails getTargetData() {
		return targetData;
	}

	public void setTargetData(ProductDetails targetData) {
		this.targetData = targetData;
	}

}
