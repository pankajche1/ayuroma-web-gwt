package com.ayurma.ayuromaweb.server.model;

//import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.ayurma.ayuromaweb.client.admin.util.UserSettings;


import java.util.Date;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserInfo {
	@PrimaryKey
	@Persistent
	private String email;
	@Persistent
	private String name;
	@Persistent
	private Date dateCreation;
	@Persistent
	private Date dateEdit;	
	//@Persistent
	//private boolean hasAdminPrivilegio=false;
	@Persistent
	private int adminLevel=UserSettings.ADMIN_LEVEL_NULL;
	// 0 site ower; 1 admin level; 2 restricted admin level; -1 no privilegio
	//@Persistent int adminLevel=-1;
	// 0 edit products 1 edit images 2 edit company info 3 edit users 4 edit sliders
	@Persistent
	private int[] accessSections;
	//public boolean isHasAdminPrivilegio() {
		//return hasAdminPrivilegio;
	//}
	//public void setHasAdminPrivilegio(boolean hasAdminPrivilegio) {
		//this.hasAdminPrivilegio = hasAdminPrivilegio;
	//}
	public int getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}
	public UserInfo(String email){
		this.email=email;
		this.dateCreation=new Date();
		//System.out.println("date:"+this.dateCreation);
		this.dateEdit=this.dateCreation;

	}
	
	public int[] getAccessSections() {
		return accessSections;
	}
	public void setAccessSections(int[] accessSections) {
		this.accessSections = accessSections;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateEdit() {
		return dateEdit;
	}
	public void setDateEdit() {
		
		dateEdit = new Date();
	}
	

}
