package com.ayurma.ayuromaweb.client.place;

import com.ayurma.ayuromaweb.client.activity.SearchActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;





public class SearchPlace  extends ActivityPlace<SearchActivity>{
	@Inject
	public SearchPlace(SearchActivity activity) {
		super(activity);
		
	}
	@Override
    public SearchActivity getActivity() {
        activity.init(this);
        return activity;
    }
    @Prefix("search-product")
    public static class Tokenizer 
                    implements PlaceTokenizer<SearchPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<SearchPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<SearchPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(SearchPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public SearchPlace getPlace(String token) {
        	SearchPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }

    }

}
