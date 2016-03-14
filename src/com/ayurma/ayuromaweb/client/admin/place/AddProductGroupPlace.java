package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.AddProductGroupActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AddProductGroupPlace extends ActivityPlace<AddProductGroupActivity> {
	@Inject
	public AddProductGroupPlace(AddProductGroupActivity activity) {
		super(activity);
		
	}
	@Override
    public AddProductGroupActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("Add-Product-Group")
    public static class Tokenizer 
                    implements PlaceTokenizer<AddProductGroupPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<AddProductGroupPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<AddProductGroupPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(AddProductGroupPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public AddProductGroupPlace getPlace(String token) {
        	AddProductGroupPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }
}
