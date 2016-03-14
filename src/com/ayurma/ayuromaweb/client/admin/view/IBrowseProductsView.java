package com.ayurma.ayuromaweb.client.admin.view;


import java.util.List;

import com.ayurma.ayuromaweb.client.admin.activity.IProductBrowserConnector;
import com.google.gwt.user.client.ui.Widget;

public interface IBrowseProductsView {
	public interface Presenter{

		void onLoadProductsButtonClicked(String text, String value, int sourceId);

		void onEditProductClicked(int productId);

		void onEditProductDetailsClicked(int productId);

		void onDeleteProductClicked(int id);

		void linkButtonClicked(int id);

		int getDisplayMode();

		void clearProductsCache();
		
		
	}

	void setPresenter(Presenter presenter);
	Widget asWidget();
	
	List<Boolean> getSelectedProducts();
	void showProductsList(List<String> names, List<String> sn, int displayMode);
	void reset();
	void setDataReceiver(IProductBrowserConnector dataReceiver);
	//void setDisplayMode(int displayMode);
	void info(String message, int code);
	void clear();
	void init();

}
