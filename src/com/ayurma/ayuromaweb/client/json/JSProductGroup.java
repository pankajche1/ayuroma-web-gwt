package com.ayurma.ayuromaweb.client.json;


import com.google.gwt.core.client.JavaScriptObject;

public class JSProductGroup extends JavaScriptObject implements IProductGroup {
	protected JSProductGroup(){}
	@Override
	public final native String getName() /*-{
		
		return this.name;
	}-*/;

	@Override
	public final native String getGroupItemsId() /*-{
		
		return this.itemsKey;
	}-*/;
	@Override
	public final native String getGroupDisplayText() /*-{
		
		return this.text;
	}-*/;
	@Override
	public final native String getImageUrl() /*-{
		
		return this.imageUrl;
	}-*/;
}
