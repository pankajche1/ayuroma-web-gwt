package com.ayurma.ayuromaweb.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public class JsTelephone extends JavaScriptObject {
	protected JsTelephone(){}
	public final native String getNumber() /*-{
	
	return this.number;
}-*/;
	

}
