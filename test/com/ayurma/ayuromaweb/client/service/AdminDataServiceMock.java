package com.ayurma.ayuromaweb.client.service;

import java.util.List;

import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.server.dao.AdminDAO;
import com.ayurma.ayuromaweb.server.dao.DAO;
import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.ayurma.ayuromaweb.shared.MobileDTO;
import com.ayurma.ayuromaweb.shared.MobileUpdateDTO;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.ayurma.ayuromaweb.shared.ProductGroupData;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AdminDataServiceMock implements AdminDataServiceAsync{

	@Override
	public void addProduct(ChemicalData product, AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(Long key, AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProductGroup(ProductGroupData data,
			AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getProductGroupsItemsByPage(Integer iPage, int nItemsPerPage,
			AsyncCallback<ProductGroupItemsData[]> callback) {
		System.out.println("AdminDataServiceMock getProductGroupsItemsByPage()...");
		callback.onSuccess(DAO.get().getProductGroupsItemsByPage(iPage, nItemsPerPage));
		
	}

	@Override
	public void getProductGroup(Long key,
			AsyncCallback<ProductGroupData> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductGroup(Long key, ProductGroupData data, int id,
			AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getProductInfoListByPage(int iPageIn, int itemsPerPage,
			AsyncCallback<List<ProductBasicInfo>> callback) {
		System.out.println("AdminDataServiceMock getProductInfoListByPage()...");
		callback.onSuccess(DAO.get().getProductInfoListByPage(iPageIn, itemsPerPage));
		
	}

	@Override
	public void setProductToGroup(Long keyGroup, Long[] productKeys,
			String[] nameProducts, Long[] detailsKeys,
			AsyncCallback<ProductGroupItemsData> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getImageUploadUrl(String base, AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getImagesDataByPage(int nPage, int itemsPerPage,
			AsyncCallback<List<ImageDataDTO>> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSliderImagesByPage(int nPage, int itemsPerPage,
			AsyncCallback<List<SliderImageDTO>> callback) {
		callback.onSuccess(AdminDAO.get().getSliderImagesByPage(nPage, itemsPerPage));
		
	}

	@Override
	public void deleteImage(Long key, int type, AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getProduct(Long key, AsyncCallback<ChemicalData> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductData(Long productKey,
			ChemicalData updataedproduct, AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductImage(Long productKey, String imgKey,
			AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getProductDetails(Long key,
			AsyncCallback<ProductDetails> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductDetailsPart(Long key, List<String> strData,
			int code, AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductUses(Long key, String uses,
			AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSliderFilm(SliderFilmDTO film,
			AsyncCallback<String> callback) {
		String serverResponse = AdminDAO.get().addSliderFilm(film);
		callback.onSuccess(serverResponse);
		
	}

	@Override
	public void getSliderFilms(int iPage, int itemsPerPage,
			AsyncCallback<List<SliderFilmDTO>> callback) {
		List<SliderFilmDTO> films = AdminDAO.get().getSliderFilms(iPage, itemsPerPage);
		callback.onSuccess(films);
		
	}

	@Override
	public void deleteSliderFilm(Long key,
			AsyncCallback<List<SliderFilmDTO>> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSliderFilm(Long key, AsyncCallback<SliderFilmDTO> callback) {
		SliderFilmDTO film = AdminDAO.get().getSliderFilm(key);
		callback.onSuccess(film);
		
	}

	@Override
	public void displaySliderFilm(Long key, AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSliderFilm(SliderFilmDTO film, Long key,
			AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAppData(AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveUser(UserDTO user, AsyncCallback<String> callback) {
		System.out.println("Admin ServletMock::saveUser() called...");
		//detect the honey pot thing for spam protection:
		if(!user.textHoneyPot.equals("")) callback.onSuccess("Are you human??");
				//ne administra privilegio estas bezonata.
				
				
		callback.onSuccess(AdminDAO.get().saveUser(user));
		
	}

	@Override
	public void getUsers(String adminEmail, AsyncCallback<List<UserDTO>> callback) {
		System.out.println("Admin Servlet::getUsers() called...");
		callback.onSuccess(AdminDAO.get().getUsers(adminEmail));
		
	}

	@Override
	public void updateUser(UserDTO user, AsyncCallback<String> callback) {
		System.out.println("Admin Servlet::updateUser() called...and got this access areas");
		int[] accessAreas=user.getAccessSections();
		System.out.print("[");
		for(int item:accessAreas){
			System.out.format("%d, ",item);
		}
		System.out.print("]");
		System.out.println("... and admin level:"+user.getAdminLevel());
		callback.onSuccess(AdminDAO.get().updateUser(user));
		
	}

	@Override
	public void deleteUser(String userEmail, String doerEmail,
			AsyncCallback<List<UserDTO>> usrs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveEmployee(EmployeeDTO employeeDTO,
			AsyncCallback<String> callback) {
		
		callback.onSuccess(AdminDAO.get().saveEmployee(employeeDTO));
		
	}

	@Override
	public void getEmployees(AsyncCallback<List<EmployeeDTO>> callback) {
		callback.onSuccess(AdminDAO.get().getEmployees());
		
	}

	@Override
	public void getEmployeeByKey(Long key,
			AsyncCallback<EmployeeDTO> callback) {
		callback.onSuccess(AdminDAO.get().getEmployeeByKey(key));
		
	}

	@Override
	public void updateEmployee(Long key, EmployeeDTO dto, int code,
			AsyncCallback<String> callback) {
		
		callback.onSuccess(AdminDAO.get().updateEmployee(key, dto, code));
	}

	@Override
	public void deleteEmployee(EmployeeDTO dto, AsyncCallback<String> callback) {
		callback.onSuccess(AdminDAO.get().deleteEmployee(dto));
		
	}

	@Override
	public void getEmployeeMobiles(Long key,
			AsyncCallback<List<MobileDTO>> callback) {
		
		callback.onSuccess(AdminDAO.get().getEmployeeMobilesByKey(key));
		
	}

	@Override
	public void addEmployeeMobile(Long key, String mobile,
			AsyncCallback<MobileUpdateDTO> callback) {
		callback.onSuccess(AdminDAO.get().addEmployeeMobile(key, mobile));
		
	}

	@Override
	public void updateMobile(MobileDTO dto, int code,
			AsyncCallback<String> callback) {
		callback.onSuccess(AdminDAO.get().updateMobile(dto, code));
		

		
	}

	@Override
	public void updateEmployeeAddress(AddressDTO dto, int code,
			AsyncCallback<String> callback) {
		callback.onSuccess(AdminDAO.get().updateEmployeeAddress(dto, code));
		
	}

}
