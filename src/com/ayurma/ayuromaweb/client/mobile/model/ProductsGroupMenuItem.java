package com.ayurma.ayuromaweb.client.mobile.model;

import java.io.Serializable;



public class ProductsGroupMenuItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ProductsGroupNames groupName;
	private String label;//no need of this cause variable name will server the same purpose
	private String strKey;
	private Long key = null;
	private String imageUrl;

	public enum ProductsGroupNames {
		ALL_PRODUCTS, AROMATIC_CHEMICALS, ESSENTIAL_OILS, AROMATHERAPY_OIL;

	}

	@SuppressWarnings("unused")
	private ProductsGroupMenuItem() {

	}

	public ProductsGroupMenuItem(ProductsGroupNames groupName, String displayName) {
		this.groupName = groupName;
		this.name = displayName;
		

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductsGroupNames getGroupName() {
		return groupName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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
