package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IImageUnitView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ImageUnitActivity extends AbstractActivity 
	implements IImageUnitView.Presenter{
	private IImageUnitView view;
	private final AdminDataServiceAsync rpcService;
	@Inject
	public ImageUnitActivity(IImageUnitView view,
			AdminDataServiceAsync rpcService) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.rpcService = rpcService;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}

	@Override
	public void copyImageUrl(String imageUrl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteImage(Long imageInfoKey) {
		// TODO Auto-generated method stub
		
	}

}
