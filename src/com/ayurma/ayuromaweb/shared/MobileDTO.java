package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MobileDTO   implements Serializable{
	private Long key;
	private String number;
	private Long keyOwner;
	
	public MobileDTO() {
		
	}
	public MobileDTO(Long key) {
		this.key = key;
	}
	public MobileDTO(String number, Long keyOwner) {
		
		this.number = number;
		this.keyOwner = keyOwner;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
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
