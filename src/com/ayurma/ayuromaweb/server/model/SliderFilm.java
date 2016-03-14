package com.ayurma.ayuromaweb.server.model;


import java.util.Date;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class SliderFilm {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;
	@Persistent
	private Date dateCreation;
	@Persistent
	private Date dateEdit;
	@Persistent
	private Long[] imageItems;//keys of the slider image infos not the image url in the blob store
	@Persistent
	private Long[] linkedProducts;//keys of the linked products
	@Persistent
	private String title="";
	public SliderFilm(String title) {
		
		this.title = title;
		//imageItems = new ArrayList<Long>();
		//linkedProducts = new ArrayList<Long>();
		dateCreation=new Date();
		dateEdit=dateCreation;
	}
	public Long[] getImageInfos() {

		return imageItems;
	}
	public void setImageInfos(Long[] imageItems) {
		this.imageItems = imageItems;
	}
	
	public void setLinkedProducts(Long[] linkedProducts) {
		this.linkedProducts = linkedProducts;
	}
	public Long[] getLinkedProducts(){

		return linkedProducts;		
	}
	public Long getKey() {
		return key;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public String getTitle() {
		return title;
	}
	public Date getDateEdit() {
		return dateEdit;
	}
	public void setDateEdit() {
		dateEdit = new Date();
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
