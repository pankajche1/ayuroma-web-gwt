package com.ayurma.ayuromaweb.client.admin.juice;

import com.ayurma.ayuromaweb.client.admin.mvp.AdminAppPlaceFactory;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.AddNewEmployeeView;
import com.ayurma.ayuromaweb.client.admin.view.AddNewEmployeeViewMock;
import com.ayurma.ayuromaweb.client.admin.view.BrowseEmployeesViewMock;
import com.ayurma.ayuromaweb.client.admin.view.EditEmployeeViewMock;
import com.ayurma.ayuromaweb.client.admin.view.EmployeesViewMock;
import com.ayurma.ayuromaweb.client.admin.view.IAddNewEmployeeView;
import com.ayurma.ayuromaweb.client.admin.view.IAddNewProductView;
import com.ayurma.ayuromaweb.client.admin.view.IAddProductGroupView;
import com.ayurma.ayuromaweb.client.admin.view.IAddProductsToGroupView;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseEmployeesView;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseImagesView;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseProductGroupView;
import com.ayurma.ayuromaweb.client.admin.view.IBrowseProductsView;
import com.ayurma.ayuromaweb.client.admin.view.IEditEmployeeView;
import com.ayurma.ayuromaweb.client.admin.view.IEditProductGroupView;
import com.ayurma.ayuromaweb.client.admin.view.IEditProductView;
import com.ayurma.ayuromaweb.client.admin.view.IEmployeesView;
import com.ayurma.ayuromaweb.client.admin.view.IHeaderView;
import com.ayurma.ayuromaweb.client.admin.view.IHomeView;
import com.ayurma.ayuromaweb.client.admin.view.IImageUnitView;
import com.ayurma.ayuromaweb.client.admin.view.IImagesView;
import com.ayurma.ayuromaweb.client.admin.view.IMenu;
import com.ayurma.ayuromaweb.client.admin.view.INewSliderView;
import com.ayurma.ayuromaweb.client.admin.view.IProductDetailsEditView;
import com.ayurma.ayuromaweb.client.admin.view.IProductGroupView;
import com.ayurma.ayuromaweb.client.admin.view.IProductsView;
import com.ayurma.ayuromaweb.client.admin.view.IRegistrationView;
import com.ayurma.ayuromaweb.client.admin.view.ISliderBrowserView;
import com.ayurma.ayuromaweb.client.admin.view.ISliderEditView;
import com.ayurma.ayuromaweb.client.admin.view.ISliderImageView;
import com.ayurma.ayuromaweb.client.admin.view.ISliderView;
import com.ayurma.ayuromaweb.client.admin.view.IToolsView;
import com.ayurma.ayuromaweb.client.admin.view.IUploadImageView;
import com.ayurma.ayuromaweb.client.admin.view.IUploadSliderImageView;
import com.ayurma.ayuromaweb.client.admin.view.IUsersView;
import com.ayurma.ayuromaweb.client.admin.view.MockAddProductsToGroupView;
import com.ayurma.ayuromaweb.client.admin.view.MockBrowseProductsView;
import com.ayurma.ayuromaweb.client.admin.view.UsersViewMock;
import com.ayurma.ayuromaweb.client.admin.view.RegistrationViewMock;
import com.ayurma.ayuromaweb.client.mvp.AppActivityMapper;
import com.ayurma.ayuromaweb.client.service.AdminDataServiceMock;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import static org.easymock.EasyMock.*;
public class AdminJuiceModule extends AbstractModule{

