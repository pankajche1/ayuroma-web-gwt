package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChemicalData implements Serializable{
	private String strId="no id";
	private Long key;
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	private String strIdDetailsInfo="no id";
	public String getStrIdDetailsInfo() {
		return strIdDetailsInfo;
	}
	public void setStrIdDetailsInfo(String strIdDetailsInfo) {
		this.strIdDetailsInfo = strIdDetailsInfo;
	}
	private String name="";
	private String description="";

	private String imageUrl="";
	private byte isInStock=0;

	//this var is putting some extra information which is not related
	//to the data itself this nPage will show the number of pages on the browser:
	private int nPages=0;
	
	public int getnPages() {
		return nPages;
	}
	public void setnPages(int nPages) {
		this.nPages = nPages;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public ChemicalData() {
		
	}
	public ChemicalData(String name, String description) {
		//this.strId=strId;
		this.name=name;
		this.description=description;
	}
	public String getStrId() {
		return strId;
	}
	public void setStrId(String strId) {
		this.strId = strId;
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
	public boolean getIsInStock() {
		return (isInStock==1)?true:false;
	}
	public void setIsInStock(boolean isInStock) {
		this.isInStock=(byte)((isInStock)?1:0);

	}
	public Long getDetailsId(){
		Long detailsId=null;
		try{
			detailsId=Long.valueOf(strIdDetailsInfo);
		}catch(Exception e){
			
		}
		return detailsId;
	}
	
}
