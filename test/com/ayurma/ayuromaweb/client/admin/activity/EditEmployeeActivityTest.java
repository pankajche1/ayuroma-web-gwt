package com.ayurma.ayuromaweb.client.admin.activity;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.client.admin.juice.AdminJuiceModule;
import com.ayurma.ayuromaweb.client.admin.place.EditEmployeePlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.view.EditEmployeeViewMock;
import com.ayurma.ayuromaweb.client.service.DatabaseManager;
import com.ayurma.ayuromaweb.server.dao.AdminDAO;
import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;
public class EditEmployeeActivityTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private DatabaseManager db;
	private Injector injector;
	private AdminCache cache;
	private EditEmployeeActivity editEmployeeActivity;
	private BrowseEmployeesActivity browseEmployeesActivity;
	private List<EmployeeDTO> employees;
	private EmployeeDTO targetEmployee;
	private String[] namesEmployees = {"Pankaj", "Sunny", "Ravi"};
	@Before
	public void setUp(){
		helper.setUp();
		injector = Guice.createInjector(new AdminJuiceModule());
		editEmployeeActivity = injector.getInstance(EditEmployeeActivity.class);
		browseEmployeesActivity = injector.getInstance(BrowseEmployeesActivity.class);
		
	}
	@After
	public void tearDown(){
		
		helper.tearDown();
	}
	@Test
	public void test(){
		
		//updateEmployeePart1();
		//updateEmployeePart2();
		editEmployeeView();
	}
	/*
	 * for updating city state and country
	 * .i mi klama le skule .i mi citka ti .i co'o .i uinai
	 */
	private void updateEmployeePart1(){
		assertNotNull(editEmployeeActivity);
		//adding some employees:
		addEmployees();
		//getting one employee:
		//get the employees from the server:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		employees =browseEmployeesActivity.getEmployees(); 
		//get one employee:
		EditEmployeePlace place = injector.getInstance(EditEmployeePlace.class);;
		place.setPlaceName(String.valueOf(employees.get(0).getKey()));
		editEmployeeActivity.init(place);//passing a null place;
		targetEmployee = editEmployeeActivity.getTargetEmployee(); 
		assertNotNull(targetEmployee);
		//check the address:
		List<AddressDTO> addresses = targetEmployee.getAddresses();
		//get the first address:
		AddressDTO address1 = addresses.get(2);
		assertNotNull(address1);
		//check the city:
		String city = address1.getCity();
		//assertEquals(city, "Delhi");
		//get the addresses directly from the AdminDAO
		List<AddressDTO> addresses2 = AdminDAO.get().getEmployeeAddresses();
		assertNotNull(addresses2);
		assertEquals(9, addresses2.size());
		//print the addresses:
		displayAddresses(addresses2);
		//update city:
		System.out.println("key of the address updated:"+address1.getKey());
		editEmployeeActivity.updateCity("Lucknow", address1.getKey());
		//check the address update:
		addresses2 = AdminDAO.get().getEmployeeAddresses();
		assertNotNull(addresses2);
		assertEquals(9, addresses2.size());
		//print the addresses:
		displayAddresses(addresses2);
		//update state:
		editEmployeeActivity.updateState("Andhra Pradesh", address1.getKey());
		addresses2 = AdminDAO.get().getEmployeeAddresses();
		//print the addresses:
		System.out.println("======== state update ===========");
		displayAddresses(addresses2);
		//update state:
		editEmployeeActivity.updateCountry("China", address1.getKey());
		addresses2 = AdminDAO.get().getEmployeeAddresses();
		//print the addresses:
		System.out.println("======== country update ===========");
		displayAddresses(addresses2);
		
	}
	/*
	 * for updating address lines
	 * .i mi klama le skule .i mi citka ti .i co'o .i uinai
	 */
	private void updateEmployeePart2(){
		assertNotNull(editEmployeeActivity);
		//adding some employees:
		addEmployees();
		//getting one employee:
		//get the employees from the server:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		employees =browseEmployeesActivity.getEmployees(); 
		//get one employee:
		EditEmployeePlace place = injector.getInstance(EditEmployeePlace.class);
		place.setPlaceName(String.valueOf(employees.get(0).getKey()));
		editEmployeeActivity.init(place);//passing a null place;
		targetEmployee = editEmployeeActivity.getTargetEmployee(); 
		assertNotNull(targetEmployee);
		//check the address:
		List<AddressDTO> addresses = targetEmployee.getAddresses();
		//get the first address:
		AddressDTO address1 = addresses.get(1);
		//get the addresses directly from the AdminDAO
		List<AddressDTO> addresses2 = AdminDAO.get().getEmployeeAddresses();
		assertNotNull(addresses2);
		assertEquals(9, addresses2.size());
		//print the addresses:
		displayAddresses(addresses2);
		//update address lines:
		List<String> lines = new ArrayList<String>();
		String[] strLines = {
				"Address Line 1",
				"Address Line 2",
				"Address Line 3"
		};
		for(String strLine:strLines){
			lines.add(strLine);
		}
		System.out.println("Address to update key:"+address1.getKey());
		editEmployeeActivity.updateAddressLines(lines, address1.getKey());
		addresses2 = AdminDAO.get().getEmployeeAddresses();
		//print the addresses:
		System.out.println("======== line update ===========");
		displayAddresses(addresses2);
		
	}//updateEmployeePart2()
	private void editEmployeeView(){
		assertNotNull(editEmployeeActivity);
		//adding some employees:
		addEmployees();
		//getting one employee:
		//get the employees from the server:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		employees =browseEmployeesActivity.getEmployees(); 
		//get one employee:
		EditEmployeePlace place = injector.getInstance(EditEmployeePlace.class);
		//get the view:
		EditEmployeeViewMock view = injector.getInstance(EditEmployeeViewMock.class);
		assertNotNull(view);
		place.setPlaceName(String.valueOf(employees.get(0).getKey()));
		editEmployeeActivity.init(place);//passing a null place;
		//targetEmployee = view.getTargetEmployee();
		//assertNotNull(targetEmployee);
		
		//displayEmployeeDTO(targetEmployee);
	}
	@SuppressWarnings("unused")
	private void displayEmployeeDTO(EmployeeDTO dto){
		int iEmployee = 0;
		assertEquals(namesEmployees[iEmployee], dto.getName());
		
	}
	private void displayAddresses(List<AddressDTO> addresses){
		for(AddressDTO a:addresses){
			System.out.println(" key:"+a.getKey());
			System.out.println(" city:"+a.getCity());
			System.out.println(" state:"+a.getState());
			System.out.println(" country:"+a.getCountry());
			for(String s:a.getAddressLines()){
				System.out.println(" line:"+s);
			}
			System.out.println("-------------------");
		}
	}
	private void addEmployees(){
		AddNewEmployeeActivity addNewEmployeeActivity = injector.getInstance(AddNewEmployeeActivity.class);
		//add a new employee:
		List<String> lstMobileNumbers = new ArrayList<String>();
		int iEmployee = 0 ;
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
		
		addNewEmployeeActivity.onAddEmployeeButtonClicked(namesEmployees[iEmployee], "No Des",
				lstMobileNumbers, lstPhones, lstEmails);
		//get the employees from the server:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		employees =browseEmployeesActivity.getEmployees(); 
		assertNotNull(employees);
		assertEquals(1, employees.size());
		for(EmployeeDTO e:employees ){
			assertEquals("Pankaj", e.getName());



		}
		iEmployee++;
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
		addNewEmployeeActivity.onAddEmployeeButtonClicked("namesEmployees[iEmployee]", "No Des",
				lstMobileNumbers, lstPhones, lstEmails);
		//get the employees from the server:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		employees =browseEmployeesActivity.getEmployees(); 
		assertNotNull(employees);
		assertEquals(2, employees.size());
		iEmployee++;
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
		addNewEmployeeActivity.onAddEmployeeButtonClicked("namesEmployees[iEmployee]", "No Des",
				lstMobileNumbers, lstPhones, lstEmails);
		//get the employees from the server:
		browseEmployeesActivity.onLoadEmployeesButtonClicked("1", "1", 0);
		employees =browseEmployeesActivity.getEmployees(); 
		assertNotNull(employees);
		assertEquals(3, employees.size());
	}
	@SuppressWarnings("unused")
	private List<AddressDTO> generateAddresses(){
		//now get the addresses from this panel:
		
		//now create the data for address:
		List<AddressDTO> addresses = new ArrayList<AddressDTO>();
		String[] cities = {"Kanpur", "Delhi", "Mumbai"};
		String[] states = {"UP", "Delhi", "Maharastra"};
		String[] countries = {"India", "India", "India"};
		String[] lines = {"Line1", "Line2", "Line3"};
		for(int i=0;i<cities.length;i++){
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setCity(cities[i]);
			addressDTO.setState(states[i]);
			addressDTO.setCountry(countries[i]);
			List<String> lines2 = new ArrayList<String>();
			for(String line:lines){
				lines2.add(line);
			}
			addressDTO.setAddressLines(lines2);
			addresses.add(addressDTO);
		}
		
		return addresses;
		
	}

}
