package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.EditEmployeeActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class EditEmployeePlace extends ActivityPlace<EditEmployeeActivity>{
	@Inject
	public EditEmployeePlace(EditEmployeeActivity activity) {
		super(activity);
		
	}
	@Override
    public EditEmployeeActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("edit-employee")
    public static class Tokenizer 
                    implements PlaceTokenizer<EditEmployeePlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<EditEmployeePlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<EditEmployeePlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(EditEmployeePlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public EditEmployeePlace getPlace(String token) {
        	EditEmployeePlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }	

}
