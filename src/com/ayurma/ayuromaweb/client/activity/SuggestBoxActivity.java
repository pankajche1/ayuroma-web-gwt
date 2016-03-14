package com.ayurma.ayuromaweb.client.activity;





import com.ayurma.ayuromaweb.client.place.ProductPlace;
import com.ayurma.ayuromaweb.client.place.SearchPlace;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.view.SuggestBoxView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class SuggestBoxActivity extends AbstractActivity implements SuggestBoxView.Presenter {
	private final DataServiceAsync rpcService;
	private SuggestBoxView view;
	private RpcSuggestOracle rpcSuggestOracle; 
	private Cache cache;
	private Provider<SearchPlace> searchPlaceProvider;
	private Provider<ProductPlace> productPlaceProvider;
	private PlaceController placeController;
	@Inject
	public SuggestBoxActivity(DataServiceAsync rpcService,SuggestBoxView view,Cache cache,
			Provider<SearchPlace> searchPlaceProvider,
			Provider<ProductPlace> productPlaceProvider,
			PlaceController placeController) {
		
		this.rpcService = rpcService;
		this.view=view;
		this.cache=cache;
		this.view.setPresenter(this);
		this.placeController=placeController;
		this.searchPlaceProvider=searchPlaceProvider;
		this.productPlaceProvider=productPlaceProvider;
		rpcSuggestOracle= new RpcSuggestOracle(rpcService,view,cache);
		this.view.setRpcOracle(this.rpcSuggestOracle);
		
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}

	@Override
	public void sendQueryToServer(String query) {
		
		//the query string is the name of the product that is being seeked
		if(query.length()>500){
			
		}else{
			//now it will go the Place searchPlace:
			/*
			SearchPlace place = searchPlaceProvider.get();
			place.setPlaceName(query);
			placeController.goTo(place);
			*/
			
			ProductPlace place2 = productPlaceProvider.get();
			//place2.setFromSearch(true);
			place2.setPlaceName("source=search&query="+query);
			//place2.setProductName(query);
			placeController.goTo(place2);
			
		}
		
	}

}
