package com.ayurma.ayuromaweb.server.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ChemicalInfo {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long key;
    @Persistent
    private String name="";

    @Persistent
    private Text description=new Text("");

	@Persistent
    private Text imageUrl=new Text("");

    @Persistent
    private Long detailInfoKey;

    @Persistent
    private Boolean isInStock=false;
    

	public Long getDetailInfoKey() {
		return detailInfoKey;
	}
	public void setDetailInfoKey(Long detailInfoKey) {
		this.detailInfoKey = detailInfoKey;
	}
	public String getImageUrl() {
		return imageUrl.getValue();
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl=new Text(imageUrl);
	}
	public ChemicalInfo(String name, String description) {
		
		this.name = name;
		this.description = new Text(description);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description.getValue();
	}
	public void setDescription(String description) {
		this.description = new Text(description);
	}
	public Long getKey() {
		return key;
	}
	public Boolean getIsInStock() {
		return isInStock;
	}
	public void setIsInStock(Boolean isInStock) {
		this.isInStock = isInStock;
	} 
    
}
