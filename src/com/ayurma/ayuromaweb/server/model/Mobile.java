package com.ayurma.ayuromaweb.server.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Mobile {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long key;
	@Persistent
	private String number;
	@Persistent
	private Long keyOwner;
	public Mobile(String number, Long keyOwner) {
		
		this.number = number;
		this.keyOwner = keyOwner;
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
	public Long getKey() {
		return key;
	}
	
	

}
