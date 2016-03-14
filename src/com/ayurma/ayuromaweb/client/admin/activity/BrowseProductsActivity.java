package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;


import com.ayurma.ayuromaweb.client.admin.place.BrowserProductsPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductEditPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductsDetailsEditPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseProductsView;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BrowseProductsActivity  extends AbstractActivity implements IBrowseProductsView.Presenter{
	private IBrowseProductsView view;
	private PlaceController placeController;
	private AdminCache cache;
	private final AdminDataServiceAsync rpcService;
	private int iPageProducts=-1;
	private int nProductsPerPage=-1;
	private List<ProductBasicInfo> listProductInfo=new ArrayList<ProductBasicInfo>();
	private BrowserProductsPlace place;
	private int displayMode=MULTI_ITEM_SELECTION_DIALOG;//default mode
	private Provider<ProductEditPlace> productEditProvider; 
	private Provider<ProductsDetailsEditPlace> productDetailsEditProvider; 
	private IProductLinker productLinker;
	private IProductBrowserConnector productDataReceiver;
	public static final int STANDALONE_BROWSER=0;
	public static final int SINGLE_ITEM_SELECTION_DIALOG=3;
	public static final int MULTI_ITEM_SELECTION_DIALOG=1;
	public static final int MULTI_ITEM_SELECTION_STANDALONE=4;
	public static final int SINGLE_ITEM_SELECTION_STANDALONE=5;
	//public static final int NORMAL=1;//shows no cancel or ok button a the bottom
	public static int id=-1;
	

	@Inject
	public BrowseProductsActivity(IBrowseProductsView view,
			PlaceController placeController, AdminDataServiceAsync rpcService,
			Provider<ProductEditPlace> productEditProvider,
			Provider<ProductsDetailsEditPlace> productDetailsEditProvider,
			AdminCache cache) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.cache=cache;
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.productEditProvider=productEditProvider;
		this.productDetailsEditProvider=productDetailsEditProvider;
		
		id++;
		System.out.println("BrowseProductsActivity created id="+id);
	}
	public void resetView(){
		view.reset();
	}
	
	public void setProductDataReceiver(IProductBrowserConnector productDataReceiver) {
		this.productDataReceiver = productDataReceiver;
		this.view.setDataReceiver(this.productDataReceiver);
	}
	public void init(BrowserProductsPlace place){
		this.place=place;
		//deciding which type of view is to be shown:
		//String token = place.getPlaceName();
		//System.out.println("BrowseProductsActivity::init() called...");
		displayMode=Integer.valueOf(place.getPlaceName());

		//initiation of the view to show the required interface
		//e.g. for a standalone browser it should not show the
		//cancel and ok buttons at the bottom:
		view.init();
		view.reset();//clear the view of earlier data items


	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		view.clear();
		
	}

	@SuppressWarnings("unused")
	@Override
	public void onLoadProductsButtonClicked(String strPageNumber,String strItemsPerPage,
			int sourceId) {
		view.info("Loading", 0);
		String strIndexPage="0";//a zero base index
		try{
			iPageProducts=Integer.valueOf(strPageNumber);//a 1 based index
			iPageProducts--;//the number given by a common user is 1 based not zero based
			strIndexPage=String.valueOf(iPageProducts);
			nProductsPerPage=Integer.valueOf(strItemsPerPage);
		}catch(NumberFormatException e){
			iPageProducts=-1;
		}
		if(iPageProducts<0){
			//view.info("No suce page number!", 2);
			view.info("", 0);
			return;
		}
		if(!getListFromCache(iPageProducts,nProductsPerPage))
			fetchProductInfoList(iPageProducts,nProductsPerPage);
		//System.out.println("BrowseProductsActivity::onLoadProductsButtonClicked(), size list="+listProductInfo.size()+", id="+id);
		
	}
	private boolean getListFromCache(int iPage,int nItemsPerPage){
		List<ProductBasicInfo> list=cache.getProductBasicInfosByPage(iPage, nItemsPerPage);
		if(list==null){
			System.out.println("Null list so Getting data from the server...");
			return false;
		}else if(list.isEmpty()){
			System.out.println("Empty list so Getting data from the server...");
			return false;
		}else{
			//it means the cache has the list:
			view.info("", 0);
			processProductsInfoData(list);			
		}
		System.out.println("Getting data from the cache...");
		System.out.println("BrowseProductsActivity::getListFromCache(), size list="+listProductInfo.size()+", id="+id);
		return true;
		
	}
	private void fetchProductInfoList(int iPage,int itemsPerPage){
		rpcService.getProductInfoListByPage(iPage, itemsPerPage, new AsyncCallback<List<ProductBasicInfo>>(){

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending message", 0);
				}else{
					view.info("Error in sending message", 0);
				}
				
			}

			@Override
			public void onSuccess(List<ProductBasicInfo> result) {
				if(result!=null){
					if(result.size()>0){
						view.info("", 0);
						//putting the list in the cache:
						cache.addProductInfosPage(iPageProducts,nProductsPerPage, result);
						processProductsInfoData(result);
					}
					else{
						view.info("No product found.", 0);
					}
				    //
				}
				
			}});
	}
	private void processProductsInfoData(List<ProductBasicInfo> list){
		
		//putting the array in the cache:
		//cache.listProductBasicInfo=list;
		
		//creating a fresh list of products:
		
		listProductInfo=list;
		System.out.println("BrowseProductsActivity::processProductsInfoData(), size list="+listProductInfo.size()+", id="+id);
		//save the list in the cache too:
		///cache.listProductBasicInfo2=list;
		///cache.groupAdmin.iPageProductsList=iPageProducts;
		///cache.groupAdmin.nProductsPerPage=nProductsPerPage;
		//creating a list of of products names:
		List<String> names= new ArrayList<String>();
		List<String> snProducts = new ArrayList<String>();//the serial number of the products
		int iProduct=0;
		for(int i=0;i<listProductInfo.size();i++){
			
			names.add(listProductInfo.get(i).getName());
			iProduct=iPageProducts*nProductsPerPage+i+1;
			snProducts.add(String.valueOf(iProduct));
		}
		//calling the view to show this list:


			  view.showProductsList(names,snProducts,displayMode);
			
      
		
		
		
	}
	public List<ProductBasicInfo> getSelectedProducts(){
		//get the values of the check boxes:
		List<Boolean> list =view.getSelectedProducts();
		//now map the above values to the real products:
		List<ProductBasicInfo> listOut=new ArrayList<ProductBasicInfo>();
		System.out.println("BrowseProductsActivity::getSelectedProducts(), size list="+listProductInfo.size()+", id="+id);
		int i=0;
		for(boolean val:list){
			if(val) listOut.add(listProductInfo.get(i));
			i++;
		}
		return listOut;
	}
	public List<ProductBasicInfo> getProductInfoList(){
		System.out.println("BrowseProductsActivity::getProductInfoList(), size list="+listProductInfo.size()+", id="+id);
		return listProductInfo;
	}
	@Override
	public void onEditProductClicked(int productId) {
		
		//EditProductEvent event= new EditProductEvent();
		//first get the product id from the data:
		int index=-1;
		try{
		  index=Integer.valueOf(productId);
		}catch(Exception e){
			return;
		}
		//getting the key of the target product:
		Long key = listProductInfo.get(index).getKey();
		ProductEditPlace place = productEditProvider.get();
		place.setPlaceName(String.valueOf(key));
		placeController.goTo(place);
		//goto the edti product place:
	   //event.productKey=listProductInfo.get(index).getKey();
	    //eventBus.fireEvent(event);
	}
	@Override
	public void onEditProductDetailsClicked(int productId) {
		
		//EditProductDetailsEvent e = new EditProductDetailsEvent();
		//first get the product id from the data:
		int index=-1;
		try{
		  index=Integer.valueOf(productId);
		}catch(Exception ex){
			return;
		}
		//getting the details key:
		Long detailsKey = listProductInfo.get(index).getDetailsKey();
		ProductsDetailsEditPlace place = productDetailsEditProvider.get();
		place.setPlaceName(String.valueOf(detailsKey));
		placeController.goTo(place);
		//e.detailsKey=listProductInfo.get(index).getDetailsKey();
		//eventBus.fireEvent(e);
		
	}
	@Override
	public void onDeleteProductClicked(int id) {
		view.info("deleting...", 0);
		//view.showAjaxLoading();
		//the id is the index of the product in the products list:
		int index=-1;
		try{
			index=Integer.valueOf(id);
		}catch(Exception e){
			//view.stopAjaxLoading();
			return;
		}
		ProductBasicInfo target = listProductInfo.get(index);
		
		if(target!=null)
			deleteProduct(target.getKey());
		else view.info("",0);
		
	}
	private void deleteProduct(Long key){
		 
		 rpcService.deleteProduct(key, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending message", 0);
				}else{
					view.info("Error in sending message", 0);
				}
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, 0);
				if(result!=null) view.info(result,0);
				
				
				
			}});
	}
	@Override
	public void linkButtonClicked(int id){
		productLinker.setProduct(listProductInfo.get(id));
	}
	public void setDisplayMode(int displayMode){
		this.displayMode=displayMode;
		//this.view.setDisplayMode(displayMode);
	}
	
	public int getDisplayMode() {
		return displayMode;
	}
	public void setProductLinker(IProductLinker productLinker) {
		this.productLinker = productLinker;
	}
	public ProductBasicInfo getProduct(int index){
		if(index<listProductInfo.size())
			return listProductInfo.get(index);
		else
			return null;
	}
	@Override
	public void clearProductsCache() {
		cache.deleteProducts();
		
	}
	

}
