package com.ayurma.ayuromaweb.client.admin.activity;



import com.ayurma.ayuromaweb.client.admin.place.AddNewEmployeePlace;
import com.ayurma.ayuromaweb.client.admin.place.AddNewProductPlace;
import com.ayurma.ayuromaweb.client.admin.place.BrowseEmployeesPlace;
import com.ayurma.ayuromaweb.client.admin.place.BrowserProductsPlace;
import com.ayurma.ayuromaweb.client.admin.place.EmployeesPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IEmployeesView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class EmployeesViewActivity  extends AbstractActivity implements IEmployeesView.Presenter{
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private EmployeesPlace place;
	private Provider<BrowseEmployeesPlace> browseEmployeesPlaceProvider;
	private Provider<AddNewEmployeePlace> addNewEmployeePlaceProvider;
	private IEmployeesView view;
	@Inject
	public EmployeesViewActivity(PlaceController placeController,
			AdminDataServiceAsync rpcService,
			Provider<BrowseEmployeesPlace> browseEmployeesPlaceProvider,
			Provider<AddNewEmployeePlace> addNewEmployeePlaceProvider,
			IEmployeesView view) {
		super();
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.browseEmployeesPlaceProvider = browseEmployeesPlaceProvider;
		this.addNewEmployeePlaceProvider = addNewEmployeePlaceProvider;
		this.view = view;
		this.view.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(EmployeesPlace place){
		this.place=place;
	}

	@Override
	public void gotoPlace(int id) {
		switch(id){
		case 0://Add New Product View
			
			AddNewEmployeePlace place0 = addNewEmployeePlaceProvider.get();
			placeController.goTo(place0);
			
			break;
		case 1://for browsing the employees
			BrowseEmployeesPlace place1 = browseEmployeesPlaceProvider.get();
			
			place1.setPlaceName(String.valueOf(BrowseEmployeesActivity.STANDALONE_BROWSER));
			placeController.goTo(place1);
			break;
		}
		
	}

}
