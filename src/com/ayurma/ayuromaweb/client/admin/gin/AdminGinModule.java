package com.ayurma.ayuromaweb.client.admin.gin;

import com.ayurma.ayuromaweb.client.admin.mvp.AdminAppActivityMapper;
import com.ayurma.ayuromaweb.client.admin.mvp.AdminAppPlaceFactory;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.AddNewEmployeeView;
import com.ayurma.ayuromaweb.client.admin.view.AddNewProductViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.AddProductGroupViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.AddProductsToGroupViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.BrowseEmployeesView;
import com.ayurma.ayuromaweb.client.admin.view.BrowseImagesViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.BrowseProductGroupViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.BrowseProductsViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.EditEmployeeView;
import com.ayurma.ayuromaweb.client.admin.view.EditProductGroupViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.EditProductViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.EmployeesView;
import com.ayurma.ayuromaweb.client.admin.view.HeaderViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.HomeViewImpl;
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
import com.ayurma.ayuromaweb.client.admin.view.ImageUnitViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.ImagesViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.MenuViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.NewSliderViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.ProductDetailsEditViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.ProductGroupViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.ProductsViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.RegistrationView;
import com.ayurma.ayuromaweb.client.admin.view.SliderBrowserViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.SliderEditViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.SliderImageViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.SliderViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.ToolsViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.UploadImageViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.UploadSliderImageViewImpl;
import com.ayurma.ayuromaweb.client.admin.view.UsersViewImpl;
import com.ayurma.ayuromaweb.client.gin.InjectablePlaceController;



import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Singleton;

public class AdminGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		 bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		 bind(PlaceController.class).to(InjectablePlaceController.class).in(Singleton.class);
		 bind(ActivityMapper.class).to(AdminAppActivityMapper.class).in(Singleton.class);
		 bind(AdminAppPlaceFactory.class).in(Singleton.class);
		 bind(AdminDataServiceAsync.class).in(Singleton.class);
		 //cache:
		 bind(AdminCache.class).in(Singleton.class);
		 //views:
		 //1 home view:
		bind(IHomeView.class).to(HomeViewImpl.class).in(Singleton.class);
		//2 menu at the left side:
		bind(IMenu.class).to(MenuViewImpl.class).in(Singleton.class);
		//3 Products browsing view:
		bind(IProductsView.class).to(ProductsViewImpl.class).in(Singleton.class);
		//4 Add New Product View:
		
		bind(IAddNewProductView.class).to(AddNewProductViewImpl.class).in(Singleton.class);
		//5 Add New Product Group View:
		
		bind(IAddProductGroupView.class).to(AddProductGroupViewImpl.class).in(Singleton.class);
		//6 Product Group view:
		
		bind(IProductGroupView.class).to(ProductGroupViewImpl.class).in(Singleton.class);
		//7 Browse Product Group view:
		
		bind(IBrowseProductGroupView.class).to(BrowseProductGroupViewImpl.class).in(Singleton.class);
		//8 Edit Product Group view:
		
		bind(IEditProductGroupView.class).to(EditProductGroupViewImpl.class).in(Singleton.class);
		//9 Add or Remove products from a group view:
		
		bind(IAddProductsToGroupView.class).to(AddProductsToGroupViewImpl.class).in(Singleton.class);
		//10 product list broswer:
		
		bind(IBrowseProductsView.class).to(BrowseProductsViewImpl.class).in(Singleton.class);
		//11 images view for image browser and upload:
		
		bind(IImagesView.class).to(ImagesViewImpl.class).in(Singleton.class);
		//12 upload image view
		
		bind(IUploadImageView.class).to(UploadImageViewImpl.class).in(Singleton.class);
		//13 Browse images view:
		
		bind(IBrowseImagesView.class).to(BrowseImagesViewImpl.class).in(Singleton.class);
		//14 showing individual images:
		
		bind(IImageUnitView.class).to(ImageUnitViewImpl.class).in(Singleton.class);
		//15 product editor view:
		
		bind(IEditProductView.class).to(EditProductViewImpl.class).in(Singleton.class);
		//16 Product Details editor:
		
		bind(IProductDetailsEditView.class).to(ProductDetailsEditViewImpl.class).in(Singleton.class);
		//17 Slider View:
		bind(ISliderView.class).to(SliderViewImpl.class).in(Singleton.class);
		//18 Create New Slider View
		bind(INewSliderView.class).to(NewSliderViewImpl.class).in(Singleton.class);
		//19 upload slider image view:
		bind(IUploadSliderImageView.class).to(UploadSliderImageViewImpl.class).in(Singleton.class);
		//20 slider images view:
		bind(ISliderImageView.class).to(SliderImageViewImpl.class).in(Singleton.class);
		//21 slider film browser:
		bind(ISliderBrowserView.class).to(SliderBrowserViewImpl.class).in(Singleton.class);
		//22 tools view
		
		bind(IToolsView.class).to(ToolsViewImpl.class).in(Singleton.class);
		//23 edit slider film view:
		bind(ISliderEditView.class).to(SliderEditViewImpl.class).in(Singleton.class);
		//24 header of the admin page:
		bind(IHeaderView.class).to(HeaderViewImpl.class).in(Singleton.class);
		//25 IRegistrationView
		bind(IRegistrationView.class).to(RegistrationView.class).in(Singleton.class);
		//26 Users View:
		bind(IUsersView.class).to(UsersViewImpl.class).in(Singleton.class);
		//27 Browse Employees View:
		bind(IBrowseEmployeesView.class).to(BrowseEmployeesView.class).in(Singleton.class);
		//28 EmployeesView
		bind(IEmployeesView.class).to(EmployeesView.class).in(Singleton.class);
		//29 AddNewEmployeeView
		bind(IAddNewEmployeeView.class).to(AddNewEmployeeView.class).in(Singleton.class);
		//30 EditEmployeeView
		bind(IEditEmployeeView.class).to(EditEmployeeView.class).in(Singleton.class);
		

		 
	}

}
