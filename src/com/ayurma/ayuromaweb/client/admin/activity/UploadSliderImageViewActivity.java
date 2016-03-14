package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.place.UploadSliderImagePlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IUploadSliderImageView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class UploadSliderImageViewActivity extends AbstractActivity implements 
					IUploadSliderImageView.Presenter{
	private IUploadSliderImageView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private UploadSliderImagePlace place;
	@Inject
	public UploadSliderImageViewActivity(IUploadSliderImageView view,
			PlaceController placeController, AdminDataServiceAsync rpcService) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
	}
	public void init(UploadSliderImagePlace place){
		this.place=place;
		view.init();
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}

	@Override
	public void getImageUploadUrl() {
		view.info("Interacting with the server...", 1);
		rpcService.getImageUploadUrl("/admin/uploadImageServlet",new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("There was some error. Please try again.", 1);
			}

			@Override
			public void onSuccess(String url) {
				if(url!=null){
					view.info("", 1);
					//give this url to the view:
					//view.setImageUploadUrl(url);
					view.setUploadForm(url);
					
				}else{
					
					
				}
				
			}});	
		
	}

}
