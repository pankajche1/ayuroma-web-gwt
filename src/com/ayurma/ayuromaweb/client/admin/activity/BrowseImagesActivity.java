package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.List;

import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseImagesView;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class BrowseImagesActivity extends AbstractActivity implements IBrowseImagesView.Presenter{
	private IBrowseImagesView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private AdminCache cache;
	private int displayMode=STANDALONE;
	private List<ImageDataDTO> images;
	public static final int STANDALONE=0;
	public static final int MULTISELECTION_DIALOG=1;
	public static final int SINGLESELECTION_DIALOG=2;
	private int nPage,nItemsPerPage;
	@Inject
	public BrowseImagesActivity(IBrowseImagesView view,
			PlaceController placeController, AdminDataServiceAsync rpcService,
			AdminCache cache) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.cache=cache;
	}
	public void init(int displayMode){
		this.displayMode=displayMode;
		//initiate the view for this type of displaymode:
		view.init(displayMode);
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		view.clear();
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.ayurma.ayuromaweb.client.admin.view.IBrowseImagesView.Presenter#loadImages(java.lang.String, java.lang.String)
	 * this method is called when the user selects the page number, and the number of images per page
	 * and then he clicks the load images button on the interface.
	 */
	@Override
	public void loadImages(String strNPage, String strItemsPerPage) {
		view.info("Loading...", 0);
		//load the slider images from the server:
		nPage = 0;
		nItemsPerPage = 0;
		try{
			//for human the pages starts from 1
			nPage = Integer.valueOf(strNPage);
			//for server it is zero based integer:
			nPage--;
			nItemsPerPage = Integer.valueOf(strItemsPerPage);
		}catch(NumberFormatException e){
			view.info("NumberFormat exception", 0);
			return;
		}
		//now check the cache for this data:
		List<ImageDataDTO> listFromCache=cache.getImagesDataByPage(nPage, nItemsPerPage);
		if(listFromCache==null){
			System.out.println("BrowserImagesActivity::load() list from the cache is null so loading images from the server...");
			fetchImagesDataFromServer(nPage, nItemsPerPage);
		}else if(listFromCache.isEmpty()){
			System.out.println("BrowserImagesActivity::load() list from the cache is empty so loading images from the server...");
			fetchImagesDataFromServer(nPage, nItemsPerPage);		
		}else{
			System.out.println("BrowserImagesActivity::load() data is present in the cache so using that...");
			images=listFromCache;
			processImages();
		}
	}
	private void fetchImagesDataFromServer(int nPage,int nItemsPerPage){
		rpcService.getImagesDataByPage(nPage, nItemsPerPage,
				new AsyncCallback<List<ImageDataDTO>>(){

					@Override
					public void onFailure(Throwable caught) {
						view.info("RPC failed.", 0);
						
					}

					@Override
					public void onSuccess(List<ImageDataDTO> result) {
						view.info("", 0);
						//putting the local data:
						images=result;
						putInCache(result);
						processImages();
						
						
					}});		
	}
	private void putInCache(List<ImageDataDTO> result){
		cache.addImagesDataPage(nPage, nItemsPerPage, result);
	}
	private void processImages(){
		//images=result;
		view.showData(images,displayMode);
	}
	public void copyImageUrl(String imageUrl) {
		cache.setClipBoardText(imageUrl);		
	}

	public void deleteImage(Long imageInfoKey) {
		view.info("Deleting...", 0);
		//the second parameter is for deleting a general image:
		rpcService.deleteImage(imageInfoKey,0, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("RPC failed.", 0);
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, 0);
				
			}});
		
	}

	public void setDisplayMode(int displayMode) {
		this.displayMode=displayMode;
		view.setDisplayMode(displayMode);
		
	}
	@Override
	public int getDisplayMode() {
		return displayMode;
	}
	public void setDataLinker(IBrowseImagesView.IDataLinker linker) {
		view.setDataLinker(linker);
		
	}

	public ImageDataDTO getImage(int iSelectedImage) {
		return images.get(iSelectedImage);
		
	}
	@Override
	public void clearImageCacheData() {
		cache.deleteImagesData();
		
	}

}
