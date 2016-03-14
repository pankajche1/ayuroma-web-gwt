package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.place.ProductEditPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseImagesView;
import com.ayurma.ayuromaweb.client.admin.view.IEditProductView;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductEditActivity extends AbstractActivity 
		implements IEditProductView.Presenter,IBrowseImagesView.IDataLinker{
	private IEditProductView view;
	private final AdminDataServiceAsync rpcService;
	private ProductEditPlace place;
	private ChemicalData target;
	private final String imageServletUrl="ayuromaweb3/serveImage?blob-key=";
	private AdminCache cache;
	private String imgUrl="";
	private Provider<BrowseImagesActivity> browserImageActivityProvider;
	private BrowseImagesActivity imageBrowserActivity;
	private ImageDataDTO selectedImage;
	@Inject
	public ProductEditActivity(IEditProductView view,
			AdminDataServiceAsync rpcService,AdminCache cache,
			Provider<BrowseImagesActivity> browserImageActivityProvider) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.rpcService = rpcService;
		this.cache=cache;
		this.browserImageActivityProvider=browserImageActivityProvider;
	}
	public void init(ProductEditPlace place){
		this.place=place;
		view.reset();
		String strKey = place.getPlaceName();
		try{
			fetchProductData(Long.valueOf(strKey));
		}catch(NumberFormatException e){
			
		}
		
	}
	private void fetchProductData(Long productKey) {
		rpcService.getProduct(productKey,new AsyncCallback<ChemicalData>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("RPC failed.", 0);//main message at the top
				
			}

			@Override
			public void onSuccess(ChemicalData result) {
				//view.stopAjaxLoading();
				processResponseData(result);
				//else view.info("Got no product with the id:"+strProductId, 0);//main message at the top
				
			}} );
		
		   
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	private void processResponseData(ChemicalData data){
		if(data==null){
			view.info("Data was not found on the server", 0);
			return;
		}
		view.info("", 0);//main message at the top
		target=data;
		String strProductId=target.getStrId();
		//System.out.println("Presenter se isInStock:"+target.getIsInStock());
		view.setDataToEdit(target.getName(), target.getDescription(),
				strProductId,target.getIsInStock());
		imgUrl=target.getImageUrl();
		if(!target.getImageUrl().equals("")) view.setImage(imageServletUrl+target.getImageUrl());
	}
	@Override
	public void onBtnSaveClick(String name, String description,Boolean isInStock) {
		String nameTrimmed = name.trim();
		//varifying the lengths of the name and the imageUrls:
		if (nameTrimmed.length()>100) {
			
			view.info("Name should be less than 100 characters.",1);
			return;
		}
		//view.showAjaxLoading();
		view.info("Updating...",1);
		//ChemicalData productToUpdate=null;
		//targetData=new ChemicalData(name,description);
		if(target==null){
			//System.out.println("Got no such data.");
			return;
		}
		target.setName(nameTrimmed);
		target.setDescription(description);
		target.setIsInStock(isInStock);
		//productToUpdate.setImageUrl(imageUrl);
		updateProductData(target);
		
	}
	private void updateProductData(ChemicalData productToUpdate){

		
		 rpcService.updateProductData(target.getKey(), productToUpdate, 
				 new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable caught) {
						view.info("RPC failed.",1);
						
					}

					@Override
					public void onSuccess(String result) {
						view.info(result,1);
						
					}});
		 
	}
	@Override
	public void getClipBoardText() {
		imgUrl=cache.getClipBoardText();
		if(!imgUrl.equals("")) view.setImage(imageServletUrl+imgUrl);
		
	}
	@Override
	public void updateImage() {
		view.info("Updating...", 2);
		if(selectedImage==null){
			view.info("No image selected.", 2);
			return;
		}
		rpcService.updateProductImage(target.getKey(), selectedImage.getImageKey(),  new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 2);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending message", 2);
				}else{
					view.info("Error in sending message", 2);
				}
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result,2);
				
			}});
		
	}
	@Override
	public void showImageBrowser() {
		imageBrowserActivity = browserImageActivityProvider.get();
		view.setImageBrowser();
		//imageBrowserActivity.setDisplayMode(1);
		imageBrowserActivity.init(BrowseImagesActivity.SINGLESELECTION_DIALOG);
		imageBrowserActivity.setDataLinker(this);
		imageBrowserActivity.start(view.getExtraPanel(), null);
		
	}
	@Override
	public void onOkClick(int iSelectedImage) {
		selectedImage = imageBrowserActivity.getImage(iSelectedImage);
		view.removeImageBrowser();
		view.setImage(imageServletUrl+selectedImage.getImageKey());
		
	}
	@Override
	public void onCancelClick() {
		view.removeImageBrowser();
		
	}

}
