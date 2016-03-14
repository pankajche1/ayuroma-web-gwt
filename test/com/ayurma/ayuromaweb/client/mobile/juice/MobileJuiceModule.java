package com.ayurma.ayuromaweb.client.mobile.juice;

import static org.easymock.EasyMock.createStrictMock;

import com.ayurma.ayuromaweb.client.mobile.PhoneActivityMapper;
import com.ayurma.ayuromaweb.client.mobile.view.ProductViewMock;
import com.ayurma.ayuromaweb.client.mobile.mvp.MobileAppPlaceFactory;
import com.ayurma.ayuromaweb.client.mobile.view.IHomeView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductGroupMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.IProductsView;
import com.ayurma.ayuromaweb.client.mobile.view.ProductGroupMobileViewMock;
import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.service.DataServiceAsyncMock;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

public class MobileJuiceModule extends AbstractModule{
	
	@SuppressWarnings("unchecked")
	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).toInstance(createStrictMock(PlaceController.class));
		bind(ActivityMapper.class).to(PhoneActivityMapper.class).in(Singleton.class);
		bind(MobileAppPlaceFactory.class).in(Singleton.class);
		bind(Cache.class).in(Singleton.class);
		bind(DataServiceAsync.class).to(DataServiceAsyncMock.class);
		bindConstant().annotatedWith(Names.named("imageServletUrl")).to("mobile/serveImage?blob-key=");
		//1 Home view:
		bind(IHomeView.class).toInstance(createStrictMock(IHomeView.class));
		//2 Products view:
		bind(IProductsView.class).toInstance(createStrictMock(IProductsView.class));
		//3 Product group view:
		bind(IProductGroupMobileView.class).to(ProductGroupMobileViewMock.class);
		//4 Product view:
		//bind(IProductView.class).toInstance(createStrictMock(IProductView.class));
		bind(new TypeLiteral<IProductMobileView<ChemicalData>>(){}).to(new TypeLiteral<ProductViewMock<ChemicalData>>(){}).in(Singleton.class);
		
	}

}
