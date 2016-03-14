package com.ayurma.ayuromaweb.client.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ayurma.ayuromaweb.client.juice.MyJuiceModule;
import com.ayurma.ayuromaweb.client.service.AdminDataServiceMock;
import com.ayurma.ayuromaweb.client.service.DataServiceAsyncMock;
import com.ayurma.ayuromaweb.server.dao.AdminDAO;
import com.ayurma.ayuromaweb.server.dao.DAO;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderFilmItem;
import com.ayurma.ayuromaweb.shared.SliderFilmItemsDTO;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;


import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class HomeViewActivityTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private AdminDataServiceMock adminDataService;
	private DataServiceAsyncMock dataService;
	private SliderFilmDTO film;
	private HomeViewActivity activity;
	@Before
	public void setUp(){
		helper.setUp();
		Injector injector = Guice.createInjector(new MyJuiceModule());
		activity = injector.getInstance(HomeViewActivity.class);
		adminDataService= injector.getInstance(AdminDataServiceMock.class);
		dataService=injector.getInstance(DataServiceAsyncMock.class);
		AdminDAO.get().createAppData();
		saveImagesToDatastore();
		
	}
	@After
	public void tearDown(){
		helper.tearDown();
	}
	@Test
	public void test(){
		addSliderFilmToServer();
		activity.load(film.getKey(), 40, 20);
	
	}
	private void addSliderFilmToServer(){
		getSliderImages();
		//String[] images={"image0","image1","image2","image3","image4","image5","image6","image7","image8","image9","image10"};
		
	}
	private void getSliderFilmFromServer(Long key){
		adminDataService.getSliderFilm(key, new AsyncCallback<SliderFilmDTO>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(SliderFilmDTO result) {
				if(result!=null){
					film=result;
					//showFilmItems(result);
				}else System.out.println("Got a null film from the server...");
				
			}});
	}
	private void getSliderFilms(){
		adminDataService.getSliderFilms(0, 10, new AsyncCallback<List<SliderFilmDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(List<SliderFilmDTO> result) {
				if(result!=null){
					if(result.size()>0)
					
					getSliderFilmFromServer(result.get(0).getKey());
				
				}else System.out.println("Got a null list of films.");
				
			}});
	}
	@SuppressWarnings("unused")
	private void showFilmItems(SliderFilmDTO film){
		List<String> images = film.getImageUrls();
		//set this film for show on the home page slider:
		AdminDAO.get().displaySliderFilm(film.getKey());
		System.out.println("image urls size="+images.size()+", and key of the film:"+film.getKey());
		//for(String image:images){
			//System.out.println("image="+image);
		//}
		//getJson();
		//getting items starting from index 20:
		//getItems(20,film.getKey());//parameters: int iStart, long filmKey
		getItems(40,film.getKey());
	}
	@SuppressWarnings("unused")
	private void getJson(){
		JSONObject appDataJson = DAO.get().getAppInitData();
		if(appDataJson!=null){
			System.out.println(appDataJson.toString());
		}
	}
	private void getItems(int iStart,Long key){
		dataService.getSliderFilmItems(key, iStart, 20,new AsyncCallback<SliderFilmItemsDTO>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(SliderFilmItemsDTO result) {
				if(result==null) System.out.println("Item got is null");
				displayItems(result);
				
			}});
	}
	private void displayItems(SliderFilmItemsDTO dto){
		List<String> imageUrls = dto.getImageUrls();
		if(imageUrls.size()<1) System.out.println("No image urls got from the server.");
		for(String image:imageUrls){
			System.out.println("item image:"+image);
		}
		
	}
	private void saveImagesToDatastore(){
		
		for(int i=0;i<100;i++){
			AdminDAO.get().saveImageInfo("slider-image", "my image", "image"+i);
		}

	}
	private void getSliderImages(){
		adminDataService.getSliderImagesByPage(0, 200, new AsyncCallback<List<SliderImageDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<SliderImageDTO> result) {
				List<String> imageUrls=new ArrayList<String>();
				List<Long> imageKeys = new ArrayList<Long>();
				int i=0;
				for(SliderImageDTO dto:result){
					//System.out.println("name:"+dto.getImageKey());
					imageUrls.add(dto.getImageKey());
					imageKeys.add(dto.getKey());
					i++;
					
				}
				makeSliderFilm(imageUrls,imageKeys);
				
			}});
	}
	private void makeSliderFilm(List<String> imageUrls,List<Long> imageKeys){
		

		List<SliderFilmItem> filmItems = new ArrayList<SliderFilmItem>();
		SliderFilmItem filmItem;
		int i=0;
		for(String image:imageUrls){
			filmItem=new SliderFilmItem();
			filmItem.setImageUrls(image);
			
			filmItem.setImageItemKey(imageKeys.get(i));
			filmItem.setProductName("product"+i);
			filmItem.setProductId(Long.valueOf(i));
			filmItems.add(filmItem);
			
			i++;
		}
		SliderFilmDTO newFilm = new SliderFilmDTO("My great film");
		newFilm.setImageItems(filmItems);
		adminDataService.saveSliderFilm(newFilm, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				////view.info("Error in saving film", 0);
				
			}

			@Override
			public void onSuccess(String result) {
				System.out.println("Response from server:"+result);
				getSliderFilms();
				
}});
	}

}
