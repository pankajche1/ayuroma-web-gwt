package com.ayurma.ayuromaweb.client.admin.place;

import com.ayurma.ayuromaweb.client.admin.activity.AddNewProductActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AddNewProductPlace extends ActivityPlace<AddNewProductActivity> {
	@Inject
	public AddNewProductPlace(AddNewProductActivity activity) {
		super(activity);
		
	}
	@Override
    public AddNewProductActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("Add-New-Product")
    public static class Tokenizer 
                    implements PlaceTokenizer<AddNewProductPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<AddNewProductPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<AddNewProductPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(AddNewProductPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public AddNewProductPlace getPlace(String token) {
        	AddNewProductPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
}
