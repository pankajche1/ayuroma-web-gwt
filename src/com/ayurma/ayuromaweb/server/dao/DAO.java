package com.ayurma.ayuromaweb.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ayurma.ayuromaweb.server.dao.util.PageData;
import com.ayurma.ayuromaweb.server.model.AppData;
import com.ayurma.ayuromaweb.server.model.ChemicalDetailsInfo;
import com.ayurma.ayuromaweb.server.model.ChemicalInfo;
import com.ayurma.ayuromaweb.server.model.ProductGroup;
import com.ayurma.ayuromaweb.server.model.ProductGroupItems;
import com.ayurma.ayuromaweb.server.model.SliderFilm;
import com.ayurma.ayuromaweb.server.model.SliderImage;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ProductBasicInfo;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.ayurma.ayuromaweb.shared.ProductGroupData;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderFilmItemsDTO;

public class DAO {
	private static final Logger log = Logger.getLogger(DAO.class.getName());
	private static final DAO instance = new DAO();
	public DAO(){
		
	}
	public static DAO get() {
		return instance;
	}
	@SuppressWarnings("unchecked")
	public ChemicalData[] getProductsListByPage(int iPage,int itemsPerPage){
		ChemicalData[] products=new ChemicalData[0];

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(ChemicalInfo.class);
		//Query q =pm.newQuery("SELECT c FROM ChemicalInfo c WHERE c.name == 'Tej Pat ka Patta'");
		q.setRange(0, 1000);//-- It will get the first data from the db
		q.setOrdering("name asc");
		List<ChemicalInfo> listFromDb;	
		ChemicalInfo infoFromDb;
		int nItemsPerPage=itemsPerPage;
		int nItems=0;
		int nStart=0;
		int nEnd=0;
		int nItemDatastore;
		int nItemsCurPage=0;//--number of items on the current page.
		try{
			listFromDb = (List<ChemicalInfo>) q.execute();
			 if(!listFromDb.isEmpty()){
				// log.log(Level.SEVERE, "Got product list page index:"+iPage);
				 //log.log(Level.WARNING, "Got product list page index:"+iPage);
				// log.log(Level.INFO, "Got product list page index:"+iPage);
				 nItemDatastore=listFromDb.size();
				//calculate the number of pages:
				 int nPages=nItemDatastore/nItemsPerPage;
				 int nItemsLeft=nItemDatastore%nItemsPerPage;
				 if(nItemsLeft>0) nPages++;
				 // System.out.println("DAO::getProductsListByPage() nPages="+nPages);
				 //System.out.println("DAO::getProductsListByPage() nItemsLeft="+nItemsLeft);
				 // listOut=new ChemicalData[listFromDb.size()];
				 //if desired page index does not exit:
				 if(iPage>=0&&iPage<nPages){
					 
					 //--the fist item of the current Page:
					 nStart=iPage*nItemsPerPage;
					 if(iPage>nPages-2){//-- if the page is the last page
						 nEnd=nStart+nItemDatastore-1-iPage*nItemsPerPage;
					 }else{
						 nEnd=nStart+nItemsPerPage-1;
					 }	
					 nItemsCurPage=nEnd-nStart+1;
					// System.out.println("nPages="+nPages
					//		 +", nItemDataStore="+nItemDatastore
					//		 +", iPage="+iPage+", nStart="+nStart+", nEnd="+nEnd+", nItemsCurPage="+nItemsCurPage);  					 
					 products=new ChemicalData[nItemsCurPage];
					 int i=0;
					 int iDataOut=0;
					 for(i=nStart;i<=nEnd;i++){
						 infoFromDb=listFromDb.get(i);
						 products[iDataOut]=new ChemicalData(infoFromDb.getName(),infoFromDb.getDescription());
						 products[iDataOut].setStrId(String.valueOf(infoFromDb.getKey()));
						 products[iDataOut].setKey(infoFromDb.getKey());
						 products[iDataOut].setIsInStock(infoFromDb.getIsInStock());
						 products[iDataOut].setImageUrl(infoFromDb.getImageUrl());
						 products[iDataOut].setStrIdDetailsInfo(String.valueOf(infoFromDb.getDetailInfoKey()));
						 products[iDataOut].setnPages(nPages);
						 products[iDataOut].setDescription(infoFromDb.getDescription());
						 iDataOut++;
						 
					 }

				 }else{
					 System.out.println("Page with this index does not exist, iPage="+iPage+", and nPages="+nPages);
				 }
				

			 }else{
				 System.out.println("DAO:: getProductsListByPage() list got is empty");
			 }
		}catch(Exception e){
			System.out.println("DAO::getProductsListByPage() ERROR: "+e.toString());
		}
		finally{
			q.closeAll();
			pm.close();
		}		
		
		return products;
	}
	public ChemicalData getProductByKey(Long key){
		ChemicalData productOut=null;
		//Long key=null;
		//try{
			//key = Long.valueOf(strProductId);
		//}catch(Exception ex){
			//return productOut;
		//}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			ChemicalInfo productFromDb= pm.getObjectById(ChemicalInfo.class,key);
			if(productFromDb!=null){
				productOut=new ChemicalData(productFromDb.getName(),productFromDb.getDescription());
				productOut.setIsInStock(productFromDb.getIsInStock());
				productOut.setStrId(String.valueOf(productFromDb.getKey()));
				productOut.setKey(productFromDb.getKey());
				productOut.setImageUrl(productFromDb.getImageUrl());
				productOut.setStrIdDetailsInfo(String.valueOf(productFromDb.getDetailInfoKey()));
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			productOut=null;
			
		}finally{
			pm.close();
		}
		return productOut;
	}
	@SuppressWarnings("unchecked")
	public ChemicalData getProductByName(String name){
		ChemicalData product = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(ChemicalInfo.class);	
		q.setRange(0, 1000);
		q.setOrdering("name asc");
		q.setFilter("name == '"+name+"'");
		
		try{
			List<ChemicalInfo> listFromDb = (List<ChemicalInfo>) q.execute();
			if(!listFromDb.isEmpty()){
				ChemicalInfo entity = listFromDb.get(0);
				 product=new ChemicalData(entity.getName(),entity.getDescription());
				 
				 product.setStrId(String.valueOf(entity.getKey()));
				 product.setKey(entity.getKey());
				 product.setImageUrl(entity.getImageUrl());
				 product.setStrIdDetailsInfo(String.valueOf(entity.getDetailInfoKey()));
				
				 product.setDescription(entity.getDescription());
				 product.setIsInStock(entity.getIsInStock());

			}
		}catch(Exception e){
			
		}finally{
			q.closeAll();
			pm.close();
		}
		return product;
	}
	/*
	 * gets all the app init data in form of a json object
	 * data to be taken
	 * (1) slider film data
	 * (2) product groups for the products menu item in the menu bar
	 * (3) other data
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getAppInitData(){
		//log.log(Level.SEVERE, "I am here");
		JSONObject appDataJson = new JSONObject();
		//get the app init data object:
		PersistenceManager pm = PMF.get().getPersistenceManager();
        Query q = pm.newQuery(AppData.class);
        AppData appData;
        //(1) the slider film:
        JSONObject sliderFilmJson;
        try{
       	 List<AppData>  appDataList = (List<AppData>) q.execute();
               if(!appDataList.isEmpty()){
                   
                   appData=appDataList.get(0);
                   //(1) get the product groups:
           			q = pm.newQuery(ProductGroup.class);
           			q.setRange(0, 50);
           			q.setOrdering("name asc");
           			try{
           			List<ProductGroup> listFromDb = (List<ProductGroup>) q.execute();
           			if(!listFromDb.isEmpty()){
           				JSONObject groups = new JSONObject();
           				//now the data will be put in the json object:
           				//name description key keyItems
           				JSONObject obj;
           				JSONArray arr = new JSONArray();
           				int i=0;
           				ProductGroup entity;
           				for(i=0;i<listFromDb.size();i++){
    						entity=listFromDb.get(i);
    						obj=new JSONObject();
    						String name=entity.getName();
    						obj.put("name", entity.getName());
    						obj.put("itemsKey", String.valueOf(entity.getKeyItems()));
    						int index=-1;
    						if(name.equals("All Products")){
    							index=0;
    						}else if(name.equals("Aromatherapy Oils")){
    							index=1;
    						}else if(name.equals("Aromatic Chemicals")){
    							index=2;
    						}else if(name.equals("Carrier Oils")){
    							index=3;
    						}else if(name.equals("Natural Essential Oils")){
    							index=4;
    						}
    						if(index>=0){
    							obj.put("text", getProductsGroupText(index));
    							obj.put("imageUrl", getProductGroupImageUrl(index));
    							index=-1;
    						}
    						//list.add(obj);
    						//groups.append("group", obj);
    						arr.put(obj);
    							 
    					 }
           				//putting the above array in the main data bundle:
           				appDataJson.put("productGroups", arr);
           			}
           			}catch(Exception e){
           				//handle when you can not get the product groups
           			}
                   //(2) slider film data
                    //get the slider film key from appData:
                  // System.out.println("appData key:"+appData.getKey());
                   Long sliderFilmKey = appData.getSliderFilmKey();
                  // System.out.println("slider film key:"+sliderFilmKey);
                    if(sliderFilmKey!=null){
                   	 //get the slider film from the data base:
                    	try{
                    		SliderFilm sliderFilm= pm.getObjectById(SliderFilm.class,sliderFilmKey);
                    		
                    		if(sliderFilm!=null){
                    			//we need to get two data from it:
                    			//(1) image url (2) the linked product with this image
                    			Long[] imageItems=sliderFilm.getImageInfos();
                    			Long[] productKeys=sliderFilm.getLinkedProducts();
                    			//getting the image keys from blob store:
                    			 SliderImage sliderImage;
                    			 JSONObject sliderJson=new JSONObject();
                    			 JSONObject imageItemJson;
                    			 JSONArray filmItems=new JSONArray();
                    			 int i=0;
                    			
                    			for(Long imageItemKey:imageItems){
                    				if(i>9) break;
                    				try{
                    					sliderImage = pm.getObjectById(SliderImage.class,imageItemKey);
                    					if(sliderImage!=null){
                    						//put it in the json object
                    						imageItemJson = new JSONObject();
                    						imageItemJson.put("imageUrl", sliderImage.getImageUrl());
                    						imageItemJson.put("linkedProduct",String.valueOf(productKeys[i]));
                    						//put the json item in the json array:
                    						filmItems.put(imageItemJson);
                    					}
                    				}catch(Exception e){}
                    				i++;
                    			}
                    			sliderJson.put("items", filmItems);
                    			sliderJson.put("key", String.valueOf(sliderFilmKey));//key of the slider
                    			sliderJson.put("totalItems", String.valueOf(imageItems.length));//total items in the slider film
                    			//appDataJson.put("slider", filmItems);
                    			appDataJson.put("slider", sliderJson);
                    		}
                    	}catch(Exception e){
                    		
                    	}
                       
                    }
                  //(3) get the company's info (address etc.)
                    String addressHead="Kanpur Office";
                    String addressLine1="116/317, Adarshnagar";
                    String addressLine2="Rawatpur gaon";
                    String city="Kanpur";
                    String pin="208019";
                    String country="India";
                    //phones:
                    //String[] mobiles={"+91 9415050346","+91 9651712468","+91 9335662851"};
                    String[] mobiles={"+91 9415050346", "+91 9335662851"};
                    String[] telephones={"(0512)2500206"};
                    //emails:
                    String[] emails={"mail@ayuromacentre.com", "ayuroma07@gmail.com"};
                    //creating the json object:
                    JSONObject companyInfo = new JSONObject();
                    companyInfo.put("addressHead", addressHead);
                    JSONArray address=new JSONArray();
                    JSONObject jsAddress=new JSONObject();
                    jsAddress.put("line", addressLine1);
                    address.put(jsAddress);
                    jsAddress=new JSONObject();
                    jsAddress.put("line", addressLine2);
                   
                    address.put(jsAddress);
                    companyInfo.put("address", address);
                    companyInfo.put("city", city);
                    companyInfo.put("pin", pin);
                    companyInfo.put("country", country);
                    JSONArray mobJsonArr=new JSONArray();
                    JSONObject mobile;
                    for(String strMobile:mobiles){
                    	mobile=new JSONObject();
                    	mobile.put("number", strMobile);
                    	mobJsonArr.put(mobile);
                    }
                    companyInfo.put("mobiles", mobJsonArr);
                    JSONArray telJsonArr=new JSONArray();
                    JSONObject jsTel;
                    for(String tel:telephones){
                    	jsTel=new JSONObject();
                    	jsTel.put("number", tel);
                    	telJsonArr.put(jsTel);
                    }
                    companyInfo.put("telephones", telJsonArr);
                    JSONArray emailsJsonArr=new JSONArray();
                    JSONObject jsEmail=new JSONObject();
                    for(String email:emails){
                    	jsEmail=new JSONObject();
                    	jsEmail.put("address", email);
                    	emailsJsonArr.put(jsEmail);
                    }
                    companyInfo.put("emails", emailsJsonArr);
                    //putting the json data bundle:
                    appDataJson.put("companyInfo", companyInfo);
                    //obj.put("name", appData.getName());
                   // obj.put("itemsKey", String.valueOf(entity.getKeyItems()));
                   // filmImages.put(obj);
               }//list is not empty     
           }catch(Exception e){
               //in case of exception make some fall down stretagy
           }
           finally{
               q.closeAll();
               pm.close();
           } 
        return appDataJson;
	}
	private String getProductsGroupText(int index){
		String text="";
		String[] texts={"All products text...",
				"Aromatherapy is a type of alternative medicine that uses essential oils and other aromatic plant compounds which are aimed at improving a person's health or mood.",
				"Our Aromatic Chemicals product range includes various natural aromatic oils obtained from flowers and plant parts.",
				"Carrier oils are used for base purposes. Visit this section to have more knowledge about the carrier oils. Ayuroma Centre offers a wide range of carrier oils for example Neem oil etc.",
				"The essential oils offered by Ayuroma Centre are 100% pure steam distilled plant oils with an unsurpassable fragrance, exceptional depth, magnificent keynote, and are free of carriers, diluents and other inputs",
				
				
				};
		if(index<texts.length){
			text=texts[index];
		}
		return text;
	}
	private String getProductGroupImageUrl(int index){
		String url="";
		String[] urls={"no image for all products",
				"images/productGroups/ao.jpg",
				"images/productGroups/ac.jpg",
				
				"images/productGroups/co.jpg",
				"images/productGroups/neo.jpg"};
		if(index<urls.length){
			url=urls[index];
		}		
		
		return url;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getProductGroupListJson(int iPage,int itemsPerPage){
		JSONObject appInitData = new JSONObject();
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONArray arr = new JSONArray();
		JSONObject groups = new JSONObject();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(ProductGroup.class);
		q.setRange(0, 50);
		q.setOrdering("name asc");
		List<ProductGroup> listFromDb;	
		ProductGroup entity;
		int iStart=0;
		int iEnd=0;
		int nItemDatastore;
		int nItemsCurPage=0;
		int nItemsPerPage=itemsPerPage;
		int nPages;
	
		try{
			listFromDb = (List<ProductGroup>) q.execute();
			if(!listFromDb.isEmpty()){
				 nItemDatastore=listFromDb.size();
				 PageData pageData=new PageData(iPage,nItemDatastore,nItemsPerPage);
				 iStart=pageData.getiStart();
				 iEnd=pageData.getiEnd();
				 nItemsCurPage=pageData.getnItemsCurPage();
				 nPages=pageData.getnPages();
				 //now the data will be put in the json object:
				 //name description key keyItems
				 JSONObject obj;
				 if(nItemsCurPage>0){
					 int i=0;
					 int iDataOut=0;
					 for(i=iStart;i<=iEnd;i++){
						entity=listFromDb.get(i);
						obj=new JSONObject();
						obj.put("name", entity.getName());
						
						obj.put("itemsKey", String.valueOf(entity.getKeyItems()));
						//list.add(obj);
						//groups.append("group", obj);
						arr.put(obj);
							 
					 }
				 }
				 appInitData.put("productGroups", arr);
			}//list is not empty	 
		}catch(Exception e){
			
		}
		finally{
			q.closeAll();
			pm.close();
		}	
		///return list;
		//return groups;
		return appInitData;
	}
	public ProductGroupItemsData getProductGroupsItemsData(Long key){
		ProductGroupItemsData dataOut=null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			ProductGroupItems entity=pm.getObjectById(ProductGroupItems.class, key);
			if(entity!=null){
				dataOut=new ProductGroupItemsData();
				dataOut.setKey(entity.getKey());
				dataOut.setName(entity.getName());
				dataOut.setItemsIds(entity.getItemsIds());
				dataOut.setItemsNames(entity.getItemsNames());
				dataOut.setDetailsKeys(entity.getItemsDetailsIds());
				//getting the description:
				Long keyParent=entity.getKeyParent();
				try{
					ProductGroup group = pm.getObjectById(ProductGroup.class, keyParent);
					if(group!=null){
						dataOut.setDescription(group.getDescription());
						dataOut.setImageUrl(group.getImageUrl());
					}
				}catch(Exception e){
					
				}
			}
			
		}catch(Exception e){
			
		}finally{
			pm.close();
		}
		return dataOut;
	}
	@SuppressWarnings("unchecked")
	public ProductGroupItemsData[] getProductGroupsItemsByPage(int iPage,int itemsPerPage){
		ProductGroupItemsData[] dataOut=new ProductGroupItemsData[0];
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(ProductGroupItems.class);
		q.setRange(0, 1000);
		List<ProductGroupItems> listFromDb;	
		ProductGroupItems entity;
		int iStart=0;
		int iEnd=0;
		int nItemDatastore;
		int nItemsCurPage=0;
		int nItemsPerPage=itemsPerPage;
		int nPages;		
		try{
			listFromDb = (List<ProductGroupItems>) q.execute();
			if(!listFromDb.isEmpty()){
				 nItemDatastore=listFromDb.size();
				 PageData pageData=new PageData(iPage,nItemDatastore,nItemsPerPage);
				 iStart=pageData.getiStart();
				 iEnd=pageData.getiEnd();
				 nItemsCurPage=pageData.getnItemsCurPage();
				 nPages=pageData.getnPages();
				 dataOut=new ProductGroupItemsData[nItemsCurPage];

				 if(dataOut.length>0){
					 int i=0;
					 int iDataOut=0;
					 for(i=iStart;i<=iEnd;i++){
						 entity=listFromDb.get(i);
						 dataOut[iDataOut]=new ProductGroupItemsData();
						 dataOut[iDataOut].setItemsIds(entity.getItemsIds());
						 dataOut[iDataOut].setItemsNames(entity.getItemsNames());
						 dataOut[iDataOut].setName(entity.getName());
						 dataOut[iDataOut].setKey(entity.getKey());
						 dataOut[iDataOut].setKeyParent(entity.getKeyParent());
						 dataOut[iDataOut].setDetailsKeys(entity.getItemsDetailsIds());
						//dataOut[iDataOut].setStrId(String.valueOf(entity.getKey()));
						 //products[iDataOut].setImageUrl(entity.getImageUrl());
						 //dataOut[iDataOut].setStrIdDetailsInfo(String.valueOf(entity.getDetailInfoKey()));
						 dataOut[iDataOut].setnPage(nPages);
						 iDataOut++;
					 
					 }	
				 }
				
			}
		}catch(Exception e){
			System.out.println("DAO::getProductGroupListByPage() ERROR: "+e.toString());
		}
		finally{
			q.closeAll();
			pm.close();
		}		
		return dataOut;
	}
	public ProductGroupData getProductGroup(Long key){
		ProductGroupData data=null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			ProductGroup entity = pm.getObjectById(ProductGroup.class, key);
			if(entity!=null){
				data=new ProductGroupData(entity.getName(),entity.getDescription());
				data.setImageUrl(entity.getImageUrl());
				data.setKey(entity.getKey());
			}
		}catch(Exception e){
			
		}finally{
			pm.close();
		}
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductBasicInfo> getProductInfoListByPage(int iPageIn,int itemsPerPage){
		List<ProductBasicInfo> listOut = new ArrayList<ProductBasicInfo>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(ChemicalInfo.class);
		//Query q =pm.newQuery("SELECT c FROM ChemicalInfo c WHERE c.name == 'Tej Pat ka Patta'");
		q.setRange(0, 1000);//-- It will get the first data from the db
		q.setOrdering("name asc");
		List<ChemicalInfo> listFromDb;	
		ChemicalInfo infoFromDb;
		int iPage=iPageIn;
		int nItemsPerPage=itemsPerPage;
		
		int nStart=0;
		int nEnd=0;
		int nItemDatastore;
		//int nItemsCurPage=0;//--number of items on the current page.
		try{
			listFromDb = (List<ChemicalInfo>) q.execute();
			 if(!listFromDb.isEmpty()){
				// log.log(Level.SEVERE, "Got product list page index:"+iPage);
				 //log.log(Level.WARNING, "Got product list page index:"+iPage);
				// log.log(Level.INFO, "Got product list page index:"+iPage);
				 nItemDatastore=listFromDb.size();
				//calculate the number of pages:
				 int nPages=nItemDatastore/nItemsPerPage;
				 int nItemsLeft=nItemDatastore%nItemsPerPage;
				 if(nItemsLeft>0) nPages++;
				 // System.out.println("DAO::getProductsListByPage() nPages="+nPages);
				 //System.out.println("DAO::getProductsListByPage() nItemsLeft="+nItemsLeft);
				 // listOut=new ChemicalData[listFromDb.size()];
				 //if desired page index does not exit:
				 if(iPage>=0&&iPage<nPages){
					 
					 //--the fist item of the current Page:
					 nStart=iPage*nItemsPerPage;
					 if(iPage>nPages-2){//-- if the page is the last page
						 nEnd=nStart+nItemDatastore-1-iPage*nItemsPerPage;
					 }else{
						 nEnd=nStart+nItemsPerPage-1;
					 }	
					// nItemsCurPage=nEnd-nStart+1;
					// System.out.println("nPages="+nPages
					//		 +", nItemDataStore="+nItemDatastore
					//		 +", iPage="+iPage+", nStart="+nStart+", nEnd="+nEnd+", nItemsCurPage="+nItemsCurPage);  					 
					
					 int i=0;
					
					 ProductBasicInfo info;
					 for(i=nStart;i<=nEnd;i++){
						 infoFromDb=listFromDb.get(i);
						 info=new ProductBasicInfo(infoFromDb.getKey(),infoFromDb.getDetailInfoKey(),infoFromDb.getName());
						 listOut.add(info);
						
					 }

				 }
				

			 }
		}catch(Exception e){
			
		}
		finally{
			q.closeAll();
			pm.close();
		}
		return listOut;
	}
	public ChemicalData getProduct(Long key){
		ChemicalData productOut=null;
		//Long key=null;
		//try{
			//key = Long.valueOf(strProductId);
		//}catch(Exception ex){
			//return productOut;
		//}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			ChemicalInfo productFromDb= pm.getObjectById(ChemicalInfo.class,key);
			if(productFromDb!=null){
				productOut=new ChemicalData(productFromDb.getName(),productFromDb.getDescription());
				productOut.setIsInStock(productFromDb.getIsInStock());
				productOut.setStrId(String.valueOf(productFromDb.getKey()));
				productOut.setKey(productFromDb.getKey());
				productOut.setImageUrl(productFromDb.getImageUrl());
				productOut.setStrIdDetailsInfo(String.valueOf(productFromDb.getDetailInfoKey()));
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			productOut=null;
			
		}finally{
			pm.close();
		}
		return productOut;
	}
	public ProductDetails getProductDetailsByKey(Long key){
		ProductDetails chemDetails=null;
		if(key==null){
			return chemDetails;
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			
			ChemicalDetailsInfo chemDetailsInfo=pm.getObjectById(ChemicalDetailsInfo.class,key);
			
			 if(chemDetailsInfo!=null){
					 //create the data transfer object:
				 chemDetails=getProductDetailsDTO(chemDetailsInfo);
				
				 }
		}catch(Exception e){
			
			log.log(Level.SEVERE, e.getLocalizedMessage());
		}
		finally{
			
			pm.close();
		}	
		return chemDetails;
	}
	private ProductDetails getProductDetailsDTO(ChemicalDetailsInfo chemDetailsInfo){
		ProductDetails chemDetails=new ProductDetails();
		chemDetails.setName(chemDetailsInfo.getName());
		chemDetails.setDescription(chemDetailsInfo.getDescription());
		chemDetails.setStrId(String.valueOf(chemDetailsInfo.getKey()));
		chemDetails.setKeyChemical(chemDetailsInfo.getKeyChemical());
		chemDetails.setIsInStock(chemDetailsInfo.getIsInStock());
		chemDetails.setIsSection2Displayed(chemDetailsInfo.getIsSection2Display());
		chemDetails.setCommonName(chemDetailsInfo.getCommonName());
		chemDetails.setBotanicalName(chemDetailsInfo.getBotanicalName());
		chemDetails.setCountryOfOrigin(chemDetailsInfo.getCountryOfOrigin());
		chemDetails.setPlantPart(chemDetailsInfo.getPlantPart());
		chemDetails.setSaparationMethod(chemDetailsInfo.getSaparationMethod());
		chemDetails.setColorAndAppearance(chemDetailsInfo.getColorAndAppearance());
		chemDetails.setOrder(chemDetailsInfo.getOrder());
		//specific gravity data:
		chemDetails.setIsSpGravDspl(chemDetailsInfo.getIsSpGravDspl());
		chemDetails.setIsSpGravRange(chemDetailsInfo.getIsSpGravRange());
		//chemDetails.setSpecificGravity(chemDetailsInfo.getSpecificGravity());
		//chemDetails.setSpecificGravity1(chemDetailsInfo.getSpecificGravity1());
		//chemDetails.setSpecificGravity2(chemDetailsInfo.getSpecificGravity2());
		chemDetails.setTempSpGrav(chemDetailsInfo.getTempSpGrav());
		chemDetails.setTempUnitSpGrav(chemDetailsInfo.getTempUnitSpGrav());
		//refrative index data:
		chemDetails.setIsRfrIndexDspl(chemDetailsInfo.getIsRfrIndexDspl());
		chemDetails.setIsRfrIndexRange(chemDetailsInfo.getIsRfrIndexRange());
		//chemDetails.setRefractiveIndex(chemDetailsInfo.getRefractiveIndex());
		//chemDetails.setRefractiveIndex1(chemDetailsInfo.getRefractiveIndex1());
		//chemDetails.setRefractiveIndex2(chemDetailsInfo.getRefractiveIndex2());
		chemDetails.setTempRefracIndex(chemDetailsInfo.getTempRefracIndex());
		chemDetails.setTempUnitRfrIndex(chemDetailsInfo.getTempUnitRfrIndex());
		
		
		chemDetails.setChemicalConstituent(chemDetailsInfo.getChemicalConstituent());
		
		chemDetails.setIsOptRotDspl(chemDetailsInfo.getIsOptRotDspl());
		chemDetails.setIsOptRotRange(chemDetailsInfo.getIsOptRotRange());
		//chemDetails.setOpticalRotation(chemDetailsInfo.getOpticalRotation());
		//chemDetails.setOpticalRotation1(chemDetailsInfo.getOpticalRotation1());
		//chemDetails.setOpticalRotation2(chemDetailsInfo.getOpticalRotation2());
		
		
		chemDetails.setSolubility(chemDetailsInfo.getSolubility());
		
		chemDetails.setIsFlashPointDspl(chemDetailsInfo.getIsFlashPointDspl());
		chemDetails.setIsFlshPntRange(chemDetailsInfo.getIsFlshPntRange());
		//chemDetails.setFlashPoint(chemDetailsInfo.getFlashPoint());
		//chemDetails.setFlashPoint1(chemDetailsInfo.getFlashPoint1());
		//chemDetails.setFlashPoint2(chemDetailsInfo.getFlashPoint2());
		chemDetails.setTempUnitFlashPoint(chemDetailsInfo.getTempUnitFlashPoint());
		
		chemDetails.setImageUrl(chemDetailsInfo.getImageUrl());
		chemDetails.setListDisplay(chemDetailsInfo.getListDisplay());
		chemDetails.setStrValues(chemDetailsInfo.getProperties());
		chemDetails.setUses(chemDetailsInfo.getUses());
		return chemDetails;
	}
	
	public SliderFilmItemsDTO getSliderFilmItems(Long key,int iStart,int nItems){
		SliderFilmItemsDTO listItems = new SliderFilmItemsDTO();
		//SliderFilmDTO filmDTO=null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		SliderFilm entity=null;
		try{
			//getting the target slider film:
			entity= pm.getObjectById(SliderFilm.class,key);
				if(entity!=null){
					//filmDTO = new SliderFilmDTO(entity.getTitle());
					//1 getting the images
           			Long[] imageItems=entity.getImageInfos();
           			//2 getting the linked products
        			Long[] productKeys=entity.getLinkedProducts();
        			//3 creating the list of the image urls that will be sent to the client:
        			List<String> imageUrls=new ArrayList<String>();
        			//the product keys:
        			 List<Long> lstProductKeys=new ArrayList<Long>();
        			//4 creating the list of the names
        			List<String> names=new ArrayList<String>();
        			//getting the urls of the images in the blob store:
        			//SliderImage sliderImage;
        			int i=0; 
        			//iter the image items keys:
        			for(Long imageItemKey:imageItems){
        				if(i>=iStart+nItems) break;
        				if(i>=iStart){
           				try{
           					//getting the corresponding slider image:
           					SliderImage sliderImage = pm.getObjectById(SliderImage.class,imageItemKey);
        					if(sliderImage!=null){
        						//putting the image url:       						
        						imageUrls.add(sliderImage.getImageUrl());
        						
        						//imageItemJson.put("linkedProduct",String.valueOf(productKeys[i]));
        						
        					}else{
        						imageUrls.add("");
        					}
        				}catch(Exception e){
        					imageUrls.add("");
        				}
        				}
        				
           				i++;
        			}//image item iter done 
        			//now getting the names of the linked products:
        			i=0;
        			
        			for(Long productKey:productKeys){
        				if(i>=iStart+nItems) break;
        				if(i>=iStart){
            			try{
            				ChemicalInfo productFromDb= pm.getObjectById(ChemicalInfo.class,productKey);
            				if(productFromDb!=null){
            					names.add(productFromDb.getName());
            					lstProductKeys.add(productKey);
            				}else{
            					names.add("");
            					lstProductKeys.add(productKey);//no meaning of it. try to remove it
            				}
            			}catch(Exception e){
            				names.add("");
            				lstProductKeys.add(productKey);
            			}	
        				}
        				i++;
        			}
        			//four type of data will be set:
        			//1
        			//filmDTO.setImageUrls(imageUrls);
        			listItems.setImageUrls(imageUrls);
        			//2
        			//filmDTO.setLinkedItemsNames(names);
        			//3
        			//filmDTO.setProductIds(productKeys);
        			//listItems.setProductIds(productKeys);
        			listItems.setProductKeys(lstProductKeys);
        			//4
        			//filmDTO.setImageItems(imageItems);
        			//total items in this slider film:
        			listItems.setnTotalItems(imageItems.length);
				
				}
			}catch(Exception e){
				System.out.println("No item got on the server with key:"+key);
			}finally{
				pm.close();
			}
			
		return listItems;
	}
}
