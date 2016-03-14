package com.ayurma.ayuromaweb.client.mobile.model;

import java.io.Serializable;



public class ProductItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String strKey;
	private Long key;
	private String imageUrl;
	
	public ProductItem(String name, Long key) {
		this.name = name;
		this.key = key;
	}
	public ProductItem(String name, String strKey, String imageUrl) {
		super();
		this.name = name;
		this.strKey = strKey;
		this.imageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStrKey() {
		return strKey;
	}
	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}

}
