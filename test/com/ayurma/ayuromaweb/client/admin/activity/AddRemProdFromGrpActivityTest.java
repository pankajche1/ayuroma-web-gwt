package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
//import static org.junit.Assert.assertEquals;
///import static org.junit.Assert.assertNotNull;

import com.ayurma.ayuromaweb.client.admin.juice.AdminJuiceModule;
import com.ayurma.ayuromaweb.client.admin.place.AddRemProductFrmGrpPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.view.MockAcceptsOneWidget;
import com.ayurma.ayuromaweb.client.admin.view.MockBrowseProductsView;
import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class AddRemProdFromGrpActivityTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private AddRemProductFrmGrpActivity activity;
	private BrowseProductsActivity browseProductsActivity;
	private DatabaseManager db;
	private Injector injector;
	private AdminCache cache;
	private String strTargetGroupKey="";
	private MockBrowseProductsView brwsPrdVw;
	@Before
	public void setUp(){
		helper.setUp();
		injector = Guice.createInjector(new AdminJuiceModule());
		//creating products and groups:
		db=new DatabaseManager();
		db.createProducts();
		db.createProductGroups();
		//adding some products to a group:
		db.addProductsToGroup();
		//getting the cache:
		cache=injector.getInstance(AdminCache.class);
		//the activity before:
		BrowseProductGroupActivity activity1=injector.getInstance(BrowseProductGroupActivity.class);
		//load the product group list in the product browser:
		activity1.loadProductGroupsList("1", "10");
		//this will place the target group in the cache
		//and then it will placeController.gotoPlace(addRemPrdsToGroup) method.
		activity1.addRemProducts(0);
		//now check in the cache if the above target group has come or not:
		//what is the key:Long for the group:
		//ProductGroupItemsData selectedGroup=cache.getProductGroupItemsData(key);
		List<ProductGroupItemsData> groups=cache.getProductGroups();
		//getting the strKey:String of the item at 0
		if(groups.size()>0) strTargetGroupKey=String.valueOf(groups.get(0).getKey());
		//display the above list:
		//db.printProductGroupsItems(groups);
		//This is the ProductBrowser View:
		brwsPrdVw=injector.getInstance(MockBrowseProductsView.class);
		//selecting these products on the above browser:
		//brwsPrdVw.selectProduct(3);
		//brwsPrdVw.selectProduct(1);
		//brwsPrdVw.selectProduct(8);
	}
	@After
	public void tearDown(){
		
		helper.tearDown();
	}
	@Test
	public void test(){
		
		//creating the AddRemProductFrmGroupActivity:
		activity=injector.getInstance(AddRemProductFrmGrpActivity.class);
		//now the browser product activity from the above activity:
		browseProductsActivity=activity.getBrowseProductsActivity();
		AddRemProductFrmGrpPlace place=injector.getInstance(AddRemProductFrmGrpPlace.class);
		//set the token for the group key:
		//place.setPlaceName("0");
		//first the place controller calls this:
		place.setPlaceName(strTargetGroupKey);
		activity.init(place);
		//then the place calls this:
		activity.start(new MockAcceptsOneWidget(),null);
		//user loads products on the product browser:
		browseProductsActivity.onLoadProductsButtonClicked("1","10",0);
		//now set the products on the browseProductsActivity:
		//ProductBasicInfo info=browseProductsActivity.getProduct(9);
		//assertNotNull(info);
		//System.out.println("info name="+info.getName());
		////List<ProductBasicInfo> infos=browseProductsActivity.getProductInfoList();
		//assertNotNull(infos);
		//assertEquals(infos.size(),10);
		//System.out.println(infos.size());
		//now addProductsToGroup button is clicked:
		// calling the addSelectedProducts button:
		//without selecting any product on the product browser:
		activity.addProductsToGroup();
		//now we call this method again to check if it shows duplicates or not:
		//select the same products in the browser:
		//selecting some products:
		brwsPrdVw.selectProduct(3);
		//brwsPrdVw.selectProduct(1);
		//brwsPrdVw.selectProduct(8);
		//now again button add add selected products:
		activity.addProductsToGroup();
		//select some other product:
		brwsPrdVw.selectProduct(6);
		activity.addProductsToGroup();
		//deselect some products:
		brwsPrdVw.deSelectProduct(3);
		brwsPrdVw.deSelectProduct(1);
		brwsPrdVw.deSelectProduct(8);
		activity.addProductsToGroup();
		//now if we take another 
		//Now suppose this view is removed by the user and some other view
		//is started. Now think what will happen? A new activity will start.
		//now suppose again the product group browser is started, what will
		//happen? 
		//then a NEW product group browser activity will start
		//but it will use the same view:
		System.out.println("------------- NEW Product Group Browser Activity STARTED---------------");
		BrowseProductGroupActivity activity2=injector.getInstance(BrowseProductGroupActivity.class);
		//load the product group list in the product browser:
		activity2.loadProductGroupsList("1", "10");
		//this will place the target group in the cache
		//and then it will call placeController.gotoPlace(addRemPrdsToGroup) method.
		activity2.addRemProducts(1);
		//now check in the cache if the above target group has come or not:
		//what is the key:Long for the group:
		//ProductGroupItemsData selectedGroup=cache.getProductGroupItemsData(key);
		List<ProductGroupItemsData> groups=cache.getProductGroups();
		if(groups.size()>0) strTargetGroupKey=String.valueOf(groups.get(0).getKey());
		//now we will start a new addRemProductsToGrp activity:
		activity=injector.getInstance(AddRemProductFrmGrpActivity.class);
		//get the browser product activity from the above activity:
		browseProductsActivity=activity.getBrowseProductsActivity();
		place.setPlaceName(strTargetGroupKey);
		activity.init(place);
		//then the place calls this:
		activity.start(new MockAcceptsOneWidget(),null);
		//user loads products on the product browser:
		browseProductsActivity.onLoadProductsButtonClicked("1","10",0);
		//adding some products without selecting any product:
		activity.addProductsToGroup();
		//now select some products:
		brwsPrdVw.selectProduct(3);
		brwsPrdVw.selectProduct(1);
		brwsPrdVw.selectProduct(8);
		//now add these products to the addRemView
		activity.addProductsToGroup();
		
		
		
	}

}
