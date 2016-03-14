package com.ayurma.ayuromaweb.client.admin.services;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
public class TestAdminCache {
	private AdminCache cache;
	private List<ProductBasicInfo> list;
	private DatabaseManager db;
	@Before
	public void setUp(){
		cache = new AdminCache();
		db = new DatabaseManager();


		
	}
	@After
	public void tearDown(){
		
	}
	@Test
	public void test(){
		//suppose user loads the page 1 and nItemsPerPage 5 from the server
		// he will get a list of products infos:
		//now this info will be put in the cache:
		int iPage=0;
		int nItemsPerPage=10;
		int nTotalProducts=49;
		//getting products:
		list=db.getProductBasicInfoByPage(iPage,nItemsPerPage,nTotalProducts);
		System.out.println("---- from server----");
		db.printProductInfos(list);
		cache.addProductInfosPage(iPage, nItemsPerPage, list);
		List<ProductBasicInfo> listFromCache=cache.getProductBasicInfosByPage(iPage, nItemsPerPage);
		System.out.println("---- from cache----");
		db.printProductInfos(listFromCache);
		//getting next prodcuts:
		iPage=1;
		list=db.getProductBasicInfoByPage(iPage,nItemsPerPage,nTotalProducts);
		System.out.println("---- from server----");
		db.printProductInfos(list);
		
		cache.addProductInfosPage(iPage, nItemsPerPage, list);
		listFromCache=cache.getProductBasicInfosByPage(iPage, nItemsPerPage);
		System.out.println("---- from cache----");
		db.printProductInfos(listFromCache);
		//now get this page from the cache:
		iPage=2;
		listFromCache=cache.getProductBasicInfosByPage(iPage, nItemsPerPage);
		System.out.println("---- from cache----");
		db.printProductInfos(listFromCache);	
		//now getting the list from the server:
		list=db.getProductBasicInfoByPage(iPage,nItemsPerPage,nTotalProducts);
		System.out.println("---- from server----");
		db.printProductInfos(list);
		//put it in the cache:
		cache.addProductInfosPage(iPage, nItemsPerPage, list);
		//now get it from the cache:
		listFromCache=cache.getProductBasicInfosByPage(iPage, nItemsPerPage);
		System.out.println("---- from cache----");
		db.printProductInfos(listFromCache);	
		//now getting a single product info from the cache:
		ProductBasicInfo info=cache.getProductBasicInfoByKey(1L);
		db.printProductBasicInfo(info);
		info=cache.getProductBasicInfoByKey(48L);
		db.printProductBasicInfo(info);		
		//now download the last page:
		iPage=4;
		list=db.getProductBasicInfoByPage(iPage,nItemsPerPage,nTotalProducts);
		System.out.println("---- from server----");
		db.printProductInfos(list);
		//put it in the cache:
		cache.addProductInfosPage(iPage, nItemsPerPage, list);
		//now get it from the cache:
		listFromCache=cache.getProductBasicInfosByPage(iPage, nItemsPerPage);
		System.out.println("---- from cache----");
		db.printProductInfos(listFromCache);	
		//again getting a single product from the cache:
		info=cache.getProductBasicInfoByKey(48L);
		db.printProductBasicInfo(info);
		//now change the nItemsPerPage data:
		nItemsPerPage=20;
		//get a page from the cache:
		iPage=0;
		listFromCache=cache.getProductBasicInfosByPage(iPage, nItemsPerPage);
		System.out.println("---- from cache----");
		db.printProductInfos(listFromCache);	
		//now load this page from server:
		list=db.getProductBasicInfoByPage(iPage,nItemsPerPage,nTotalProducts);
		System.out.println("---- from server----");
		db.printProductInfos(list);
		//adding to the cache:
		cache.addProductInfosPage(iPage, nItemsPerPage, list);
		//now see in the cache:
		listFromCache=cache.getProductBasicInfosByPage(iPage, nItemsPerPage);
		System.out.println("---- from cache----");
		db.printProductInfos(listFromCache);		
			
		
	
	}
	
}
