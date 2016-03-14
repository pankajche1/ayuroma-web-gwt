package com.ayurma.ayuromaweb.client.mvp;

import com.ayurma.ayuromaweb.client.place.EnquiryPlace;
import com.ayurma.ayuromaweb.client.place.HomePlace;
import com.ayurma.ayuromaweb.client.place.ProductDetailsPlace;
import com.ayurma.ayuromaweb.client.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.place.ProductPlace;
import com.ayurma.ayuromaweb.client.place.SearchPlace;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class AppPlaceFactory {
		//1 SearchPlace entries:
		@Inject
		SearchPlace.Tokenizer searchPlaceTokenizer;
		@Inject
		Provider<SearchPlace> searchPlaceProvider;
		// place1
		public SearchPlace.Tokenizer getPlace1Tokenizer() {
			return searchPlaceTokenizer;
		}

		public SearchPlace getPlace1() {
			return searchPlaceProvider.get();
		}
		//2 HomePlace:
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
		//3 Product Place:
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
		//4 Product Group place: 
		
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
		
		//5 Product Details Place:
		
		@Inject
		ProductDetailsPlace.Tokenizer productDetailsPlaceTokenizer;
		@Inject
		Provider<ProductDetailsPlace> productDetailsPlaceProvider;
		// place1
		public ProductDetailsPlace.Tokenizer getProductDetailsPlaceTokenizer() {
			return productDetailsPlaceTokenizer;
		}

		public ProductDetailsPlace getProductDetailsPlace() {
			return productDetailsPlaceProvider.get();
		}
		
		//6 Enquiry Place:
		
		@Inject
		EnquiryPlace.Tokenizer enquiryPlaceTokenizer;
		@Inject
		Provider<EnquiryPlace> enquiryPlaceProvider;
		// place1
		public EnquiryPlace.Tokenizer getEnquiryPlaceTokenizer() {
			return enquiryPlaceTokenizer;
		}

		public EnquiryPlace getEnquiryPlace() {
			return enquiryPlaceProvider.get();
		}
		//7: ToolsViewPlace
		

}
