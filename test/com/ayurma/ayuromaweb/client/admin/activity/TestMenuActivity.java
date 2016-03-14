package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.client.admin.juice.AdminJuiceModule;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.json.JSUser;
import com.ayurma.ayuromaweb.shared.UserDTO;


import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestMenuActivity {
	MenuActivity menuActivity;
	private AdminCache cache;
	//user data:
	String email="test@gmail.com";
	String userName="Pankaj";
	boolean isAdmin=true;
	String logoutUri="what is the logout uri?";
	int adminLevel=0;
	boolean exists=true;
	String accessAreas="1,1,1,1,0,0,0,1,";
	@Before
	public void setUp(){
		
		Injector injector = Guice.createInjector(new AdminJuiceModule());
		cache=injector.getInstance(AdminCache.class);
		menuActivity = injector.getInstance(MenuActivity.class);
		//putting a user in the cache:
		cache.setUser(getUser());
	}
	@After
	public void tearDown(){
		
	}
	@Test
	public void test(){
		//menuActivity.createFullMenu();
		//printMenuLabels();
		//test1();
		testMenuActivity();
	}
	private void testMenuActivity(){
		//1 first menu activiy is created:
		//2 now its' init() is called:
		menuActivity.init();
		//3 now get the user to see if every thing is ok:
		UserDTO user=menuActivity.getUser();
		System.out.println(user.toString());
	}
	private void test1(){
		// prepare accessSections:
				//0home, 1product,2group,3images,4tools,5sliders,6companydata,7users,8settings
				//HOME=0;	PRODUCTS=1;PRODUCT_GROUPS=2;IMAGES=3;TOOLS=4;SLIDERS=5;
				//COMPANY_DATA=6;USERS=7;SETTINGS=8;
				int home=1,product=0,group=0,images=1,tools=1,sliders=0,company=1,users=0,
						settings=1;
				int[] areas={home,product,group,images,tools,sliders,company,users,settings};
				List<Integer> menus = new ArrayList<Integer>();
				for(int i=0;i<areas.length;i++){
					if(areas[i]==1) menus.add(i);
				}
				menuActivity.createMenu(menus);
				printMenuLabels();
				

	}
	private void printMenuLabels(){
		List<String> menuNames = menuActivity.getMenus();
		for(String menuName:menuNames){
			System.out.println(menuName);
		}
		List<Integer> menuIds=menuActivity.getMenuCodes();
		for(int id:menuIds){
			System.out.println(id);
		}
	}
	
	private UserDTO getUser(){
		
		UserDTO user = new UserDTO();


		user.setEmail(email);
		user.setName(userName);
		user.setAdmin(isAdmin);//the creater of the site
		user.setLogoutUri(logoutUri);
		user.setAdminLevel(adminLevel);
		user.setExists(exists);
		
		if(accessAreas!=null){
			String[] areas=accessAreas.split("[,]");
			int[] intVals=new int[areas.length];
			int i=0;
			for(String val:areas){
				
				try{
					intVals[i]=Integer.valueOf(val);
					
				}catch(NumberFormatException e){
					
					intVals[i]=0;
				}
				i++;
			}
			user.setAccessSections(intVals);
			


		}
		

		return user;
	}
	
}
