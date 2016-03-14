package com.ayurma.ayuromaweb.client.admin.activity;


import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.gin.AdminGinjector;
import com.ayurma.ayuromaweb.client.admin.place.BrowserProductsPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderEditPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderImagesPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.ISliderEditView;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderFilmItem;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class SliderEditViewActivity extends AbstractActivity implements  ISliderEditView.Presenter,
								ISliderImageBrowserConnector,
								IProductBrowserConnector{
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private ISliderEditView view;
	private SliderEditPlace place;
	private Provider<BrowserProductsPlace> browserProductsPlaceProvider;
	private Provider<SliderImagesPlace> sliderImagesPlaceProvider;
	private AdminCache cache;
	private int iSelected;
	private int iSelectedItem=-1;//the item selected for image change by pressing the button at the right side of the item
	private List<SliderFilmItem> items;
	//private List<SliderFilmItem> filmItems = new ArrayList<SliderFilmItem>();
	private AdminGinjector ginjector =GWT.create(AdminGinjector.class);
	private SliderImagesBrowseActivity imageBrowserActivity;
	private BrowseProductsActivity browserProductActivity;
	private String servletUrl="ayuromaweb3/serveImage?blob-key=";
	private Long keyTarget;
	private SliderFilmDTO updatedFilm;
	public static final int DIALOG_LAYOUT=0;
	public static final int MAIN_LAYOUT=1;
	@Inject
	public SliderEditViewActivity(PlaceController placeController,
			AdminDataServiceAsync rpcService, ISliderEditView view,
			AdminCache cache,Provider<BrowserProductsPlace> browserProductsPlaceProvider,
			Provider<SliderImagesPlace> sliderImagesPlaceProvider) {
		
		this.placeController = placeController;
		this.browserProductsPlaceProvider=browserProductsPlaceProvider;
		this.rpcService = rpcService;
		this.view = view;
		this.view.setPresenter(this);
		this.cache=cache;
		this.sliderImagesPlaceProvider=sliderImagesPlaceProvider;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		//show the default data panel on the view:
		view.reset(MAIN_LAYOUT);
		
	}
	public void init(SliderEditPlace place){
		this.place=place;
		String strKey = place.getPlaceName();
		keyTarget=Long.valueOf(strKey);
		try{
			fetchSliderFilm(keyTarget);
		}catch(NumberFormatException e){
			
		}
	}
	private void fetchSliderFilm(Long key){
		view.info("Loading...", 0);
		SliderFilmDTO film=cache.getSliderFilm(key);
		if(film!=null){
			if(film.getImageUrls().size()==0){
				//load the full data from the server
				getSliderFilmFromServer(key);
			}else{
				view.info("", 0);
				processFilm(film);
			}
			
		}else{
			//fetch film from the server
			view.info("", 0);
		}
	}
	private void getSliderFilmFromServer(Long key){
		rpcService.getSliderFilm(key, new AsyncCallback<SliderFilmDTO>(){

			@Override
			public void onFailure(Throwable caught) {
				if(caught instanceof InvocationException) { 
                    view.info("please check internet connection.", 0);
                }else if(caught instanceof IncompatibleRemoteServiceException){
                    view.info("Error loading...", 0);
                }else{
                    view.info("Error loading...", 0);
                
				
			}
			}	
			@Override
			public void onSuccess(SliderFilmDTO result) {
				view.info("", 0);
				processFilm(result);
				
			}});
	}
	private void processFilm(SliderFilmDTO film){
		//setting a list of items inside the film:
		items=new ArrayList<SliderFilmItem>();
		//getting the data from the film object:
		//1
		List<String> imageUrls = film.getImageUrls();
		//2
		List<String> names = film.getLinkedItemsNames();
		//3
		Long[] imageItems = film.getImageItems();
		//4
		Long[] linkedProducts = film.getProductIds();
		//film.get
		
		int i=0;
		//this list will be created locally:
		List<String> imageUrlsFull = new ArrayList<String>();
		for(String imageUrl:imageUrls){
			imageUrlsFull.add(servletUrl+imageUrl);
			//adding the items to the local list:
			SliderFilmItem item=new SliderFilmItem();
			//setting the four data in the current item:
			//1
			item.setImageUrls(imageUrl);
			//2
			item.setImageItemKey(imageItems[i]);
			//3
			item.setProductId(linkedProducts[i]);
			//4
			item.setProductName(names.get(i));
			items.add(item);
			
			i++;
		}
		view.showFilmItems(imageUrlsFull,names);
	}

	@Override
	public void onDragEnd(int widgetIndex) {
		SliderFilmItem item;
		if(widgetIndex<iSelected||widgetIndex>iSelected){
			item =items.remove(iSelected);
			items.add(widgetIndex, item);
		}
		
	}

	@Override
	public void onDragStart(int widgetIndex) {

		iSelected=widgetIndex;
		
	}

	@Override
	public void updateFilm() {
		view.info("Updating...", 0);
		if(items==null){
			view.info("", 0);
			return;
		}
		updatedFilm = new SliderFilmDTO("title");
		//update the filmItems:
		//1 set the image data
		//2 set the product data:
		updatedFilm.setImageItems(items);
		updateFilmOnServer(updatedFilm);
		//simulateAjaxLag();
	}
	private void simulateAjaxLag(){
		Timer t = new Timer(){
		    @Override
		    public void run() {
		    	updateFilmOnServer(updatedFilm);
		    }
		    };
		    t.schedule(2000);   
	}
	private void updateFilmOnServer(SliderFilmDTO dto){
		rpcService.updateSliderFilm(dto, keyTarget, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				if(caught instanceof InvocationException) { 
                    view.info("No internet connection.", 0);
                }else if(caught instanceof IncompatibleRemoteServiceException){
                    view.info("Error in sending enquiry", 0);
                }else{
                    view.info("Error in sending enquiry", 0);
                }
			}

			@Override
			public void onSuccess(String result) {
				//updating the data in the cache:
				view.info("", 0);
				cache.addSliderFilm(updatedFilm);
				System.out.println("Message from the server:"+result);
				
			}});
	}
	@Override
	public void changeImage(int iSelectedItem) {
		this.iSelectedItem=iSelectedItem;
		//System.out.println("id change image:"+id);
		//prepare the view interface for the slider images browser dialog box:
		view.reset(DIALOG_LAYOUT);
		startSliderImageBrowser(SliderImagesBrowseActivity.MOUSECLICK_SINGLESELECTION_DIALOG);
		
	}
	/*
	 * to show the slider image browser panel
	 */
	
	public void startSliderImageBrowser(int displayMode){
		SliderImagesPlace place= sliderImagesPlaceProvider.get();
		place.setPlaceName(String.valueOf(displayMode));
		imageBrowserActivity = place.getActivity();
		
		imageBrowserActivity.init(place);
		//imageBrowserActivity.setDisplayMode(displayMode);//showing checkboxes on each image unit;
		imageBrowserActivity.setBrowserDataReceiver(this);
		/*
		imageBrowserActivity.getExportButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				List<SliderImageDTO> selectedImages = imageBrowserActivity.getSelectedImages();
				//showSelectedImages(selectedImages);
				
		}});
		*/
		imageBrowserActivity.start(view.getExternalPanel(),ginjector.getEventBus());
	}
	/*
	 * (non-Javadoc)
	 * @see com.ayurma.ayuromaweb.client.admin.view.ISliderEditView.Presenter#changeLink(int)
	 * this method is called when the user clicks the change link
	 * button on any slider item button. A product browser is shown as diaglog
	 * to like the product item with the correponsing slider item
	 */
	@Override
	public void changeLink(int iSelectedItem){
		this.iSelectedItem=iSelectedItem;
		view.reset(DIALOG_LAYOUT);
		startProductBrowser();
	}
	/* start the product browser to select any product:
	 * (non-Javadoc)
	 * @see com.ayurma.ayuromaweb.client.admin.view.ISliderEditView.Presenter#startProductBrowser()
	 */
	
	public void startProductBrowser(){
		//browserProductActivity=ginjector.getBrowseProductActivity();
		BrowserProductsPlace place= browserProductsPlaceProvider.get();
		place.setPlaceName(String.valueOf(BrowseProductsActivity.SINGLE_ITEM_SELECTION_DIALOG));
		browserProductActivity=place.getActivity();
		browserProductActivity.setProductDataReceiver(this);
		//browserProductActivity.setDisplayMode(BrowseProductsActivity.SINGLE_ITEM_SELECTION_DIALOG);
		browserProductActivity.start(view.getExternalPanel(),ginjector.getEventBus());
	}
	@Override
	public void getSelectedImage(int iSelectedImage) {
		//  remove the image browser from the view:
		view.clearExternalPanel();
		view.reset(MAIN_LAYOUT);
		//get the data from the image browser view
		
		if(iSelectedImage>=0){
			
			
			changeImage(imageBrowserActivity.getSelectedImage(iSelectedImage));
		}

		
		
	}
	@Override
	public void onImageBrowserOkClick(){
		view.clearExternalPanel();
		view.reset(MAIN_LAYOUT);
		//this will take the list of selected items from the image browser activity:
		//imageBrowserActivity.g
		List<SliderImageDTO> imagesSelected=imageBrowserActivity.getSelectedImages();
		int i=0;
		//this list will be created locally:
		List<String> imageUrlsFull = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		//the already existing list:
		//preparing the above lists:
		
		for(SliderFilmItem unit:items){
			
			imageUrlsFull.add(servletUrl+unit.getImageUrls());
			names.add(unit.getProductName());
			
			i++;
		}
		i=0;
		//newly added images:
		for(SliderImageDTO imageInfo:imagesSelected){
			String imageUrl=imageInfo.getImageKey();
			imageUrlsFull.add(servletUrl+imageUrl);
			//adding the items to the local list:
			SliderFilmItem item=new SliderFilmItem();
			//setting the four data in the current item:
			//1
			item.setImageUrls(imageUrl);
			//2
			item.setImageItemKey(imageInfo.getKey());
			names.add("No item linked.");
			items.add(item);
			
			i++;
		}
		view.showFilmItems(imageUrlsFull,names);
	}
	private void changeImage(SliderImageDTO selectedImage){
		if(iSelectedItem<0) return;
		
		SliderFilmItem item = items.get(iSelectedItem);
		//setting the image data of the slider film item:
		//each image will have two data:
		//1
		item.setImageUrls(selectedImage.getImageKey());
		//2
		item.setImageItemKey(selectedImage.getKey());
		//now prepare the lists to show the data on the view interface:
		//1 images urls:
		List<String> imageUrlsFull = new ArrayList<String>();
		//2 linked products names:
		List<String> names = new ArrayList<String>();

		//preparing the above lists:
		int i=0;
		for(SliderFilmItem unit:items){
			
			imageUrlsFull.add(servletUrl+unit.getImageUrls());
			names.add(unit.getProductName());
			
			i++;
		}
		view.showFilmItems(imageUrlsFull,names);
	}

	@Override
	public void cancelImageBrowser() {
		//  remove the browser from the view:
		view.clearExternalPanel();
		view.reset(MAIN_LAYOUT);
	}

	@Override
	public void setProduct(int iSelected) {
		//  remove the image browser from the view:
		view.clearExternalPanel();
		view.reset(MAIN_LAYOUT);
		if(iSelected<0) return;
		changeLinkedItem(browserProductActivity.getProduct(iSelected));
		
	}
	private void changeLinkedItem(ProductBasicInfo item){
		if(iSelectedItem<0) return;
		//get the selected slider film item for edit:
		SliderFilmItem filmItem = items.get(iSelectedItem);
		//now update the linked product:
		filmItem.setProductId(item.getKey());
		filmItem.setProductName(item.getName());
		//now prepare the lists to show the data on the view interface:
		//1 images urls:
		List<String> imageUrlsFull = new ArrayList<String>();
		//2 linked products names:
		List<String> names = new ArrayList<String>();
		//preparing the above lists:
		int i=0;
		for(SliderFilmItem unit:items){
			
			imageUrlsFull.add(servletUrl+unit.getImageUrls());
			names.add(unit.getProductName());
			
			i++;
		}
		view.showFilmItems(imageUrlsFull,names);
	}
	@Override
	public void cancelProductBrowser() {
		//  remove the browser from the view:
		view.clearExternalPanel();
		view.reset(MAIN_LAYOUT);
		
		
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.ayurma.ayuromaweb.client.admin.view.ISliderEditView.Presenter#onAddImagesClicked()
	 * this method is called when the btn Add Images is clicked
	 * the purpose is to select multi images with a check box at the bottom of each image
	 */
	@Override
	public void onAddImagesClicked() {
		
		view.reset(DIALOG_LAYOUT);
		startSliderImageBrowser(SliderImagesBrowseActivity.MULTISELECTION_DIALOG);
		
		
	}

	@Override
	public void removeItem(int id) {

		items.remove(id);
		
		//now prepare the lists to show the data on the view interface:
		//1 images urls:
		List<String> imageUrlsFull = new ArrayList<String>();
		//2 linked products names:
		List<String> names = new ArrayList<String>();

		//preparing the above lists:
		int i=0;
		for(SliderFilmItem unit:items){
			
			imageUrlsFull.add(servletUrl+unit.getImageUrls());
			names.add(unit.getProductName());
			
			i++;
		}
		view.showFilmItems(imageUrlsFull,names);
		
		
	}

	

}
