package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.layout.ProductViewLayout;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;

public class ProductViewMock<T> implements IProductMobileView<T>{

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHeaderTitle(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HasTapHandlers getHeaderRightButton() {
		
		return new RightButton();
	}

	@Override
	public void showData(T data) {
		System.out.println("In Product View...");
		//System.out.println("    Chemical Name:"+targetProduct.getName());
		//System.out.println("    Image Url:"+targetProduct.getImageUrl());
		
		
	}
	private class RightButton implements HasTapHandlers{

		@Override
		public HandlerRegistration addTapHandler(TapHandler handler) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	@Override
	public void setLayouts(List<ProductViewLayout<T>> layouts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAjaxAnim() {
		System.out.println("In Product Mobile View Mock showAjaxAnim()...");
		
	}

	@Override
	public void stopAjaxAnim() {
		System.out.println("In Product Mobile View Mock stopAjaxAnim()...");
		
	}

	@Override
	public void reset() {
		System.out.println("In Product Mobile View Mock reset()...");
		
	}

	@Override
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String msg, int id, int type) {
		System.out.println("In Product View Mock::info() msg:"+msg+", id:"+id+", type:"+type);
		
	}

}
