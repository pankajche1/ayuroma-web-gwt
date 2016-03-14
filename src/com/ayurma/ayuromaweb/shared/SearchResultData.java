package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;


@SuppressWarnings("serial")
public class SearchResultData implements Serializable{
	private ChemicalData[] products=new ChemicalData[0];
	//private ProductGroupData group;

	public ChemicalData[] getProducts() {
		return products;
	}
	public void setProducts(ChemicalData[] products) {
		this.products = products;
	}
	//public ProductGroupData getGroup() {
		//return group;
	//}
	//public void setGroup(ProductGroupData group) {
		//this.group = group;
	//}
	public SearchResultData() {
		
	}

}
