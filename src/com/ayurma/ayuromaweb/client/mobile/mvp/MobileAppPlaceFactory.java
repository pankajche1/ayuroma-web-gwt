package com.ayurma.ayuromaweb.client.mobile.mvp;


import com.ayurma.ayuromaweb.client.mobile.place.AboutUsMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.EnquiryMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductDetailsMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductPlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductsGroupsMenuPlace;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MobileAppPlaceFactory {
	/**
	 * this class for mapping a place with a token. other wise the error will come
	 * that the place is not mapped to a token.
	 */
	//1 HomePlace:
	@Inject
	HomePlace.Tokenizer homePlaceTokenizer;
	@Inject
	Provider<HomePlace> homePlaceProvider;
	// place1
	public HomePlace.Tokenizer getHomePlaceTokenizer() {
		return homePlaceTokenizer;
	}

	public HomePlace getHomePlace() {
		return homePlaceProvider.get();
	}
	
	//2 ProductsGroupsMenuPlace:
	@Inject
	ProductsGroupsMenuPlace.Tokenizer productsPlaceTokenizer;
	@Inject
	Provider<ProductsGroupsMenuPlace> productsPlaceProvider;
	// place1
	public ProductsGroupsMenuPlace.Tokenizer getProductsPlaceTokenizer() {
		return productsPlaceTokenizer;
	}

	public ProductsGroupsMenuPlace getProductsPlace() {
		return productsPlaceProvider.get();
	}
	
	//3 ProductGroupPlace:
		@Inject
		ProductGroupPlace.Tokenizer productGroupPlaceTokenizer;
		@Inject
		Provider<ProductGroupPlace> productGroupPlaceProvider;
		// place1
		public ProductGroupPlace.Tokenizer getProductGroupPlaceTokenizer() {
			return productGroupPlaceTokenizer;
		}

		public ProductGroupPlace getProductGroupPlace() {
			return productGroupPlaceProvider.get();
		}
	//4 ProductPlace:
		@Inject
		ProductPlace.Tokenizer productPlaceTokenizer;
		@Inject
		Provider<ProductPlace> productPlaceProvider;
		// place1
		public ProductPlace.Tokenizer getProductPlaceTokenizer() {
			return productPlaceTokenizer;
		}

		public ProductPlace getProductPlace() {
			return productPlaceProvider.get();
		}
	//5 About Us Place:
		@Inject
		AboutUsMobilePlace.Tokenizer aboutUsMobilePlaceTokenizer;
		@Inject
		Provider<AboutUsMobilePlace> aboutUsMobilePlaceProvider;
		// place1
		public AboutUsMobilePlace.Tokenizer getAboutUsMobilePlaceTokenizer() {
			return aboutUsMobilePlaceTokenizer;
		}

		public AboutUsMobilePlace getAboutUsMobilePlace() {
			return aboutUsMobilePlaceProvider.get();
		}
	//6 Enquiry Mobile Place:
	@Inject
	EnquiryMobilePlace.Tokenizer enquiryMobilePlaceTokenizer;
	@Inject
	Provider<EnquiryMobilePlace> enquiryMobilePlaceProvider;
	// place1
	public EnquiryMobilePlace.Tokenizer getEnquiryMobilePlaceTokenizer() {
		return enquiryMobilePlaceTokenizer;
	}
	public EnquiryMobilePlace getEnquiryMobilePlace() {
		return enquiryMobilePlaceProvider.get();
	}
	//7 Product Details Mobile Place
	@Inject
	ProductDetailsMobilePlace.Tokenizer productDetailsMobilePlaceTokenizer;
	@Inject
	Provider<ProductDetailsMobilePlace> productDetailsMobilePlaceProvider;
	// place1
	public ProductDetailsMobilePlace.Tokenizer getProductDetailsMobilePlaceTokenizer() {
		return productDetailsMobilePlaceTokenizer;
	}
	public ProductDetailsMobilePlace getProductDetailsMobilePlace() {
		return productDetailsMobilePlaceProvider.get();
	}
		
}
