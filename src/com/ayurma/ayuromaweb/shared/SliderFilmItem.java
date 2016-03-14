package com.ayurma.ayuromaweb.shared;



public class SliderFilmItem {
	//1
	private String imageUrls="";
	//2
	private Long imageItemKey;
	//3
	private Long productId;
	//4
	private String productName="";

	public SliderFilmItem(){
		
	}
	public SliderFilmItem(Long imageItemKey,String imageUrls, Long productId) {
		
		this.imageUrls = imageUrls;
		this.imageItemKey=imageItemKey;
		this.productId = productId;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getImageItemKey() {
		return imageItemKey;
	}
	public void setImageItemKey(Long imageItemKey) {
		this.imageItemKey = imageItemKey;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
