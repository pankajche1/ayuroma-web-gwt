package com.ayurma.ayuromaweb.client.admin.activity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.ayurma.ayuromaweb.client.admin.activity.RegistrationViewPresenter;
import com.ayurma.ayuromaweb.client.admin.juice.AdminJuiceModule;
import com.ayurma.ayuromaweb.client.juice.MyJuiceModule;
import com.ayurma.ayuromaweb.client.service.AdminDataServiceMock;
import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class UserRegPreTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	//private AdminDataServiceMock adminDataService;
	private RegistrationViewPresenter presenter;
	private  DatabaseManager db;
	@Before
	public void setUp(){
		helper.setUp();
		db = new DatabaseManager();
		//create a user:
		db.createUser("pa@gmail.com", "gmail.com");
		Injector injector = Guice.createInjector(new AdminJuiceModule());
		presenter = injector.getInstance(RegistrationViewPresenter.class);
	}
	@After
	public void tearDown(){
		helper.tearDown();
	}
	@Test
	public void test(){
		//saving the first user:
		String email="pankaj@gmail.com";
		String msgFromDb = db.saveUser("pankaj@gmail.com", "pankaj",false,1,1);
		System.out.println("Test::test()messagefrom db:"+msgFromDb);
		//saving the user with the same primary key but with a different name:
		email="pankaj@gmail.com";
		msgFromDb = db.saveUser(email, "pankaj2",false,1,1);
		System.out.println("Test::test()messagefrom db:"+msgFromDb);
		//saving a third user with different key:
		email="hemu@gmail.com";
		msgFromDb = db.saveUser(email, "hemu",false,1,2);
		System.out.println("Test::test()messagefrom db:"+msgFromDb);
		
		//now getting the users:
		db.getUsers();
		//presenter.apply();	
		//now get user based on the email
		UserDTO userDto = db.getUser(email);
		if(userDto!=null)
			System.out.println("User with email:"+email+" UserDTO:"+userDto.toString());
		else System.out.println("no user found with email:"+email);
	}

}
