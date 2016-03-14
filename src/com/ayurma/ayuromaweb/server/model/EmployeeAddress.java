package com.ayurma.ayuromaweb.server.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class EmployeeAddress {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long key;
	@Persistent
	private String city;
	@Persistent
	private Long keyOwner;
	@Persistent
	private String state;
	@Persistent
	private String country;
	@Persistent
	private String[] lines;
	public EmployeeAddress(Long keyOwner, String city, String state,
			String country, String[] lines) {
		super();
		this.city = city;
		this.keyOwner = keyOwner;
		this.state = state;
		this.country = country;
		this.lines = lines;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getKeyOwner() {
		return keyOwner;
	}
	public void setKeyOwner(Long keyOwner) {
		this.keyOwner = keyOwner;
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
	public String[] getLines() {
		return lines;
	}
	public void setLines(String[] lines) {
		this.lines = lines;
	}
	public Long getKey() {
		return key;
	}
	

}
