
package com.ayurma.ayuromaweb.client.gin;

import com.ayurma.ayuromaweb.client.AppInitData;
import com.ayurma.ayuromaweb.client.activity.FooterActivity;
import com.ayurma.ayuromaweb.client.activity.HeaderActivity;
import com.ayurma.ayuromaweb.client.activity.MenubarActivity;
import com.ayurma.ayuromaweb.client.activity.SuggestBoxActivity;

import com.ayurma.ayuromaweb.client.mvp.AppPlaceFactory;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;



@GinModules(AyuromaGinModule.class)
public interface AyuromaGinjector extends Ginjector {
	EventBus getEventBus();
    PlaceController getPlaceController();
    ActivityMapper getActivityMapper();    
    AppPlaceFactory getAppPlaceFactory();
    //app initiation data:
    AppInitData getAppInitData();
    //activity:
    HeaderActivity getHeaderActivity();
    SuggestBoxActivity getSuggestBoxActivity();
    MenubarActivity getMenubarActivity();
    FooterActivity getFooterActivity();
   
}
