package com.ayurma.ayuromaweb.client.activity;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.client.juice.MyJuiceModule;
import com.ayurma.ayuromaweb.client.place.ProductPlace;
import com.ayurma.ayuromaweb.server.dao.AdminDAO;
import com.ayurma.ayuromaweb.server.dao.DAO;
import com.ayurma.ayuromaweb.shared.ChemicalData;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ProductViewActivityTest {
	private ProductViewActivity activity;
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	@Before
	public void setUp(){
		helper.setUp();
		ChemicalData product = new ChemicalData("Chemical 0","Chemical 0 description...");
		AdminDAO.get().addChemicalCompound(product);
		product = new ChemicalData("Chemical 1","Chemical 1 description...");
		AdminDAO.get().addChemicalCompound(product);
		product = new ChemicalData("Chemical 2","Chemical 2 description...");
		AdminDAO.get().addChemicalCompound(product);
	}
	@After
	public void tearDown(){
		helper.tearDown();
	}
	@Test
	public void test(){
		Injector injector = Guice.createInjector(new MyJuiceModule());
		activity = injector.getInstance(ProductViewActivity.class);
		ProductPlace place=injector.getInstance(ProductPlace.class);
		//PlaceController placeController=injector.getInstance(PlaceController.class);
		ChemicalData[] products = DAO.get().getProductsListByPage(0, 10);
		Long keyTarget = products[1].getKey();
		place.setPlaceName("source=group&query="+String.valueOf(keyTarget));
		//placeController.goTo(place);
		activity.init(place);
		//activity.fetchProductFromServer(22L);
	}
}
