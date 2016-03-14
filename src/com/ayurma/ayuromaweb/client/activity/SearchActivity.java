package com.ayurma.ayuromaweb.client.activity;

import com.ayurma.ayuromaweb.client.place.ProductPlace;
import com.ayurma.ayuromaweb.client.place.SearchPlace;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.view.ISearchView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class SearchActivity extends AbstractActivity implements ISearchView.Presenter{
	private final ISearchView view;
	private final DataServiceAsync rpcService;
	private SearchPlace place;
	private Provider<ProductPlace> productPlaceProvider;
	private PlaceController placeController;
	@Inject
	public SearchActivity(ISearchView view, DataServiceAsync rpcService,
			Provider<ProductPlace> productPlaceProvider,
			PlaceController placeController) {
		
		this.view = view;
		this.rpcService = rpcService;
		this.view.setPresenter(this);
		this.productPlaceProvider=productPlaceProvider;
		this.placeController=placeController;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		panel.setWidget(view.asWidget());
		
	   
		
	}
	public void init(SearchPlace place) {
		
        this.place = place;
       
        
    }

}
