package com.ayurma.ayuromaweb.client.admin.activity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.client.admin.juice.AdminJuiceModule;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.ayurma.ayuromaweb.shared.EmployeeEmailDTO;
import com.ayurma.ayuromaweb.shared.EmployeePhoneDTO;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class AddNewEmployeeActivityTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private DatabaseManager db;
	private Injector injector;
	private AdminCache cache;
	private AddNewEmployeeActivity addNewEmployeeActivity;
	private BrowseEmployeesActivity browseEmployeesActivity;
	private EmployeeActivity employeeActivity;
	@Before
	public void setUp(){
		helper.setUp();
		injector = Guice.createInjector(new AdminJuiceModule());
		addNewEmployeeActivity = injector.getInstance(AddNewEmployeeActivity.class);
		browseEmployeesActivity = injector.getInstance(BrowseEmployeesActivity.class);
		employeeActivity = injector.getInstance(EmployeeActivity.class);
	}
	@After
	public void tearDown(){
		
		helper.tearDown();
	}
	@Test
	public void test(){
		addNewEmployee();
	}
	private void addNewEmployee(){
		assertNotNull(addNewEmployeeActivity);
		assertNotNull(browseEmployeesActivity);
		//add a new employee:
		List<String> lstMobileNumbers = new ArrayList<String>();
		List<String> lstPhones = new ArrayList<String>();
		lstPhones.add("0000000");
		lstPhones.add("2222222");
		lstPhones.add("6666666");
		List<String> lstEmails = new ArrayList<String>();
		lstEmails.add("a@g.com");
		lstEmails.add("a1@g.com");
		lstEmails.add("a2@g.com");
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
		EmployeeDTO targetEmployee = employees.get(0);
		Long keyTargetEmployee = targetEmployee.getKey(); 
		
		//get the employee by key:
		employeeActivity.getEmployeeByKey(keyTargetEmployee);//get first employee
		targetEmployee = employeeActivity.getTargetEmployee();
		assertNotNull(targetEmployee);
		List<AddressDTO> addresses = targetEmployee.getAddresses();
		assertNotNull(addresses);
		assertEquals(2, addresses.size());
		//get address 1:
		AddressDTO targetAddress = addresses.get(1);
		assertEquals("Kanpur", targetAddress.getCity());
		assertEquals("UP", targetAddress.getState());
		assertEquals("India", targetAddress.getCountry());
		//get address lines:
		List<String> lines = targetAddress.getAddressLines();
		assertNotNull(lines);
		assertEquals(4, lines.size());
		assertEquals("Line1", lines.get(0));
		assertEquals("Line2", lines.get(1));
		//get phones:
		List<EmployeePhoneDTO> phonesDTO = targetEmployee.getPhones();
		assertNotNull(phonesDTO);
		assertEquals(3, phonesDTO.size());
		int i = 0;
		for(EmployeePhoneDTO phone:phonesDTO){
			//System.out.println("Number"+phone.getNumber());
			//System.out.println("Key Owner:"+phone.getKeyOwner());
			if(i==0) assertEquals("0000000", phone.getNumber());
			i++;
		}
		//for emails:
		List<EmployeeEmailDTO> emailsDTO = targetEmployee.getEmails();
		assertNotNull(emailsDTO);
		assertEquals(3, emailsDTO.size());
		i = 0;
		for(EmployeeEmailDTO e:emailsDTO){
			//System.out.println("email address:"+e.getAddress());
			//System.out.println("email Key Owner:"+e.getKeyOwner());
			//System.out.println("email database Key:"+e.getKey());
			if(i==0) assertEquals("a@g.com", e.getAddress());
			i++;
		}
		
	}
}
