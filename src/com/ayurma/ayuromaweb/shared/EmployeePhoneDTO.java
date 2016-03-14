package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;


public class EmployeePhoneDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Long key;
	private String countryCode;
	private String areaCode;
	private String number;
	private Long keyOwner;
	public EmployeePhoneDTO() {
		
	}
	public EmployeePhoneDTO(String countryCode, String areaCode, String number) {
		super();
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.number = number;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Long getKeyOwner() {
		return keyOwner;
	}
	public void setKeyOwner(Long keyOwner) {
		this.keyOwner = keyOwner;
	}
	

}
