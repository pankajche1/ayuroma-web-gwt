package com.ayurma.ayuromaweb.client.view;

import java.util.List;

import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.ProductGrpItemsNamedGroup;
import com.google.gwt.user.client.ui.Widget;

public class ProductGroupDesktopViewMock implements IProductGroupView{

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAjaxAnim() {
		System.out.println("ProductGroupDesktopViewMock::showAjaxAnim()");
		
	}

	@Override
	public void stopAjaxAnim() {
		System.out.println("ProductGroupDesktopViewMock::stopAjaxAnim()");
		
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
		System.out.println("ProductGroupDesktopViewMock::showData() targetData:"+ data.getName());
		System.out.println("    description:"+ data.getDescription());
		String[] itemsNames = data.getItemsNames();
		if(itemsNames.length==0){
			System.out.println("itemsNames length is zero.");
			return;
		}
		if(data.getListGroups()!=null){
			System.out.println("itemsNames group list was found");
			//if the names have been grouped by initial letters
			//the table will be made based on this group
			//get the groups out:
			List<ProductGrpItemsNamedGroup> list=data.getListGroups();
			ProductGrpItemsNamedGroup groupATOF = list.get(0);
			ProductGrpItemsNamedGroup groupGTOL = list.get(1);
			ProductGrpItemsNamedGroup groupMTOR = list.get(2);
			ProductGrpItemsNamedGroup groupSTOZ = list.get(3);
			ProductGrpItemsNamedGroup groupOthers = list.get(4);
		}else{
			System.out.println("itemsNames group list was not found.");
			String names[] = data.getItemsNames();
			int i = 0;
			for(String name:names){
				System.out.println("Name Product:"+name);
				i++;
			}
		}//else
		}
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}

}
