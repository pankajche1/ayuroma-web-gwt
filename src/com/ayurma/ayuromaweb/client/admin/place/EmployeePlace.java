package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.EmployeeActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class EmployeePlace extends ActivityPlace<EmployeeActivity>{
	@Inject
	public EmployeePlace(EmployeeActivity activity) {
		super(activity);
		
	}
	@Override
    public EmployeeActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("employee")
    public static class Tokenizer 
                    implements PlaceTokenizer<EmployeePlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<EmployeePlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<EmployeePlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(EmployeePlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public EmployeePlace getPlace(String token) {
        	EmployeePlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }
	}

}
