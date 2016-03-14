package com.ayurma.ayuromaweb.server.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class EmployeeEMail {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long key;
	@Persistent
	private String address;
	@Persistent
	private Long keyOwner;
	public EmployeeEMail(String address, Long keyOwner) {
		super();
		this.address = address;
		this.keyOwner = keyOwner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getKey() {
		return key;
	}
	public Long getKeyOwner() {
		return keyOwner;
	}
	public void setKeyOwner(Long keyOwner) {
		this.keyOwner = keyOwner;
	}
	
	

}
