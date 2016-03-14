package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.place.AddNewProductPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IAddNewProductView;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.FieldVerifier;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class AddNewProductActivity extends AbstractActivity implements IAddNewProductView.Presenter{
	private IAddNewProductView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private AddNewProductPlace place;
	@Inject
	public AddNewProductActivity(IAddNewProductView view,
			PlaceController placeController, AdminDataServiceAsync rpcService) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(AddNewProductPlace place){
		this.place=place;
		//clearing the already exiting data on the view:
		view.reset();
		
	}

	@Override
	public void gotoPlace(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAddProductButtonClicked(String name, String description) {
		addProduct(name,description);
		
	}
	public void addProduct(String name,String description){
		
		String nameTrimmed = name.trim();
		//varifying the lengths of the name and the imageUrls:
		if (!FieldVerifier.isValidName(nameTrimmed)) {
			view.info("Name is > 100 characters.",0);
			return;
		}
		//if (!FieldVerifier.isValidName(imageUrl)) {
			//view.showMessage("ImageUrl is > 100 characters.");
			//return;
		//}		
		
		view.info("Saving the data...",0);
		ChemicalData productToAdd=null;
		productToAdd=new ChemicalData(nameTrimmed,description);
		//productToAdd.setImageUrl(imageUrl);
		addProductToDatabase(productToAdd);
	}
	private void addProductToDatabase(ChemicalData product){
		rpcService.addProduct(product, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				
				view.info("RPC failed!",0);
				
			}

			@Override
			public void onSuccess(String result) {
				
				
				if(result!=null) view.info(result,0);
				else view.info("result string from the server is NULL",0);

				
				
			}});
	}

}
