package com.ayurma.ayuromaweb.client.admin.activity;

import com.ayurma.ayuromaweb.client.admin.view.IHeaderView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class HeaderActivity {
	private final IHeaderView view;
	@Inject
	public HeaderActivity(IHeaderView view){
		this.view=view;
	}
	public void init(String userName,String logoutHref){
		view.setLogoutHref(logoutHref);
		view.setUserName(userName);
	}
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}
	
}
