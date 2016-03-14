package com.ayurma.ayuromaweb.client.admin.activity;


import com.ayurma.ayuromaweb.client.admin.place.NewSliderViewPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderBrowserPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderEditPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderImagesPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderViewPlace;
import com.ayurma.ayuromaweb.client.admin.place.UploadSliderImagePlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.ISliderView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SliderViewActivity extends AbstractActivity implements ISliderView.Presenter{
	private ISliderView view;
	private final AdminDataServiceAsync rpcService;
	private SliderViewPlace place;
	private PlaceController placeController;
	private Provider<NewSliderViewPlace> newSliderlaceProvider;
	private Provider<UploadSliderImagePlace> uploadSliderImagePlaceProvider;
	private Provider<SliderImagesPlace> sliderImagesPlaceProvider;
	private Provider<SliderBrowserPlace> sliderBrowserPlaceProvider;
	private Provider<SliderEditPlace> sliderEditPlaceProvider;
	
	@Inject
	public SliderViewActivity(ISliderView view,
			AdminDataServiceAsync rpcService,
			PlaceController placeController,
			Provider<NewSliderViewPlace> newSliderlaceProvider,
			Provider<UploadSliderImagePlace> uploadSliderImagePlaceProvider,
			Provider<SliderImagesPlace> sliderImagesPlaceProvider,
			Provider<SliderBrowserPlace> sliderBrowserPlaceProvider,
			Provider<SliderEditPlace> sliderEditPlaceProvider) {
	
		this.view = view;
		this.view.setPresenter(this);
		this.rpcService = rpcService;
		this.placeController=placeController;
		this.newSliderlaceProvider=newSliderlaceProvider;
		this.uploadSliderImagePlaceProvider=uploadSliderImagePlaceProvider;
		this.sliderImagesPlaceProvider=sliderImagesPlaceProvider;
		this.sliderBrowserPlaceProvider=sliderBrowserPlaceProvider;
		this.sliderEditPlaceProvider=sliderEditPlaceProvider;
		
	}
	public void init(SliderViewPlace place){
		this.place=place;
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	@Override
	public void createNewSlider() {
		NewSliderViewPlace place = newSliderlaceProvider.get();
		placeController.goTo(place);
		
	}
	@Override
	public void browseSliders() {
		SliderBrowserPlace place = sliderBrowserPlaceProvider.get();
		placeController.goTo(place);
	}
	@Override
	public void uploadSliderImage() {
		UploadSliderImagePlace place = uploadSliderImagePlaceProvider.get();
		placeController.goTo(place);
		
	}
	@Override
	public void browseSliderImages(){
		SliderImagesPlace place =  sliderImagesPlaceProvider.get();
		place.setPlaceName(String.valueOf(SliderImagesBrowseActivity.STANDALONE));
		placeController.goTo(place);
	}
	@Override
	public void showEditSliderView(){
		SliderEditPlace place=sliderEditPlaceProvider.get();
		placeController.goTo(place);
	}
}
