package com.ayurma.ayuromaweb.client.mobile.activity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.ayurma.ayuromaweb.client.admin.view.MockAcceptsOneWidget;
import com.ayurma.ayuromaweb.client.mobile.juice.MobileJuiceModule;
import com.ayurma.ayuromaweb.client.mobile.place.ProductPlace;
import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.server.dao.DAO;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class ProductActivityTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private ProductActivity productActivity;
	private Provider<ProductPlace> productPlaceProvider;
	private DatabaseManager db;
	private ChemicalData targetChemical;
	@Before
	public void setUp(){
		helper.setUp();
		Injector injector = Guice.createInjector(new MobileJuiceModule());
		//creating products and groups:
		db=new DatabaseManager();
		db.createProducts();
		db.createProductGroups();
		//adding some products to a group:
		db.addProductsToGroup();
		//get some products:
		int iPage = 0;
		int itemsPerPage = 10;
		ChemicalData[] chemicals = DAO.get().getProductsListByPage(iPage, itemsPerPage);
		/*
		for(int i=0;i<chemicals.length;i++){
			System.out.println("Name:"+chemicals[i].getName()+", key:"+chemicals[i].getKey());
		}
		*/
		targetChemical = chemicals[0];
		Assert.assertNotNull(targetChemical);
		//productActivity = injector.getInstance(ProductActivity.class);
		//2 now the user goes to 'users' menu area:
		productPlaceProvider = injector.getProvider(ProductPlace.class);
		ProductPlace place = productPlaceProvider.get();
		// the place name is set to go to it:
		String token = "source=group&key="+String.valueOf(targetChemical.getKey());
		place.setPlaceName(token);
		productActivity = place.getActivity();
		
		
	}
	@After
	public void tearDown(){
		helper.tearDown();
		
	}
	@Test
	public void test(){
		productActivity.start(new MockAcceptsOneWidget(),null);
		ChemicalData targetProduct = productActivity.getTargetProduct();
		Assert.assertNotNull(targetProduct);
		Assert.assertEquals(targetProduct.getName(),"Chemical_00");
		//Assert.assertEquals(1,1);
	
	}
}
