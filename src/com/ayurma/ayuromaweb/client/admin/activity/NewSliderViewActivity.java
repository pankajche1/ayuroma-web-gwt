package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.gin.AdminGinjector;
import com.ayurma.ayuromaweb.client.admin.place.NewSliderViewPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.INewSliderView;
import com.ayurma.ayuromaweb.client.gin.AyuromaGinjector;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderFilmItem;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class NewSliderViewActivity  extends AbstractActivity implements INewSliderView.Presenter,
			IProductLinker{
	private INewSliderView view;
	private final AdminDataServiceAsync rpcService;
	private NewSliderViewPlace place;
	private AdminGinjector ginjector =GWT.create(AdminGinjector.class);
	private SliderImagesBrowseActivity imageBrowserActivity;
	private BrowseProductsActivity browseProductsActivity;
	private List<SliderImageDTO> selectedImages = new ArrayList<SliderImageDTO>();
	private List<SliderFilmItem> filmItems = new ArrayList<SliderFilmItem>();
	private SliderImageDTO selectedImage;
	private int indexSelectedImage=-1;
			
	
	@Inject
	public NewSliderViewActivity(INewSliderView view,
			AdminDataServiceAsync rpcService,
			Provider<NewSliderViewPlace> newSliderlaceProvider) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.rpcService = rpcService;
		
		
	}
	public void init(NewSliderViewPlace place){
		this.place=place;
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	@Override
	public void startProductsBrowser(){
		browseProductsActivity=ginjector.getBrowseProductActivity();
		browseProductsActivity.setProductLinker(this);
		browseProductsActivity.setDisplayMode(2);
		browseProductsActivity.start(view.getRightDataPanel(),ginjector.getEventBus());
	}
	/*
	 * to show the slider image browser panel
	 */
	@Override
	public void startSliderImageBrowser(){
		imageBrowserActivity = ginjector.getSliderBrowserActivity();
		imageBrowserActivity.setDisplayMode(1);//showing checkboxes on each image unit;
		imageBrowserActivity.getExportButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				List<SliderImageDTO> selectedImages = imageBrowserActivity.getSelectedImages();
				showSelectedImages(selectedImages);
				
		}});
		imageBrowserActivity.start(view.getRightDataPanel(),ginjector.getEventBus());
	}
	private void showSelectedImages(List<SliderImageDTO> images){
		List<SliderImageDTO> imagesNew=new ArrayList<SliderImageDTO>();
		//first check there is no pre existing image:
		boolean flag=false;
		for(SliderImageDTO item:images){
			flag=false;
			for(SliderImageDTO item0:selectedImages){
				if(item0.getKey().equals(item.getKey())){
					//yes there is an image pre existing
					flag=true;
					break;
				}
			}
			if(!flag){
				//there is no pre existing image
				imagesNew.add(item);
			}
			
		}
		
		//add the new images:
		SliderFilmItem filmItem;
		for(SliderImageDTO item:imagesNew){
			selectedImages.add(item);
			filmItem=new SliderFilmItem();
			filmItem.setImageUrls(item.getImageKey());
			filmItem.setImageItemKey(item.getKey());
			filmItems.add(filmItem);
			
		}
		view.showSelectedImages(selectedImages);
	}
	@Override
	public void removeSelectedImage(int id){
		if(selectedImages.size()>0){
			selectedImage=null;
			indexSelectedImage=-1;	
			selectedImages.remove(id);
			filmItems.remove(id);
			view.showSelectedImages(selectedImages);
		}
	}
	@Override
	public void selectImage(int id){
		if(selectedImages.size()>0){
			indexSelectedImage=id;
			selectedImage=selectedImages.get(indexSelectedImage);
		}
	}
	@Override
	public void setProduct(ProductBasicInfo product) {
		
		
		if(indexSelectedImage>=0){
			filmItems.get(indexSelectedImage).setProductId(product.getKey());
			view.showLinkedProduct(product.getName(), indexSelectedImage);
		}
		
	}
	@Override
	public void saveFilm(){
		//create a slider film:
		/*
		int i=0;
		
		Long[] arr = new Long[filmItems.size()];
		for(SliderFilmItem item:filmItems){
			
			arr[i]=item.getImageItemKey();
			i++;
		}	
		*/
		SliderFilmDTO newFilm = new SliderFilmDTO("title");
		newFilm.setImageItems(filmItems);
		
		saveFilmToServer(newFilm);
	}
	public void saveFilmToServer(SliderFilmDTO newFilm){
		//now save this film to the server:
		rpcService.saveSliderFilm(newFilm, new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable caught) {
						////view.info("Error in saving film", 0);
						
					}

					@Override
					public void onSuccess(String result) {
						//view.info("", 0);
						
		}});
	}
}
