package com.ayurma.ayuromaweb.shared;

import java.util.ArrayList;
import java.util.List;

public class CompanyInfoDTO {

    private String companyHead;
	private List<String> address;
	private List<String> mobiles;
	private List<String> telephones;
	private List<String> emails;
	private String pin;
	private String city;
	private String country;
	public CompanyInfoDTO(){
		
	}
	public CompanyInfoDTO(String companyHead, String pin, String city,
			String country) {
		
		this.companyHead = companyHead;
		this.pin = pin;
		this.city = city;
		this.country = country;
		//System.out.println("In company info constructor...");
	}
	public String getCompanyHead() {
		return companyHead;
	}
	public void setCompanyHead(String companyHead) {
		this.companyHead = companyHead;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void addAddressLine(String line){
		if(address==null) address=new ArrayList<String>();
		address.add(line);
	}
	public void addMobile(String mobile){
		if(mobiles==null) mobiles=new ArrayList<String>();
		//System.out.println("adding mobile in company info size:..."+mobiles.size());
		mobiles.add(mobile);
	}
	public void addTelephone(String telephone){
		if(telephones==null) telephones=new ArrayList<String>();
		telephones.add(telephone);
	}
	public void addEmail(String email){
		if(emails==null) emails=new ArrayList<String>();
		emails.add(email);
	}
	public List<String> getAddress() {
		return address;
	}
	public List<String> getMobiles() {
		return mobiles;
	}
	public List<String> getTelephones() {
		return telephones;
	}
	public List<String> getEmails() {
		return emails;
	}
	
}
