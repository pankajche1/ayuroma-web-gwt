package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AddressDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Long key;
	private String city;
	private String state;
	private String country;
	private List<String> addressLines = new ArrayList<String>();
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<String> getAddressLines() {
		return addressLines;
	}
	public void setAddressLines(List<String> addressLines) {
		this.addressLines = addressLines;
	}
	
	

}
