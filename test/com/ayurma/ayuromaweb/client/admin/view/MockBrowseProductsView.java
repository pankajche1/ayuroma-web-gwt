package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.activity.IProductBrowserConnector;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;

public class MockBrowseProductsView implements IBrowseProductsView{
	private List<Boolean> listValues = new ArrayList<Boolean>();
	public MockBrowseProductsView(){
		for(int i=0;i<10;i++){
			listValues.add(false);
		}
	}
	@Override
	public void setPresenter(Presenter presenter) {
		System.out.println("MockBrowseProductsView::setPresenter()...");
		
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Boolean> getSelectedProducts() {
		//List<Boolean> listValues = new ArrayList<Boolean>();
		//boolean[] chks={true,true,false,false,false,false,false,false,false,false,false};
		
		//for(boolean val:chks){
			//listValues.add(val);
		//}
		
		return listValues;
		
	}

	@Override
	public void showProductsList(List<String> names, List<String> sn,
			int displayMode) {
		System.out.println("MockBrowseProductsView::showProductList()...");
		System.out.println("    Products list loaded:");
		int i=0;
		for(String name:names){
			System.out.println("        sn="+sn.get(i)+" name="+name);
			i++;
		}
		
	}

	@Override
	public void reset() {
		System.out.println("MockBrowseProductsView::reset()...");
	}

	@Override
	public void setDataReceiver(IProductBrowserConnector dataReceiver) {
		System.out.println("MockBrowseProductsView::setDataReceiver()...");
		
	}
/*
	@Override
	public void setDisplayMode(int displayMode) {
		System.out.println("MockBrowseProductsView::setDisplayMode() displayMode="+displayMode);
		
	}
*/
	@Override
	public void info(String message, int code) {
		System.out.println("MockBrowseProductsView::info() message="+message
				+", code="+code);
		
	}

	@Override
	public void clear() {
		System.out.println("MockBrowseProductsView::clear()...");
		for(int i=0;i<10;i++){
			listValues.set(i, false);
		}
		
	}
	public void selectProduct(int id){
		System.out.println("MockBrowseProductsView::selectProduct()...id="+id);
		if(id<listValues.size()){
			listValues.set(id, true);
		}
	}
	public void deSelectProduct(int id){
		System.out.println("MockBrowseProductsView::deSelectProduct()...id="+id);
		if(id<listValues.size()){
			listValues.set(id, false);
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
