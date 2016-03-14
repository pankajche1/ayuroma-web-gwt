package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.UsersViewActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class UsersPlace extends ActivityPlace<UsersViewActivity>{
	@Inject
	public UsersPlace(UsersViewActivity activity) {
		super(activity);
		
	}
	@Override
	public UsersViewActivity getActivity(){
		activity.init(this);
		return activity;
	}
	@Prefix("users")
    public static class Tokenizer 
                    implements PlaceTokenizer<UsersPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<UsersPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<UsersPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(UsersPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public UsersPlace getPlace(String token) {
        	UsersPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

}
