package com.ayurma.ayuromaweb.client.admin.services;

import java.util.List;

import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.ayurma.ayuromaweb.shared.MobileDTO;
import com.ayurma.ayuromaweb.shared.MobileUpdateDTO;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.ayurma.ayuromaweb.shared.ProductGroupData;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdminDataServiceAsync {
	 void addProduct(ChemicalData product,AsyncCallback<String> callback);
	 void deleteProduct(Long key,AsyncCallback<String> callback);
	 void addProductGroup(ProductGroupData data,AsyncCallback<String> callback);
	 void getProductGroupsItemsByPage(Integer iPage,int nItemsPerPage,AsyncCallback<ProductGroupItemsData[]> callback );
	 void getProductGroup(Long key,AsyncCallback<ProductGroupData> callback);
	 void updateProductGroup(Long key,ProductGroupData data,int id,AsyncCallback<String> callback);
	 void getProductInfoListByPage(int iPageIn,int itemsPerPage,AsyncCallback<List<ProductBasicInfo>> callback );
	 void setProductToGroup(Long keyGroup,Long[] productKeys,String[] nameProducts,Long[] detailsKeys,AsyncCallback<ProductGroupItemsData> callback );
	 void getImageUploadUrl(String base,AsyncCallback<String> callback);
	 void getImagesDataByPage(int nPage,int itemsPerPage,AsyncCallback<List<ImageDataDTO>> callback);
	 void getSliderImagesByPage(int nPage,int itemsPerPage,AsyncCallback<List<SliderImageDTO>> callback);
	 void deleteImage(Long key,int type,AsyncCallback<String> callback);
	 void getProduct(Long key,AsyncCallback<ChemicalData> callback);
	 void updateProductData(Long productKey,ChemicalData updataedproduct,AsyncCallback<String> callback);
	 void updateProductImage(Long productKey,String imgKey,AsyncCallback<String> callback);
	 void getProductDetails(Long key, AsyncCallback<ProductDetails> callback );
	 void updateProductDetailsPart(Long key,List<String> strData, int code,AsyncCallback<String> callback);
	 void updateProductUses(Long key,String uses,AsyncCallback<String> callback);
	 void saveSliderFilm(SliderFilmDTO film,AsyncCallback<String> callback);
	 void getSliderFilms(int iPage,int itemsPerPage,AsyncCallback<List<SliderFilmDTO>> callback);
	 void deleteSliderFilm(Long key,AsyncCallback<List<SliderFilmDTO>> callback);
	 void getSliderFilm(Long key,AsyncCallback<SliderFilmDTO> callback);
	 void displaySliderFilm(Long key,AsyncCallback<String> callback);
	 void updateSliderFilm(SliderFilmDTO film,Long key,AsyncCallback<String> callback);
	 void createAppData(AsyncCallback<String> callback);
	 void saveUser(UserDTO user,AsyncCallback<String> callback);
	 void updateUser(UserDTO user,AsyncCallback<String> callback);
	 void getUsers(String adminEmail,AsyncCallback<List<UserDTO>> usrs);
	 void deleteUser(String userEmail,String doerEmail,AsyncCallback<List<UserDTO>> usrs);
	 void saveEmployee(EmployeeDTO employee, AsyncCallback<String> callback);
	 void getEmployees(AsyncCallback<List<EmployeeDTO>> employees);
	 void getEmployeeByKey(Long key, AsyncCallback<EmployeeDTO> employeeDTO);
	 void updateEmployee(Long key, EmployeeDTO dto, int code, AsyncCallback<String> callback);
	 void updateEmployeeAddress(AddressDTO dto, int code, AsyncCallback<String> callback);
	 void deleteEmployee(EmployeeDTO dto, AsyncCallback<String> callback);
	 void getEmployeeMobiles(Long key, AsyncCallback<List<MobileDTO>> callback);
	 void addEmployeeMobile(Long key, String mobile, AsyncCallback<MobileUpdateDTO> callback);
	 void updateMobile(MobileDTO dto, int code, AsyncCallback<String> callback);
}
