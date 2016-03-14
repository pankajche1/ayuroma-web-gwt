package com.ayurma.ayuromaweb.client.admin.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.client.admin.juice.AdminJuiceModule;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.server.dao.AdminDAO;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.ayurma.ayuromaweb.shared.MobileDTO;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class BrowseEmployeesActivityTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private DatabaseManager db;
	private Injector injector;
	private AdminCache cache;
	private BrowseEmployeesActivity browseEmployeesActivity;
	@Before
	public void setUp(){
		helper.setUp();
		injector = Guice.createInjector(new AdminJuiceModule());
		browseEmployeesActivity = injector.getInstance(BrowseEmployeesActivity.class);
	}
	@After
	public void tearDown(){
		
		helper.tearDown();
	}
	@Test
	public void test(){
		deleteEmployee();
	}
	private void deleteEmployee(){
		assertNotNull(browseEmployeesActivity);
		//now create some employees:
		addEmployees();
		//now for deleting employee in the view with index 0;
		browseEmployeesActivity.onDeleteEmployeeClicked(0);
		//now load employees again and check the size:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		List<EmployeeDTO> employees =browseEmployeesActivity.getEmployees(); 
		assertNotNull(employees);
		assertEquals(2, employees.size());
		int i = 0;
		for(EmployeeDTO e:employees ){
			
			if(i==0) assertEquals("Pankaj 2", e.getName());
			i++;
		}
		//now check if the mobiles, emails, and phones are also deleted:
		//get mobiles:
		//get directly from the DAO:
		List<MobileDTO>  mobilesFromDb = AdminDAO.get().getEmployeeMobiles();
		assertEquals(6,mobilesFromDb.size());
		
		//for(MobileDTO mobile:mobiles){
			//System.out.println("Mobile:"+mobile.getNumber()+", keyOwner:"+mobile.getKeyOwner());
		//}
		
	}
	private void addEmployees(){
		AddNewEmployeeActivity addNewEmployeeActivity = injector.getInstance(AddNewEmployeeActivity.class);
		//add a new employee:
		List<String> lstMobileNumbers = new ArrayList<String>();
		lstMobileNumbers.add("mobile1");
		lstMobileNumbers.add("mobile2");
		lstMobileNumbers.add("mobile3");
		List<String> lstPhones = new ArrayList<String>();
		lstPhones.add("0000000");
		lstPhones.add("111111");
		lstPhones.add("222222");
		List<String> lstEmails = new ArrayList<String>();
		lstEmails.add("a1@g.com");
		lstEmails.add("a2@g.com");
		lstEmails.add("a3@g.com");
		addNewEmployeeActivity.onAddEmployeeButtonClicked("Pankaj", "No Des",
				lstMobileNumbers, lstPhones, lstEmails);
		//get the employees from the server:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		List<EmployeeDTO> employees =browseEmployeesActivity.getEmployees(); 
		assertNotNull(employees);
		assertEquals(1, employees.size());
		for(EmployeeDTO e:employees ){
			assertEquals("Pankaj", e.getName());



		}
		//add a second employee:
		lstMobileNumbers = new ArrayList<String>();
		lstMobileNumbers.add("mobile4");
		lstMobileNumbers.add("mobile5");
		lstMobileNumbers.add("mobile6");
		lstPhones = new ArrayList<String>();
		lstPhones.add("333333");
		lstPhones.add("444444");
		lstPhones.add("555555");
		lstEmails = new ArrayList<String>();
		lstEmails.add("a4@g.com");
		lstEmails.add("a5@g.com");
		lstEmails.add("a6@g.com");
		addNewEmployeeActivity.onAddEmployeeButtonClicked("Pankaj 2", "No Des",
				lstMobileNumbers, lstPhones, lstEmails);
		//get the employees from the server:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		employees =browseEmployeesActivity.getEmployees(); 
		assertNotNull(employees);
		assertEquals(2, employees.size());
		//add a third employee:
		lstMobileNumbers = new ArrayList<String>();
		lstMobileNumbers.add("mobile7");
		lstMobileNumbers.add("mobile8");
		lstMobileNumbers.add("mobile9");
		lstPhones = new ArrayList<String>();
		lstPhones.add("666666");
		lstPhones.add("777777");
		lstPhones.add("888888");
		lstEmails = new ArrayList<String>();
		lstEmails.add("a7@g.com");
		lstEmails.add("a8@g.com");
		lstEmails.add("a9@g.com");
		addNewEmployeeActivity.onAddEmployeeButtonClicked("Pankaj 3", "No Des",
				lstMobileNumbers, lstPhones, lstEmails);
		//get the employees from the server:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		employees =browseEmployeesActivity.getEmployees(); 
		assertNotNull(employees);
		assertEquals(3, employees.size());
	}
}
