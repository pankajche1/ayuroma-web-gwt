package com.ayurma.ayuromaweb.client.json;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JSUser extends JavaScriptObject{
	/*java script object is like this:
	 * user={
	 * 		'name':'my name',
	 * 		'email':'myemail@gmail.com'
	 * 		'isAdmin':'true'
	 * 
	 * 	}
	 */
	protected JSUser(){}
	//get name
	public final native String getName()/*-{
		return this.name;
	}-*/;
	//get email
	public final native String getEmail()/*-{
		return this.email;
	}-*/;
	//isAdmin
	public final native String isAdmin()/*-{
		return this.isAdmin;
	}-*/;
	//isLoggedin
	public final native String isLoggedin()/*-{
		return this.isLoggedin;
	}-*/;
	//login uri
	public final native String getLoginUri()/*-{
		return this.loginUri;
	}-*/;	
	//logout uri
	public final native String getLogoutUri()/*-{
		return this.logoutUri;
	}-*/;
	//admin level
	public final native String getAdminLevel()/*-{
		return this.adminLevel;
	}-*/;
	//exists in the company user db or not
	public final native String exists()/*-{
		return this.exists;
	}-*/;
	
	@SuppressWarnings("rawtypes")
	public final native String getAccessAreas() /*-{
		
		return this.accessAreas;
	}-*/;
}
