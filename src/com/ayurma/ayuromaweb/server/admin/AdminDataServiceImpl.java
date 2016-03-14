package com.ayurma.ayuromaweb.server.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.admin.services.AdminDataService;
import com.ayurma.ayuromaweb.client.admin.util.UserSettings;
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
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class AdminDataServiceImpl  extends RemoteServiceServlet  implements AdminDataService {
	private static final Logger log = Logger.getLogger(AdminDataServiceImpl.class.getName());
	//private boolean isUserAdmin=false;
	private boolean hasMinimumPrivilego(String email,int minLevel){
		boolean hasMinPri=false;
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();	
	    if (user != null) {//--some user is logged on
	    	if(userService.isUserAdmin()){
	    		hasMinPri=true;
	    	}else if(AdminDAO.get().hasDoerPrivilego(email, minLevel)){
	    		hasMinPri=true;
			}else hasMinPri=false;
	    	
	    }else{//--user is not logged on>
	    	hasMinPri=false;
	    }
	    return hasMinPri;
	}
	private boolean isUserAllowed(){
		boolean isUserAdmin=false;
		 UserService userService = UserServiceFactory.getUserService();
		 User user = userService.getCurrentUser();	
		    if (user != null) {//--some user is logged on
		    	if(userService.isUserAdmin()){
		    		isUserAdmin=true;
		    	}else if(AdminDAO.get().hasUserPrivilego(user.getEmail())){
		    		isUserAdmin=true;
				}else isUserAdmin=false;
		    	
		    }else{//--user is not logged on>
		    	isUserAdmin=false;
		    }
		    return isUserAdmin;
	 }
	@Override
	public String addProduct(ChemicalData product) {
		if(isUserAllowed()) return AdminDAO.get().addChemicalCompound(product);
		else return "Service Access denied.";
	}

	@Override
	public String addProductGroup(ProductGroupData data) {
		if(isUserAllowed()) 
		return AdminDAO.get().addProductGroup(data);
		else return "Service Access denied.";
	}

	@Override
	public ProductGroupItemsData[] getProductGroupsItemsByPage(Integer iPage,
			int nItemsPerPage) {
		
		return DAO.get().getProductGroupsItemsByPage(iPage, nItemsPerPage);
	}

	@Override
	public ProductGroupData getProductGroup(Long key) {
		
		return DAO.get().getProductGroup(key);
	}

	@Override
	public String updateProductGroup(Long key, ProductGroupData data, int id) {
		if(isUserAllowed()) 
		return AdminDAO.get().updateProductGroup(key, data, id);
		else return "Service Access denied.";
	}

	@Override
	public List<ProductBasicInfo> getProductInfoListByPage(int iPageIn,
			int itemsPerPage) {
		
		return DAO.get().getProductInfoListByPage(iPageIn, itemsPerPage);
	}

	@Override
	public ProductGroupItemsData setProductToGroup(Long keyGroup,
			Long[] productKeys, String[] nameProducts, Long[] detailsKeys) {
		if(isUserAllowed()) 
		return AdminDAO.get().setProductToGroup(keyGroup, productKeys, nameProducts, detailsKeys);
		else return null;
	}

	@Override
	public String getImageUploadUrl(String base) {
		 BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		 //131012 1756 hrs it was like this
		 ///String url= blobstoreService.createUploadUrl("/ayuromaweb/uploadImage");
		 //but changed to:
		 String url= blobstoreService.createUploadUrl(base);
		 if(isUserAllowed()) return url;
		 else return "Service Access denied.";
	}

	@Override
	public List<ImageDataDTO> getImagesDataByPage(int nPage, int itemsPerPage) {
		
		return AdminDAO.get().getImagesDataByPage(nPage, itemsPerPage);
	}

	@Override
	public String deleteImage(Long key,int type) {
		if(isUserAllowed()) 
		return AdminDAO.get().deleteImage(key,type);
		else return "Service Access denied.";
	}

	@Override
	public ChemicalData getProduct(Long key) {
		
		return DAO.get().getProduct(key);
	}

	@Override
	public String updateProductData(Long productKey,
			ChemicalData updataedproduct) {
		if(isUserAllowed()) 
		return AdminDAO.get().updateProductData(productKey, updataedproduct);
		else return "Service Access denied.";
	}

	@Override
	public String updateProductImage(Long productKey, String imgKey) {
		if(isUserAllowed()){ 
			//System.out.println("value:"+isUserAllowed());
			return AdminDAO.get().updateProductImage(productKey, imgKey);
		}else return "Service Access denied.";
	}

	@Override
	public ProductDetails getProductDetails(Long key) {
		
		return DAO.get().getProductDetailsByKey(key);
	}

	@Override
	public String updateProductDetailsPart(Long key, List<String> strData,
			int code) {
		if(isUserAllowed()) 
		return AdminDAO.get().updateProductDetailsPart(key, strData, code);
		else return "Service Access denied.";
	}

	@Override
	public String updateProductUses(Long key, String uses) {
		if(isUserAllowed()) 
		return AdminDAO.get().updateProductUses(key, uses);
		else return "Service Access denied.";
	}

	@Override
	public List<SliderImageDTO> getSliderImagesByPage(int nPage,
			int itemsPerPage) {
		
		return AdminDAO.get().getSliderImagesByPage(nPage, itemsPerPage);
	}

	@Override
	public String saveSliderFilm(SliderFilmDTO film) {
		if(isUserAllowed()) 
		return AdminDAO.get().addSliderFilm(film);
		else return "Service Access denied.";
	}

	@Override
	public List<SliderFilmDTO> getSliderFilms(int iPage, int itemsPerPage) {
		
		return AdminDAO.get().getSliderFilms(iPage, itemsPerPage);
	}

	@Override
	public List<SliderFilmDTO> deleteSliderFilm(Long key) {
		if(isUserAllowed()) 
		return AdminDAO.get().deleteSliderFilm(key);
		else return null;
	}

	@Override
	public String displaySliderFilm(Long key) {
		if(isUserAllowed()) 
		return AdminDAO.get().displaySliderFilm(key);
		else return "Service Access denied.";
	}

	@Override
	public String createAppData() {
		if(isUserAllowed()) 
		return AdminDAO.get().createAppData();
		else return "Service Access denied.";
	}

	@Override
	public SliderFilmDTO getSliderFilm(Long key) {
		
		return AdminDAO.get().getSliderFilm(key);
	}

	@Override
	public String updateSliderFilm(SliderFilmDTO film, Long key) {
		//System.out.println("came on the servlet...");
		/*
		try {
			wait(5000);
			System.out.println("wait on servlet...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		if(isUserAllowed()) 
		return AdminDAO.get().updateSliderFilm(film, key);
		else return "Service Access denied.";
	}

	@Override
	public String deleteProduct(Long key) {
		if(isUserAllowed()) 
		return AdminDAO.get().deleteProduct(key);
		else return "Service Access denied.";
	}
	@Override
	public String saveUser(UserDTO user) {
		//detect the honey pot thing for spam protection:
		if(!user.textHoneyPot.equals("")) return "okkkk!!!!";
		//ne administra privilegio estas bezonata.
		
		return AdminDAO.get().saveUser(user);
		/*
		if(isUserAllowed()) 
			return null;
		else return "Ne administra privilegio.";
		*/
	}
	@Override
	public List<UserDTO> getUsers(String adminEmail) {
		
		return AdminDAO.get().getUsers(adminEmail);
	}
	@Override
	public String updateUser(UserDTO userDto) {
		 UserService userService = UserServiceFactory.getUserService();
		 User user = userService.getCurrentUser();	
		 if (user != null) {
			 if(userService.isUserAdmin()){
				return AdminDAO.get().updateUser(userDto); 
			 }else if(AdminDAO.get().hasUserPrivilego(user.getEmail())){
				 return AdminDAO.get().updateUser(userDto);  
			 }
		 }
		return "User not admin";
	}
	@Override
	public List<UserDTO> deleteUser(String userEmail,String doerEmail) {
		if(hasMinimumPrivilego(doerEmail,UserSettings.ADMIN_LEVEL_ONE)){
			return AdminDAO.get().deleteUser(userEmail, doerEmail);
		}else return AdminDAO.get().getUsers(doerEmail); 
		
	}
	@Override
	public String saveEmployee(EmployeeDTO employee) {
		if(isUserAllowed()) 
			return AdminDAO.get().saveEmployee(employee);
		else return "Service Access denied.";
	}
	@Override
	public List<EmployeeDTO> getEmployees() {
		if(isUserAllowed()) 
			return AdminDAO.get().getEmployees();
		else return new ArrayList<EmployeeDTO>();//return empty list
	}
	@Override
	public EmployeeDTO getEmployeeByKey(Long key) {
		if(isUserAllowed()) 
			return AdminDAO.get().getEmployeeByKey(key);
		else return null;//return null
	}
	@Override
	public String updateEmployee(Long key, EmployeeDTO dto, int code) {
		
		if(isUserAllowed()) 
			return AdminDAO.get().updateEmployee(key, dto, code);
		else return "Service Access denied.";
	}
	@Override
	public String deleteEmployee(EmployeeDTO dto) {
		if(isUserAllowed()) 
			return AdminDAO.get().deleteEmployee(dto);
		else return "Service Access denied.";
	}
	@Override
	public List<MobileDTO> getEmployeeMobiles(Long key) {
		
		if(isUserAllowed()) 
			return AdminDAO.get().getEmployeeMobilesByKey(key);
		else return new ArrayList<MobileDTO>();//return empty list
	}
	@Override
	public MobileUpdateDTO addEmployeeMobile(Long key, String mobile) {
		if(isUserAllowed()) 
			return AdminDAO.get().addEmployeeMobile(key, mobile);
		else{
			MobileUpdateDTO dto = new MobileUpdateDTO();
			dto.setResponse("Service Access denied.");
			return dto;
		}
	}
	@Override
	public String updateMobile(MobileDTO dto, int code) {
		if(isUserAllowed()) 
			return AdminDAO.get().updateMobile(dto, code);
		else return "Service Access denied.";
	}
	@Override
	public String updateEmployeeAddress(AddressDTO dto, int code) {
		
		if(isUserAllowed()) 
			return AdminDAO.get().updateEmployeeAddress(dto, code);
		else return "Service Access denied.";
	}
	
}
