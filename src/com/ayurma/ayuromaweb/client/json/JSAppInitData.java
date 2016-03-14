package com.ayurma.ayuromaweb.client.json;



import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JSAppInitData  extends JavaScriptObject  implements IAppInitData{
	protected JSAppInitData(){}
	/*
	* for getting the array of product groups: []
	 */
	@Override
	public final native JsArray<JSProductGroup> getProductGroups() /*-{
		
		return this.productGroups;
	}-*/;
	/*
	 * for getting the json array of slider film items:
	 */
	@Override
	public final native JsArray<JSSliderImageItem> getSliderFilmItems() /*-{
		
		return this.slider;
	}-*/;
	/*
	 * for getting company info json object:
	 * 
	 */
	@Override
	public final native JSCompanyInfo getCompanyInfo() /*-{
		
		return this.companyInfo;
	}-*/;
	
	@Override
	public final native JSSliderFilm getSliderFilm()/*-{
	
		return this.slider;
	}-*/;
}
