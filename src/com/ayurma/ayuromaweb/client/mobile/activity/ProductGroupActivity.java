package com.ayurma.ayuromaweb.client.mobile.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.activity.IProductGroupActivity;
import com.ayurma.ayuromaweb.client.mobile.model.ProductItem;
import com.ayurma.ayuromaweb.client.mobile.model.ProductsGroupMenuItem;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductPlace;
import com.ayurma.ayuromaweb.client.mobile.view.IProductGroupMobileView;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.utils.ProductGroupDataProvider;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.ProductGrpItemsNamedGroup;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;

public class ProductGroupActivity extends MGWTAbstractActivity implements IProductGroupActivity{
	//private ClientFactory clientFactory;
	private Logger logger = Logger.getLogger("");
	//private String token = "";
	private DataServiceAsync rpcService;
	private PlaceController placeController;
	private ProductGroupPlace place;
	private String strProductGroupKey = "";
	private Cache cache;
	private ProductGroupItemsData targetData;
	private IProductGroupMobileView view;
	private Provider<HomePlace> homePlaceProvider;
	private Provider<ProductPlace> ProductPlaceProvider;
	private List<ProductItem> listProducts;
	private ProductGroupDataProvider dataProvider;
	private final int displayMode = 1;//for mobile display
	@Inject
	public ProductGroupActivity(IProductGroupMobileView view, DataServiceAsync rpcService,
			Cache cache,
			PlaceController placeController,
			Provider<HomePlace> homePlaceProvider,
			Provider<ProductPlace> ProductPlaceProvider) {
		this.view = view;
		this.homePlaceProvider = homePlaceProvider;
		this.ProductPlaceProvider = ProductPlaceProvider;
		this.placeController=placeController;
		this.rpcService=rpcService;
		this.cache = cache;
		dataProvider = new ProductGroupDataProvider(rpcService, cache, this); 
		
		
	}
	public void init(ProductGroupPlace place){
		//logger.log(Level.INFO, "init()");
		view.showAjaxAnim();
		this.place = place;
		String token = place.getPlaceName();
		
		dataProvider.processToken(token);
		/*
		try{
		
			//logger.log(Level.INFO, "place name:"+token);
			Long key = Long.valueOf(token);
			ProductsGroupMenuItem item = cache.getMobileProductGroupsItem(key); 
			if(item!=null){
				view.setTitle(item.getName());
				fectchProductGroup(key);
			}else{
				logger.log(Level.SEVERE, "GroupItem from cache is null.");
			}
			
			
		}catch(NumberFormatException e){
			logger.log(Level.SEVERE, "Number Format Exception");
		}
		
		//pass an emtpy list to clear:
		List<ProductItem> listProducts = new ArrayList<ProductItem>();
	    view.clearList(listProducts);
	    */
		
	}
	/*
	@Inject
	public ProductGroupActivity(ClientFactory clientFactory,String token) {
		
		this.clientFactory = clientFactory;
		this.rpcService = this.clientFactory.getRpcService();
		this.token = token;
		this.cache = this.clientFactory.getCache();
		//String strKey =place.getPlaceName();
	}
	*/
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//view = clientFactory.getProductGroupView();
		//logger.log(Level.INFO, "key:"+token);
		//logger.log(Level.INFO, "here ProductGroupActivity::start()");
		 addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(
		            new CellSelectedHandler() {

		              @Override
		              public void onCellSelected(CellSelectedEvent event) {
		                int index = event.getIndex();
		                
		                //logger.log(Level.INFO, "index: "+String.valueOf(index));
		                //get the item at the index:
		                ProductItem selectedItem = listProducts.get(index);
		                if(selectedItem != null){
		                	String strKey = String.valueOf(selectedItem.getKey());
		    	        	ProductPlace place= ProductPlaceProvider.get();
		    	        	String token = "source=group&key="+strKey;
		            		place.setPlaceName(token);
		            		placeController.goTo(place);
		                	//clientFactory.getPlaceController().goTo(new ProductPlace(strKey));
		                }else{
		                	logger.log(Level.INFO, "selected item null...");
		                }
		                //clientFactory.getPlaceController().goTo(new ProductPlace("product-1"));
		                
		

		              }
		            }));
			
		addHandlerRegistration(view.getHeaderRightButton().addTapHandler(new TapHandler() {

	        @Override
	        public void onTap(TapEvent event) {
	        	HomePlace place= homePlaceProvider.get();
        		
        		place.setPlaceName("home");
        		placeController.goTo(place);


	        }
	      }));
		//logger.log(Level.INFO, "here ProductGroupActivity::start()1");
	    panel.setWidget(view.asWidget());
	    //logger.log(Level.INFO, "here ProductGroupActivity::start2()");
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
	public void showData(ProductGroupItemsData targetData){
		//this.targetData=targetData;
		//cache.addGroupItemsData(this.targetData);
		String[] names = this.targetData.getItemsNames();
		//for(String name:names){
			//logger.log(Level.INFO, "Name:"+name);
		//}
		//in mobile view we need ArrayList<ProductItem> list to show a list items on display
		
		listProducts = getListProducts(this.targetData);
		view.showListView();
		view.setProducts(listProducts);
		//view.stopAjaxAnim();
		//view.showData(data);
	}
	
