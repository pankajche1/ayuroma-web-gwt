package com.ayurma.ayuromaweb.client.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.mvp.client.history.HistoryHandler;
import com.googlecode.mgwt.mvp.client.history.HistoryObserver;
import com.googlecode.mgwt.ui.client.MGWT;


public class AppHistoryObserver implements HistoryObserver  {
	@Override
	public void onPlaceChange(Place place, HistoryHandler handler) {

	}

	@Override
	public void onHistoryChanged(Place place, HistoryHandler handler) {

	}

	@Override
	public void onAppStarted(Place place, HistoryHandler historyHandler) {
		if (MGWT.getOsDetection().isPhone()) {
			onPhoneNav(place, historyHandler);
		} else {
			// tablet
			onTabletNav(place, historyHandler);

		}

	}

	@Override
	public HandlerRegistration bind(EventBus eventBus, final HistoryHandler historyHandler) {
		


		HandlerRegistrationCollection col = new HandlerRegistrationCollection();
		//col.addHandlerRegistration(register);
		//col.addHandlerRegistration(register2);
		//col.addHandlerRegistration(register3);
		//col.addHandlerRegistration(addHandler);
		return col;
	}

	private void onPhoneNav(Place place, HistoryHandler historyHandler) {
		
	}//onPhoneNav

	private void onTabletNav(Place place, HistoryHandler historyHandler) {
		
	}//onTableNav

}
