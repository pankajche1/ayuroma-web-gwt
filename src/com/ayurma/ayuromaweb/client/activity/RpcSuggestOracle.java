package com.ayurma.ayuromaweb.client.activity;

import java.util.ArrayList;
import java.util.List;


import com.ayurma.ayuromaweb.client.service.Cache;
import com.ayurma.ayuromaweb.client.service.DataServiceAsync;
import com.ayurma.ayuromaweb.client.view.SuggestBoxView;
import com.ayurma.ayuromaweb.shared.NameSuggestDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.google.gwt.user.client.ui.SuggestOracle.Request;
import com.google.gwt.user.client.ui.SuggestOracle.Response;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public class RpcSuggestOracle extends SuggestOracle {
	 private DataServiceAsync suggestService;
	    private SuggestBoxView view;
	   //private Image imgLoading = new Image(Resources.INSTANCE.getLoadingAnimation());
	   //private Image growingFlower= new Image(Resources.INSTANCE.getLoadingGrowingFlower());
	   //private Image arrowPink= new Image(Resources.INSTANCE.getLoaderArrowPink());
	   private Cache cache;

		public RpcSuggestOracle(DataServiceAsync searchService,SuggestBoxView view,Cache cache){
	        this.suggestService = searchService;
	        this.cache=cache;
	        this.view=view;
	    }
		@Override
		public void requestSuggestions(final Request request, final Callback callback) {
			//the request is coming from the typing the input box
			
			//show ajax calling:
			view.showAjaxAnim();
			
			 //We only support one keyword for now.
	        if (!request.getQuery().contains(" ")){
	        	//now check the cache store for the response:
	        	if(cache.getSearchResponse(request.getQuery())==null){
	        	//data service:
	            suggestService.suggest(request.getQuery(),new AsyncCallback<NameSuggestDTO>() {
	                public void onFailure(Throwable caught) {
	                	//arrowPink.removeFromParent();
	                	view.stopAjaxAnim();
	                    // Window.alert("Error while getting suggestions.");
	                }
	                public void onSuccess(NameSuggestDTO result) {
	                	
	                		//arrowPink.removeFromParent();
	                		view.stopAjaxAnim();
	                		if (result!=null){
	                			//now we have got the list of names from the server:
	                			//and we make suggestios to diplay on the suggestbox view:
	                			ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
	                			//traverse the list of Strings obtained from the server:
	                			List<String> names = result.getNames();
	                			List<Long> keys = result.getKeys();
	                			int i=0;
	                			for (final String sug:names){
	                				cache.putSearchedName(sug, keys.get(i));
	                				suggestions.add(new Suggestion() {
	                					//we have here the 'sug' variable which is the name data from the server
	                					public String getReplacementString() { return sug; }
	                					public String getDisplayString() { return sug; }
	                				});
	                				i++;
	                			}
	                			Response resp = new Response(suggestions);
	                			//adding the response to the cache:
	                			cache.addSearchResponse(request.getQuery(), resp);
	                			callback.onSuggestionsReady(request, resp);
	                		}
	                	}
	            	});
	        	}//if the cache does not have the required response
	        	else{//we have the response in the cache
	        		view.stopAjaxAnim();
	        		//System.out.println("RpcSuggestOracle::hitting the cache...");
	        		callback.onSuggestionsReady(request, cache.getSearchResponse(request.getQuery()));
	        	}
	    }else{//the query contains a space character
	    	//arrowPink.removeFromParent();
	    	view.stopAjaxAnim();
	        //an empty array list of suggestion is given to the callback which 
	    	//gives this list to the suggestbox on the view to show the list
	        Response resp = new Response(new ArrayList<Suggestion>());
	        callback.onSuggestionsReady(request, resp);
			
		}

		}
}
