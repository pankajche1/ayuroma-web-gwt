package com.ayurma.ayuromaweb.client.mobile.gin;




import com.ayurma.ayuromaweb.client.mobile.mvp.MobileAppPlaceFactory;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;

@GinModules(MobileGinModule.class)
public interface MobileGinjector extends Ginjector {
	//ClientFactory getClientFactory();
	EventBus getEventBus();
    PlaceController getPlaceController();
    ActivityMapper getActivityMapper();   
    MobileAppPlaceFactory getAppPlaceFactory();

}
