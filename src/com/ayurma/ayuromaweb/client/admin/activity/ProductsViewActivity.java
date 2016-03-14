package com.ayurma.ayuromaweb.client.admin.activity;


import com.ayurma.ayuromaweb.client.admin.place.AddNewProductPlace;
import com.ayurma.ayuromaweb.client.admin.place.BrowserProductsPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IProductsView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductsViewActivity extends AbstractActivity
		implements IProductsView.Presenter{
	private IProductsView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private ProductPlace place;
	private Provider<AddNewProductPlace> addNewProductPlaceProvider;
	private Provider<BrowserProductsPlace> browserProductsPlaceProvider;
	@Inject
	public ProductsViewActivity(IProductsView view,
			PlaceController placeController, AdminDataServiceAsync rpcService,
			Provider<AddNewProductPlace> addNewProductPlaceProvider,
			Provider<BrowserProductsPlace> browserProductsPlaceProvider) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.addNewProductPlaceProvider=addNewProductPlaceProvider;
		this.browserProductsPlaceProvider=browserProductsPlaceProvider;
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(ProductPlace place){
		this.place=place;
	}

	@Override
	public void gotoPlace(int id) {
		switch(id){
		case 0://Add New Product View
			AddNewProductPlace place0 = addNewProductPlaceProvider.get();
			placeController.goTo(place0);
			
			break;
		case 1://for browsing the products
			BrowserProductsPlace place1 = browserProductsPlaceProvider.get();
			
			place1.setPlaceName(String.valueOf(BrowseProductsActivity.STANDALONE_BROWSER));
			placeController.goTo(place1);
			break;
		}
		
		
	}

}
