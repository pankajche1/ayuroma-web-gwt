package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.admin.place.AddProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.place.EmployeesPlace;
import com.ayurma.ayuromaweb.client.admin.place.HomePlace;
import com.ayurma.ayuromaweb.client.admin.place.ImagesViewPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderViewPlace;
import com.ayurma.ayuromaweb.client.admin.place.ToolsViewPlace;
import com.ayurma.ayuromaweb.client.admin.place.UsersPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminCache;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.util.UserSettings;
import com.ayurma.ayuromaweb.client.admin.view.IMenu;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MenuActivity extends AbstractActivity implements IMenu.Presenter {
	//private Logger logger = Logger.getLogger("");
	private IMenu view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private Provider<HomePlace> homePlaceProvider;
	private Provider<ProductPlace> productPlaceProvider;
	private Provider<AddProductGroupPlace> addProductGroupPlaceProvider;
	private Provider<ProductGroupPlace> productGroupPlaceProvider;
	private Provider<ImagesViewPlace> imagesPlaceProvider;
	private Provider<SliderViewPlace> sliderPlaceProvider;
	private Provider<ToolsViewPlace> toolsPlaceProvider;
	private Provider<UsersPlace> usersPlaceProvider;
	private Provider<EmployeesPlace> employeesPlaceProvider;
	private List<String> menuLabels;
	private List<Integer> menuCodes;
	private AdminCache cache;
	private UserDTO user;
	
	@Inject
	public MenuActivity(IMenu view, PlaceController placeController,
			AdminDataServiceAsync rpcService,
			Provider<HomePlace> homePlaceProvider,
			Provider<ProductPlace> productPlaceProvider,
			Provider<AddProductGroupPlace> addProductGroupPlaceProvider,
			Provider<ProductGroupPlace> productGroupPlaceProvider,
			Provider<ImagesViewPlace> imagesPlaceProvider,
			Provider<SliderViewPlace> sliderPlaceProvider,
			Provider<ToolsViewPlace> toolsPlaceProvider,
			Provider<UsersPlace> usersPlaceProvider,
			Provider<EmployeesPlace> employeesPlaceProvider,
			AdminCache cache) {
	
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
		this.homePlaceProvider=homePlaceProvider;
		this.productPlaceProvider=productPlaceProvider;
		this.addProductGroupPlaceProvider=addProductGroupPlaceProvider;
		this.productGroupPlaceProvider=productGroupPlaceProvider;
		this.imagesPlaceProvider=imagesPlaceProvider;
		this.sliderPlaceProvider=sliderPlaceProvider;
		this.toolsPlaceProvider=toolsPlaceProvider;
		this.usersPlaceProvider=usersPlaceProvider;
		this.employeesPlaceProvider = employeesPlaceProvider;
		this.cache=cache;
	}
	public void init(){
		user = cache.getUser();
		
		List<Integer> menus = new ArrayList<Integer>();
		if(user.isAdmin()){
			createFullMenu();
		}else{//if user is other than creator of the site
			
			//see which areas the user havas la privilego:
			int[] accessAreas=user.getAccessSections();
			//the index of the items in the array is used to
			//get the menu name from the storage
			for(int i=0;i<accessAreas.length;i++){
				if(accessAreas[i]==1) menus.add(i);
			}
			createMenu(menus);
		}
		
	}
	public void createFullMenu(){
		menuLabels = new ArrayList<String>();
		menuCodes= new ArrayList<Integer>();
		Map<Integer,String> mapMenus=UserSettings.getMapMenu();
		Set<Integer> keySet = mapMenus.keySet();
		Iterator<Integer> iterator = keySet.iterator();
		while(iterator.hasNext()){
			int key=iterator.next();
			menuCodes.add(key);
			menuLabels.add(mapMenus.get(key));
		}
		//showing the interface on the view:
		view.createMenu(menuLabels);
	}
	public void createMenu(List<Integer> menuIds){
		Map<Integer,String> mapMenus=UserSettings.getMapMenu();
		menuLabels = new ArrayList<String>();
		menuCodes= new ArrayList<Integer>();
		for(int idMenu:menuIds){
			//getting the menu names
			String menu=mapMenus.get(idMenu);
			if(menu!=null){
				menuLabels.add(menu);
				menuCodes.add(idMenu);
			}
		}
		view.createMenu(menuLabels);
	}
	
	public List<String> getMenus() {
		return menuLabels;
	}
	
	public List<Integer> getMenuCodes() {
		return menuCodes;
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}

	@Override
	public void gotoPlace(int id) {
		//System.out.println(id);
		//logger.log(Level.INFO, "id:"+id);
		int menuCode = -1;
		if(id<menuCodes.size()) menuCode = menuCodes.get(id);
		switch(menuCode){
		case UserSettings.HOME://home
			HomePlace place0 = homePlaceProvider.get();
			placeController.goTo(place0);
			
			break;
		case UserSettings.PRODUCTS://product
			ProductPlace place1 = productPlaceProvider.get();
			placeController.goTo(place1);
			break;
		case UserSettings.PRODUCT_GROUPS://product groups
			ProductGroupPlace place2 = productGroupPlaceProvider.get();
			placeController.goTo(place2);
			break;
		case UserSettings.IMAGES://images
			ImagesViewPlace place3 = imagesPlaceProvider.get();
			placeController.goTo(place3);
			break;
		case UserSettings.TOOLS://tools
			ToolsViewPlace place4= toolsPlaceProvider.get();
			placeController.goTo(place4);
			break;
		case UserSettings.SLIDERS://the view to show the content slider images:
			SliderViewPlace place5 = sliderPlaceProvider.get();
			placeController.goTo(place5);
			break;
		case UserSettings.USERS://the view to show the users:
			
			UsersPlace usersPlace = usersPlaceProvider.get();
			placeController.goTo(usersPlace );
			break;
		case UserSettings.SETTINGS://the view to show the users:
			

			break;	
		case UserSettings.EMPLOYEES://employees view:
			//logger.log(Level.INFO, "came here:");
			EmployeesPlace employeesPlace = employeesPlaceProvider.get();
			placeController.goTo(employeesPlace);
			break;
			
		}
		
	}
	public UserDTO getUser() {
		return user;
	}
	

}
