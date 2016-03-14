package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.mobile.model.ProductItem;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;

public class ProductGroupMobileViewMock implements IProductGroupMobileView{

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAjaxAnim() {
		System.out.println("ProductGroupMobileViewMock::showAjaxAnim()");
		
	}

	@Override
	public void stopAjaxAnim() {
		System.out.println("ProductGroupMobileViewMock::stopAjaxAnim()");
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showData(ProductGroupItemsData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitle(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HasCellSelectedHandler getCellSelectedHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProducts(List<ProductItem> list) {
		System.out.println("ProductGroupMobileViewMock::setProducts()");
		for(ProductItem item:list){
			System.out.println("    Label:"+item.getName()+", Key:"+item.getKey());
		}
		
	}

	@Override
	public void clearList(List<ProductItem> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showProgressIndicator() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showListView() {
		System.out.println("ProductGroupMobileViewMock::showListView()");
		
	}

	@Override
	public HasTapHandlers getHeaderRightButton() {
		// TODO Auto-generated method stub
		return null;
	}

}
