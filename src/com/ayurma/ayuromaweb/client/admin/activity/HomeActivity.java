package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.place.HomePlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IHomeView;



import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class HomeActivity extends AbstractActivity implements
				IHomeView.Presenter{
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private HomePlace place;
	@Inject
	public HomeActivity(PlaceController placeController, IHomeView view,
			AdminDataServiceAsync rpcService) {
		this.rpcService=rpcService;
		this.placeController = placeController;
		this.view = view;
	}
	private IHomeView view;
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(HomePlace place){
		this.place=place;
	}

}
