package com.ayurma.ayuromaweb.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public class JsEmail extends JavaScriptObject{
	protected JsEmail(){}
	public final native String getEmail() /*-{
	
		return this.address;
	}-*/;	
}
