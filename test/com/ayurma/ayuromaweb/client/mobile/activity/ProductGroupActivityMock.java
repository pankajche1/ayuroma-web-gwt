package com.ayurma.ayuromaweb.client.mobile.activity;

import java.util.List;

import com.ayurma.ayuromaweb.client.activity.IProductGroupActivity;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.ProductGrpItemsNamedGroup;

public class ProductGroupActivityMock implements IProductGroupActivity{

	@Override
	public void processDataFromServer(ProductGroupItemsData result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showData(ProductGroupItemsData data) {
		System.out.println("ProductGroupActivityMock::showData() targetData:"+ data.getName());
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
		}
		
	}

	@Override
	public void info(String msg, int id, int type) {
		System.out.println("ProductGroupActivityMock info() message:"+msg);
		
	}

	@Override
	public void stopAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

}
