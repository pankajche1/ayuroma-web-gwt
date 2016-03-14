package com.ayurma.ayuromaweb.client.admin.view.widgets;

import java.util.List;

import com.google.gwt.user.client.ui.HasText;

public interface IAddressWidget {
	HasText getTbCity();
	HasText getTbState();
	HasText getTbCountry();
	List<String> getLines();

}
