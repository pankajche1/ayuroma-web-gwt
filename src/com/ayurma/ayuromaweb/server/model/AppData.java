package com.ayurma.ayuromaweb.server.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AppData {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;
	@Persistent
	private Date dateCreation;
	@Persistent
	private Date dateEdit;
	@Persistent
	private Long SliderFilmKey;
	public AppData() {
		dateCreation=new Date();
	}
	public Date getDateEdit() {
		return dateEdit;
	}
	public void setDateEdit() {
		dateEdit = new Date();
	}
	public Long getKey() {
		return key;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public Long getSliderFilmKey() {
		return SliderFilmKey;
	}
	public void setSliderFilmKey(Long sliderFilmKey) {
		SliderFilmKey = sliderFilmKey;
	}
	
}
