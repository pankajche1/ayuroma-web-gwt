package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductBasicInfo implements Serializable{
	private Long key;
	private Long detailsKey;
	private String name;
	public ProductBasicInfo() {
		
	}
	public ProductBasicInfo(Long key, Long detailsKey, String name) {
		
		this.key = key;
		this.detailsKey = detailsKey;
		this.name = name;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public Long getDetailsKey() {
		return detailsKey;
	}
	public void setDetailsKey(Long detailsKey) {
		this.detailsKey = detailsKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
