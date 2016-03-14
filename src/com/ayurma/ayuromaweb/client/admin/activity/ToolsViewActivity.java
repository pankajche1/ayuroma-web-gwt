package com.ayurma.ayuromaweb.client.admin.activity;



import com.ayurma.ayuromaweb.client.admin.place.ToolsViewPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IToolsView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ToolsViewActivity extends AbstractActivity implements IToolsView.Presenter{
	private IToolsView view;
	private final AdminDataServiceAsync rpcService;
	private ToolsViewPlace place;
	private final PlaceController placeController;
	@Inject
	public ToolsViewActivity(IToolsView view, AdminDataServiceAsync rpcService,
			PlaceController placeController) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.rpcService = rpcService;
		this.placeController = placeController;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(ToolsViewPlace place){
		this.place=place;
	}
	@Override
	public void createAppData(){
		rpcService.createAppData(new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				
			}});
	}

}
