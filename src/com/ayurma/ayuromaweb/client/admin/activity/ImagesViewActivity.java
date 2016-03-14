package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.place.BrowserProductsPlace;
import com.ayurma.ayuromaweb.client.admin.place.ImagesViewPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IImagesView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ImagesViewActivity extends AbstractActivity implements IImagesView.Presenter{
	private IImagesView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private ImagesViewPlace place;
	private UploadImageActivity uploadImagePresenter;
	private BrowseImagesActivity browseImagePresenter;
	private Provider<BrowserProductsPlace> browserProductsPlaceProvider;
	private EventBus eventBus;
	private Provider<ImagesViewPlace> imagesViewPlaceProvider;
	public static final int IMAGEBROWSER_STANDALONE=2;
	public static final int UPLOADIMAGE_LAYOUT=1;
	public static final int DEFAULT_LAYOUT=0;
	
	@Inject
	public ImagesViewActivity(IImagesView view,
			PlaceController placeController, AdminDataServiceAsync rpcService,EventBus eventBus,
			UploadImageActivity uploadImagePresenter,
			BrowseImagesActivity browseImagePresenter,
			Provider<ImagesViewPlace> imagesViewPlaceProvider) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.uploadImagePresenter=uploadImagePresenter;
		this.browseImagePresenter=browseImagePresenter;
		this.eventBus=eventBus;
		this.imagesViewPlaceProvider=imagesViewPlaceProvider;
	}
	public void init(ImagesViewPlace place){
		this.place=place;
		//default layout which shows the default menubar:
		view.updateLayout(DEFAULT_LAYOUT);
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		//removing the image browser if it is on here:
		//view.get("content-panel").
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.ayurma.ayuromaweb.client.admin.view.IImagesView.Presenter#showView(int)
	 * this method is called when the user clicks the upload images button or the 
	 * browse images button on the interface.
	 */
	@Override
	public void showView(int viewId){
		if(viewId==0){
			//show upload view
			view.updateLayout(UPLOADIMAGE_LAYOUT);
			uploadImagePresenter.init();
			uploadImagePresenter.start(view.get("content-panel"), eventBus);
		}else if(viewId==1){
			ImagesViewPlace nextPlace= imagesViewPlaceProvider.get();
			nextPlace.setPlaceName(String.valueOf(BrowseImagesActivity.STANDALONE));
			//this will show a stand alone image browser:
			browseImagePresenter.init(BrowseImagesActivity.STANDALONE);
			//starting the image browser:
			view.updateLayout(IMAGEBROWSER_STANDALONE);
			browseImagePresenter.start(view.get("content-panel"), eventBus);
		}
	}
	@Override
	public void loadImages(String strNPage, String strItemsPerPage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getImageUploadUrl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copyImageUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteImage(Long key) {
		// TODO Auto-generated method stub
		
	}

}
