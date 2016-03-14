package com.ayurma.ayuromaweb.client.view;



import java.util.List;

import com.ayurma.ayuromaweb.client.layout.ProductDetailsLayout;

import com.google.gwt.user.client.ui.Widget;

public interface IProductDetailsView<T> {
	public interface Presenter<T>{

		void gotoEnquiry();
		void gotoGetReports();
		
	}

	void setPresenter(Presenter<T> presenter);
	//void showAjaxAnim();
	//void stopAjaxAnim();
	Widget asWidget();
	//void setData(ProductDetails data);
	void scrollTo(int left, int top);
	void setListLayouts(List<ProductDetailsLayout<T>> listLayouts);
	void setData(T data);
	void showAjaxLoading();
	void reset();
	void stopAjaxLoading();
	
}
