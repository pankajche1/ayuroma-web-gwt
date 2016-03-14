package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.BrowseEmployeesActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BrowseEmployeesPlace  extends ActivityPlace<BrowseEmployeesActivity>{
	@Inject
	public BrowseEmployeesPlace(BrowseEmployeesActivity activity) {
		super(activity);
		
	}
	@Override
	public BrowseEmployeesActivity getActivity(){
		activity.init(this);
		return activity;
	}
	@Prefix("employees-browser")
    public static class Tokenizer 
                    implements PlaceTokenizer<BrowseEmployeesPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<BrowseEmployeesPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<BrowseEmployeesPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(BrowseEmployeesPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public BrowseEmployeesPlace getPlace(String token) {
        	BrowseEmployeesPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

}
