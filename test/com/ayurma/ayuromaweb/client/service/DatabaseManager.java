package com.ayurma.ayuromaweb.client.service;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.ayurma.ayuromaweb.client.admin.util.UserSettings;
import com.ayurma.ayuromaweb.server.dao.AdminDAO;
import com.ayurma.ayuromaweb.server.dao.DAO;
import com.ayurma.ayuromaweb.server.dao.PMF;
import com.ayurma.ayuromaweb.server.model.UserInfo;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.ayurma.ayuromaweb.shared.ProductGroupData;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Injector;

public class DatabaseManager {
	/*
	 * authDomain: 'gmail.com'
	 */
	public void createUser(String email,String authDomain){
		User user=new User(email,authDomain);
	}
	public List<UserDTO> getUsers(){
		List<UserDTO> list = AdminDAO.get().getUsers("");
		for(UserDTO item:list){
			
			System.out.format("----\nEmail:%s\nName:%s\nDate:%s\nAdminLevel:%d\n----\n", item.getEmail(),item.getName(),item.getDateCreation(),
					item.getAdminLevel());
		}
		return list;
	}
	public UserDTO getUser(String email){
		UserDTO dto = AdminDAO.get().getUserInfo(email);
		return dto;
		
	}
	public UserDTO getAdmin(){
		String email="ayuroma-kanpur@gmail.com";
		String name="Ayuroma Centre";
		boolean isAdmin=true;
		int adminLevel=UserSettings.ADMIN_LEVEL_ROOT;
		boolean isAcessAreaOdd=true;
		UserDTO userDto = new UserDTO();
		userDto.setEmail(email);
		userDto.setName(name);
		userDto.setAdmin(isAdmin);
		userDto.setAdminLevel(adminLevel);
		//setting the user's access areas:
		//get the menu template interger array:
		int[] accessData = UserSettings.getMenuArray();
		//change some data of the above:
	
		for(int i=0;i<accessData.length;i++){
			if(i%2==0){
				if(isAcessAreaOdd) accessData[i]=0;
				else accessData[i]=1;
			}
			else{
				if(isAcessAreaOdd) accessData[i]=1;
				else accessData[i]=0;
			}
		}

		
		userDto.setAccessSections(accessData);
		return userDto;
	}
	public static final int ACCESS_TYPE1=0;
	public static final int ACCESS_TYPE2=1;
	public static final int ACCESS_TYPE3=2;
	public static final int ACCESS_TYPE4=3;
	public static final int ACCESS_TYPE5=4;
	private int[] getAccessData(int type){
		int[] accessData = UserSettings.getMenuArray();
		int[] type1={1,1,1,0,0,0,1,1,1,0,1,0};
		int[] type2={0,1,0,0,0,0,1,1,1,0,0,0};
		int[] type3={1,1,0,0,1,1,1,1,1,0,0,0};
		switch(type){
		case 0://even:
			for(int i=0;i<accessData.length;i++){
				if(i%2==0)
					accessData[i]=1;
				else
					accessData[i]=0;
			}
			break;
		case 1://odd
			for(int i=0;i<accessData.length;i++){
				if(i%2==0) accessData[i]=0;
				else accessData[i]=1;
			}

			break;
		case 2://type1
			for(int i=0;i<accessData.length;i++){
				accessData[i]=type1[i];
				
			}
		
			break;
		case 3:
			for(int i=0;i<accessData.length;i++){
				accessData[i]=type2[i];
			}
			
			break;
		case 4:
			for(int i=0;i<accessData.length;i++){
				accessData[i]=type3[i];
			}
			
			break;
			
		}
		return accessData;
	}
	public String  saveUser(String email,String name,
			boolean isAdmin,
			int adminLevel,
			int accessDataType){
		UserDTO userDto = new UserDTO();
		userDto.setEmail(email);
		userDto.setName(name);
		userDto.setAdmin(isAdmin);
		userDto.setAdminLevel(adminLevel);
		//setting the user's access areas:
		//get the menu template interger array:


		
		userDto.setAccessSections(getAccessData(accessDataType));
		String output="";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		
			// it means the user is logged in:
			// get the email address and create its data
			try{
				UserInfo entity = new UserInfo(userDto.getEmail());
				entity.setName(userDto.getName());
				//access areas:
				entity.setAccessSections(userDto.getAccessSections());
				//user level:
				entity.setAdminLevel(userDto.getAdminLevel());
				//set the name (It is a primary key)
				//entity.
				pm.makePersistent(entity);
				output+="User name added.";

				
			}catch(IllegalArgumentException e){
				output="Error: Name can not be null or Empty.";
			}catch(Exception e){
				output="Error in adding the user name."+e.toString();
				
			}finally{
				pm.close();
			}
			//System.out.println("AdminDAO:: save User email: "+user.getEmail());


		return output;
	}
	public void createProducts(){
		for(int i=0;i<50;i++){
			String name="";
			if(i>9)
				name="Chemical_"+i;
			else name="Chemical_0"+i;
			ChemicalData product = new ChemicalData(name,"Chemical "+i+" description...");
			AdminDAO.get().addChemicalCompound(product);			
		}

	}
	public ChemicalData getProductByKey(Long key) {
		
		return DAO.get().getProduct(key);
		
	}
	public String updateProductImage(Long productKey, String imgKey) {
		return AdminDAO.get().updateProductImage(productKey, imgKey);
		
	}
	public void createProductGroups(){
		for(int i=0;i<5;i++){
			ProductGroupData data = new ProductGroupData();
			data.setName("ProductGroup_"+i);
			data.setDescription("Description of the group ProductGroup_"+i);
			AdminDAO.get().addProductGroup(data);
		}
	}
	public void addProductsToGroup(){
		//load the products info:
		List<ProductBasicInfo> list=DAO.get().getProductInfoListByPage(0, 20);
		//loading a product group:
		ProductGroupItemsData[] grp=DAO.get().getProductGroupsItemsByPage(0, 10);
		//getting the key of the first:
		Long keyTargetGroup=grp[0].getKey();
		//get some products:
		int iPage = 0;
		int itemsPerPage = 10;
		ChemicalData[] chemicals = DAO.get().getProductsListByPage(iPage, itemsPerPage);		
		// prepare the products to be added to the group:
		Long[] keysProducts;
		Long[] keysDetails;
		String[] namesProducts;
		int i = 0;
		int len = chemicals.length;
		if(len > 5){
			keysProducts=new Long[5];
			keysDetails=new Long[5];
			namesProducts=new String[5];
			for(i=0; i<5; i++){
				keysProducts[i] = chemicals[i].getKey();
				keysDetails[i] = chemicals[i].getDetailsId();
				namesProducts[i] = chemicals[i].getName();
			}
			AdminDAO.get().setProductToGroup(keyTargetGroup, keysProducts, namesProducts, keysDetails);
		}

		
		
	}
	public ProductGroupItemsData[] getProductGroupsItemsByPage(int iPage, int itemsPerPage){
		return DAO.get().getProductGroupsItemsByPage(iPage, itemsPerPage);
		
	}
	public void printProductGroupsItems(List<ProductGroupItemsData> list){
		System.out.println("Product Groups Items List:");
		if(list.isEmpty()){
			System.out.println("    No item in the list.");
			return;
				
		}
		int i=0;
		for(ProductGroupItemsData item:list){
			System.out.println("    ["+i+"] "+item.getName()+", key="+item.getKey());
		}
		
	}
	public void printProductInfos(List<ProductBasicInfo> list){
		System.out.println("Displaying ProductBasicInfo list:");
		if(list==null){
			System.out.println("\tgot a null list");
			return;
		}
		if(list.isEmpty()){
			System.out.println("\tgot an empty list");
		}
		for(ProductBasicInfo item:list){
			System.out.println("\tName="+item.getName());
		}
		
	}
	/**
	 * this function simulates the server:
	 */
	public List<ProductBasicInfo> getProductBasicInfoByPage(int iPage,int nItemsPerPage,int nTotalProducts){
		List<ProductBasicInfo> list = new ArrayList<ProductBasicInfo>();
		for(int i=iPage*nItemsPerPage;i<iPage*nItemsPerPage+nItemsPerPage;i++){
			if(i>=nTotalProducts) break;
			ProductBasicInfo info = new ProductBasicInfo();
			//put the name:
			info.setName("Product_"+i);
			//put the key:
			info.setKey(Long.valueOf(i));
			list.add(info);
		}
		return list;
	}
	public void printProductBasicInfo(ProductBasicInfo item){
		if(item==null){
			System.out.println("\tGot a Null ProductBasicInfo.");
		}else{
			System.out.println("\tName="+item.getName());
		}
	}
}
