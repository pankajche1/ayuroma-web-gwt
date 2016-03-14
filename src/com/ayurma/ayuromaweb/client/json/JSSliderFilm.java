package com.ayurma.ayuromaweb.client.json;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JSSliderFilm extends JavaScriptObject{
	protected JSSliderFilm(){}
    //(1)
	public final native String getSliderKey() /*-{
	
		return this.key;
	}-*/;
    //(2)
	public final native String getTotolItems() /*-{
	
		return this.totalItems;
	}-*/;
    //(3)
	
	public final native JsArray<JSSliderImageItem> getSliderFilmItems() /*-{
		
		return this.items;
	}-*/;
}
