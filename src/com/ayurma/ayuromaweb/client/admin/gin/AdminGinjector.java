package com.ayurma.ayuromaweb.client.admin.gin;

import com.ayurma.ayuromaweb.client.admin.activity.BrowseImagesActivity;
import com.ayurma.ayuromaweb.client.admin.activity.BrowseProductsActivity;
import com.ayurma.ayuromaweb.client.admin.activity.HeaderActivity;
import com.ayurma.ayuromaweb.client.admin.activity.MenuActivity;
import com.ayurma.ayuromaweb.client.admin.activity.RegistrationViewPresenter;
import com.ayurma.ayuromaweb.client.admin.activity.SliderImagesBrowseActivity;
import com.ayurma.ayuromaweb.client.admin.mvp.AdminAppPlaceFactory;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
@GinModules(AdminGinModule.class)
public interface AdminGinjector extends Ginjector {
	   EventBus getEventBus();
	   PlaceController getPlaceController();
	   ActivityMapper getActivityMapper(); 
	   AdminAppPlaceFactory getAppPlaceFactory();
	   MenuActivity getMenuActivity();
	   SliderImagesBrowseActivity getSliderBrowserActivity();
	   BrowseProductsActivity getBrowseProductActivity();
	   HeaderActivity getHeaderActivity();
	   BrowseImagesActivity getBrowserImagesActivity();
	   RegistrationViewPresenter getRegistrationViewPresenter();
	   AdminCache getAdminCache();
}
