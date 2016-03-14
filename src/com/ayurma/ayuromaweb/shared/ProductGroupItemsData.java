package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ProductGroupItemsData implements Serializable{
	 private String[] itemsNames;
	 private Long[] itemsIds;
	 private Long[] detailsKeys;
	 private String name="";
	 private Long key=null;
	 private Long keyParent=null;//the main Group object
	 private String description="";
	 private String imageUrl="";
	 //this contains the named group data:
	 private List<ProductGrpItemsNamedGroup> listGroups;
	 public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	private int nPage;
	public int getnPage() {
		return nPage;
	}
	public void setnPage(int nPage) {
		this.nPage = nPage;
	}
	public String[] getItemsNames() {
		return itemsNames;
	}
	public void setItemsNames(String[] itemsNames) {
		this.itemsNames = itemsNames;
	}
	public Long[] getItemsIds() {
		return itemsIds;
	}
	public void setItemsIds(Long[] itemsIds) {
		this.itemsIds = itemsIds;
	}
	public ProductGroupItemsData() {
		itemsNames=new String[0];
		 itemsIds=new Long[0]; 
		 detailsKeys=new Long[0]; 
	}
	public Long getKeyParent() {
		return keyParent;
	}
	public void setKeyParent(Long keyParent) {
		this.keyParent = keyParent;
	}
	public Long[] getDetailsKeys() {
		return detailsKeys;
	}
	public void setDetailsKeys(Long[] detailsKeys) {
		this.detailsKeys = detailsKeys;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<ProductGrpItemsNamedGroup> getListGroups() {
		return listGroups;
	}
	public void setListGroups(List<ProductGrpItemsNamedGroup> listGroups) {
		this.listGroups = listGroups;
	}
	
}
