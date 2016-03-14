package com.ayurma.ayuromaweb.client.admin.activity;


import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.place.SliderImagesPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.ISliderImageView;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.inject.Inject;

public class SliderImagesBrowseActivity  extends AbstractActivity implements ISliderImageView.Presenter{
	private ISliderImageView view;
	private SliderImagesPlace place;
	private final AdminDataServiceAsync rpcService;
	private List<SliderImageDTO> sliderImages;
	private List<Boolean> selectionList;
	private int displayMode=STANDALONE;
	private AdminCache cache;
	private int nPage,nItemsPerPage;
	//images are shown with a delete button at the bottom:
	public static final int STANDALONE=0;
	//images are shown with checkboxes at the bottom
	public static final int MULTISELECTION_DIALOG=1;
	//images can be selected with a mouse click:
	public static final int MOUSECLICK_SINGLESELECTION_DIALOG=2;
	
	@Inject
	public SliderImagesBrowseActivity(ISliderImageView view,
			AdminDataServiceAsync rpcService,
			AdminCache cache) {
		this.view=view;
		this.rpcService = rpcService;
		this.view.setPresenter(this);
		this.cache=cache;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		view.clear();
		//System.out.println("called start");
		
	}
	public void init(SliderImagesPlace place){
		this.place=place;
		displayMode=Integer.valueOf(place.getPlaceName());
		//initiating the view:
		view.init(displayMode);
		//System.out.println("init()");
		//view.clear();
		//loadSliderImages("1","10");
		
	}
	public void setDisplayMode(int displayMode){
		this.displayMode=displayMode;
		this.view.setDisplayMode(displayMode);
	}
	@Override
	public void loadSliderImages(String strNPage, String strItemsPerPage) {
		//load the slider images from the server:

				try{
					nPage = Integer.valueOf(strNPage);
					//for server it is zero based integer:
					nPage--;
					nItemsPerPage = Integer.valueOf(strItemsPerPage);
				}catch(NumberFormatException e){}
				//now check the cache for this data:
				List<SliderImageDTO> listFromCache=cache.getSliderImagesDataByPage(nPage, nItemsPerPage);
				if(listFromCache==null){
					System.out.println("BrowserSliderImagesActivity::load() list from the cache is null so loading images from the server...");
					fetchImagesDataFromServer(nPage, nItemsPerPage);
				}else if(listFromCache.isEmpty()){
					System.out.println("BrowserSliderImagesActivity::load() list from the cache is empty so loading images from the server...");
					fetchImagesDataFromServer(nPage, nItemsPerPage);		
				}else{
					System.out.println("BrowserSliderImagesActivity::load() data is present in the cache so using that...");
					//add to local list:
					sliderImages=listFromCache;
					processSliderImageData();
				}
		
	}
	private void fetchImagesDataFromServer(int iPage,int numItemsPerPage){
		rpcService.getSliderImagesByPage(iPage, numItemsPerPage, new AsyncCallback<List<SliderImageDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(List<SliderImageDTO> result) {
				//add to the cache:
				cache.addSliderImagesDataPage(nPage, nItemsPerPage, result);
				//add to local:
				sliderImages=result;
				processSliderImageData();
			}});
	}
	
	private void processSliderImageData(){
		//sliderImages=result;
		/*
		List<String> imageUrls= new ArrayList<String>();
		List<String> imageInfos = new ArrayList<String>();
		for(SliderImageDTO item:sliderImages){
			imageUrls.add(item.getImageKey());
			imageInfos.add(item.getImageInfo());
			//System.out.println("image info:"+item.getImageInfo());
		}
		*/
		if(displayMode==MULTISELECTION_DIALOG){
			selectionList=new ArrayList<Boolean>();
			for(SliderImageDTO info:sliderImages){
				selectionList.add(false);
			}
		}
		view.showData(sliderImages,displayMode);

		
	}
	@Override
	public void deleteImage(Long imageInfoKey) {
		view.info("Deleting...", 0);
		//the second parameter 1 is for deleting the slider image data
		rpcService.deleteImage(imageInfoKey,1, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("RPC failed.", 0);
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, 0);
				
			}});
		
	}
	public HasClickHandlers getExportButton() {
		   return view.getExportButton();
	}
	public List<SliderImageDTO> getSelectedImages(){
		int i=0;
		List<Boolean> listValues = view.getSelectedImages();
		
		
		List<SliderImageDTO> imagesOut=new ArrayList<SliderImageDTO>();
		if(displayMode==MULTISELECTION_DIALOG){
			i=0;
			for(boolean val:selectionList){
				if(val){
					imagesOut.add(sliderImages.get(i));
				}
				i++;
			}
		}else{
		for(Boolean value:listValues){
			if(value){
				//include the image item for the film:
				imagesOut.add(sliderImages.get(i));
				//items.add(sliderImages.get(i).getKey());
				
			}
			i++;
		}
		}
		return imagesOut;
	}
    public void setBrowserDataReceiver(ISliderImageBrowserConnector imgDataReceiver){
    	view.setDataPresenter(imgDataReceiver);
    }

	public SliderImageDTO getSelectedImage(int iSelected) {
		return sliderImages.get(iSelected);
		
	}

	@Override
	public void setSelected(int id,boolean isSelected) {
		selectionList.set(id, isSelected);
		
	}

	@Override
	public void clearImageCacheData() {
		cache.deleteSliderImagesData();
		
	}
}
