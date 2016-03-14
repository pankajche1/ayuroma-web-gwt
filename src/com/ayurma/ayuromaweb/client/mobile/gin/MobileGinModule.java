package com.ayurma.ayuromaweb.client.mobile.gin;

import com.ayurma.ayuromaweb.client.gin.InjectablePlaceController;
import com.ayurma.ayuromaweb.client.mobile.PhoneActivityMapper;
import com.ayurma.ayuromaweb.client.mobile.mvp.MobileAppPlaceFactory;
import com.ayurma.ayuromaweb.client.mobile.view.AboutUsMobileView;

import com.ayurma.ayuromaweb.client.mobile.view.EnquiryMobileView2;
import com.ayurma.ayuromaweb.client.mobile.view.HomeView;
import com.ayurma.ayuromaweb.client.mobile.view.IAboutUsMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.IEnquiryMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.IHomeView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductDetailsMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductGroupMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductsView;

import com.ayurma.ayuromaweb.client.mobile.view.ProductDetailsMobileView2;
import com.ayurma.ayuromaweb.client.mobile.view.ProductGroupMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.ProductMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.ProductsView;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;


public class MobileGinModule extends AbstractGinModule{

	@Override
	protected void configure() {
		
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).to(InjectablePlaceController.class).in(Singleton.class);
		bind(ActivityMapper.class).to(PhoneActivityMapper.class).in(Singleton.class);
		bind(MobileAppPlaceFactory.class).in(Singleton.class);
		//name of the image serving servlet:
		bindConstant().annotatedWith(Names.named("imageServletUrl")).to("mobile/serveImage?blob-key=");
		//cache:
		bind(Cache.class).in(Singleton.class);
		bind(DataServiceAsync.class).in(Singleton.class);
		//1 Home view:
		bind(IHomeView.class).to(HomeView.class).in(Singleton.class);
		//2 Products view:
		bind(IProductsView.class).to(ProductsView.class).in(Singleton.class);
		//3 Product group view:
		bind(IProductGroupMobileView.class).to(ProductGroupMobileView.class).in(Singleton.class);
		//4 Product view:
		//bind(IProductMobileView.class).to(ProductMobileView.class).in(Singleton.class);
		bind(new TypeLiteral<IProductMobileView<ChemicalData>>(){}).to(new TypeLiteral<ProductMobileView<ChemicalData>>(){}).in(Singleton.class);
		//5 AboutUs view:
		bind(IAboutUsMobileView.class).to(AboutUsMobileView.class).in(Singleton.class);
		//6 Enquiry view:
		bind(IEnquiryMobileView.class).to(EnquiryMobileView2.class).in(Singleton.class);
		//7 Product Details View:
		//bind(new TypeLiteral<IProductDetailsMobileView<ProductDetails>>(){}).to(new TypeLiteral<ProductDetailsMobileView<ProductDetails>>(){}).in(Singleton.class);
		//for test purpose the layout problem with the mgwt header:
		bind(new TypeLiteral<IProductDetailsMobileView<ProductDetails>>(){}).to(new TypeLiteral<ProductDetailsMobileView2<ProductDetails>>(){}).in(Singleton.class);
		
		
	}

}
