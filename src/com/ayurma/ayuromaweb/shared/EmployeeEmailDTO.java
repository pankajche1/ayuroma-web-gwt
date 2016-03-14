package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;


public class EmployeeEmailDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long key;
	private String address;
	private Long keyOwner;
	public EmployeeEmailDTO() {
		
	}
	public EmployeeEmailDTO(String address) {
		super();
		this.address = address;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getKeyOwner() {
		return keyOwner;
	}
	public void setKeyOwner(Long keyOwner) {
		this.keyOwner = keyOwner;
	}
	
	

}
