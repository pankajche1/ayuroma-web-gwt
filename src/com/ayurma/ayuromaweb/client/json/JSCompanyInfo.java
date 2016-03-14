package com.ayurma.ayuromaweb.client.json;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;


public class JSCompanyInfo extends JavaScriptObject{
	protected JSCompanyInfo(){}
	/*
   
    "address":["116/317, Adarshnagar","Rawatpur gaon"],
    "telephones":["(0512)2500206"],
    "mobiles":["+91 9415050346","+91 9651712468","+91 9335662851"],
    "addressHead":"Kanpur Office",
    "emails":["ayuroma07@gmail.com","ayuroma07@rediffmail.com"],
  
  
    	*/
    //(1)
	public final native String getPin() /*-{
	
		return this.pin;
	}-*/;
	//(2)
	public final native String getCountry() /*-{
	
		return this.country;
	}-*/;	
	//(3)
	public final native String getCity() /*-{
	
		return this.city;
	}-*/;	
	//(4)
	public final native String getAddressHead() /*-{
	
		return this.addressHead;
	}-*/;	
	//(5)
	public final native JsArray<JsAddress> getAddressLines()/*-{
	
		return this.address;
	}-*/;	
	//(6)
	public final native JsArray<JsMobile> getMobiles()/*-{
	
		return this.mobiles;
	}-*/;	
	//(7)
	public final native JsArray<JsTelephone> getTelephones()/*-{
	
		return this.telephones;
	}-*/;	
	//(8)
	public final native JsArray<JsEmail> getEmails()/*-{
	
		return this.emails;
	}-*/;
	
}
