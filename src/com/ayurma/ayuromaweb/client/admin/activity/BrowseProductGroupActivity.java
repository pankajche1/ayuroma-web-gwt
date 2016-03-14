package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.place.AddRemProductFrmGrpPlace;
import com.ayurma.ayuromaweb.client.admin.place.BrowseProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.place.EditProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseProductGroupView;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BrowseProductGroupActivity extends AbstractActivity
	implements IBrowseProductGroupView.Presenter{
	private IBrowseProductGroupView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private BrowseProductGroupPlace place;
	private Provider<EditProductGroupPlace> editGroupPlaceProvider;
	private Provider<AddRemProductFrmGrpPlace> addRemProdsPlaceProvider;
	private AdminCache cache;
	//for the group list:
	int iPageGroup=-1;
	int nGroupsPerPage=10;
	//data:
	private List<ProductGroupItemsData> groups;
	private ProductGroupItemsData selectedGroup;

	@Inject
	public BrowseProductGroupActivity(IBrowseProductGroupView view,
			PlaceController placeController, AdminDataServiceAsync rpcService,
			AdminCache cache,
			Provider<EditProductGroupPlace> editGroupPlaceProvider,
			Provider<AddRemProductFrmGrpPlace> addRemProdsPlaceProvider) {
		this.cache=cache;
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.editGroupPlaceProvider=editGroupPlaceProvider;
		this.addRemProdsPlaceProvider=addRemProdsPlaceProvider;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public void init(BrowseProductGroupPlace place){
		this.place=place;
		view.reset();
	}

	@Override
	public void gotoPlace(int id) {
		
		
	}

	@Override
	public void loadProductGroupsList(String strIndexPg, String strItemsPerPage) {
		view.info("Loading Group List...", 1);
		//this data we get from the view's ui:
		try{
			iPageGroup=Integer.valueOf(strIndexPg);
			//for a common user the page starts from 1
			//so:
			iPageGroup--;
			nGroupsPerPage=Integer.valueOf(strItemsPerPage);
		}catch(NumberFormatException e){
			iPageGroup=-1;
			view.info("NumberFormatException", 1);
		}
		if(iPageGroup>=0)fetchProductGroupsItemsByPage(iPageGroup,nGroupsPerPage);
		else view.info("No Group was found.", 1);
		
	}
	private void fetchProductGroupsItemsByPage(int iPageGroup,int nGroupsPerPage){
		rpcService.getProductGroupsItemsByPage(iPageGroup, nGroupsPerPage,new AsyncCallback<ProductGroupItemsData[]>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("RPC failed", 1);
				
			}

			@Override
			public void onSuccess(ProductGroupItemsData[] result) {
				if(result!=null){

					processProductGroupItemsData(result);
				}
				
			}});
	}
	private void processProductGroupItemsData(ProductGroupItemsData[] result){
		//local data which will map the data with the view interface:
		groups=new ArrayList<ProductGroupItemsData>();
		//this list will go to the view:	
		List<String> names=new ArrayList<String>();
		//for creating the serial number on the list:
		List<String> snGroups = new ArrayList<String>();//the serial number of the products
		int iProduct=0;
		int i=0;
		for(ProductGroupItemsData data:result){
			//adding the local data:
			groups.add(data);
			//data to be shown on the view:
			names.add(data.getName());
			//for creating serial number on the list:
			iProduct=iPageGroup*nGroupsPerPage+i+1;
			snGroups.add(String.valueOf(iProduct));
			i++;

		}
		if(names.size()>0){ 
			view.info("", 1);
			view.showGroupsList(names,snGroups);
		}
		else view.info("No data to display.", 1);

	}

	@Override
	public void editProductGroup(int id) {
		//getting the key of the target group:
		try{
			Long key = groups.get(id).getKeyParent();
			
			EditProductGroupPlace place = editGroupPlaceProvider.get();
			place.setPlaceName(String.valueOf(key));
			place.setKey(key);
			placeController.goTo(place);
		}catch(IndexOutOfBoundsException e){
			
		}catch(NullPointerException e){
			
		}
		
	}
	@Override
	public void addRemProducts(int id){
		System.out.println("BrowseProductGroupActivity::addRemProducts()...");
		//getting the key of the target group:
		try{
			Long key = groups.get(id).getKeyParent();
			selectedGroup=groups.get(id);
			cache.addProductGroupItemsData(selectedGroup);
			AddRemProductFrmGrpPlace place =  addRemProdsPlaceProvider.get();
			place.setPlaceName(String.valueOf(selectedGroup.getKey()));
			
			placeController.goTo(place);
		}catch(IndexOutOfBoundsException e){
			System.out.println("    IndexOutOfBoundsException");
		}catch(NullPointerException e){
			System.out.println("    NullPointerException=");
		}
	}

}
