package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.AddNewEmployeeActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AddNewEmployeePlace extends ActivityPlace<AddNewEmployeeActivity>{
	@Inject
	public AddNewEmployeePlace(AddNewEmployeeActivity activity) {
		super(activity);
		
	}
	@Override
    public AddNewEmployeeActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("Add-New-employee")
    public static class Tokenizer 
                    implements PlaceTokenizer<AddNewEmployeePlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<AddNewEmployeePlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<AddNewEmployeePlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(AddNewEmployeePlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public AddNewEmployeePlace getPlace(String token) {
        	AddNewEmployeePlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

}
