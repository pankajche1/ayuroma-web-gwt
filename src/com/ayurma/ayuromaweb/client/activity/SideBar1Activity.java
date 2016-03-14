package com.ayurma.ayuromaweb.client.activity;

import com.ayurma.ayuromaweb.client.view.ISideBar1View;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class SideBar1Activity extends AbstractActivity implements ISideBar1View.Presenter{
	private ISideBar1View view;
	@Inject
	public SideBar1Activity(ISideBar1View view){
		this.view=view;
		this.view.setPresenter(this);
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	} 
	public void go(HasWidgets container){
		container.add(view.asWidget());
	}
	@Override
	public HasWidgets get() {
		
		return view.get();
	}
	

}