	@Override
	protected void configure() {
		//basic objects:
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).toInstance(createStrictMock(PlaceController.class));
		bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);
		bind(AdminAppPlaceFactory.class).in(Singleton.class);
		bind(AdminDataServiceAsync.class).to(AdminDataServiceMock.class);
		//view etc:
		
		 bind(AdminCache.class).in(Singleton.class);
		 //views:
		 //1 home view:
		bind(IHomeView.class).toInstance(createStrictMock(IHomeView.class));
		//2 menu at the left side:
		bind(IMenu.class).toInstance(createStrictMock(IMenu.class));
		//3 Products browsing view:
		bind(IProductsView.class).toInstance(createStrictMock(IProductsView.class));
		//4 Add New Product View:
		
		bind(IAddNewProductView.class).toInstance(createStrictMock(IAddNewProductView.class));
		//5 Add New Product Group View:
		
		bind(IAddProductGroupView.class).toInstance(createStrictMock(IAddProductGroupView.class));
		//6 Product Group view:
		
		bind(IProductGroupView.class).toInstance(createStrictMock(IProductGroupView.class));
		//7 Browse Product Group view:
		
		bind(IBrowseProductGroupView.class).toInstance(createStrictMock(IBrowseProductGroupView.class));
		//8 Edit Product Group view:
		
		bind(IEditProductGroupView.class).toInstance(createStrictMock(IEditProductGroupView.class));
		//9 Add or Remove products from a group view:
		
		bind(IAddProductsToGroupView.class).to(MockAddProductsToGroupView.class);
		//10 product list broswer:
		bind(MockBrowseProductsView.class).in(Singleton.class);
		bind(IBrowseProductsView.class).to(MockBrowseProductsView.class).in(Singleton.class);
		//11 images view for image browser and upload:
		
		bind(IImagesView.class).toInstance(createStrictMock(IImagesView.class));
		//12 upload image view
		
		bind(IUploadImageView.class).toInstance(createStrictMock(IUploadImageView.class));
		//13 Browse images view:
		
		bind(IBrowseImagesView.class).toInstance(createStrictMock(IBrowseImagesView.class));
		//14 showing individual images:
		
		bind(IImageUnitView.class).toInstance(createStrictMock(IImageUnitView.class));
		//15 product editoInstancer view:
		
		bind(IEditProductView.class).toInstance(createStrictMock(IEditProductView.class));
		//16 Product Details editoInstancer:
		
		bind(IProductDetailsEditView.class).toInstance(createStrictMock(IProductDetailsEditView.class));
		//17 Slider View:
		bind(ISliderView.class).toInstance(createStrictMock(ISliderView.class));
		//18 Create New Slider View
		bind(INewSliderView.class).toInstance(createStrictMock(INewSliderView.class));
		//19 upload slider image view:
		bind(IUploadSliderImageView.class).toInstance(createStrictMock(IUploadSliderImageView.class));
		//20 slider images view:
		bind(ISliderImageView.class).toInstance(createStrictMock(ISliderImageView.class));
		//21 slider film browser:
		bind(ISliderBrowserView.class).toInstance(createStrictMock(ISliderBrowserView.class));
		//22 toInstanceols view
		
		bind(IToolsView.class).toInstance(createStrictMock(IToolsView.class));
		//23 edit slider film view:
		bind(ISliderEditView.class).toInstance(createStrictMock(ISliderEditView.class));
		//24 header of the admin page:
		bind(IHeaderView.class).toInstance(createStrictMock(IHeaderView.class));
		bind(IRegistrationView.class).to(RegistrationViewMock.class).in(Singleton.class);
		//25 user view:
		bind(UsersViewMock.class).in(Singleton.class);
		bind(IUsersView.class).to(UsersViewMock.class).in(Singleton.class);
		//25 Add new employee view:
		bind(AddNewEmployeeView.class).in(Singleton.class);
		bind(IAddNewEmployeeView.class).to(AddNewEmployeeViewMock.class).in(Singleton.class);
		//26 Browse Employees View:
		bind(IBrowseEmployeesView.class).to(BrowseEmployeesViewMock.class).in(Singleton.class);
		//27 EmployeesView
		bind(IEmployeesView.class).to(EmployeesViewMock.class).in(Singleton.class);
		//28 EditEmployeesView
		bind(IEditEmployeeView.class).to(EditEmployeeViewMock.class).in(Singleton.class);
	}

}
