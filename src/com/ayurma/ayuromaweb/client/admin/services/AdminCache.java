package com.ayurma.ayuromaweb.client.admin.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import java.util.List;
import java.util.Map;

import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.ayurma.ayuromaweb.shared.UserDTO;

public class AdminCache {
	private String strClipBoard="";
	//the user:
	private UserDTO user;
//for adding or removing products from groups:
	private Map<Long,ProductGroupItemsData> groups 
					= new HashMap<Long,ProductGroupItemsData>();
	//slider images:
	private Map<Integer, PageGroup> sliderImagesPageGroups=new HashMap<Integer, PageGroup>();
	private Map<Long,SliderImageDTO> sliderImagesData=new HashMap<Long,SliderImageDTO>();
	
	//images:
	private Map<Integer, PageGroup> imagesPageGroups=new HashMap<Integer, PageGroup>();
	private Map<Long,ImageDataDTO> imagesData=new HashMap<Long,ImageDataDTO>();
	
	//products:
	//here the product groups are mapped to the product per page:
	private Map<Integer, ProductPageGroup> productPageGroups=new HashMap<Integer, ProductPageGroup>();
	//the actual storage of products basic infos:
	private Map<Long,ProductBasicInfo> productsBasicInfos=new HashMap<Long,ProductBasicInfo>();
	//for adding slider films:
	private Map<Long,SliderFilmDTO> sliderFilms= new HashMap<Long,SliderFilmDTO>();
	public void addProductGroupItemsData(ProductGroupItemsData data){
		System.out.println("AdminCache::addProductGroupItemsData()...");
		groups.put(data.getKey(), data);
	}
	public ProductGroupItemsData getProductGroupItemsData(Long key){
		return groups.get(key);
	}
	public String getClipBoardText() {
		return strClipBoard;
	}
	public void setClipBoardText(String strClipBoard) {
		this.strClipBoard = strClipBoard;
	}
	public void addSliderFilm(SliderFilmDTO film){
		sliderFilms.put(film.getKey(), film);
	}
	public SliderFilmDTO getSliderFilm(Long key){
		return sliderFilms.get(key);
	}
	public List<ProductGroupItemsData> getProductGroups(){
		List<ProductGroupItemsData> list=new ArrayList<ProductGroupItemsData>();
		Set<Long> keySet=groups.keySet();
		Iterator<Long> iter=keySet.iterator();
		while(iter.hasNext()){
			list.add(groups.get(iter.next()));
		}
		
		return list;
	}
	public ProductBasicInfo getProductBasicInfoByKey(Long key){
		return productsBasicInfos.get(key);
	}
	public void deleteProducts(){
		productPageGroups=new HashMap<Integer, ProductPageGroup>();
		productsBasicInfos=new HashMap<Long,ProductBasicInfo>();
	}
	public List<ProductBasicInfo> getProductBasicInfosByPage(int iPage,int nItemsPerPage){
		List<ProductBasicInfo> listOut=new ArrayList<ProductBasicInfo>();
		//check if this page exists in the cache or not:
		if(productPageGroups.containsKey(nItemsPerPage)){
			//means the page exists:
			//now get the page from that group:
			ProductPageGroup group = productPageGroups.get(nItemsPerPage);
			if(group!=null){
				ProductPage page = group.getPage(iPage);
				if(page!=null){
					List<Long> keys=page.getProductKeys();
					for(Long key:keys){
						ProductBasicInfo info=productsBasicInfos.get(key);
						if(info!=null){
							listOut.add(info);
						}
					}
				}
			}
		}
		return listOut;
	}
	public void addProductInfosPage(int iPage,int nItemsPerPage,List<ProductBasicInfo> list){
		//convert this data in to a product page:
		ProductPage page = new ProductPage(iPage);
		//now add the list to it:
		List<Long> productKeys = new ArrayList<Long>();
		//and also add the info items to the main list:
		
		for(ProductBasicInfo info:list){
			productKeys.add(info.getKey());
			productsBasicInfos.put(info.getKey(), info);
		}
		//setting the keys to the page:
		page.setProductKeys(productKeys);
		//now getting the productPageGroup from the map:
		ProductPageGroup pageGroup =productPageGroups.get(nItemsPerPage);
		if(pageGroup!=null){
			pageGroup.addPage(page, iPage);
		}else{
			//create a group and add it:
			pageGroup = new ProductPageGroup();
			pageGroup.addPage(page, iPage);
			productPageGroups.put(nItemsPerPage, pageGroup);
			
			
		}
		
		
	}
	public void deleteSliderImagesData(){
		sliderImagesPageGroups=new HashMap<Integer, PageGroup>();
		sliderImagesData=new HashMap<Long,SliderImageDTO>();


	}
	public List<SliderImageDTO> getSliderImagesDataByPage(int iPage,int nItemsPerPage){
		List<SliderImageDTO> listOut=new ArrayList<SliderImageDTO>();
		//check if this page exists in the cache or not:
		if(sliderImagesPageGroups.containsKey(nItemsPerPage)){
			//means the page exists:
			//now get the page from that group:
			PageGroup group = sliderImagesPageGroups.get(nItemsPerPage);
			if(group!=null){
				Page page = group.getPage(iPage);
				if(page!=null){
					List<Long> keys=page.getKeys();
					for(Long key:keys){
						SliderImageDTO info=sliderImagesData.get(key);
						if(info!=null){
							listOut.add(info);
						}
					}
				}
			}
		}
		return listOut;
	}	
	public void addSliderImagesDataPage(int iPage,int nItemsPerPage,List<SliderImageDTO> list){
		//convert this data in to a product page:
		Page page = new Page(iPage);
		//now add the list to it:
		List<Long> keys = new ArrayList<Long>();
		//and also add the info items to the main list:
		
		for(SliderImageDTO info:list){
			keys.add(info.getKey());
			sliderImagesData.put(info.getKey(), info);
		}
		//setting the keys to the page:
		page.setKeys(keys);
		//now getting the productPageGroup from the map:
		PageGroup pageGroup =sliderImagesPageGroups.get(nItemsPerPage);
		if(pageGroup!=null){
			pageGroup.addPage(page, iPage);
		}else{
			//create a group and add it:
			pageGroup = new PageGroup();
			pageGroup.addPage(page, iPage);
			sliderImagesPageGroups.put(nItemsPerPage, pageGroup);
			
			
		}		
	}
	public void deleteImagesData(){
		imagesPageGroups=new HashMap<Integer, PageGroup>();
		imagesData=new HashMap<Long,ImageDataDTO>();

	}
	/*
	 * 
	 */
	public List<ImageDataDTO> getImagesDataByPage(int iPage,int nItemsPerPage){
		List<ImageDataDTO> listOut=new ArrayList<ImageDataDTO>();
		//check if this page exists in the cache or not:
		if(imagesPageGroups.containsKey(nItemsPerPage)){
			//means the page exists:
			//now get the page from that group:
			PageGroup group = imagesPageGroups.get(nItemsPerPage);
			if(group!=null){
				Page page = group.getPage(iPage);
				if(page!=null){
					List<Long> keys=page.getKeys();
					for(Long key:keys){
						ImageDataDTO info=imagesData.get(key);
						if(info!=null){
							listOut.add(info);
						}
					}
				}
			}
		}
		return listOut;
	}
	/*
	 * 
	 */
	public void addImagesDataPage(int iPage,int nItemsPerPage,List<ImageDataDTO> list){
		//convert this data in to a product page:
		Page page = new Page(iPage);
		//now add the list to it:
		List<Long> keys = new ArrayList<Long>();
		//and also add the info items to the main list:
		
		for(ImageDataDTO info:list){
			keys.add(info.getKey());
			imagesData.put(info.getKey(), info);
		}
		//setting the keys to the page:
		page.setKeys(keys);
		//now getting the productPageGroup from the map:
		PageGroup pageGroup =imagesPageGroups.get(nItemsPerPage);
		if(pageGroup!=null){
			pageGroup.addPage(page, iPage);
		}else{
			//create a group and add it:
			pageGroup = new PageGroup();
			pageGroup.addPage(page, iPage);
			imagesPageGroups.put(nItemsPerPage, pageGroup);
			
			
		}		
	}
	private class ProductPageGroup{
		//pages mapped to their numbers:
		private Map<Integer,ProductPage> pages=new HashMap<Integer,ProductPage>();
		public ProductPage getPage(int iPage){
			return pages.get(iPage);
		}
		public void addPage(ProductPage page, int iPage){
			pages.put(iPage, page);
		}
		
	}
	private class ProductPage{
		private int iPage;
		private List<Long> productKeys;

