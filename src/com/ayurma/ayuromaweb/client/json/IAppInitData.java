package com.ayurma.ayuromaweb.client.json;



import com.google.gwt.core.client.JsArray;

public interface IAppInitData {
	public JsArray<JSProductGroup> getProductGroups();

	JsArray<JSSliderImageItem> getSliderFilmItems();

	JSCompanyInfo getCompanyInfo();

	JSSliderFilm getSliderFilm();
}
