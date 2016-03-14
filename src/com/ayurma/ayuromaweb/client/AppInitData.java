package com.ayurma.ayuromaweb.client;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.shared.CompanyInfoDTO;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;

public class AppInitData {
	private List<ProductGroup> productGroups = new ArrayList<ProductGroup>();
	private SliderFilmDTO sliderFilm;
	private CompanyInfoDTO companyInfo;
	public AppInitData() {
		
	}
	public void addProductGroup(String name,String strKey,String imageUrl,String text){
		productGroups.add(new ProductGroup(name,strKey,imageUrl,text));
	}
	public String getProductGroupName(int i){
		if(i<productGroups.size()){
			return productGroups.get(i).getName(); 
		}else return "";
	}
	public String getProductGroupKey(int i){
		if(i<productGroups.size()){
			return productGroups.get(i).getStrKey(); 
		}else return "";
	}
	public String getProductGroupText(int i){
		if(i<productGroups.size()){
			return productGroups.get(i).getText(); 
		}else return "";		
	}
	public String getProductGroupImageUrl(int i){
		if(i<productGroups.size()){
			return productGroups.get(i).getImageUrl(); 
		}else return "";		
	}
	public int getProductGroupSize(){
		return productGroups.size();
	}
	private class ProductGroup{
		private String name;
		private String strKey;
		private String imageUrl;
		private String text;
		
		public ProductGroup(String name, String strKey, String imageUrl,
				String text) {
			
			this.name = name;
			this.strKey = strKey;
			this.imageUrl = imageUrl;
			this.text = text;
		}
		@SuppressWarnings("unused")
		public ProductGroup(String name,String strKey){
			this.name=name;
			this.strKey=strKey;
		}
		public String getName() {
			return name;
		}
		public String getStrKey() {
			return strKey;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		@SuppressWarnings("unused")
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public String getText() {
			return text;
		}
		@SuppressWarnings("unused")
		public void setText(String text) {
			this.text = text;
		}
		
		
	}
	public SliderFilmDTO getSliderFilm() {
		return sliderFilm;
	}
	public void setSliderFilm(SliderFilmDTO sliderFilm) {
		this.sliderFilm = sliderFilm;
	}
	public CompanyInfoDTO getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(CompanyInfoDTO companyInfo) {
		
		this.companyInfo = companyInfo;

	}
	
}
