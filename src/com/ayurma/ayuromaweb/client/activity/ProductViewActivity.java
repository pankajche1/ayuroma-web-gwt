package com.ayurma.ayuromaweb.client.activity;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.activity.widget.Widget1Activity;
import com.ayurma.ayuromaweb.client.layout.ProductViewLayoutImpl;
import com.ayurma.ayuromaweb.client.place.EnquiryPlace;
import com.ayurma.ayuromaweb.client.place.ProductDetailsPlace;
import com.ayurma.ayuromaweb.client.place.ProductPlace;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.utils.ProductDataProvider;
import com.ayurma.ayuromaweb.client.view.ILayout1View;
import com.ayurma.ayuromaweb.client.view.IProductView;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
//import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;


public class ProductViewActivity  extends AbstractActivity 
					implements IProductView.Presenter<ChemicalData>,IProductViewActivity{
	private final IProductView<ChemicalData> view;
	private ILayout1View rootPanel;
	private ProductPlace place;
	private Cache cache;
	private ChemicalData targetProduct;
	private final DataServiceAsync rpcService;
	private Provider<ProductDetailsPlace> productDetailsPlaceProvider;
	private Provider<EnquiryPlace> enquiryPlaceProvider;
	private PlaceController placeController;
	private SideBar1Activity sidebar1Activity;
	private AppInitData appInitData;
	private Provider<Widget1Activity> widgetActivity1Provider;
	private final ProductDataProvider dataProvider;
	//private String imageServletUrl;
	
	@Inject
	public ProductViewActivity(ILayout1View rootPanel,
			DataServiceAsync rpcService,IProductView<ChemicalData> view,Cache cache,
			Provider<ProductDetailsPlace> productDetailsPlaceProvider,
			Provider<EnquiryPlace> enquiryPlaceProvider,
			PlaceController placeController, SideBar1Activity sidebar1Activity,
			AppInitData appInitData,
			Provider<Widget1Activity> widgetActivity1Provider,
			@Named("imageServletUrl") String imageServletUrl,
			ProductViewLayoutImpl layouts) {
		this.placeController=placeController;
		this.rpcService=rpcService;
		this.view = view;
		this.rootPanel=rootPanel;
		this.cache = cache;
		this.view.setPresenter(this);
		this.view.setLayouts(layouts);
		this.productDetailsPlaceProvider=productDetailsPlaceProvider;
		this.enquiryPlaceProvider=enquiryPlaceProvider;
		this.sidebar1Activity=sidebar1Activity;
		this.appInitData=appInitData;
		this.widgetActivity1Provider=widgetActivity1Provider;
		dataProvider = new ProductDataProvider(this.rpcService, this.cache, this);
		//this.imageServletUrl=imageServletUrl;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//panel.setWidget(view.asWidget());
		rootPanel.scrollTo(0, 0);
		panel.setWidget(rootPanel.asWidget());
		
		
	}

	public void init(ProductPlace place) {
		
		//1 clear the already exiting content:
		
		rootPanel.get("mainContent").clear();
		rootPanel.get("mainContent").add(view.asWidget());
		//2 adding the side bar:
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
		//now data to the main view:
		view.reset();//clear the view of existing data
		view.showAjaxAnim();
		this.place = place;
		String token = place.getPlaceName();
		dataProvider.processToken(token);
		
		/*
		String source="";
		String query;
		String[] nameValuesPairs=token.split("[&]");
		
		if(nameValuesPairs.length>0){
			String[] children=nameValuesPairs[0].split("[=]");
			if(children.length>1) source=children[1];
			
		}
		if(source.equals("search")){
			String[] queryNameValuePairs=nameValuesPairs[1].split("[=]");
			query=queryNameValuePairs[1];
			getProductFromSearch(query);
		}else if(source.equals("group")){
			getProductFromData(nameValuesPairs[1].split("[=]")[1]);
		}
		*/
       
    }
	/*
	public void getProductFromSearch(String query){
		String productName = query;
		//search in the cache if this name exists
		//if yes then get its key
		//and call the rpc to get this product by this key:
		Long key = cache.getKeySearchedProduct(productName);
		if(key!=null){
			//now see if in the cache the product is alerady present
			ChemicalData dataFromCache = cache.getProductByKey(key);
			if(dataFromCache==null){
				//call the rpc
				fetchProductFromServer(key);
			}else{
				//show the product in the view
				processDataFromServer(dataFromCache);
				//view.showData(dataFromCache);
			}	
			//if yes then don't go to the server
		}else{
			//call the rpc to get the product by Product name
			fetchProductByName(productName);
		}
	}
	*/
	/*
	public void getProductFromData(String strKey){
		
		try{
			Long key = Long.valueOf(strKey);
			targetProduct=cache.getProductByKey(key);
			if(targetProduct!=null){
				view.stopAjaxAnim();
				view.showData(targetProduct);
			}else{
				//goto rpc service to get the product
				fetchProductFromServer(key);
			}
			
		}catch(NumberFormatException e){
			
		}
	}
	*/
	/*
	public void fetchProductFromServer(Long key){
		rpcService.getProductByKey(key,new AsyncCallback<ChemicalData>(){

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof InvocationException) { 
					//view.info("No internet connection.", 0,1);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					//view.info("Error in sending message", 0,1);
				}else{
					//view.info("Error in sending message", 0,1);
				}
				view.stopAjaxAnim();
				
			}

			@Override
			public void onSuccess(ChemicalData result) {
				
				if(result!=null) processDataFromServer(result);
				else view.stopAjaxAnim();
				
			}});
	}
	*/
	
	@Override
	public void processDataFromServer(ChemicalData result){
		targetProduct=result;
		cache.putProduct(targetProduct);
		
		//Timer t = new Timer(){

			//@Override
			//public void run() {
				view.stopAjaxAnim();
				view.showData(targetProduct);
				
			//}};
		//t.schedule(2000);

		
	}
	
	/*
	private void fetchProductByName(String name){
		rpcService.getProductByName(name, new AsyncCallback<ChemicalData>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ChemicalData result) {
				processDataFromServer(result);
				
			}});
	}
*/
	@Override
	public void goToDetails() {
		 
		 ProductDetailsPlace place = productDetailsPlaceProvider.get();
		 Long keyDetails = targetProduct.getDetailsId();
		 String strKeyDetails=String.valueOf(keyDetails); 
		 place.setPlaceName(strKeyDetails);
		 placeController.goTo(place);
		
		 
	}
	@Override
	public void goToEnquiry(){
		EnquiryPlace place = enquiryPlaceProvider.get();
		String queryString="source=product&key="+String.valueOf(targetProduct.getKey());
		place.setPlaceName(queryString);
		placeController.goTo(place);
	}
	@Override
	public void getReports(){
		EnquiryPlace place = enquiryPlaceProvider.get();
		String queryString="source=product-report&key="+String.valueOf(targetProduct.getKey());
		place.setPlaceName(queryString);
		placeController.goTo(place);
	}



	@Override
	public void info(String msg, int id, int type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAjaxAnim() {
		view.stopAjaxAnim();
		
	}

	@Override
	public void startAjaxAnim() {
		view.showAjaxAnim();
		
	}

	@Override
	public void showData(ChemicalData chemical) {
		targetProduct=chemical;
		view.showData(targetProduct);
		
	}


}
