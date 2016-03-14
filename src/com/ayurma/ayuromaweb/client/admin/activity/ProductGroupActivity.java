package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.place.AddProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.place.BrowseProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IProductGroupView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class ProductGroupActivity extends AbstractActivity implements IProductGroupView.Presenter{
	private IProductGroupView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private ProductGroupPlace place;
	private Provider<AddProductGroupPlace> addProductGroupPlaceProvider;
	private Provider<BrowseProductGroupPlace> browseProductGroupPlaceProvider;
	
	@Inject
	public ProductGroupActivity(IProductGroupView view,
			PlaceController placeController, AdminDataServiceAsync rpcService,
			Provider<AddProductGroupPlace> addProductGroupPlaceProvider,
			Provider<BrowseProductGroupPlace> browseProductGroupPlaceProvider) {
		
		this.view = view;
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.view.setPresenter(this);
		this.addProductGroupPlaceProvider=addProductGroupPlaceProvider;
		this.browseProductGroupPlaceProvider=browseProductGroupPlaceProvider;
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	@Override
	public void gotoPlace(int id) {
		switch(id){
		case 0://add New Product Group place
			AddProductGroupPlace place0 = addProductGroupPlaceProvider.get();
			placeController.goTo(place0);
			break;
		case 1:
			BrowseProductGroupPlace place1 = browseProductGroupPlaceProvider.get();
			placeController.goTo(place1);
			break;
		}
		
	}
	public void init(ProductGroupPlace place){
		this.place=place;
	}
	


}
