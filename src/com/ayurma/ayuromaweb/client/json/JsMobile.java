package com.ayurma.ayuromaweb.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public class JsMobile extends JavaScriptObject{
	protected JsMobile(){}
	public final native String getNumber() /*-{
	
	return this.number;
}-*/;

}
