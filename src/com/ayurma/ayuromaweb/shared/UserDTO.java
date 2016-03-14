package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.Date;

import com.ayurma.ayuromaweb.client.admin.util.UserSettings;

@SuppressWarnings("serial")
public class UserDTO  implements Serializable{
	private String email;
	private String name;
	private Date dateCreation;
	private Date dateEdit;
	private int adminLevel=UserSettings.ADMIN_LEVEL_NULL;
	private int[] accessSections;
	private String logoutUri;
	boolean isAdmin=false;
	boolean exists=false;
	public String textHoneyPot="";
	public boolean isExists() {
		return exists;
	}
	public void setExists(boolean exists) {
		this.exists = exists;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getLogoutUri() {
		return logoutUri;
	}
	public void setLogoutUri(String logoutUri) {
		this.logoutUri = logoutUri;
	}
	public UserDTO() {
		
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
	public void setDateEdit(Date dateEdit) {
		this.dateEdit = dateEdit;
	}
	public int getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}
	@Override
	public String toString() {
		String strAccess="[null]";
		if(accessSections!=null){
			strAccess="[";
			for(int sec:accessSections){
				strAccess+=sec+",";
			}
			strAccess+="]";
		}
		return "UserDTO [email=" + email + ", name=" + name + ", dateCreation="
				+ dateCreation + ", dateEdit=" + dateEdit + ", adminLevel="
				+ adminLevel + ", accessSections="+strAccess+"]";
	}
	public int[] getAccessSections() {
		return accessSections;
	}
	public void setAccessSections(int[] accessSections) {
		this.accessSections = accessSections;
	}
	
	
	
}
