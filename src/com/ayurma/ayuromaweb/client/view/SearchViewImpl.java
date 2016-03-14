package com.ayurma.ayuromaweb.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SearchViewImpl extends Composite implements ISearchView{

	private static SearchViewImplUiBinder uiBinder = GWT
			.create(SearchViewImplUiBinder.class);

	interface SearchViewImplUiBinder extends UiBinder<Widget, SearchViewImpl> {
	}
	private Presenter presenter;
	public SearchViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	@Override
	public void showAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

}
