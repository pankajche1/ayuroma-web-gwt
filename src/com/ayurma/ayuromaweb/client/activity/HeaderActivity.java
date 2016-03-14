package com.ayurma.ayuromaweb.client.activity;

import com.ayurma.ayuromaweb.client.view.HeaderView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class HeaderActivity extends AbstractActivity{
	//view of the header:
	private HeaderView view;
	@Inject
	public HeaderActivity(HeaderView view) {
		this.view=view;
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	public HasWidgets get(String id){
		return view.get(id);
	}



}
