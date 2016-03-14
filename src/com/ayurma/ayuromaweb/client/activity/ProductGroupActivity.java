package com.ayurma.ayuromaweb.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.activity.widget.Widget1Activity;
import com.ayurma.ayuromaweb.client.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.place.ProductPlace;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.utils.ProductGroupDataProvider;
import com.ayurma.ayuromaweb.client.view.ILayout1View;
import com.ayurma.ayuromaweb.client.view.IProductGroupView;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.ProductGrpItemsNamedGroup;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
//import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProductGroupActivity extends AbstractActivity
			implements IProductGroupView.Presenter, IProductGroupActivity{
	private final IProductGroupView view;
	private ILayout1View rootPanel;
	private final DataServiceAsync rpcService;
	private PlaceController placeController;
	private Cache cache;
	private Provider<ProductPlace> productPlaceProvider;
	private Provider<Widget1Activity> widgetActivity1Provider;
	private SideBar1Activity sidebar1Activity;
	private ProductGroupPlace place;
	private ProductGroupItemsData targetData ;
	private Long key;
	private AppInitData appInitData;
	private ProductGroupDataProvider dataProvider;
	private final int displayMode = 0;//for desktop display
	@Inject
	public ProductGroupActivity(ILayout1View rootPanel,
			IProductGroupView view,
			DataServiceAsync rpcService,
			PlaceController placeController,
			Cache cache,
			Provider<ProductPlace> productPlaceProvider,
			SideBar1Activity sidebar1Activity,
			Provider<Widget1Activity> widgetActivity1Provider,
			AppInitData appInitData) {
		
		this.view = view;
		this.rootPanel=rootPanel;
		this.rpcService = rpcService;
		this.cache=cache;
		this.placeController=placeController;
		this.productPlaceProvider=productPlaceProvider;
		this.sidebar1Activity=sidebar1Activity;
		this.appInitData=appInitData;
		this.widgetActivity1Provider=widgetActivity1Provider;
		dataProvider = new ProductGroupDataProvider(rpcService, cache, this); 
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		rootPanel.scrollTo(0, 0);
		panel.setWidget(rootPanel.asWidget());
		
		
	}
	public void init(ProductGroupPlace place) {
		
		this.place = place;
		
		
		
		//1 clear the already exiting content:
		//rootPanel.scrollTo(0, 0);
		rootPanel.get("mainContent").clear();
		view.reset();
		rootPanel.get("mainContent").add(view.asWidget());
		view.showAjaxAnim();
		//2 adding the side bar:
		rootPanel.get("sidebar").clear();
		sidebar1Activity.get().clear();
		sidebar1Activity.go(rootPanel.get("sidebar"));
		//3 setting the data on the side bar:
		//2 putting the product group entry links
		//List<String> headTexts=new ArrayList<String>();
		//List<String> bodyTexts=new ArrayList<String>();
		//List<String> imageUrls=new ArrayList<String>();
		//List<String> linkUrls=new ArrayList<String>();
		int nGroups=appInitData.getProductGroupSize();
		for(int i=0;i<nGroups;i++){
			if(appInitData.getProductGroupName(i).equals("All Products")) continue;
			Widget1Activity act1 = widgetActivity1Provider.get();
			act1.setHeading(appInitData.getProductGroupName(i));
			act1.setBody(appInitData.getProductGroupText(i));
			act1.setKey(appInitData.getProductGroupKey(i));
			act1.go(sidebar1Activity.get());	
			//headTexts.add(appInitData.getProductGroupName(i));
			//bodyTexts.add(appInitData.getProductGroupText(i));
			//imageUrls.add(appInitData.getProductGroupImageUrl(i));
			//linkUrls.add(appInitData.getProductGroupKey(i));
		}
		//adding content to the side bar:
		//String[] names={"Pankaj","Kumar","Lodhi","Bareilly"};
		//for(String name:names){
			//Widget1Activity act1 = widgetActivity1Provider.get();
			//act1.setHeading(name);
			//act1.go(sidebar1Activity.get());			
		//}
		//now processing of the data:
		String token =place.getPlaceName();
		dataProvider.processToken(token);
		/*
		try{
			//System.out.println("strKey:"+strKey);
			key = Long.valueOf(token);
			ProductGroupItemsData dataInCache=cache.getGroupsItems(key);
			
				
			if(dataInCache==null){
				//goto the server to take the data
				//System.out.println("Going to server key="+keyGroup);
				
				fectchProductGroup(key);
			}else{
				//view.stopAjaxLoading();
				data=dataInCache;
				//use the data in cache
				showData(data);
				//view.showData(dataInCache);
			}
			
		}catch(NumberFormatException e){
			//System.out.println("Some Number Format Exception...");
		}catch(NullPointerException e){
			//System.out.println("Some Null Pointer Exception...");
		}
		*/
		
	}	
	/*
	private void fectchProductGroup(Long key){
		
		rpcService.getProductGroupsItemsData(key,
				new AsyncCallback<ProductGroupItemsData>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ProductGroupItemsData result) {
						processData(result);
						
					}});
	}
	*/
	@Override
	public void showData(ProductGroupItemsData targetData){
		//data=targetData;
		//put ProductGroupItemsData object in the cache:
		//cache.addGroupItemsData(data);
	
		//Timer t = new Timer(){

		//@Override
		//public void run() {
			view.stopAjaxAnim();
			view.showData(targetData);
			
		//}};
		//t.schedule(1000);
	}
	/*
private void processData(ProductGroupItemsData result){
		
		ProductGroupItemsData targetData=result;
		//process the data:
		String[] namesProducts = result.getItemsNames();
		Long[] idProducts=result.getItemsIds();
		Long[] idDetails=result.getDetailsKeys();
		//int a='a';
		//int f='f';
		//int z='z';
		List<ProductGrpItemsNamedGroup> list = new ArrayList<ProductGrpItemsNamedGroup>();
		ProductGrpItemsNamedGroup groupATOF = new ProductGrpItemsNamedGroup(0);
		ProductGrpItemsNamedGroup groupGTOL = new ProductGrpItemsNamedGroup(1);
		ProductGrpItemsNamedGroup groupMTOR = new ProductGrpItemsNamedGroup(2);
		ProductGrpItemsNamedGroup groupSTOZ = new ProductGrpItemsNamedGroup(3);
		ProductGrpItemsNamedGroup groupOthers = new ProductGrpItemsNamedGroup(4);
		list.add(groupATOF);
		list.add(groupGTOL);
		list.add(groupMTOR);
		list.add(groupSTOZ);
		list.add(groupOthers);
		int i=0;
		if(namesProducts.length>20){
		for(String name:namesProducts){
			//put these names into groups A-F, G-L, M-R, S-Z, others
			char c = name.charAt(0);
			
			if((c>='a'&&c<='f')||(c>='A'&&c<='F')){
				groupATOF.addProductItem(name, idProducts[i], idDetails[i]);
				
			}else if((c>='g'&&c<='l')||(c>='G'&&c<='L')){
				groupGTOL.addProductItem(name, idProducts[i], idDetails[i]);
			}else if((c>='m'&&c<='r')||(c>='M'&&c<='R')){
				groupMTOR.addProductItem(name, idProducts[i], idDetails[i]);
			}else if((c>='s'&&c<='z')||(c>='S'&&c<='Z')){
				groupSTOZ.addProductItem(name, idProducts[i], idDetails[i]);
			}else{
				groupOthers.addProductItem(name, idProducts[i], idDetails[i]);
			}
			i++;
		}
		targetData.setListGroups(list);
		}
		
		
	
		showData(targetData);
		
		
	 }
	 */
	@Override
	public void processDataFromServer(ProductGroupItemsData result) {
		// TODO Auto-generated method stub
	
	}



	@Override
	public void info(String msg, int id, int type) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void stopAjaxAnim() {
		view.stopAjaxAnim();
	
	}

	@Override
	public void startAjaxAnim() {
		view.showAjaxAnim();
	
	}

	@Override
	public void setTargetData(ProductGroupItemsData targetData) {
		this.targetData = targetData;
		
	}

	@Override
	public int getDisplayMode() {
		
		return displayMode;
	}

	@Override
	public void setGroupData(String[] namesProducts, Long[] idProducts,
			Long[] idDetails) {
		// TODO Auto-generated method stub
		
	}

}
