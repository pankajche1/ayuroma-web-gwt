package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.BrowseProductGroupActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BrowseProductGroupPlace extends ActivityPlace<BrowseProductGroupActivity>{
	@Inject
	public BrowseProductGroupPlace(BrowseProductGroupActivity activity) {
		super(activity);
		
	}
	@Override
    public BrowseProductGroupActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("browse-product-groups")
    public static class Tokenizer 
                    implements PlaceTokenizer<BrowseProductGroupPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<BrowseProductGroupPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<BrowseProductGroupPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(BrowseProductGroupPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public BrowseProductGroupPlace getPlace(String token) {
        	BrowseProductGroupPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
}
