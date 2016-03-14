package com.ayurma.ayuromaweb.client.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.layout.ProductViewLayout;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.gwt.user.client.ui.Widget;

public class ProductViewMock<T> implements IProductView<T>{

	@Override
	public void setPresenter(Presenter<T> presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showData(T data) {
		//System.out.println("Product View data name:"+data.getName());
		//System.out.println("Product View data description:"+data.getDescription());
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLayouts(List<ProductViewLayout<T>> layouts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String msg, int id, int type) {
		// TODO Auto-generated method stub
		
	}

}
