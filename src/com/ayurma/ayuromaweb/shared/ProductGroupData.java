package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;



@SuppressWarnings("serial")
public class ProductGroupData  implements Serializable{
    private String name="";
   
    public Long getKeyItems() {
		return keyItems;
	}

	public void setKeyItems(Long keyItems) {
		this.keyItems = keyItems;
	}

	private String description="";
    private Long key=null;
    private Long keyItems=null;//the key of the items object
    private String imageUrl="";
    private ProductGroupItemsData items;
    public int getnPages() {
		return nPages;
	}

	public void setnPages(int nPages) {
		this.nPages = nPages;
	}

	private int nPages=0;//this is nothing to do with the information of this object
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

	public ProductGroupData() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductGroupData(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public ProductGroupItemsData getItems() {
		return items;
	}

	public void setItems(ProductGroupItemsData items) {
		this.items = items;
	}
	
}
