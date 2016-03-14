package com.ayurma.ayuromaweb.server.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class EmployeePhone {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long key;
	@Persistent
	private String number;
	@Persistent
	private String areaCode;
	@Persistent
	private String countryCode;
	@Persistent
	private Long keyOwner;
	public EmployeePhone(String number, String countryCode, String areaCode, Long keyOwner) {
		super();
		this.number = number;
		this.areaCode = areaCode;
		this.countryCode = countryCode;
		this.keyOwner = keyOwner;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
