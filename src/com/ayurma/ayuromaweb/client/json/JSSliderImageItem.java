package com.ayurma.ayuromaweb.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public class JSSliderImageItem extends JavaScriptObject {

	protected JSSliderImageItem() {}
	
	public final native String getImageUrl() /*-{
		
		return this.imageUrl;
	}-*/;

	
	public final native String getLinkedProduct() /*-{
		
		return this.linkedProduct;
	}-*/;
}
