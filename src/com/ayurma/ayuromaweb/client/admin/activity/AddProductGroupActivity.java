package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.place.AddProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataService;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IAddProductGroupView;
import com.ayurma.ayuromaweb.shared.ProductGroupData;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class AddProductGroupActivity extends AbstractActivity implements IAddProductGroupView.Presenter{
	private IAddProductGroupView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private AddProductGroupPlace place;
	@Inject
	public AddProductGroupActivity(IAddProductGroupView view,
			PlaceController placeController, AdminDataServiceAsync rpcService) {
		
		this.view = view;
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.view.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(AddProductGroupPlace place){
		this.place=place;
		this.view.reset();
	}

	@Override
	public void gotoPlace(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAddProductGroupButtonClicked(String name, String description,
			String imageUrl) {
		String nameTrimmed = name.trim();
		if(nameTrimmed.length()>500){
			
			view.info("Name is > 500 characters.", 0);
			return;
		}
		view.info("Saving Group...", 0);
		ProductGroupData dataToServer=new ProductGroupData(nameTrimmed,description);
		dataToServer.setImageUrl(imageUrl);
		addProductGroupToDatabase(dataToServer);
		
	}
	private void addProductGroupToDatabase(ProductGroupData dataToServer){
		 AdminDataServiceAsync adminRpcService = GWT.create(AdminDataService.class);
		 adminRpcService.addProductGroup(dataToServer, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("Exception", 0);
			}

			@Override
			public void onSuccess(String result) {
				//view.stopAjaxLoading();
				//strMessageFromServer=result;
				if(result!=null) view.info(result, 0);
				else view.info("Error", 0);
				//view.reset();
				
			}});
	}

}
