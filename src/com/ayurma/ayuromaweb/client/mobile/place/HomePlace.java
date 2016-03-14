package com.ayurma.ayuromaweb.client.mobile.place;


import com.ayurma.ayuromaweb.client.mobile.activity.HomeActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;


import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class HomePlace extends ActivityPlace<HomeActivity>{
	@Inject
	public HomePlace(HomeActivity activity) {
		super(activity);
		
	}
	@Override
	public HomeActivity getActivity(){
		activity.init(this);
		return activity;
	}
	 @Prefix("home")
	    public static class Tokenizer 
	                    implements PlaceTokenizer<HomePlace> {

	        // Since the place is injectable, 
	        // we'll let Gin do the construction.
	        //1
	        private final Provider<HomePlace> placeProvider; 
	        //2
	        @Inject
	        public Tokenizer(
	                   Provider<HomePlace> placeProvider){
	            this.placeProvider = placeProvider;
	        } 
	        //3
	        @Override
	        public String getToken(HomePlace place) {
	            return place.getPlaceName();
	        }
	        //4
	        @Override
	        public HomePlace getPlace(String token) {
	        	HomePlace place = placeProvider.get();
	            place.setPlaceName(token);
	            return place;
	        }

	    }
	/*
	public static class HomePlaceTokenizer implements PlaceTokenizer<HomePlace> {

		@Override
		public HomePlace getPlace(String token) {
			return new HomePlace();
		}

		@Override
		public String getToken(HomePlace place) {
			return "";
		}

	}
*/
	@Override
	public int hashCode() {
		return 3;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;

		if (other instanceof HomePlace)
			return true;
		return false;
	}
}
