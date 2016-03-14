package com.ayurma.ayuromaweb.client.activity;

import java.util.List;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.gin.AyuromaGinjector;
import com.ayurma.ayuromaweb.client.place.EnquiryPlace;
import com.ayurma.ayuromaweb.client.place.HomePlace;
import com.ayurma.ayuromaweb.client.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.place.ProductPlace;
import com.ayurma.ayuromaweb.client.view.IFooterView;
import com.ayurma.ayuromaweb.shared.CompanyInfoDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class FooterActivity extends AbstractActivity implements IFooterView.Presenter{
	private IFooterView view;
	private PlaceController placeController;
	 private Provider<ProductPlace> productPlaceProvider;
	 private Provider<ProductGroupPlace> productGroupPlaceProvider;
	 private Provider<HomePlace> homePlaceProvider;
	private Provider<EnquiryPlace> enquiryPlaceProvider;
	private AppInitData appInitData;
	private CompanyInfoDTO companyInfo;
	
	@Inject
	public FooterActivity(IFooterView view, PlaceController placeController,
			Provider<HomePlace> homePlaceProvider,
			Provider<ProductPlace> productPlaceProvider,
			Provider<ProductGroupPlace> productGroupPlaceProvider,
			Provider<EnquiryPlace> enquiryPlaceProvider,
			AppInitData appInitData) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.productPlaceProvider=productPlaceProvider;
		this.homePlaceProvider=homePlaceProvider;
		this.productGroupPlaceProvider=productGroupPlaceProvider;
		this.enquiryPlaceProvider=enquiryPlaceProvider;
		this.appInitData=appInitData;
		companyInfo= appInitData.getCompanyInfo();
		

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
		
	}
	public void init(){
		List<String> mobiles = companyInfo.getMobiles();
		List<String> addressLines= companyInfo.getAddress();
		String city=companyInfo.getCity();
		String country=companyInfo.getCountry();
		String pin = companyInfo.getPin();
		view.setAddress(companyInfo.getCompanyHead(),addressLines,mobiles,companyInfo.getTelephones(),
				companyInfo.getEmails(),
				city,pin,country);
	}

	@Override
	public void gotoPlace(String token) {
		
		if(token.equals("home")){
			HomePlace place =homePlaceProvider.get();
			place.setPlaceName("My Home Page");
			placeController.goTo(place);
		}else if(token.equals("products")){
			
		}else if(token.equals("contactUs")){
			EnquiryPlace place=enquiryPlaceProvider.get();
			place.setPlaceName("source=contact-us");
			placeController.goTo(place);
		}else if(token.equals("aboutUs")){
			
		}
		
	}



}
