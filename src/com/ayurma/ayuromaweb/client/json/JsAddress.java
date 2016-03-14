package com.ayurma.ayuromaweb.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public class JsAddress extends JavaScriptObject{
	protected JsAddress(){}
	public final native String getLine() /*-{
	
	return this.line;
}-*/;	
}
