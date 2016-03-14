package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class SliderFilmDTO  implements Serializable{
	private Long key;
	//1 urls of the images in the blob store
	private List<String> imageUrls=new ArrayList<String>();
	//2 linked items keys on the data store. Here they are Products
    private List<String> linkedItemsUrls= new ArrayList<String>();
    //3 the names of the above products
    private List<String> linkedItemsNames=new ArrayList<String>();
    
	private List<Long> items=new ArrayList<Long>();
	//4 keys of the image items on the data store
	private Long[] imageItems;
	//5 keys of the linked products on the data store:
	private Long[] productIds;
	private int totalItems;
	private List<SliderFilmItem> filmItems=new ArrayList<SliderFilmItem>();
	private String title="";
	private Date dateCreation;
	private Date dateEdit;
	public SliderFilmDTO(String title) {
		
		this.title = title;
	}
	public SliderFilmDTO() {
		
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	public List<String> getLinkedItemsUrls() {
		return linkedItemsUrls;
	}
	public void setLinkedItemsUrls(List<String> linkedItemsUrls) {
		this.linkedItemsUrls = linkedItemsUrls;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Long> getItems() {
		return items;
	}
	public void setItems(Long[] itemsIn) {
	 
		items = new ArrayList<Long>(); 
		for(Long item:itemsIn){
			items.add(item);
		}
		
	}
	public void setImageItems(List<SliderFilmItem> filmItems){
		imageItems = new Long[filmItems.size()];
		productIds= new Long[filmItems.size()];
		int i=0;
		for(SliderFilmItem item:filmItems){
			imageItems[i]=item.getImageItemKey();
			productIds[i]=item.getProductId();
			i++;
		}
	}
	
	public Long[] getImageItems() {
		return imageItems;
	}
	public Long[] getProductIds() {
		return productIds;
	}
	public void addItem(SliderFilmItem item){
		filmItems.add(item);
	}
	public void setImageItems(Long[] imageItems) {
		this.imageItems = imageItems;
	}
	public void setProductIds(Long[] productIds) {
		this.productIds = productIds;
	}
	public List<String> getLinkedItemsNames() {
		return linkedItemsNames;
	}
	public void setLinkedItemsNames(List<String> linkedItemsNames) {
		this.linkedItemsNames = linkedItemsNames;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateEdit() {
		return dateEdit;
	}
	public void setDateEdit(Date dateEdit) {
		this.dateEdit = dateEdit;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems=totalItems;
		
	}
	public int getTotalItems() {
		return totalItems;
	}
	
}