private List<ProductItem> getListProducts(ProductGroupItemsData data){
	listProducts = new ArrayList<ProductItem>();
	String[] itemsNames = data.getItemsNames();
	if(itemsNames.length==0){
		//sb.append(str);
		return listProducts;
	}//length is zero
	
	if(data.getListGroups()!=null){//if groups have been put in the ProductGroupItemsData data 
		//if the names have been grouped by initial letters
		//the table will be made based on this group
		//get the groups out:
		List<ProductGrpItemsNamedGroup> list=data.getListGroups();
		ProductGrpItemsNamedGroup groupATOF = list.get(0);
		ProductGrpItemsNamedGroup groupGTOL = list.get(1);
		ProductGrpItemsNamedGroup groupMTOR = list.get(2);
		ProductGrpItemsNamedGroup groupSTOZ = list.get(3);
		ProductGrpItemsNamedGroup groupOthers = list.get(4);
		// group A to F
		List<String> names=groupATOF.getItemsNames();
		
		if(names.size()>0){
			//str+="<h2 class='"+styles[2]+"'>A-F</h2>";
			//str+=buildTable(groupATOF.getItemsNames(),groupATOF.getItemsIds(),
					//groupATOF.getDetailsKeys(),styles);
			listProducts.addAll(
			buildCellTable(groupATOF.getItemsNames(),groupATOF.getItemsIds(),
					groupATOF.getDetailsKeys()));
			
	    }
		// group G to L:
		names=groupGTOL.getItemsNames();
		//System.out.println("G-L");
		if(names.size()>0){
			//str+="<h2 class='"+styles[2]+"'>G-L</h2>";
			//str+=buildTable(groupGTOL.getItemsNames(),groupGTOL.getItemsIds(),
					//groupGTOL.getDetailsKeys(),styles);
			listProducts.addAll(
					buildCellTable(groupGTOL.getItemsNames(),groupATOF.getItemsIds(),
							groupATOF.getDetailsKeys()));
			
	    }
		// group M to R
		names=groupMTOR.getItemsNames();
		//System.out.println("M-R");
		if(names.size()>0){
			//str+="<h2 class='"+styles[2]+"'>M-R</h2>";
			//str+=buildTable(groupMTOR.getItemsNames(),groupMTOR.getItemsIds(),
					//groupMTOR.getDetailsKeys(),styles);
			listProducts.addAll(
					buildCellTable(groupMTOR.getItemsNames(),groupATOF.getItemsIds(),
							groupATOF.getDetailsKeys()));
			
	    }
		// group S to Z:
		names=groupSTOZ.getItemsNames();
		//System.out.println("S-Z");
		if(names.size()>0){
			//str+="<h2 class='"+styles[2]+"'>S-Z</h2>";
			//str+=buildTable(groupSTOZ.getItemsNames(),groupSTOZ.getItemsIds(),
					//groupSTOZ.getDetailsKeys(),styles);
			listProducts.addAll(
					buildCellTable(groupSTOZ.getItemsNames(),groupATOF.getItemsIds(),
							groupATOF.getDetailsKeys()));
			
	    }
		// group other names like alpha beta etc:
		names=groupOthers.getItemsNames();
		//System.out.println("Others");
		if(names.size()>0){
			//str+="<h2 class='"+styles[2]+"'>Others</h2>";
			//str+=buildTable(groupOthers.getItemsNames(),groupOthers.getItemsIds(),
					//groupOthers.getDetailsKeys(),styles);
			
	    }

	}else{//if data listgroup is null
		//if names have not been grouped by names:
		listProducts = buildCellTable(data.getItemsNames(),data.getItemsIds(),
				data.getDetailsKeys());
		
		
		
	}//else
	return listProducts;
	
}//processList
private List<ProductItem> buildCellTable(List<String> names,List<Long> keys,List<Long> datailsKeys){
	
	int nRows=names.size()/3;
	List<ProductItem> list = new ArrayList<ProductItem>();
	for(int i=0;i<names.size();i++){
		ProductItem item = new ProductItem(names.get(i),keys.get(i));
		list.add(item);
	}//for
	
	return list;
}
private List<ProductItem> buildCellTable(String[] names,Long[] keys,Long[] datailsKeys){
	List<String> nameList=new ArrayList<String>();
	List<Long> idList = new ArrayList<Long>();
	List<Long> detailsKeysList = new ArrayList<Long>();
	int i=0;
	for(String name:names){
		nameList.add(name);
		idList.add(keys[i]);
		detailsKeysList.add(datailsKeys[i]);
		i++;
	}
	
	return buildCellTable(nameList,idList,detailsKeysList);
}
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
	// TODO Auto-generated method stub
	
}
@Override
public void startAjaxAnim() {
	// TODO Auto-generated method stub
	
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
	listProducts = new ArrayList<ProductItem>();
	
	if(namesProducts.length!=idProducts.length){
		//TODO give some message that some error has occurred. 
		System.out.println("ProductGroupActivity(mobile)::setGroupData() length does not match!!");
		return;
	}
	for(int i=0;i<namesProducts.length;i++){
		ProductItem item = new ProductItem(namesProducts[i], idProducts[i]);
		listProducts.add(item);
	}//for
	//display the data:
	view.stopAjaxAnim();
	view.showListView();
	view.setProducts(listProducts);
	
}
}
