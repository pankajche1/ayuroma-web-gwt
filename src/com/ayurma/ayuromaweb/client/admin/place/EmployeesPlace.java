package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.EmployeesViewActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class EmployeesPlace extends ActivityPlace<EmployeesViewActivity> {
	@Inject
	public EmployeesPlace(EmployeesViewActivity activity) {
		super(activity);
		
	}
	@Override
    public EmployeesViewActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("employees-menu")
    public static class Tokenizer 
                    implements PlaceTokenizer<EmployeesPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<EmployeesPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<EmployeesPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(EmployeesPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public EmployeesPlace getPlace(String token) {
        	EmployeesPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

}
