package com.ayurma.ayuromaweb.client.mobile.layout;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;








import com.ayurma.ayuromaweb.client.mobile.juice.MobileJuiceModule;
import com.ayurma.ayuromaweb.client.mobile.place.ProductPlace;
import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.server.dao.DAO;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ProductMobileViewLayoutTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private ProductMobileViewLayoutImpl layouts;
	private DatabaseManager db;
	private ChemicalData targetChemical;
	@Before
	public void setUp(){
		helper.setUp();
		Injector injector = Guice.createInjector(new MobileJuiceModule());
		layouts = injector.getInstance(ProductMobileViewLayoutImpl.class);
		//creating products and groups:
		db=new DatabaseManager();
		db.createProducts();
		//get some products:
		int iPage = 0;
		int itemsPerPage = 10;
		ChemicalData[] chemicals = DAO.get().getProductsListByPage(iPage, itemsPerPage);
		targetChemical = chemicals[0];
		//attach an image url with the product:
		Long productKey = targetChemical.getKey(); 
		String imgKey = "images/sunny-scary-teeth-small.png";
		db.updateProductImage(productKey, imgKey);
		Assert.assertNotNull(targetChemical);
		
	}
	@After
	public void tearDown(){
		helper.tearDown();
		
	}
	@Test
	public void test(){
		Long lngTargetChemicalKey = targetChemical.getKey();
		
		
		StringBuilder sb = new StringBuilder();
		ChemicalData chemical = db.getProductByKey(lngTargetChemicalKey);
		String nameChemical = chemical.getName();
		Assert.assertNotNull(chemical);
		layouts.get(0).render(chemical, sb, getStyles());
		System.out.println("sb:"+sb.toString());
		assertEquals(nameChemical,sb.toString());
		sb = new StringBuilder();
		layouts.get(1).render(chemical, sb, getStyles());
		
		System.out.println("sb:"+sb.toString());
	}
	private Map<String,String> getStyles(){
		Map<String,String> styles = new HashMap<String,String>();
		styles.put("image-box","productImageBox");//0
		styles.put("image","productImage");//1
		styles.put("product-description","productDescription");//2
		//styles.put("link-more","");//3
		//styles.put("table","");//4
		return styles;
	}

}
