package com.ayurma.ayuromaweb.client.admin.view;


import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
//import com.google.gwt.user.client.ui.Widget;

public interface IAddProductsToGroupView {
	public interface Presenter{
		void gotoPlace(int id);

		void addProductsToGroup();

		void save();

		void removeProductFromProposed(int id);

		void includeProducts();

		void removeProduct(int id);
		
	}

	void setPresenter(Presenter presenter);
	IsWidget asWidget();
	void setGroupData(String name, String[] namesProducts, String[] strSn);
	AcceptsOneWidget get(String id);
	void setSelectedProductData(String name, String[] namesProducts,
			String[] strSn);
	void info(String message, int code);

}
