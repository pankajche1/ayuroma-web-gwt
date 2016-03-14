package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class HeaderViewImpl extends Composite implements IHeaderView{

	private static HeaderViewImplUiBinder uiBinder = GWT
			.create(HeaderViewImplUiBinder.class);

	interface HeaderViewImplUiBinder extends UiBinder<Widget, HeaderViewImpl> {
	}
	@UiField Anchor ancLogout;
	@UiField InlineLabel lblUserName;
	public HeaderViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	@Override
	public void setLogoutHref(String href){
		ancLogout.setHref(href);
	}
	@Override
	public void setUserName(String userName){
		lblUserName.setText(userName);
	}

}
