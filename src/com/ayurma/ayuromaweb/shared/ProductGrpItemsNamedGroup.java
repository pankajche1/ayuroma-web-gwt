package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ProductGrpItemsNamedGroup implements Serializable {
	 private List<String> itemsNames;
	 private List<Long> itemsIds;
	 private List<Long> detailsKeys;
	 
	 //0 for A-F 1 for G-L 2 for M-R 3 for S-Z 4 for others
	 private int id;
		
		public ProductGrpItemsNamedGroup(int id) {
			this.id=id;
			itemsNames=new ArrayList<String>();
			 itemsIds=new ArrayList<Long>(); 
			 detailsKeys=new ArrayList<Long>(); 
		}
		public void addProductItem(String name,Long key,Long detailsKey){
			itemsNames.add(name);
			itemsIds.add(key);
			detailsKeys.add(detailsKey);
			
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public List<String> getItemsNames() {
			return itemsNames;
		}
		public List<Long> getItemsIds() {
			return itemsIds;
		}
		public List<Long> getDetailsKeys() {
			return detailsKeys;
		}
		
}
