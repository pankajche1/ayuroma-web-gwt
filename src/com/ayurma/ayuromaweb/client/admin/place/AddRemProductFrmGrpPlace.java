package com.ayurma.ayuromaweb.client.admin.place;


import com.ayurma.ayuromaweb.client.admin.activity.AddRemProductFrmGrpActivity;
import com.ayurma.ayuromaweb.client.mvp.ActivityPlace;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AddRemProductFrmGrpPlace extends ActivityPlace<AddRemProductFrmGrpActivity> {
	private Long key;//key of the target group
	@Inject
	public AddRemProductFrmGrpPlace(AddRemProductFrmGrpActivity activity) {
		super(activity);
		
	}
	@Override
    public AddRemProductFrmGrpActivity getActivity() {
        activity.init(this);
        return activity;
    }
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}

	@Prefix("Add-Products-to-Group")
    public static class Tokenizer 
                    implements PlaceTokenizer<AddRemProductFrmGrpPlace> {

        // Since the place is injectable, 
        // we'll let Gin do the construction.
        //1
        private final Provider<AddRemProductFrmGrpPlace> placeProvider; 
        //2
        @Inject
        public Tokenizer(
                   Provider<AddRemProductFrmGrpPlace> placeProvider){
            this.placeProvider = placeProvider;
        } 
        //3
        @Override
        public String getToken(AddRemProductFrmGrpPlace place) {
            return place.getPlaceName();
        }
        //4
        @Override
        public AddRemProductFrmGrpPlace getPlace(String token) {
        	AddRemProductFrmGrpPlace place = placeProvider.get();
            place.setPlaceName(token);
            return place;
        }
	}

}
