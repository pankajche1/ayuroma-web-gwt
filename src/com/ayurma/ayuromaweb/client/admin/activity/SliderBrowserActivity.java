package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.place.SliderBrowserPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderEditPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.ISliderBrowserView;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SliderBrowserActivity extends AbstractActivity implements ISliderBrowserView.Presenter{
	private ISliderBrowserView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private SliderBrowserPlace place;
	private List<SliderFilmDTO> films;
	private Provider<SliderEditPlace> sliderEditPlaceProvider;
	private AdminCache cache;
	@Inject
	public SliderBrowserActivity(ISliderBrowserView view,
			PlaceController placeController, AdminDataServiceAsync rpcService,
			Provider<SliderEditPlace> sliderEditPlaceProvider,
			AdminCache cache) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.sliderEditPlaceProvider=sliderEditPlaceProvider;
		this.cache=cache;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		view.clear();
		
	}
	public void init(SliderBrowserPlace place){
		this.place=place;
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.ayurma.ayuromaweb.client.admin.view.ISliderBrowserView.Presenter#loadFilms(int, int)
	 * the slider films will be small in numbers so all will be downloaded 
	 * at a time
	 * interesting thing is that the nPage and itemsPerPage parameters
	 * are not used in the server for any thing. So at this time
	 * they are useless
	 */
	@Override
	public void loadFilms(int nPage, int itemsPerPage) {
		view.info("Loading...", 0);
		rpcService.getSliderFilms(nPage, itemsPerPage, 
				new AsyncCallback<List<SliderFilmDTO>>(){

					@Override
					public void onFailure(Throwable caught) {
						view.info("May be no internet connection...", 0);
						
					}

					@Override
					public void onSuccess(List<SliderFilmDTO> result) {
						view.info("", 0);
						processFilms(result);
						
					}});
		
	}
	private void processFilms(List<SliderFilmDTO> result){
		films=result;
		List<String> titles = new ArrayList<String>();
		List<String> datesCreation=new ArrayList<String>();
		List<String> datesEdit=new ArrayList<String>();
		for(SliderFilmDTO dto:films){
			//putting the data to the cache:
			cache.addSliderFilm(dto);
			titles.add(dto.getTitle());
			//datesCreation.add(dto.getDateCreation().toLocaleString());
			//datesEdit.add(dto.getDateEdit().toLocaleString());
		}
		view.setFilms(titles,datesCreation,datesEdit);
	}
	private void deleteFilmFromServer(Long key){
		rpcService.deleteSliderFilm(key, new AsyncCallback<List<SliderFilmDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<SliderFilmDTO> result) {
				processFilms(result);
				
			}});
	}
	@Override
	public void deleteFilm(int iFilm) {
		// get key of the film to delete:
				Long key = null;
				if(films!=null){
					if(films.size()>iFilm){
						key=films.get(iFilm).getKey();
						deleteFilmFromServer(key);
					}
				
				}
		
	}
	/*
	 * to display the film on the site advertisement page
	 */
	@Override
	public void displayFilm(int iFilm){
		Long key = null;
		if(films!=null){
			if(films.size()>iFilm){
				key=films.get(iFilm).getKey();
				displayFilm(key);
			}
		
		}
	}
	private void displayFilm(Long key){
		rpcService.displaySliderFilm(key, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				
			}});
	}
	@Override
	public void showEditSliderView(int iFilm){
		Long key = null;
		if(films!=null){
			if(films.size()>iFilm){
				key=films.get(iFilm).getKey();
				SliderEditPlace place=sliderEditPlaceProvider.get();
				String token= String.valueOf(key);
				place.setPlaceName(token);
				placeController.goTo(place);
				
			}
		
		}

	}
}
