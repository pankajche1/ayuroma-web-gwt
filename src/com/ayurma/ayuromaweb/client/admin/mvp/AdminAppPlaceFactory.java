package com.ayurma.ayuromaweb.client.admin.mvp;


import com.ayurma.ayuromaweb.client.admin.place.AddNewEmployeePlace;
import com.ayurma.ayuromaweb.client.admin.place.AddNewProductPlace;
import com.ayurma.ayuromaweb.client.admin.place.AddProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.place.AddRemProductFrmGrpPlace;
import com.ayurma.ayuromaweb.client.admin.place.BrowseEmployeesPlace;
import com.ayurma.ayuromaweb.client.admin.place.BrowseProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.place.BrowserProductsPlace;
import com.ayurma.ayuromaweb.client.admin.place.EditEmployeePlace;
import com.ayurma.ayuromaweb.client.admin.place.EditProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.place.EmployeePlace;
import com.ayurma.ayuromaweb.client.admin.place.EmployeesPlace;
import com.ayurma.ayuromaweb.client.admin.place.HomePlace;
import com.ayurma.ayuromaweb.client.admin.place.ImagesViewPlace;
import com.ayurma.ayuromaweb.client.admin.place.NewSliderViewPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductEditPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductPlace;
import com.ayurma.ayuromaweb.client.admin.place.ProductsDetailsEditPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderBrowserPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderEditPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderImagesPlace;
import com.ayurma.ayuromaweb.client.admin.place.SliderViewPlace;
import com.ayurma.ayuromaweb.client.admin.place.ToolsViewPlace;
import com.ayurma.ayuromaweb.client.admin.place.UploadSliderImagePlace;
import com.ayurma.ayuromaweb.client.admin.place.UsersPlace;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AdminAppPlaceFactory {
	//1 HomePlace entries:
	@Inject
	HomePlace.Tokenizer homePlaceTokenizer;
	@Inject
	Provider<HomePlace> homePlaceProvider;
	// place1
	public HomePlace.Tokenizer getHomePlaceTokenizer() {
		return homePlaceTokenizer;
	}

	public HomePlace getHomePlace() {
		return homePlaceProvider.get();
	}
	//2 Product place:

	@Inject
	ProductPlace.Tokenizer productPlaceTokenizer;
	@Inject
	Provider<ProductPlace> productPlaceProvider;
	// place1
	public ProductPlace.Tokenizer getProductPlaceTokenizer() {
		return productPlaceTokenizer;
	}

	public ProductPlace getProductPlace() {
		return productPlaceProvider.get();
	}
	//3 Add New Product Place:
	

	@Inject
	AddNewProductPlace.Tokenizer addNewProductPlaceTokenizer;
	@Inject
	Provider<AddNewProductPlace> addNewProductPlaceProvider;
	// place1
	public AddNewProductPlace.Tokenizer getAddNewProductPlaceTokenizer() {
		return addNewProductPlaceTokenizer;
	}

	public AddNewProductPlace getAddNewProductPlace() {
		return addNewProductPlaceProvider.get();
	}
	//4 Add New Product Group:
	
	@Inject
	AddProductGroupPlace.Tokenizer addProductGroupPlaceTokenizer;
	@Inject
	Provider<AddProductGroupPlace> addProductGroupPlaceProvider;
	// place1
	public AddProductGroupPlace.Tokenizer getAddProductGroupPlaceTokenizer() {
		return addProductGroupPlaceTokenizer;
	}

	public AddProductGroupPlace getAddProductGroupPlace() {
		return addProductGroupPlaceProvider.get();
	}
	//5 Product Group:
	
	@Inject
	ProductGroupPlace.Tokenizer productGroupPlaceTokenizer;
	@Inject
	Provider<ProductGroupPlace> productGroupPlaceProvider;
	// place1
	public ProductGroupPlace.Tokenizer getProductGroupPlaceTokenizer() {
		return productGroupPlaceTokenizer;
	}

	public ProductGroupPlace getProductGroupPlace() {
		return productGroupPlaceProvider.get();
	}
	//6 Browse Product Groups:
	
	
	@Inject
	BrowseProductGroupPlace.Tokenizer browseGroupPlaceTokenizer;
	@Inject
	Provider<BrowseProductGroupPlace> browseGroupPlaceProvider;
	// place1
	public BrowseProductGroupPlace.Tokenizer getBrowseGroupPlaceTokenizer() {
		return browseGroupPlaceTokenizer;
	}

	public BrowseProductGroupPlace getBrowseGroupPlace() {
		return browseGroupPlaceProvider.get();
	}
	//7 Edit product group:
	
	@Inject
	EditProductGroupPlace.Tokenizer editGroupPlaceTokenizer;
	@Inject
	Provider<EditProductGroupPlace> editGroupPlaceProvider;
	// place1
	public EditProductGroupPlace.Tokenizer getEditGroupPlaceTokenizer() {
		return editGroupPlaceTokenizer;
	}

	public EditProductGroupPlace getEditGroupPlace() {
		return editGroupPlaceProvider.get();
	}
	//8 add remove products from a group 
	
	@Inject
	AddRemProductFrmGrpPlace.Tokenizer addRemProdFrmGrpPlaceTokenizer;
	@Inject
	Provider<AddRemProductFrmGrpPlace> addRemProdFrmGrpPlaceProvider;
	// place1
	public AddRemProductFrmGrpPlace.Tokenizer getAddRemProdFrmGrpPlaceTokenizer() {
		return addRemProdFrmGrpPlaceTokenizer;
	}

	public AddRemProductFrmGrpPlace getAddRemProdFrmGrpPlace() {
		return addRemProdFrmGrpPlaceProvider.get();
	}
	//9 images view place 
	@Inject
	ImagesViewPlace.Tokenizer imagesPlaceTokenizer;
	@Inject
	Provider<ImagesViewPlace> imagesPlaceProvider;
	// place1
	public ImagesViewPlace.Tokenizer getImagesPlaceTokenizer() {
		return imagesPlaceTokenizer;
	}

	public ImagesViewPlace getImagesPlace() {
		return imagesPlaceProvider.get();
	}
	//10 product browser place: 
	@Inject
	BrowserProductsPlace.Tokenizer browserProductsPlaceTokenizer;
	@Inject
	Provider<BrowserProductsPlace> browserProductsPlaceProvider;
	// place1
	public BrowserProductsPlace.Tokenizer getBrowserProductsPlaceTokenizer() {
		return browserProductsPlaceTokenizer;
	}

	public BrowserProductsPlace getBrowserProductsPlace() {
		return browserProductsPlaceProvider.get();
	}
	//11 product edit place:
	@Inject
	ProductEditPlace.Tokenizer tokenizer11;
	@Inject
	Provider<ProductEditPlace> provider11;
	// place1
	public ProductEditPlace.Tokenizer getTokenizer11() {
		return tokenizer11;
	}

	public ProductEditPlace getProductEditPlace() {
		return provider11.get();
	}
	//12 products details edit place:
	
	@Inject
	ProductsDetailsEditPlace.Tokenizer tokenizer12;
	@Inject
	Provider<ProductsDetailsEditPlace> provider12;
	// place1
	public ProductsDetailsEditPlace.Tokenizer getTokenizer12() {
		return tokenizer12;
	}

	public ProductsDetailsEditPlace getProductDetailsEditPlace() {
		return provider12.get();
	}
	
	//13 slider place:
	@Inject
	SliderViewPlace.Tokenizer tokenizer13;
	@Inject
	Provider<SliderViewPlace> provider13;
	// place1
	public SliderViewPlace.Tokenizer getTokenizer13() {
		return tokenizer13;
	}

	public SliderViewPlace getSliderViewPlace() {
		return provider13.get();
	}
	
	//14 new slider view place:
	
	@Inject
	NewSliderViewPlace.Tokenizer tokenizer14;
	@Inject
	Provider<NewSliderViewPlace> provider14;
	// place1
	public NewSliderViewPlace.Tokenizer getTokenizer14() {
		return tokenizer14;
	}

	public NewSliderViewPlace getNewSliderViewPlace() {
		return provider14.get();
	}
	
	//15 upload slider image view place:
	
	@Inject
	UploadSliderImagePlace.Tokenizer tokenizer15;
	@Inject
	Provider<UploadSliderImagePlace> provider15;
	// place1
	public UploadSliderImagePlace.Tokenizer getTokenizer15() {
		return tokenizer15;
	}

	public UploadSliderImagePlace getUploadSliderImagePlace() {
		return provider15.get();
	}
	
	//16 Slider Images browse place:
	
	@Inject
	SliderImagesPlace.Tokenizer tokenizer16;
	@Inject
	Provider<SliderImagesPlace> provider16;
	// place1
	public SliderImagesPlace.Tokenizer getTokenizer16() {
		return tokenizer16;
	}

	public SliderImagesPlace getSliderImagesPlace() {
		return provider16.get();
	}
	//17 SliderBrowserPlace
	@Inject
	SliderBrowserPlace.Tokenizer tokenizer17;
	@Inject
	Provider<SliderBrowserPlace> provider17;
	// place1
	public SliderBrowserPlace.Tokenizer getTokenizer17() {
		return tokenizer17;
	}

	public SliderBrowserPlace getSliderBrowserPlace() {
		return provider17.get();
	}
	//18 ToolsViewPlace
	@Inject
	ToolsViewPlace.Tokenizer tokenizer18;
	@Inject
	Provider<ToolsViewPlace> provider18;
	// place1
	public ToolsViewPlace.Tokenizer getTokenizer18() {
		return tokenizer18;
	}

	public ToolsViewPlace getToolsViewPlace() {
		return provider18.get();
	}
	//19 edit slider place SliderEditPlace
	@Inject
	SliderEditPlace.Tokenizer tokenizer19;
	@Inject
	Provider<SliderEditPlace> provider19;
	// place1
	public SliderEditPlace.Tokenizer getTokenizer19() {
		return tokenizer19;
	}

	public SliderEditPlace getSliderEditPlace() {
		return provider19.get();
	}
	//20 UsersPlace
	@Inject
	UsersPlace.Tokenizer tokenizer20;
	@Inject
	Provider<UsersPlace> provider20;
	// place1
	public UsersPlace.Tokenizer getTokenizer20() {
		return tokenizer20;
	}

	public UsersPlace getUsersPlace() {
		return provider20.get();
	}
	//21 EmployeePlace
	@Inject
	EmployeePlace.Tokenizer tokenizer21;
	@Inject
	Provider<EmployeePlace> provider21;
	// place21
	public EmployeePlace.Tokenizer getTokenizer21() {
		return tokenizer21;
	}

	public EmployeePlace getEmployeePlace() {
		return provider21.get();
	}
	//22 EmployeePlace
	@Inject
	BrowseEmployeesPlace.Tokenizer tokenizer22;
	@Inject
	Provider<BrowseEmployeesPlace> provider22;
	// place21
	public BrowseEmployeesPlace.Tokenizer getTokenizer22() {
		return tokenizer22;
	}

	public BrowseEmployeesPlace getBrowseEmployeesPlace() {
		return provider22.get();
	}
	//23 EmployeesPlace
	@Inject
	EmployeesPlace.Tokenizer tokenizer23;
	@Inject
	Provider<EmployeesPlace> provider23;
	// place21
	public EmployeesPlace.Tokenizer getTokenizer23() {
		return tokenizer23;
	}

	public EmployeesPlace getEmployeesPlace() {
		return provider23.get();
	}
	//24 AddNewEmployeePlace
	@Inject
	AddNewEmployeePlace.Tokenizer tokenizer24;
	@Inject
	Provider<AddNewEmployeePlace> provider24;
	// place21
	public AddNewEmployeePlace.Tokenizer getTokenizer24() {
		return tokenizer24;
	}

	public AddNewEmployeePlace getAddNewEmployeePlace() {
		return provider24.get();
	}
	//25 EditEmployeePlace
	@Inject
	EditEmployeePlace.Tokenizer tokenizer25;
	@Inject
	Provider<EditEmployeePlace> provider25;
	// place21
	public EditEmployeePlace.Tokenizer getTokenizer25() {
		return tokenizer25;
	}

	public EditEmployeePlace getEditEmployeePlace() {
		return provider25.get();
	}
}
