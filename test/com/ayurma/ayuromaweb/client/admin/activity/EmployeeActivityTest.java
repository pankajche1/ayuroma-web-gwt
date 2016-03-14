package com.ayurma.ayuromaweb.client.admin.activity;

import static org.junit.Assert.*;

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

public class EmployeeActivityTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private DatabaseManager db;
	private Injector injector;
	private AdminCache cache;
	private EmployeeActivity employeeActivity;
	@Before
	public void setUp(){
		helper.setUp();
		injector = Guice.createInjector(new AdminJuiceModule());
		employeeActivity = injector.getInstance(EmployeeActivity.class);
	}
	@After
	public void tearDown(){
		
		helper.tearDown();
	}
	@Test
	public void test(){
		//assertEquals(10,10);
		//addEmployeeToDb();
		//addEmployeeMobiles();
		addEmployeeAddress();
	}
	private void addEmployeeToDb(){
		assertNotNull(employeeActivity);
		//employee 1
		employeeActivity.addEmployee("Pankaj Kumar", "No designation");
		//employee 2
		employeeActivity.addEmployee("Sunny Dagar", "Student");
		//employee 3
		employeeActivity.addEmployee("Raj Shah", "Student");
		//get the employees list from the db:
		employeeActivity.getEmployeesFromDb();
		List<EmployeeDTO> lstEmployees = employeeActivity.getEmployees();
		assertNotNull(lstEmployees);
		
		String[] names = {"Pankaj Kumar", "Sunny Dagar", "Raj Shah"};
		String[] namesNew = {"Pankaj Kumar Lodhi", "Sunny Kailash Dagar", "Raj Singh Shah"};
		Long[] keys = new Long[lstEmployees.size()];
		assertEquals(names.length,lstEmployees.size());
		int i = 0;
		for(EmployeeDTO item:lstEmployees){
			assertEquals(names[i],item.getName());
			//System.out.println(item.getKey());
			keys[i] = item.getKey();
			i++;
		}
		int indexTargetEmployee = 0;
		employeeActivity.getEmployeeByKey(keys[indexTargetEmployee ]);//get first employee
		EmployeeDTO targetEmployee = employeeActivity.getTargetEmployee();
		assertNotNull(targetEmployee);
		//check the employee data:
		assertEquals(names[indexTargetEmployee],targetEmployee.getName());
		assertEquals(keys[indexTargetEmployee],targetEmployee.getKey());
		//now updating the employee data:
		targetEmployee.setName(namesNew[indexTargetEmployee]);
		int code = 0;//all the data is to be updated:
		employeeActivity.updateEmployee(keys[indexTargetEmployee], targetEmployee, code);
		//get the updated employee:
		employeeActivity.getEmployeeByKey(keys[indexTargetEmployee ]);//get first employee
		targetEmployee = employeeActivity.getTargetEmployee();
		assertNotNull(targetEmployee);
		//check the employee data:
		assertEquals(namesNew[indexTargetEmployee],targetEmployee.getName());
		assertEquals(keys[indexTargetEmployee],targetEmployee.getKey());
		//changing only one data:
		//first only the name:
		employeeActivity.updateEmployeeName("Rahman");
		assertEquals("Rahman",targetEmployee.getName());
		//second only the designation:
		employeeActivity.updateEmployeeDesignation("Rahman is a worker");
		assertEquals("Rahman is a worker",targetEmployee.getDesignation());
		//now delete the employee:
		indexTargetEmployee = 1;
		employeeActivity.deleteEmployee(keys[indexTargetEmployee]);
		//now see the remaining employees:
		//get the employees list from the db:
		employeeActivity.getEmployeesFromDb();
		lstEmployees = employeeActivity.getEmployees();
		assertNotNull(lstEmployees);
		assertEquals(names.length-1,lstEmployees.size());
		for(EmployeeDTO item:lstEmployees){
			
			System.out.println("Name:"+item.getName());
		}
		
		
		
		
		
		
	}
	private void addEmployeeMobiles(){
		//first add some employees:
		//employee 1
		employeeActivity.addEmployee("Pankaj Kumar", "No designation");
		//employee 2
		employeeActivity.addEmployee("Sunny Dagar", "Student");
		//employee 3
		employeeActivity.addEmployee("Raj Shah", "Student");
		//get the employees list from the db:
		employeeActivity.getEmployeesFromDb();
		List<EmployeeDTO> lstEmployees = employeeActivity.getEmployees();
		assertNotNull(lstEmployees);
		
		String[] names = {"Pankaj Kumar", "Sunny Dagar", "Raj Shah"};
		String[] namesNew = {"Pankaj Kumar Lodhi", "Sunny Kailash Dagar", "Raj Singh Shah"};
		Long[] keys = new Long[lstEmployees.size()];
		assertEquals(names.length,lstEmployees.size());
		int i = 0;
		for(EmployeeDTO item:lstEmployees){
			assertEquals(names[i],item.getName());
			//System.out.println(item.getKey());
			keys[i] = item.getKey();
			i++;
		}
		int indexTargetEmployee = 0;
		employeeActivity.getEmployeeByKey(keys[indexTargetEmployee ]);//get first employee
		EmployeeDTO targetEmployee = employeeActivity.getTargetEmployee();
		assertNotNull(targetEmployee);
		//now see the mobile numbers:
		employeeActivity.getEmployeeMobilesFromDb(targetEmployee.getKey());
		//get the mobile list:
		List<MobileDTO> mobiles = targetEmployee.getMobiles();
		assertNotNull(mobiles);
		assertEquals(0,mobiles.size());
		//now add one mobile:
		String[] mobilesToAdd = {"123456789","987654321","0022558899"};
		//adding the first mobile:
		employeeActivity.addEmployeeMobile(targetEmployee.getKey(), mobilesToAdd[0]);
		
		//again get the mobiles:
		mobiles = targetEmployee.getMobiles();
		//get directly from the DAO:
		List<MobileDTO>  mobilesFromDb = AdminDAO.get().getEmployeeMobilesByKey(targetEmployee.getKey());
		assertEquals(1,mobilesFromDb.size());
		assertEquals(1,mobiles.size());
		for(MobileDTO mobile:mobiles){
			System.out.println("Mobile:"+mobile.getNumber()+", keyOwner:"+mobile.getKeyOwner());
		}
		//now add the same mobile number to the employee mobiles lists:
		employeeActivity.addEmployeeMobile(targetEmployee.getKey(), mobilesToAdd[0]);
		
		mobiles = targetEmployee.getMobiles();
		
		assertEquals(1,mobiles.size());
		for(MobileDTO mobile:mobiles){
			System.out.println("Mobile:" + mobile.getNumber()+", key:"+mobile.getKey());
		}
		//now add next mobile number to the employee mobiles lists:
		employeeActivity.addEmployeeMobile(targetEmployee.getKey(), mobilesToAdd[1]);
		mobiles = targetEmployee.getMobiles();
		assertEquals(2,mobiles.size());
		System.out.println("-----------");
		for(MobileDTO mobile:mobiles){
			System.out.println("Mobile:" + mobile.getNumber()+", key:"+mobile.getKey());
		}
		//now add next mobile number to the employee mobiles lists:
		employeeActivity.addEmployeeMobile(targetEmployee.getKey(),
				mobilesToAdd[2]);
		mobiles = targetEmployee.getMobiles();
		assertEquals(3, mobiles.size());
		System.out.println("-----------");
		for (MobileDTO mobile : mobiles) {
			System.out.println("Mobile:" + mobile.getNumber()+", key:"+mobile.getKey());
		}
		//now update the mobile:
		//first get the mobile key:
		//Long keyMobileToUpdate = mobiles.get(0).getKey();//get the first mobile
		MobileDTO targetMobile = mobiles.get(0);
		//edit something:
		targetMobile.setNumber("0000000000");
		employeeActivity.updateMobile(targetMobile, 1);//code 1 is for updating the number
		//get a fresh list of mobiles from db:
		employeeActivity.getEmployeeMobilesFromDb(targetEmployee.getKey());
		mobiles = targetEmployee.getMobiles();
		assertEquals(3, mobiles.size());
		System.out.println("-----------");
		for (MobileDTO mobile : mobiles) {
			System.out.println("Mobile:" + mobile.getNumber()+", key:"+mobile.getKey());
		}
		//getting the employee to see his mobiles list:
		employeeActivity.getEmployeeByKey(keys[indexTargetEmployee ]);//get the first employee
		targetEmployee = employeeActivity.getTargetEmployee();
		assertNotNull(targetEmployee);
		mobiles = targetEmployee.getMobiles();
		assertEquals(3, mobiles.size());
		System.out.println("-----------");
		for (MobileDTO mobile : mobiles) {
			System.out.println("Mobile:" + mobile.getNumber()+", key:"+mobile.getKey());
		}
		//deleting a mobile:
		employeeActivity.updateMobile(targetMobile, 3);//code 3 is for updating the number
		//see the mobiles:
		mobiles = targetEmployee.getMobiles();
		assertEquals(2, mobiles.size());
		System.out.println("-----------");
		for (MobileDTO mobile : mobiles) {
			System.out.println("Mobile:" + mobile.getNumber()+", key:"+mobile.getKey());
		}
		
		
		
		
		
	}
	private void addEmployeeAddress(){
		
	}
	

}
