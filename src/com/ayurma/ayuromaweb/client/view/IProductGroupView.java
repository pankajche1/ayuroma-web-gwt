package com.ayurma.ayuromaweb.client.view;



import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.google.gwt.user.client.ui.Widget;

public interface IProductGroupView {
	public interface Presenter{
		
	}

	void setPresenter(Presenter presenter);
	void showAjaxAnim();
	void stopAjaxAnim();
	void reset();
	Widget asWidget();
	void showData(ProductGroupItemsData data);
	//void showData(ChemicalData data);
	void scrollTo(int left, int top);
}
