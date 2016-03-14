package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface IHeaderView {

	IsWidget asWidget();

	void setLogoutHref(String href);

	void setUserName(String userName);

}
