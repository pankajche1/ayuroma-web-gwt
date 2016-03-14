package com.ayurma.ayuromaweb.client.admin.util;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import java.util.List;
import java.util.Map;

import com.ayurma.ayuromaweb.server.model.UserConstants;

public abstract class UserSettings {
	/*
	HOME=0;	PRODUCTS=1;PRODUCT_GROUPS=2;IMAGES=3;TOOLS=4;SLIDERS=5;COMPANY_DATA=6;USERS=7;SETTINGS=8;
	 */
	public static final int ADMIN_LEVEL_ROOT = 0;
	public static final int ADMIN_LEVEL_ONE = 1;
	public static final int ADMIN_LEVEL_SECOND = 2;
	public static final int ADMIN_LEVEL_THIRD = 3;
	public static final int ADMIN_LEVEL_NULL = -1;
	//access items:
	public static final int HOME=0;
	public static final int PRODUCTS=1;
	public static final int PRODUCT_GROUPS=2;
	public static final int IMAGES=3;
	public static final int TOOLS=4;
	public static final int SLIDERS=5;
	public static final int COMPANY_DATA=6;
	public static final int USERS=7;
	public static final int SETTINGS=8;
	public static final int EMPLOYEES=9;
	public static Map<Integer,String> getMapMenu(){
		Map<Integer,String> mapMenu= new HashMap<Integer,String>();
		mapMenu.put(HOME, "Home");
		mapMenu.put(COMPANY_DATA, "Company Info");
		mapMenu.put(IMAGES, "Images");
		mapMenu.put(PRODUCTS, "Products");
		mapMenu.put(PRODUCT_GROUPS, "Product Groups");
		mapMenu.put(TOOLS, "Tools");
		mapMenu.put(SLIDERS, "Slider");
		mapMenu.put(USERS, "Users");
		mapMenu.put(SETTINGS, "Settings");
		mapMenu.put(EMPLOYEES, "Employees");
		return mapMenu;
	}
	
	public static List<String> getMenuNames(){
		List<String> menuNames=new ArrayList<String>();
		Map<Integer,String> map = getMapMenu();
		Set<Integer> keySet =map.keySet();
		
		Iterator<Integer> iterator = keySet.iterator();
		while(iterator.hasNext()){
			menuNames.add(map.get(iterator.next()));
		}
		return menuNames;
	}
	public static int[] getMenuArray(){
		//give the last item value+1 in the following:
		return new int[9];
	}
	public static UserLevelData getUserAdminLevels(int iLevelMinimum){
		UserLevelData item = new UserLevelData();
		String[] names={"Root","Level 1","Level 2","Level 3","No Access",};
		int[] values={ADMIN_LEVEL_ROOT,ADMIN_LEVEL_ONE,ADMIN_LEVEL_SECOND,
				ADMIN_LEVEL_THIRD,ADMIN_LEVEL_NULL};
		for(int i=iLevelMinimum;i<names.length;i++){
			item.addItem(names[i], values[i]);
		}
		

		return item;
		
		
		
		
	}
}
