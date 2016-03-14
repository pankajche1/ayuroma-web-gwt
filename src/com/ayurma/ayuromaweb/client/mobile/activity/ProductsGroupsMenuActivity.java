package com.ayurma.ayuromaweb.client.mobile.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.mobile.ClientFactory;
import com.ayurma.ayuromaweb.client.mobile.model.ProductsGroupMenuItem;
import com.ayurma.ayuromaweb.client.mobile.model.ProductsGroupMenuItem.ProductsGroupNames;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductsGroupsMenuPlace;
import com.ayurma.ayuromaweb.client.mobile.view.IProductsView;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.utils.AppInitDataManager;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;


public class ProductsGroupsMenuActivity extends MGWTAbstractActivity{
	//private final ClientFactory clientFactory;
	private Logger logger = Logger.getLogger("");
	private IProductsView view;
	private DataServiceAsync rpcService;
	private PlaceController placeController;
	private ProductsGroupsMenuPlace place;
	private Provider<HomePlace> homePlaceProvider;
	private Provider<ProductGroupPlace> productGroupPlaceProvider;
	private List<ProductsGroupMenuItem> productsGroups;
	 
	private Cache cache;
	 /*
	 public ProductsActivity(ClientFactory clientFactory) {
		
		//this.clientFactory = clientFactory;
		//this.cache = this.clientFactory.getCache();
	}
	*/
		@Inject
		public ProductsGroupsMenuActivity(IProductsView view, DataServiceAsync rpcService,
				PlaceController placeController,
				Cache cache,
				Provider<HomePlace> homePlaceProvider,
				Provider<ProductGroupPlace> productGroupPlaceProvider) {
			this.view = view;
			this.rpcService=rpcService;
			this.placeController=placeController;
			this.homePlaceProvider = homePlaceProvider;
			this.productGroupPlaceProvider = productGroupPlaceProvider;
			this.cache = cache;
			
		}	 
		public void init(ProductsGroupsMenuPlace place){
			//logger.log(Level.INFO, "init()");
			this.place = place;
			
		}
	  @Override
	  public void start(AcceptsOneWidget panel, EventBus eventBus) {
		  //view = clientFactory.getProductsView();
		  //logger.log(Level.INFO, "start()");
		    //view.setHeaderTitle("Ayuroma Centre");
		    productsGroups = createProductsGroups();
		    view.setGroups(productsGroups);
		    //view.setTopics(createTopicsList());
		   
		    addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(
		            new CellSelectedHandler() {

		              @Override
		              public void onCellSelected(CellSelectedEvent event) {
		                int index = event.getIndex();
		                
		                //logger.log(Level.INFO, "index: "+String.valueOf(index));
		                //get the item at the index:
		                ProductsGroupMenuItem selectedItem = productsGroups.get(index);
		                if(selectedItem != null){
		                	//logger.log(Level.INFO, "Name: "+selectedItem.getName()+", key:"+selectedItem.getStrKey());
		                	//clientFactory.getPlaceController().goTo(new ProductGroupPlace(selectedItem.getStrKey()));
		                	ProductGroupPlace place= productGroupPlaceProvider.get();
		                	place.setPlaceName(selectedItem.getStrKey());
		        			place.setProductGroupName(selectedItem.getName());
		        			place.setStrKey(selectedItem.getStrKey());
		                	placeController.goTo(place);
		                }
		                
		

		              }
		            }));
			//getting the user interface elements and adding the handlers:
		    addHandlerRegistration(view.getHeaderRightButton().addTapHandler(new TapHandler() {

		        @Override
		        public void onTap(TapEvent event) {
		        	//clientFactory.getPlaceController().goTo(new HomePlace());
		        	HomePlace place= homePlaceProvider.get();
            		
            		place.setPlaceName("home");
            		placeController.goTo(place);


		        }
		      }));
		      
		    panel.setWidget(view);	    
	  }
	  private List<ProductsGroupMenuItem> createProductsGroups() {
		  //logger.log(Level.INFO, "createProductsGroups()");
		  AppInitDataManager appInitDataManager = new AppInitDataManager();
		  AppInitData appInitData = appInitDataManager.createData();
		 
		  int size = appInitData.getProductGroupSize();
		  List<ProductsGroupMenuItem> list = new ArrayList<ProductsGroupMenuItem>();
		  for(int i=0;i<size;i++){
			
				ProductsGroupMenuItem item = new ProductsGroupMenuItem(ProductsGroupNames.ALL_PRODUCTS, 
						appInitData.getProductGroupName(i));
				item.setStrKey(appInitData.getProductGroupKey(i));
				item.setImageUrl(appInitData.getProductGroupImageUrl(i));
				try{
					Long key = Long.valueOf(item.getStrKey());
					item.setKey(key);
					//TODO
					cache.addMobileProductGroupsItem(item);
					
				}catch(NumberFormatException e){
					logger.log(Level.SEVERE, "Number Format Exception");
				}
				
				list.add(item);
			}
		  

		    
		    //list.add(new ProductsGroupMenuItem(ProductsGroupNames.AROMATIC_CHEMICALS, "Aromatic Chemicals"));
		    //list.add(new ProductsGroupMenuItem(ProductsGroupNames.ESSENTIAL_OILS, "Essential Oils"));
		    //list.add(new ProductsGroupMenuItem(ProductsGroupNames.AROMATHERAPY_OIL, "Aromatherapy Oils"));
	

		    return list;
		  }
	  
}
