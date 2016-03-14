package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.ToolsViewActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ToolsViewPlace extends ActivityPlace<ToolsViewActivity>{
	@Override
	public ToolsViewActivity getActivity() {
		activity.init(this);
		return activity;
	}
	@Inject
	public ToolsViewPlace(ToolsViewActivity activity) {
		super(activity);
		
	}
	@Prefix("tools")
    public static class Tokenizer 
                    implements PlaceTokenizer<ToolsViewPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<ToolsViewPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<ToolsViewPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(ToolsViewPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public ToolsViewPlace getPlace(String token) {
        	ToolsViewPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
}
