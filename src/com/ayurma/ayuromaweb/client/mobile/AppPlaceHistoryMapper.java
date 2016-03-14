package com.ayurma.ayuromaweb.client.mobile;


import com.ayurma.ayuromaweb.client.mobile.mvp.MobileAppPlaceFactory;


import com.google.gwt.place.shared.PlaceHistoryMapperWithFactory;

//this mapping creates the #token thing in the url address bar of the browser
/*
@WithTokenizers({
	HomePlace.Tokenizer.class,
	ProductsPlace.ProductsPlaceTokenizer.class ,
	ProductGroupPlace.ProductGroupPlaceTokenizer.class,
	ContactUsPlace.ContactPlaceTokenizer.class,
	AboutUsPlace.AboutUsPlaceTokenizer.class,
	ProductPlace.ProductPlaceTokenizer.class,
	})

public interface AppPlaceHistoryMapper extends PlaceHistoryMapper{

}
*/
public interface AppPlaceHistoryMapper extends PlaceHistoryMapperWithFactory<MobileAppPlaceFactory>{
	
}