		public ProductPage(int iPage) {
			productKeys=new ArrayList<Long>();
			this.iPage = iPage;
		}

		public int getiPage() {
			return iPage;
		}

		public List<Long> getProductKeys() {
			return productKeys;
		}

		public void setProductKeys(List<Long> productKeys) {
			this.productKeys = productKeys;
		}
		
		
		
		
	}
	private class PageGroup{
		//pages mapped to their numbers:
		private Map<Integer,Page> pages=new HashMap<Integer,Page>();
		public Page getPage(int iPage){
			return pages.get(iPage);
		}
		public void addPage(Page page, int iPage){
			pages.put(iPage, page);
		}
		
	}
	private class Page{
		private int iPage;
		private List<Long> keys;

		public Page(int iPage) {
			keys=new ArrayList<Long>();
			this.iPage = iPage;
		}

		public int getiPage() {
			return iPage;
		}

		public List<Long> getKeys() {
			return keys;
		}

		public void setKeys(List<Long> keys) {
			this.keys = keys;
		}		
	}
	/*
	 * DataStore of which type of objects? it is T type of objects
	 */
	/* Pankaj note: Error is due to T generic type think more for its solution 8 feb 2014 1747 hrs
	private class DataStore<T>{
		//this will have a storage of items:
		private Map<Long,T> items=new HashMap<Long,T>();
		//now it will have pages grops mapped to the number that one page contains how many items?
		private Map<Integer, PageGroup> pageGroups=new HashMap<Integer, PageGroup>();
		public void addPage(int iPage,int nItemsPerPage,List<T> list){
			//convert this data in to a product page:
			Page page = new Page(iPage);
			//now add the list to it:
			List<Long> keys = new ArrayList<Long>();
			//and also add the info items to the main list:
			
			for(T info:list){
				keys.add(info.getKey());
				items.put(info.getKey(), info);
			}
			//setting the keys to the page:
			page.setKeys(keys);
			//now getting the productPageGroup from the map:
			PageGroup pageGroup =imagesPageGroups.get(nItemsPerPage);
			if(pageGroup!=null){
				pageGroup.addPage(page, iPage);
			}else{
				//create a group and add it:
				pageGroup = new PageGroup();
				pageGroup.addPage(page, iPage);
				imagesPageGroups.put(nItemsPerPage, pageGroup);
				
				
			}		
		}
		
		
	}
	*/
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
}
