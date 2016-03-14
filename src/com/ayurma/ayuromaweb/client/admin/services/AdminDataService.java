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
import com.ayurma.ayuromaweb.shared.ProductGroupData;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("admindataservice")
public interface AdminDataService  extends RemoteService {
	String addProduct(ChemicalData product);
	String deleteProduct(Long key);
	String addProductGroup(ProductGroupData data);
	ProductGroupItemsData[] getProductGroupsItemsByPage(Integer iPage,int nItemsPerPage);
	ProductGroupData getProductGroup(Long key);
	String updateProductGroup(Long key,ProductGroupData data,int id);
	List<ProductBasicInfo> getProductInfoListByPage(int iPageIn,int itemsPerPage);
	ProductGroupItemsData setProductToGroup(Long keyGroup,Long[] productKeys,String[] nameProducts,Long[] detailsKeys);
	String getImageUploadUrl(String base);
	List<ImageDataDTO> getImagesDataByPage(int nPage,int itemsPerPage);
	List<SliderImageDTO> getSliderImagesByPage(int nPage,int itemsPerPage);
	String deleteImage(Long key,int type);
	ChemicalData getProduct(Long key);
	String updateProductData(Long productKey,ChemicalData updataedproduct);
	String updateProductImage(Long productKey,String imgKey);
	ProductDetails getProductDetails(Long key);
	String updateProductDetailsPart(Long key,List<String> strData,int code);
	String updateProductUses(Long key,String uses);
	String saveSliderFilm(SliderFilmDTO film);
	List<SliderFilmDTO> getSliderFilms(int iPage,int itemsPerPage);
	List<SliderFilmDTO> deleteSliderFilm(Long key);
	SliderFilmDTO getSliderFilm(Long key);
	String displaySliderFilm(Long key);
	String updateSliderFilm(SliderFilmDTO film,Long key);
	String createAppData();
	String saveUser(UserDTO user);
	String updateUser(UserDTO user);
	List<UserDTO> getUsers(String adminEmail);
	List<UserDTO> deleteUser(String userEmail,String doerEmail);
	String saveEmployee(EmployeeDTO employee);
	List<EmployeeDTO> getEmployees();
	EmployeeDTO getEmployeeByKey(Long key);
	String updateEmployee(Long key, EmployeeDTO dto, int code);
	String updateEmployeeAddress(AddressDTO dto, int code);
	String deleteEmployee(EmployeeDTO dto);
	List<MobileDTO> getEmployeeMobiles(Long key);
	MobileUpdateDTO addEmployeeMobile(Long key, String mobile);
	String updateMobile(MobileDTO dto, int code);
}
