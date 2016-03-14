package com.ayurma.ayuromaweb.client.gin;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.admin.view.ISliderImageView;

import com.ayurma.ayuromaweb.client.admin.view.SliderImageViewImpl;

import com.ayurma.ayuromaweb.client.mvp.AppActivityMapper;
import com.ayurma.ayuromaweb.client.mvp.AppPlaceFactory;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.view.EnquiryViewImpl;
import com.ayurma.ayuromaweb.client.view.FooterViewImpl;
import com.ayurma.ayuromaweb.client.view.HeaderView;
import com.ayurma.ayuromaweb.client.view.HeaderViewImpl;
import com.ayurma.ayuromaweb.client.view.HomeViewImpl;
import com.ayurma.ayuromaweb.client.view.IEnquiryView;
import com.ayurma.ayuromaweb.client.view.IFooterView;
import com.ayurma.ayuromaweb.client.view.IHomeView;
import com.ayurma.ayuromaweb.client.view.ILayout1View;
import com.ayurma.ayuromaweb.client.view.IProductDetailsView;
import com.ayurma.ayuromaweb.client.view.IProductGroupView;
import com.ayurma.ayuromaweb.client.view.IProductView;
import com.ayurma.ayuromaweb.client.view.ISearchView;
import com.ayurma.ayuromaweb.client.view.ISideBar1View;
import com.ayurma.ayuromaweb.client.view.Layout1View;
import com.ayurma.ayuromaweb.client.view.ProductDetailsViewImpl;
import com.ayurma.ayuromaweb.client.view.ProductGroupViewImpl;
import com.ayurma.ayuromaweb.client.view.ProductViewImpl;
import com.ayurma.ayuromaweb.client.view.SearchViewImpl;
import com.ayurma.ayuromaweb.client.view.SideBar1ViewImpl;
import com.ayurma.ayuromaweb.client.view.SuggestBoxView;
import com.ayurma.ayuromaweb.client.view.SuggestBoxViewImpl;
import com.ayurma.ayuromaweb.client.view.widgets.IItemsEntryView;
import com.ayurma.ayuromaweb.client.view.widgets.ISliderWidget1;
import com.ayurma.ayuromaweb.client.view.widgets.IWidget1View;
import com.ayurma.ayuromaweb.client.view.widgets.ItemsEntryView;
import com.ayurma.ayuromaweb.client.view.widgets.SliderWidget1Impl;
import com.ayurma.ayuromaweb.client.view.widgets.Widget1ViewImpl;
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





public class AyuromaGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).to(InjectablePlaceController.class).in(Singleton.class);
		bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);
		bind(AppPlaceFactory.class).in(Singleton.class);
		bind(DataServiceAsync.class).in(Singleton.class);
		//name of the image serving servlet:
		bindConstant().annotatedWith(Names.named("imageServletUrl")).to("ayuromaweb3/serveImage?blob-key=");
		//name of the product link url:
		bindConstant().annotatedWith(Names.named("productLinkFromGroupUrl")).to("#product-display:source=group&key=");
		//cache:
		bind(Cache.class).in(Singleton.class);
		//appInitData:
		bind(AppInitData.class).in(Singleton.class);
		//views:
		bind(ILayout1View.class).to(Layout1View.class).in(Singleton.class);
		//1 Header view:
		bind(HeaderView.class).to(HeaderViewImpl.class).in(Singleton.class);
		//2 Suggest box view:
		bind(SuggestBoxView.class).to(SuggestBoxViewImpl.class).in(Singleton.class);
		//3 search proudct view:
		bind(ISearchView.class).to(SearchViewImpl.class).in(Singleton.class); 
		//4 Home view:
		bind(IHomeView.class).to(HomeViewImpl.class).in(Singleton.class);
		//5 Product View:
		//bind(IProductView.class).to(ProductViewImpl.class).in(Singleton.class);
		bind(new TypeLiteral<IProductView<ChemicalData>>(){}).to(new TypeLiteral<ProductViewImpl<ChemicalData>>(){}).in(Singleton.class);
		//6 Product Group View:
		bind(IProductGroupView.class).to(ProductGroupViewImpl.class).in(Singleton.class);
		//7 Product Details View:
		bind(new TypeLiteral<IProductDetailsView<ProductDetails>>(){}).to(new TypeLiteral<ProductDetailsViewImpl<ProductDetails>>(){}).in(Singleton.class);
		//bind(IProductDetailsView.class).to(ProductDetailsViewImpl.class).in(Singleton.class);
		//8 Enquiry View:
		
		bind(IEnquiryView.class).to(EnquiryViewImpl.class).in(Singleton.class);
		//9 side bar view 1
		bind(ISideBar1View.class).to(SideBar1ViewImpl.class).in(Singleton.class);
		//10 widget 1 view;
		
		bind(IWidget1View.class).to(Widget1ViewImpl.class);
		//11 Slider Widget:
		
		bind(ISliderWidget1.class).to(SliderWidget1Impl.class).in(Singleton.class);;
		//12 browse slider images:
		bind(ISliderImageView.class).to(SliderImageViewImpl.class).in(Singleton.class);
		//13 four product group entry points on the home page:
		bind(IItemsEntryView.class).to(ItemsEntryView.class).in(Singleton.class);
		//14 footer:
		bind(IFooterView.class).to(FooterViewImpl.class).in(Singleton.class);
	}

}
