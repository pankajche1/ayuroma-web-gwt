package com.ayurma.ayuromaweb.client.layout;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

import com.ayurma.ayuromaweb.client.juice.MyJuiceModule;
import com.ayurma.ayuromaweb.client.mobile.view.resource.MainClientBundleMobile;
import com.ayurma.ayuromaweb.client.mobile.view.resource.MainClientBundleMobile.ProductStyle;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ProductViewLayoutTest {
	private ProductViewLayoutImpl layouts;
	@Test
	public void test(){
		Injector injector = Guice.createInjector(new MyJuiceModule());
		layouts = injector.getInstance(ProductViewLayoutImpl.class);
		//ProductStyle style = MainClientBundleMobile.INSTANCE.productStyle();
		StringBuilder sb = new StringBuilder();
		layouts.get(0).render(getProduct(), sb, getStyles());
		
		assertEquals("Mint Oil",sb.toString());
		 sb = new StringBuilder();
		layouts.get(1).render(getProduct(), sb, getStyles());
		
		System.out.println(sb.toString());
	}
	private Map<String,String> getStyles(){
		Map<String,String> styles = new HashMap<String,String>();
		styles.put("image-box","");//0
		styles.put("image","");//1
		styles.put("link-enquiry","");//2
		styles.put("link-more","");//3
		styles.put("table","");//4
		return styles;
	}
	private ChemicalData getProduct(){
		ChemicalData product = new ChemicalData("Mint Oil","This is mint oil.");
		return product;
	}
}
