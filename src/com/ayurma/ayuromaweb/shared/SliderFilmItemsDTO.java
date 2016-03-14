package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SliderFilmItemsDTO implements Serializable{
	private List<SliderFilmItem> item;
	//1 urls of the images in the blob store
	private List<String> imageUrls=new ArrayList<String>();
	//2 the productc keys:
	private List<Long> productKeys=new ArrayList<Long>();
	//5 keys of the linked products on the data store:
	private Long[] productIds;
	private int nTotalItems;
	public SliderFilmItemsDTO() {
		
	}
	public List<SliderFilmItem> getItem() {
		return item;
	}
	public void setItem(List<SliderFilmItem> item) {
		this.item = item;
	}
	public int getnTotalItems() {
		return nTotalItems;
	}
	public void setnTotalItems(int nTotalItems) {
		this.nTotalItems = nTotalItems;
	}
	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	public Long[] getProductIds() {
		return productIds;
	}
	public void setProductIds(Long[] productIds) {
		this.productIds = productIds;
	}
	public List<Long> getProductKeys() {
		return productKeys;
	}
	public void setProductKeys(List<Long> productKeys) {
		this.productKeys = productKeys;
	}

}
