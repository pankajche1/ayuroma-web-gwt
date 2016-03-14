package com.ayurma.ayuromaweb.client.admin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.shared.ProductBasicInfo;

public class TestComparableUtils {
	private List<ProductBasicInfo> list;
	private ProductBasicInfoComparable[] arrayToSort;
	@Before
	public void setUp(){
		String names[]={"C","A","B","D","F","G","I","J","K","E"};
		list = new ArrayList<ProductBasicInfo>();
		//fill the list:
		arrayToSort=new ProductBasicInfoComparable[10];
		for(int i=0;i<10;i++){
			ProductBasicInfo item = new ProductBasicInfo();
			item.setName(names[i]);
			ProductBasicInfoComparable itemToSort=new ProductBasicInfoComparable(item);
			
			arrayToSort[i]=itemToSort;
			list.add(item);
		}
		displayList(list);
		displayArray(arrayToSort);
	}
	@After
	public void tearDown(){
		
	}
	@Test
	public void test(){
		Comparator<ProductBasicInfoComparable> comparator = new ProductBasicInfoComparator();
		Arrays.sort(arrayToSort,comparator);
		displayList(list);
		displayArray(arrayToSort);
		
	}
	private void displayArray(ProductBasicInfoComparable[] array){
		System.out.println("----data----");
		for(ProductBasicInfoComparable item:array){
			System.out.println("name="+item.getSource().getName());
		}
	}
	private void displayList(List<ProductBasicInfo> list){
		System.out.println("====list data====");
		for(ProductBasicInfo item:list){
			System.out.println("name="+item.getName());
		}		
	}
}
