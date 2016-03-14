package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.place.EditProductGroupPlace;

import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseImagesView;
import com.ayurma.ayuromaweb.client.admin.view.IEditProductGroupView;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.ayurma.ayuromaweb.shared.ProductGroupData;
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

public class EditProductGroupActivity extends AbstractActivity
		implements IEditProductGroupView.Presenter,
		IBrowseImagesView.IDataLinker{
	private IEditProductGroupView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private EditProductGroupPlace place;
	private ProductGroupData curProductGroup=null;
	private String imageServletUrl="ayuromaweb3/serveImage?blob-key=";
	private Provider<BrowseImagesActivity> browserImageActivityProvider;
	private BrowseImagesActivity imageBrowserActivity;
	private ImageDataDTO selectedImage;
	private int updateItemId=0;
	public static final int UPDATE_IMAGE=1;
	public static final int UPDATE_DATA=0;
	@Inject
	public EditProductGroupActivity(IEditProductGroupView view,
			PlaceController placeController, AdminDataServiceAsync rpcService,
			Provider<BrowseImagesActivity> browserImageActivityProvider) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.browserImageActivityProvider=browserImageActivityProvider;
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	@Override
	public void gotoPlace(int id) {
		// TODO Auto-generated method stub
		
	}
	public void init(EditProductGroupPlace place){
		this.place=place;
		this.view.reset();
		//start getting the data from the server:
		//get the key of the target product group:
		try{
			Long key = place.getKey();
			fetchDataFromServer(key);
		}catch(NullPointerException e){
			
		}
		
	}
	private void fetchDataFromServer(Long key){
		rpcService.getProductGroup(key, new AsyncCallback<ProductGroupData>(){

			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(ProductGroupData result) {
				curProductGroup=result;
				if(result!=null){
					view.setData(curProductGroup.getName(),curProductGroup.getDescription());
				
				if(!curProductGroup.getImageUrl().equals(""))
					view.setProductGroupImage(imageServletUrl+curProductGroup.getImageUrl());
					
				}else view.info("Could not perform the operation. Try again after refresh.", 1);
				
			}
			
		});
	}
	@Override
	public void updateProductGroup(String name, String description, int id) {
		updateItemId=id;
		//System.out.println("id"+id);
		switch(updateItemId){
		case UPDATE_DATA:
			view.info("Updating data...", 0);
			String nameTrimmed = name.trim();
			curProductGroup.setName(nameTrimmed);
			curProductGroup.setDescription(description);
		break;
		case UPDATE_IMAGE://update image url
			view.info("Updating Image...", 1);
			if(selectedImage!=null)
			curProductGroup.setImageUrl(selectedImage.getImageKey());
			break;
		}
		updateProductGroupData(curProductGroup.getKey(),curProductGroup,updateItemId);
		/*
		Timer t = new Timer(){
		    @Override
		    public void run() {
		        fn();
		    }
		    };
		    t.schedule(2000);
		    */
		
	}
	/*
	private void fn(){
		updateProductGroupData(curProductGroup.getKey(),curProductGroup,updateItemId);
	}
	*/
	private void updateProductGroupData(Long key,ProductGroupData data,int id){
		 
		 rpcService.updateProductGroup(key, data,id, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", updateItemId);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending message", updateItemId);
				}else{
					view.info("Error in sending message", updateItemId);
				}
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, updateItemId);
				
			}});
	}
	@Override
	public void showImageBrowser() {
		imageBrowserActivity = browserImageActivityProvider.get();
		view.setImageBrowser();
		imageBrowserActivity.init(BrowseImagesActivity.SINGLESELECTION_DIALOG);
		//imageBrowserActivity.setDisplayMode(1);
		imageBrowserActivity.setDataLinker(this);
		imageBrowserActivity.start(view.getExtraPanel(), null);
		
	}
	@Override
	public void onOkClick(int iSelectedImage) {
		selectedImage = imageBrowserActivity.getImage(iSelectedImage);
		view.removeImageBrowser();
		view.setProductGroupImage(imageServletUrl+selectedImage.getImageKey());
		
	}
	@Override
	public void onCancelClick() {
		view.removeImageBrowser();
		
	}

}
