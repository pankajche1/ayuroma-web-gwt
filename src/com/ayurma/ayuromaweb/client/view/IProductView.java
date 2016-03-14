package com.ayurma.ayuromaweb.client.view;



import java.util.List;

import com.ayurma.ayuromaweb.client.layout.ProductViewLayout;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.gwt.user.client.ui.Widget;


public interface IProductView<T> {
	public interface Presenter<T>{

		void goToDetails();

		void goToEnquiry();

		void getReports();
		
	}

	void setPresenter(Presenter<T> presenter);
	void showAjaxAnim();
	void stopAjaxAnim();
	void info(String msg,int id,int type);
	Widget asWidget();
	
	
	
	void reset();
	void scrollTo(int left, int top);
	void setLayouts(List<ProductViewLayout<T>> layouts);
	void showData(T data);

}
