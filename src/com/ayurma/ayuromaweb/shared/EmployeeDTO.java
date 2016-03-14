package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("serial")
public class EmployeeDTO  implements Serializable{
	private Long key;
	private String name;
	
	private List<MobileDTO> mobiles = new ArrayList<MobileDTO>();
	private List<AddressDTO> addresses = new ArrayList<AddressDTO>();
	private List<EmployeeEmailDTO> emails = new ArrayList<EmployeeEmailDTO>();
	private List<EmployeePhoneDTO> phones = new ArrayList<EmployeePhoneDTO>();
	private String designation;
	public EmployeeDTO() {
		
	}
	public EmployeeDTO(Long key) {
		this.key = key;
	}
	public EmployeeDTO(String name, String designation) {
		
		this.name = name;
		this.designation = designation;
	}
	
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public List<MobileDTO> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<MobileDTO> mobiles) {
		this.mobiles = mobiles;
	}
	public void addMobile(MobileDTO mobile){
		if(!mobiles.contains(mobile)){
			mobiles.add(mobile);
		}
		
	}
	public void deleteMobile(MobileDTO mobile){
		int targetIndex = -1;
		for(MobileDTO item:mobiles){
			if(item.getKey() == mobile.getKey()){
				targetIndex = mobiles.indexOf(item);
				break;
			}
		}
		if(targetIndex>=0) mobiles.remove(targetIndex);
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	public List<EmployeeEmailDTO> getEmails() {
		return emails;
	}
	public void setEmails(List<EmployeeEmailDTO> emails) {
		this.emails = emails;
	}
	public List<EmployeePhoneDTO> getPhones() {
		return phones;
	}
	public void setPhones(List<EmployeePhoneDTO> phones) {
		this.phones = phones;
	}
	
	

}
