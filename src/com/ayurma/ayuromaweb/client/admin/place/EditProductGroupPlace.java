package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.EditProductGroupActivity;

import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class EditProductGroupPlace extends ActivityPlace<EditProductGroupActivity> {
	private Long key;//key of the target group
	@Inject
	public EditProductGroupPlace(EditProductGroupActivity activity) {
		super(activity);
		
	}
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	@Override
    public EditProductGroupActivity getActivity() {
        activity.init(this);
        return activity;
    }
	@Prefix("edit-product-group")
    public static class Tokenizer 
                    implements PlaceTokenizer<EditProductGroupPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<EditProductGroupPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<EditProductGroupPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(EditProductGroupPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public EditProductGroupPlace getPlace(String token) {
        	EditProductGroupPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }	
}
