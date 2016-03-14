package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ayurma.ayuromaweb.client.admin.place.AddRemProductFrmGrpPlace;
import com.ayurma.ayuromaweb.client.admin.place.BrowserProductsPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;

import com.ayurma.ayuromaweb.client.admin.util.ProductBasicInfoComparable;
import com.ayurma.ayuromaweb.client.admin.util.ProductBasicInfoComparator;
import com.ayurma.ayuromaweb.client.admin.view.IAddProductsToGroupView;

import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.DataFields;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SortBySource;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AddRemProductFrmGrpActivity extends AbstractActivity implements IAddProductsToGroupView.Presenter{
	private IAddProductsToGroupView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private final EventBus eventBus;
	private AddRemProductFrmGrpPlace place;
	private AdminCache cache;
	private ProductGroupItemsData selectedGroup;
	private BrowseProductsActivity browseProductsActivity;
	//this array goes to the server:
	private Long[] keysProducts;
	private Long[] keysDetails;
	private String[] namesProducts;
	private List<Item> tobeList=new ArrayList<Item>();
	private Provider<BrowserProductsPlace> browserProductsPlaceProvider;
	// the presente list of products:
	private List<ProductBasicInfo> listProducts=new ArrayList<ProductBasicInfo>();
	@Inject
	public AddRemProductFrmGrpActivity(IAddProductsToGroupView view,
			PlaceController placeController, AdminDataServiceAsync rpcService,EventBus eventBus,
			AdminCache cache,
			Provider<BrowserProductsPlace> browserProductsPlaceProvider) {
	
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.eventBus=eventBus;
		this.cache=cache;
		//this.browseProductsActivity=browseProductsActivity;
		this.browserProductsPlaceProvider=browserProductsPlaceProvider;
	}
	public void init(AddRemProductFrmGrpPlace place){
		this.place = place;
		
		try{
			String strKey=place.getPlaceName();
			Long key= Long.valueOf(strKey);
			selectedGroup=cache.getProductGroupItemsData(key);
			if(selectedGroup!=null){
				System.out.println("init() selected group is NOT null for key="+key);
				processData(selectedGroup);
			}else{
				System.out.println("init() selected group is null for key="+key);
				//load the group from the server:
				//TODO
			}
		}catch(NumberFormatException e){
			System.out.println("Number Format Exception for the parameter");
		}
		//for the list of the products:
		//System.out.println("AddRemActivity::init() place created actiivity id:"+browseProductsActivity.id);
		BrowserProductsPlace placeBrowserProducts=browserProductsPlaceProvider.get();
		placeBrowserProducts.setPlaceName(String.valueOf(BrowseProductsActivity.MULTI_ITEM_SELECTION_STANDALONE));
		browseProductsActivity=placeBrowserProducts.getActivity();
		
		//System.out.println("AddRemActivity::init() place name was set id:"+browseProductsActivity.id);
		//browseProductsActivity.init(placeBrowserProducts);
		browseProductsActivity.resetView();
		browseProductsActivity.start(view.get("right-panel"), eventBus);
		
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
		
	}
	
	public BrowseProductsActivity getBrowseProductsActivity() {
		return browseProductsActivity;
	}
	
	private void processData(ProductGroupItemsData data){
		//serial number of the items:
		//getting the data from the group:
		String[] itemsNames = data.getItemsNames();
		Long[] keys=data.getItemsIds();
		Long[] detailsKeys=data.getDetailsKeys();
		//create a list of the currently included products:
		listProducts=new ArrayList<ProductBasicInfo>();
		int i=0;
		for(String itemName:itemsNames){
			ProductBasicInfo product=new ProductBasicInfo();
			product.setName(itemName);

			if(i<keys.length) product.setKey(keys[i]);
			if(i<detailsKeys.length) product.setDetailsKey(detailsKeys[i]);

			listProducts.add(product);
			i++;
		}
		//populating the above list:
		showDataInView(listProducts);
		
	}
	private void showDataInView(List<ProductBasicInfo> list){
		//putting the serial number based on 1 for view display:
		String[] strSNums=new String[list.size()];
		//getting the names to be displayed on the view:
		String[] itemsNames = new String[list.size()];
		//now populating the above arrays:
		int i=0;
		for(ProductBasicInfo item:list){
			strSNums[i]=String.valueOf(i+1);
			itemsNames[i]=item.getName();
			i++;
		}
		view.setGroupData(selectedGroup.getName(),itemsNames,strSNums);
		
	}
	@Override
	public void gotoPlace(int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void includeProducts(){
		if(selectedGroup==null){
			System.out.println("selected group is null.");
			return;
		}
		List<ProductBasicInfo> listProductInfo = browseProductsActivity.getProductInfoList();
		if(listProductInfo==null) return;
		//view.info("Adding Product...", 2);//this message goes to the right side message box
		//List<Boolean> productsToAdd=browseProductsActivity.getSelectedProducts();
		List<ProductBasicInfo> listProductSelected=browseProductsActivity.getSelectedProducts();
		//now combine these products with the current products and making a new combined list:
		listProducts=combineNewProducts(listProductSelected,listProducts);
		//now the above list is to be sorted;
		listProducts=sortProductBasicInfoList(listProducts);
		//now show the data in view:
		showDataInView(listProducts);		
	}
	@Override
	public void addProductsToGroup() {
		//If there no group for adding or removing the products what
		//is the sense of going ahead. Just return and stop:
		
		if(selectedGroup==null){
			System.out.println("selected group is null.");
			return;
		}
		List<ProductBasicInfo> listProductInfo = browseProductsActivity.getProductInfoList();
		if(listProductInfo==null) return;
		//view.info("Adding Product...", 2);//this message goes to the right side message box
		//List<Boolean> productsToAdd=browseProductsActivity.getSelectedProducts();
		List<ProductBasicInfo> listProductSelected=browseProductsActivity.getSelectedProducts();
		//now combine these products with the current products and making a new combined list:
		listProducts=combineNewProducts(listProductSelected,listProducts);
		//now the above list is to be sorted;
		listProducts=sortProductBasicInfoList(listProducts);
		//now show the data in view:
		showDataInView(listProducts);
		//ALL WORK IS DONE 
		//return;
		//List<ChemicalData> listToAdd=new ArrayList<ChemicalData>();
		//Map<Long,ChemicalData> itemsToAdd=new HashMap<Long,ChemicalData>(); 
		Map<Long,ProductBasicInfo> itemsToAdd2=new HashMap<Long,ProductBasicInfo>(); 
		int i=0;
		//putting the selected products in a map mapped for their keys:
		for(ProductBasicInfo item:listProductSelected){
			itemsToAdd2.put(item.getKey(),item);
		}
		
		//based on the info if the checkbox is checked or not 
		//NOW THERE IS NO NEED OF THIS:
		//add the item to the list:
		//for(Boolean value:productsToAdd){
			//if(value){
			//	
				//itemsToAdd2.put(listProductInfo.get(i).getKey(), listProductInfo.get(i));
			///}
			//i++;
		//}
		//If not item to add then stop here
		if(itemsToAdd2.size()<=0){
			//view.info("No product to add!", 2);
			
			
			return;
		}
		//to find if the some of the above items are already present in the list:
		Long[] curItemsKeys = selectedGroup.getItemsIds();
		//looping through this array:
		for(Long key:curItemsKeys){
			//if(itemsToAdd.containsKey(key)){
				//itemsToAdd.remove(key);
			//}
			if(itemsToAdd2.containsKey(key)){
				itemsToAdd2.remove(key);
			}
		}
		//if there is no item to add then stop
		if(itemsToAdd2.size()<=0){
			//view.info("Selected Products Already included!", 2);
			
			return;
		}		
		//for sorting by name
		//Set<Long> keySet = itemsToAdd2.keySet();
		//Iterator<Long> iterator =keySet.iterator();
		
		//DataFields[] fieldsData = new DataFields[itemsToAdd2.size()+selectedGroup.getItemsIds().length];
		DataFields[] fieldsData = this.sortData(itemsToAdd2, selectedGroup);
		/*
		Long curKey;
		//ChemicalData curProduct;
		ProductBasicInfo curItem;
		i=0;
		while(iterator.hasNext()){
			curKey=iterator.next();
			//curProduct=itemsToAdd.get(curKey);
			curItem=itemsToAdd2.get(curKey);
			//fieldsData[i] = new DataFields(curProduct.getName(),"",curProduct.getKey(),curProduct.getDetailsId());
			fieldsData[i] = new DataFields(curItem.getName(),"",curItem.getKey(),curItem.getDetailsKey());
			i++;
		}
		int curIndex=i;
		//adding the already existing data:
		curItemsKeys = selectedGroup.getItemsIds();
		Long[] curDetailsKeys=selectedGroup.getDetailsKeys();
		String[] curNames=selectedGroup.getItemsNames();
        //now adding:
		i=0;
		//System.out.println("From ProductGroupAdminPresenter: adding the already existing data");
		//System.out.println("    itemsKeys:"+curItemsKeys.length+", detailsKeys:"+curDetailsKeys.length+", curNames:"+curNames.length);
		//System.out.println("    fieldsData:"+fieldsData.length);
		for(i=0;i<curItemsKeys.length;i++){
			//System.out.println("        in loop:fieldsData["+(curIndex+i)+"]");
			fieldsData[curIndex+i] = new DataFields(curNames[i],"",curItemsKeys[i],curDetailsKeys[i]);
		}
        // comparator to sort by
        Comparator<DataFields> sortBySource = new SortBySource();
        
        // sort it
        Arrays.sort(fieldsData, sortBySource);
        */
        //now the fieldsData array object is sorted		 
		//filling the fieldsData:
		
		//prepares three arrays of the keys and names of the product which are selected:
		keysProducts=new Long[fieldsData.length];
		keysDetails=new Long[fieldsData.length];
		namesProducts=new String[fieldsData.length];
		//now get the products that are selected:
		for(i=0;i<fieldsData.length;i++){
			keysProducts[i]=fieldsData[i].keyProduct;
			namesProducts[i]=fieldsData[i].source;
			keysDetails[i]=fieldsData[i].keyDetails;
			tobeList.add(new Item(fieldsData[i].source,fieldsData[i].keyProduct,
					fieldsData[i].keyDetails));
		}
		//include the items in the list:
		
		if(keysProducts.length>0){
		//saveProductsToGroup(selectedGroup.getKey(),keysProducts,namesProducts,keysDetails);
		}else{
			//view.info("No product to add!", 2);
		}
		//showing the selected products on the group view:
		i=1;//for creating the serial number display:
		
		String[] strSNums=new String[namesProducts.length];
		
		for(String itemName:namesProducts){
			strSNums[i-1]=String.valueOf(i);
			i++;
		}
		view.setSelectedProductData(selectedGroup.getName(),namesProducts,strSNums);

	}
	/**
	 * this function is for combining the new products with the products that are already included
	 * in the group.
	 */
	private List<ProductBasicInfo> combineNewProducts(List<ProductBasicInfo> newList,List<ProductBasicInfo> currentList){
		List<ProductBasicInfo> list=new ArrayList<ProductBasicInfo>();
		//make a current map:
		Map<Long,ProductBasicInfo> curMap=new HashMap<Long,ProductBasicInfo>();
		//put the new list in this cur map:
		for(ProductBasicInfo info:currentList){
			curMap.put(info.getKey(),info);
			//fill the new list to be made:
			list.add(info);
		}
		//now will iterate the new list:
		for(ProductBasicInfo info:newList){
			if(!curMap.containsKey(info.getKey())){
				//append the item to the new list
				list.add(info);
			}
		}
		return list;
	}
	private List<ProductBasicInfo> sortProductBasicInfoList(List<ProductBasicInfo> list){
		List<ProductBasicInfo> newList= new ArrayList<ProductBasicInfo>();
		//creating an array of comparable object:
		ProductBasicInfoComparable[] arrayToSort=new ProductBasicInfoComparable[list.size()];
		//populating the above array:
		for(int i=0;i<arrayToSort.length;i++){
			ProductBasicInfoComparable itemToSort=new ProductBasicInfoComparable(list.get(i));
			arrayToSort[i]=itemToSort;
		}
		//preparing the comparator:
		Comparator<ProductBasicInfoComparable> comparator = new ProductBasicInfoComparator();
		//sorting the array:
		Arrays.sort(arrayToSort,comparator);
		//now creating a new list:
		for(ProductBasicInfoComparable info:arrayToSort){
			newList.add(info.getSource());
		}
		return newList;
		
	}
	private DataFields[] sortData(Map<Long,ProductBasicInfo> itemsToAdd2,ProductGroupItemsData selectedGroup){
		//preparing for iterating the items in the list:
		Set<Long> keySet = itemsToAdd2.keySet();
		Iterator<Long> iterator =keySet.iterator();
		//creating list of data that are tobe added and that are already included:
		DataFields[] fieldsData = new DataFields[itemsToAdd2.size()+selectedGroup.getItemsIds().length];
		Long curKey;
		//ChemicalData curProduct;
		ProductBasicInfo curItem;
		int i=0;
		//creating the datafileds with the new list proposed items:
		while(iterator.hasNext()){
			curKey=iterator.next();
			//curProduct=itemsToAdd.get(curKey);
			curItem=itemsToAdd2.get(curKey);
			//fieldsData[i] = new DataFields(curProduct.getName(),"",curProduct.getKey(),curProduct.getDetailsId());
			fieldsData[i] = new DataFields(curItem.getName(),"",curItem.getKey(),curItem.getDetailsKey());
			i++;
		}
		//Processing the already existing items:
		int curIndex=i;
		//adding the already existing data:
		//we are getting 3 things cause data field needs three parameters:
		Long[] curItemsKeys = selectedGroup.getItemsIds();
		Long[] curDetailsKeys=selectedGroup.getDetailsKeys();
		String[] curNames=selectedGroup.getItemsNames();
        //now adding:
		i=0;
		//System.out.println("From ProductGroupAdminPresenter: adding the already existing data");
		//System.out.println("    itemsKeys:"+curItemsKeys.length+", detailsKeys:"+curDetailsKeys.length+", curNames:"+curNames.length);
		//System.out.println("    fieldsData:"+fieldsData.length);
		for(i=0;i<curItemsKeys.length;i++){
			//System.out.println("        in loop:fieldsData["+(curIndex+i)+"]");
			fieldsData[curIndex+i] = new DataFields(curNames[i],"",curItemsKeys[i],curDetailsKeys[i]);
		}
        // comparator to sort by
        Comparator<DataFields> sortBySource = new SortBySource();
        
        // sort it
        Arrays.sort(fieldsData, sortBySource);
        
        return fieldsData;
        
	}
	@Override
	public void save() {
		//System.out.println("AddRemProductsFromGroup saving...");
		view.info("Updating the list of products...", 0);
		simulateAjax();
		/*
		//prepare the data for saving to the server:
		Long[] keysProducts=new Long[listProducts.size()];
		Long[] keysDetails=new Long[listProducts.size()];;
		String[] namesProducts=new String[listProducts.size()];
		int i=0;
		for(ProductBasicInfo item:listProducts){
			namesProducts[i]=item.getName();
			keysProducts[i]=item.getKey();
			keysDetails[i]=item.getDetailsKey();
			i++;
		}
		if(selectedGroup!=null){
			saveProductsToGroup(selectedGroup.getKey(),keysProducts,namesProducts,keysDetails);
		}

		*/
	}
	private void fn(){
		Long[] keysProducts=new Long[listProducts.size()];
		Long[] keysDetails=new Long[listProducts.size()];;
		String[] namesProducts=new String[listProducts.size()];
		int i=0;
		for(ProductBasicInfo item:listProducts){
			namesProducts[i]=item.getName();
			keysProducts[i]=item.getKey();
			keysDetails[i]=item.getDetailsKey();
			i++;
		}
		if(selectedGroup!=null){
			saveProductsToGroup(selectedGroup.getKey(),keysProducts,namesProducts,keysDetails);
		}
	}
	private void simulateAjax(){
		Timer t = new Timer(){
		    @Override
		    public void run() {
		       fn();
		    }
		    };
		    t.schedule(2000); 
	}
	private void saveProductsToGroup(Long keyCurGroup,
			Long[] keysProducts,String[] namesProducts,Long[] keysDetails) {
		//view.info("Adding Products...", 3);
		//making the rpc
		AsyncCallback<ProductGroupItemsData> callback=	new AsyncCallback<ProductGroupItemsData>(){

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof InvocationException) { 
					view.info("No internet connection.", 0);
				}else if(caught instanceof IncompatibleRemoteServiceException){
					view.info("Error in sending enquiry", 0);
				}else{
					view.info("Error in sending enquiry", 0);
				}
				
			}

			@Override
			public void onSuccess(ProductGroupItemsData result) {
				//view.info("", 3);
				//view.info("", 2);
				//int index = groups.indexOf(selectedGroup);
				//groups.set(index, result);
				view.info("", 0);
				selectedGroup=result;
				//processCurGroup(selectedGroup);
				
				
			}


					
		};
		rpcService.setProductToGroup(keyCurGroup, keysProducts, namesProducts,keysDetails, callback);
		
	}
	private void delProductFrmGroupDb(Long keyGroup,Long keyProduct){
		AsyncCallback<String> callback=	new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String result) {
				System.out.println("From presenter message from server:"+result);
				
			}


					
		};
		//adminRpcService.deleteProductFromGroup(keyGroup, keyProduct, callback);
	}
	@Override
	public void removeProduct(int id){
		//remove the desired index item from the list:
		if(id<listProducts.size()){
			listProducts.remove(id);
			//reflect this change on the view:
			showDataInView(listProducts);
		}
	}
	@Override
	public void removeProductFromProposed(int id) {
		Long key=null;
		//remove the desired product:
		if(id<tobeList.size()){
			tobeList.remove(id);
			showOnView();
		}
		//show the new tobe list display on the view:
		
		
		
	}
	private void showOnView(){
		
		
		String[] strSNums=new String[tobeList.size()];
		String[] names=new  String[tobeList.size()]; 
		int i=0;
		for(Item item:tobeList){
			strSNums[i]=String.valueOf(i+1);
			names[i]=item.getName();
			i++;
		}
		view.setSelectedProductData(selectedGroup.getName(),names,strSNums);

	}
	private class Item{
		private String name="";
		private Long key=0L;
		private Long keyDatails=0L;
		public Item(String name, Long key, Long keyDatails) {
			
			this.name = name;
			this.key = key;
			this.keyDatails = keyDatails;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Long getKey() {
			return key;
		}
		public void setKey(Long key) {
			this.key = key;
		}
		public Long getKeyDatails() {
			return keyDatails;
		}
		public void setKeyDatails(Long keyDatails) {
			this.keyDatails = keyDatails;
		}
		
	}

}
