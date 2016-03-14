package com.ayurma.ayuromaweb.client.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.ayurma.ayuromaweb.client.activity.IProductGroupActivity;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.ProductGrpItemsNamedGroup;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ProductGroupDataProvider {
	private Logger logger = Logger.getLogger("");
	private final DataServiceAsync rpcService;
	private final Cache cache;
	private final IProductGroupActivity activity;
	private Long key;
	private ProductGroupItemsData targetData;
	public ProductGroupDataProvider(DataServiceAsync rpcService, Cache cache, IProductGroupActivity activity) {
		
		this.rpcService = rpcService;
		this.cache = cache;
		this.activity = activity;
	}
	public void processToken(String token){
		try{
			//System.out.println("strKey:"+strKey);
			key = Long.valueOf(token);
			ProductGroupItemsData dataInCache=cache.getGroupsItems(key);
			//logger.log(Level.INFO, "ProductGroupDataProvider::processToken().");
				
			if(dataInCache==null){
				//goto the server to take the data
				//System.out.println("Going to server key="+keyGroup);
				
				fectchProductGroup(key);
			}else{
				//System.out.println("ProductGroupDataProvider::processToken(). taking data from cache...");
				//logger.log(Level.INFO, "ProductGroupDataProvider::processToken(). taking data from cache...");
				//view.stopAjaxLoading();
				targetData=dataInCache;
				//use the data in cache
				//process the data:
				String[] namesProducts;
				Long[] idProducts;
				Long[] idDetails;
				activity.setTargetData(targetData);
				if(activity.getDisplayMode()==1){//display mode 1 = mobile view
					//logger.log(Level.INFO, "ProductGroupDataProvider::processToken(). before...");
					namesProducts = targetData.getItemsNames();
					idProducts=targetData.getItemsIds();
					idDetails=targetData.getDetailsKeys();
					//logger.log(Level.INFO, "ProductGroupDataProvider::processToken(). after...");
					activity.setGroupData(namesProducts, idProducts, idDetails);
					//at this time there is no need to sort names in to alphabetically order. so skip this
					
					
					
				}else{//for desktop
					
					activity.showData(targetData);
				}
				//view.showData(dataInCache);
			}
			
		}catch(NumberFormatException e){
			System.out.println("From ProductGroupDataProvider Some Number Format Exception...");
		}catch(NullPointerException e){
			System.out.println("From ProductGroupDataProvider Some Null Pointer Exception...");
		}
	}//processToken()
	private void fectchProductGroup(Long key){
		
		rpcService.getProductGroupsItemsData(key,
				new AsyncCallback<ProductGroupItemsData>(){

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("ProductGroupDataProvider rpc failure");
						
					}

					@Override
					public void onSuccess(ProductGroupItemsData result) {
						//System.out.println("ProductGroupDataProvider rpc success");
						processData(result);
						
					}});
	}
private void processData(ProductGroupItemsData result){
	//put the data in the cache:
		cache.addGroupItemsData(result);
		/* the ProductGroupItemsData object has these attributes:
		 *  private String[] itemsNames;
	 	 *	private Long[] itemsIds;
	 	 *	private Long[] detailsKeys;
	 	 *	private String name="";
	 	 *	private Long key=null;
	 	 * 	private Long keyParent=null;
		 * 
		 */
		// you don't need to arrange the names in alphabatically order. cause they were 
		//+ put in that order when they were uploaded to the server from the admin section
		if(result==null){
			activity.info("No Such Product group found!", 0, 1);
			return;
		}
		ProductGroupItemsData targetData=result;
		//process the data:
		String[] namesProducts = result.getItemsNames();
		Long[] idProducts=result.getItemsIds();
		Long[] idDetails=result.getDetailsKeys();
		if(activity.getDisplayMode()==1){//display mode 1 = mobile view
			activity.setTargetData(targetData);
			activity.setGroupData(namesProducts, idProducts, idDetails);
			//at this time there is no need to sort names in to alphabetically order. so skip this
			
			return;
			
		}
		//int a='a';
		//int f='f';
		//int z='z';
		//A list is maded to group the items based on their names:
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
		//If number of items is more than 20 then only it will be put in groups:
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
		//setting the target data of the activity:
		activity.setTargetData(targetData);
		// displaying the targetData:
		activity.showData(targetData);
		
	}

}
