package com.ayurma.ayuromaweb.client.juice;

//import com.ayurma.ayuromaweb.client.gin.InjectablePlaceController;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.mvp.AppActivityMapper;
import com.ayurma.ayuromaweb.client.mvp.AppPlaceFactory;
import com.ayurma.ayuromaweb.client.service.AdminDataServiceMock;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.service.DataServiceAsyncMock;
//import com.ayurma.ayuromaweb.client.view.HomeViewImpl;
import com.ayurma.ayuromaweb.client.view.IEnquiryView;
import com.ayurma.ayuromaweb.client.view.IHomeView;
import com.ayurma.ayuromaweb.client.view.ILayout1View;
import com.ayurma.ayuromaweb.client.view.IProductDetailsView;
import com.ayurma.ayuromaweb.client.view.IProductGroupView;
import com.ayurma.ayuromaweb.client.view.IProductView;
import com.ayurma.ayuromaweb.client.view.ISearchView;
import com.ayurma.ayuromaweb.client.view.ISideBar1View;
import com.ayurma.ayuromaweb.client.view.Layout1ViewMock;
import com.ayurma.ayuromaweb.client.view.ProductGroupDesktopViewMock;
import com.ayurma.ayuromaweb.client.view.ProductViewImpl;
import com.ayurma.ayuromaweb.client.view.ProductViewMock;
import com.ayurma.ayuromaweb.client.view.Sidebar1ViewMock;
//import com.ayurma.ayuromaweb.client.view.ProductsViewImplMock;
import com.ayurma.ayuromaweb.client.view.widgets.ISliderWidget1;
import com.ayurma.ayuromaweb.client.view.widgets.IWidget1View;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import static org.easymock.EasyMock.*;

public class MyJuiceModule extends AbstractModule {

	@SuppressWarnings("unchecked")
	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).toInstance(createStrictMock(PlaceController.class));
		bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);
		bind(AppPlaceFactory.class).in(Singleton.class);
		bind(new TypeLiteral<IProductView<ChemicalData>>(){}).to(new TypeLiteral<ProductViewMock<ChemicalData>>(){}).in(Singleton.class);
		//bind(IProductView.class).to(ProductViewMock.class);
		bind(DataServiceAsync.class).to(DataServiceAsyncMock.class);
		bind(AdminDataServiceAsync.class).to(AdminDataServiceMock.class);
		bind(IEnquiryView.class).toInstance(createStrictMock(IEnquiryView.class));
		bind(ILayout1View.class).to(Layout1ViewMock.class);
		bind(new TypeLiteral<IProductDetailsView<ProductDetails>>(){}).toInstance(createStrictMock(IProductDetailsView.class));
		bind(IProductGroupView.class).to(ProductGroupDesktopViewMock.class);
		bind(ISideBar1View.class).to(Sidebar1ViewMock.class);
		bind(IWidget1View.class).toInstance(createStrictMock(IWidget1View.class));
		//name of the image serving servlet:
		bindConstant().annotatedWith(Names.named("imageServletUrl")).to("ayuromaweb3/serveImage?blob-key=");
		bindConstant().annotatedWith(Names.named("productLinkFromGroupUrl")).to("#product-display:source=group&key=");
		bind(IHomeView.class).toInstance(createStrictMock(IHomeView.class));
		bind(ISearchView.class).toInstance(createStrictMock(ISearchView.class));
		bind(ISliderWidget1.class).toInstance(createStrictMock(ISliderWidget1.class));
		
	}

}
