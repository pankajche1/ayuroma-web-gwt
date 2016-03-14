package com.ayurma.ayuromaweb.client.mobile.activity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.client.juice.MyJuiceModule;
import com.ayurma.ayuromaweb.client.mobile.juice.MobileJuiceModule;
import com.ayurma.ayuromaweb.client.mobile.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.client.utils.ProductGroupDataProvider;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class ProductGroupMobileActivityTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private ProductGroupActivity groupActivityMobile;
	private com.ayurma.ayuromaweb.client.activity.ProductGroupActivity groupActivityDesktop;
	private Provider<ProductGroupPlace> productGroupPlaceProvider;
	private Provider<com.ayurma.ayuromaweb.client.place.ProductGroupPlace> productGroupDesktopPlaceProvider;
	private DatabaseManager db;
	private DataServiceAsync rpcService;
	private Cache cache;
	private ProductGroupDataProvider dataProvider;
	private ProductGroupActivityMock groupActivityMock;
	private ProductGroupPlace place;
	private com.ayurma.ayuromaweb.client.place.ProductGroupPlace placeDesktop;
	
	@Before
	public void setUp(){
		helper.setUp();
		Injector injector = Guice.createInjector(new MobileJuiceModule());
		Injector injector1 = Guice.createInjector(new MyJuiceModule());
		//creating products and groups:
		db=new DatabaseManager();
		db.createProducts();
		db.createProductGroups();
		//adding some products to a group:
		db.addProductsToGroup();
		//mobile ProductGroupActivity object:
		productGroupPlaceProvider = injector.getProvider(ProductGroupPlace.class);
		place = productGroupPlaceProvider.get(); 
		//desktop:
		productGroupDesktopPlaceProvider = injector1.getProvider(com.ayurma.ayuromaweb.client.place.ProductGroupPlace.class);
		placeDesktop = productGroupDesktopPlaceProvider.get();
		//cache:
		cache = injector.getInstance(Cache.class);
		rpcService = injector.getInstance(DataServiceAsync.class);
		groupActivityMock = injector.getInstance(ProductGroupActivityMock.class); 
		dataProvider = new ProductGroupDataProvider(rpcService, cache, groupActivityMock);

		
		
	}
	@After
	public void tearDown(){
		helper.tearDown();
		
	}
	/**
	 * testing dataProvider object:
	 */
	@Test
	public void test1(){
		//
		//Assert.assertNotNull(cache);
		//Assert.assertNotNull(rpcService);
		ProductGroupItemsData[] grp = db.getProductGroupsItemsByPage(0, 10);
		String token = String.valueOf(grp[0].getKey());
		//Assert.assertEquals(token, "");
		//dataProvider.processToken(token);
	}
	/**
	 * testing desktop ProductGroupActivity object:
	 */
	@Test
	public void testDesktopActivity(){
		
		//get the group key:
		ProductGroupItemsData[] grp = db.getProductGroupsItemsByPage(0, 10);
		String token = String.valueOf(grp[0].getKey());
		placeDesktop.setPlaceName(token);
		groupActivityDesktop = placeDesktop.getActivity();
		Assert.assertNotNull(groupActivityDesktop);

		
		
	}
	/**
	 * testing mobile ProductGroupActivity object:
	 */
	@Test
	public void testMobileActivity(){
		
		//get the group key:
		ProductGroupItemsData[] grp = db.getProductGroupsItemsByPage(0, 10);
		String token = String.valueOf(grp[0].getKey());
		place.setPlaceName(token);
		groupActivityMobile = place.getActivity();
		Assert.assertNotNull(groupActivityMobile);
		//test if the data is taken from the cache:
		place = productGroupPlaceProvider.get(); 
		place.setPlaceName(token);
		groupActivityMobile = place.getActivity();
		

		
		
	}

}
