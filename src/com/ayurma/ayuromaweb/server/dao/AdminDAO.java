package com.ayurma.ayuromaweb.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.json.JSONException;
import org.json.JSONObject;

import com.ayurma.ayuromaweb.client.admin.util.UserSettings;
import com.ayurma.ayuromaweb.server.dao.util.PageData;
import com.ayurma.ayuromaweb.server.model.AdminInfo;
import com.ayurma.ayuromaweb.server.model.AppData;
import com.ayurma.ayuromaweb.server.model.ChemicalDetailsInfo;
import com.ayurma.ayuromaweb.server.model.ChemicalInfo;
import com.ayurma.ayuromaweb.server.model.Employee;
import com.ayurma.ayuromaweb.server.model.EmployeeAddress;
import com.ayurma.ayuromaweb.server.model.EmployeeEMail;
import com.ayurma.ayuromaweb.server.model.EmployeePhone;
import com.ayurma.ayuromaweb.server.model.ImageData;
import com.ayurma.ayuromaweb.server.model.Mobile;
import com.ayurma.ayuromaweb.server.model.ProductGroup;
import com.ayurma.ayuromaweb.server.model.ProductGroupItems;
import com.ayurma.ayuromaweb.server.model.ProductIndex;
import com.ayurma.ayuromaweb.server.model.SliderFilm;
import com.ayurma.ayuromaweb.server.model.UserInfo;
import com.ayurma.ayuromaweb.server.model.SliderImage;
import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.ayurma.ayuromaweb.shared.AdminInfoDTO;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.ayurma.ayuromaweb.shared.EmployeeEmailDTO;
import com.ayurma.ayuromaweb.shared.EmployeePhoneDTO;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.ayurma.ayuromaweb.shared.MobileDTO;
import com.ayurma.ayuromaweb.shared.MobileUpdateDTO;
import com.ayurma.ayuromaweb.shared.ProductGroupData;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SliderFilmDTO;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.ayurma.ayuromaweb.shared.UserDTO;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AdminDAO {

	private static final Logger log = Logger.getLogger(AdminDAO.class.getName());
	private static final AdminDAO instance = new AdminDAO();
	public AdminDAO(){
		
	}
	public static AdminDAO get() {
		return instance;
	}
	
	public String addChemicalCompound(ChemicalData chemical){
		String output="";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ChemicalInfo entity = new ChemicalInfo(chemical.getName(),chemical.getDescription());
		ChemicalDetailsInfo detailInfo;
		entity.setImageUrl(chemical.getImageUrl());
		try{
			pm.makePersistent(entity);
			output+="Product saved successfully";
		    //now creating indexes for the current entity:
			String name=entity.getName().toLowerCase();
			String keyword="";
			ProductIndex index = null;
			int i=0;
		    for(i=0;i<name.length();++i){
		          keyword = name.substring(0, i+1);
		          try{
		              index = pm.getObjectById(ProductIndex.class, keyword);
		              if (index==null) index = new ProductIndex(keyword);
		          }catch(JDOObjectNotFoundException e){
		             index =  new ProductIndex(keyword);
		          }
		          Long key=entity.getKey();
		          index.getKeys().add(key);
		          pm.makePersistent(index);
		     }
		    output+=" and "+i+" Indexes saved successfull";
		    //now creating the product details info:
			detailInfo=new ChemicalDetailsInfo(entity.getName(),entity.getDescription());
			detailInfo.setKeyChemical(entity.getKey());
			detailInfo.setImageUrl(entity.getImageUrl());
			pm.makePersistent(detailInfo);
			Long keyDetails=detailInfo.getKey();
			//System.out.println("from AdminDAO::addChemicalCompound() details id="+key);
			//making sure that the details object is created:
			ChemicalDetailsInfo details= pm.getObjectById(ChemicalDetailsInfo.class,keyDetails);
			if(details!=null){
				entity.setDetailInfoKey(detailInfo.getKey());
				//System.out.println("from AdminDAO::addChemicalCompound() small data's detail's id="+entity.getDetailInfoKey());
				output+=" And Chemical Details data created successfully.";
			}else{
				output+=" It seems that there is some error in saving the details information. ";
			}			
			
		}catch(Exception e){
			//System.out.println("error:"+e.toString());
			//log.
			output="Error in saving chemical compound data.";
		}finally{
			pm.close();
		}
		return output;
	}
	public String deleteProduct(Long key){
		String strOutput="";
		if(key==null){
			return strOutput="got a null key";
			
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		//BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		try{
			ChemicalInfo productFromDb= pm.getObjectById(ChemicalInfo.class,key);
			if(productFromDb!=null){
				//get the name of the product to remove the indexes:
				String name=productFromDb.getName().toLowerCase();
				//String imageUrl=productFromDb.getImageUrl();//for deleting the image
				strOutput="Product found with the given id. ";
				//for deleting the corresponding detailed info:
				
				Long keyDetailsInfo=productFromDb.getDetailInfoKey();
				try{
					ChemicalDetailsInfo info=pm.getObjectById(ChemicalDetailsInfo.class,keyDetailsInfo);
					if(info!=null){ 
						pm.deletePersistent(info);
						strOutput+="Product details deleted successfully. ";
					}else strOutput+="Product details was not found. ";
				}catch(javax.jdo.JDOObjectNotFoundException e){
					strOutput+="But product detail was not found ";
				}
				//now removing all the attachment to the index data with the 
				//product name:
				int i=0;
				String keyword="";
				ProductIndex index = null;
				ArrayList<Long> keys;
			    for(i=0;i<name.length();++i){
			          keyword = name.substring(0, i+1);
			          try{
			              index = pm.getObjectById(ProductIndex.class, keyword);
			              if (index!=null){
			            	  //get the keys of this index
			            	  //and remove the key of the current product
			            	 index.removeKey(key);
			            	 //now see if the index has zero keys storage for the products
			            	 //if so delete the index:
			            	 if(index.isEmpty()){
			            		 try{
			            			 pm.deletePersistent(index); 
			            		 }catch(Exception e){
			            			//System.out.println("Index could not be deleted.");
			            		 }
			            	 }
			              }
			          }catch(JDOObjectNotFoundException e){
			             //index =  new ProductIndex(keyword);
			          }
			          //Long key=entity.getKey();
			          //index.getKeys().add(key);
			          //pm.makePersistent(index);
			     }
			    //now deleting the image in the blob store:
			    //if(!imageUrl.equals("")){
			    	//BlobKey blobKey=new BlobKey(imageUrl);
			    	//blobstoreService.delete(blobKey);
			   // }
			    //finally deleting the product:
			    
				pm.deletePersistent(productFromDb);
				strOutput+="product deleted successfully ";
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			strOutput="product was not found";
		}finally{
			pm.close();
		}		
		
		return strOutput;
	}
	public String addProductGroup(ProductGroupData data){
		String output="";
		if(data==null){
			output="Product group data received is NULL.";
			return output;
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ProductGroup entity=new ProductGroup(data.getName(),data.getDescription());
		entity.setImageUrl(data.getImageUrl());
		try{
			pm.makePersistent(entity);
			output+="Product group added successfully with Key:'"+entity.getKey()+"', Name:'"+entity.getName()+"'.";
			
			
		}catch(Exception e){
			//System.out.println("error="+e.toString());
			//log.
			output="Error in saving product group data.";
			entity=null;
		}finally{
			//now creating a ProductGroupItems object for this:
			try{
				if(entity!=null){
					ProductGroupItems items=new ProductGroupItems();
					items.setName(entity.getName());
					pm.makePersistent(items);
					
					ProductGroupItems savedItem=pm.getObjectById(ProductGroupItems.class,items.getKey());
					if(savedItem!=null){
						//connection the items object with the ProductGroup object
						entity.setKeyItems(savedItem.getKey());
						savedItem.setKeyParent(entity.getKey());
						output+=" and ProductGroupItems was created sucessfully with key='"+savedItem.getKey()+"'.";
					}else{
						output+="ProductGroupItems could not be saved successfull.";
					}
				}
				//now connecting it with the ProductGroup object
			}catch(Exception e){
				
			}finally{
				pm.close();
			}
			
		}
		return output;
	}
	public String updateProductGroup(Long keyProductGroup,ProductGroupData data,int id){
	String strOutput="";
		
		Long key=keyProductGroup;
		if(key==null){
			strOutput="Updated for Product Group could not be performed because the key received is NULL.";
			return strOutput;
		}
		if(data==null){
			strOutput="Updated for Product Group could not be performed because the data received is NULL.";
			return strOutput;			
		}

		PersistenceManager pm = PMF.get().getPersistenceManager();
		ProductGroup productFromDb=null;
		try{
			productFromDb= pm.getObjectById(ProductGroup.class,key);
			if(productFromDb!=null){
				//update the product data:
				if(id==0){
				productFromDb.setName(data.getName());
				productFromDb.setDescription(data.getDescription());
				
				//productFromDb.se
				//strOutput="product group updated successfully with name:"+productFromDb.getName()+", and key:'"+productFromDb.getKey()+"'";
				strOutput="";
				}else if(id==1){
					productFromDb.setImageUrl(data.getImageUrl());
					strOutput="";
				}
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			strOutput="Error in updating the Product Group.";
			
		}finally{
			//now updating the data for the object containing the items data
			try{
				if(productFromDb!=null){
				  ProductGroupItems items=pm.getObjectById(ProductGroupItems.class,productFromDb.getKeyItems());
				  if(items!=null){
					  items.setName(productFromDb.getName());
				  }
				}
			}catch(javax.jdo.JDOObjectNotFoundException e){
				
			}finally{
				pm.close();
			}
			
		}		
		return strOutput;	
	}
	/*
	 * this replaces the entire data:
	 */
	public ProductGroupItemsData setProductToGroup(Long keyGroup,Long[] productKeys,String[] nameProducts,
			Long[] detailsKeys){
		String output="";
		ProductGroupItemsData updatedData=null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ProductGroupItems savedItem=null;
		try{
			savedItem=pm.getObjectById(ProductGroupItems.class,keyGroup);
			if(savedItem!=null&&productKeys.length==nameProducts.length){
				savedItem.setItemsNames(nameProducts);
				savedItem.setItemsIds(productKeys);
				savedItem.setItemsDetailsIds(detailsKeys);
				
				output="The group information is saved successfully.";
				updatedData=new ProductGroupItemsData();
				updatedData.setItemsIds(savedItem.getItemsIds());
				updatedData.setItemsNames(savedItem.getItemsNames());
				updatedData.setDetailsKeys(savedItem.getItemsDetailsIds());
				updatedData.setName(savedItem.getName());
				updatedData.setKey(savedItem.getKey());
				updatedData.setKeyParent(savedItem.getKeyParent());
			}else output="The group information was NOT saved beacuse the array of" +
					" ids lenght does not match with the name array";
		}catch(javax.jdo.JDOObjectNotFoundException e){
			output="No such group items was found with the key:"+keyGroup;
		}finally{
			pm.close();
		}
		return updatedData;
	}
	@SuppressWarnings("unchecked")
	public AdminInfoDTO getAdminInfo(){
		AdminInfoDTO dto=new AdminInfoDTO();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(AdminInfo.class);
		
		try{
			List<AdminInfo> entities = (List<AdminInfo>) q.execute();
			if(!entities.isEmpty()){
				//taking the first use:
				dto=new AdminInfoDTO(entities.get(0).getName(),
						entities.get(0).getEmail());
			}
		}catch(Exception e){
			
		}
		finally{
			q.closeAll();
			pm.close();
		}
		return dto;
	}
	public void saveImageInfo(String folderName,String imageInfo,String keyImage){
		PersistenceManager pm = PMF.get().getPersistenceManager();	
		
		//System.out.println("folder name:"+folderName);
		try{
			if(folderName.equals("image")){
				ImageData entity = new ImageData(imageInfo,keyImage);
				pm.makePersistent(entity);
			}else if(folderName.equals("slider-image")){
				SliderImage entity = new SliderImage(imageInfo,keyImage);
				pm.makePersistent(entity);
			}
					
			
		}catch(Exception e){
			log.log(Level.SEVERE, "Image Data could not be saved.");
		}finally{
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ImageDataDTO> getImagesDataByPage(int iPage,int itemsPerPage) {
		List<ImageDataDTO> list = new ArrayList<ImageDataDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(ImageData.class);
		q.setRange(0, 1000);
		
		
		int iStart=0;
		int iEnd=0;
		int nItemDatastore;
		int nItemsCurPage=0;
		int nItemsPerPage=itemsPerPage;
		//int nPages;	
		try{
			List<ImageData>  listFromDb = (List<ImageData>) q.execute();
			if(!listFromDb.isEmpty()){
				 nItemDatastore=listFromDb.size();
				 PageData pageData=new PageData(iPage,nItemDatastore,nItemsPerPage);
				 iStart=pageData.getiStart();
				 iEnd=pageData.getiEnd();
				 nItemsCurPage=pageData.getnItemsCurPage();
				// nPages=pageData.getnPages();
				 //System.out.println("In server... iPage="+iPage+", nItemsCurPage:"+nItemsCurPage);
				 if(nItemsCurPage>0){
					// System.out.println("In server... iPage="+iPage+", itemsPerPage:"+itemsPerPage);
					 int i=0;
					 ImageData entity;
					 for(i=iStart;i<=iEnd;i++){
						 entity=listFromDb.get(i);
						 list.add(new ImageDataDTO(entity.getKey(),entity.getImageInfo(),entity.getImageUrl()));
					 }

				 }
			}
				 

				 
		}catch(Exception e){
			// System.out.println("in server...Error");
		}
		finally{
			q.closeAll();
			pm.close();
		}	
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<SliderImageDTO> getSliderImagesByPage(int iPage,int itemsPerPage) {
		List<SliderImageDTO> list = new ArrayList<SliderImageDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(SliderImage.class);
		q.setRange(0, 1000);
		
		
		int iStart=0;
		int iEnd=0;
		int nItemDatastore;
		int nItemsCurPage=0;
		int nItemsPerPage=itemsPerPage;
		//int nPages;	
		try{
			List<SliderImage>  listFromDb = (List<SliderImage>) q.execute();
			if(!listFromDb.isEmpty()){
				 nItemDatastore=listFromDb.size();
				 PageData pageData=new PageData(iPage,nItemDatastore,nItemsPerPage);
				 iStart=pageData.getiStart();
				 iEnd=pageData.getiEnd();
				 nItemsCurPage=pageData.getnItemsCurPage();
				// nPages=pageData.getnPages();
				 //System.out.println("In server... iPage="+iPage+", nItemsCurPage:"+nItemsCurPage);
				 if(nItemsCurPage>0){
					// System.out.println("In server... iPage="+iPage+", itemsPerPage:"+itemsPerPage);
					 int i=0;
					 SliderImage entity;
					 for(i=iStart;i<=iEnd;i++){
						 entity=listFromDb.get(i);
						 list.add(new SliderImageDTO(entity.getKey(),entity.getImageInfo(),entity.getImageUrl()));
					 }

				 }
			}
				 

				 
		}catch(Exception e){
			// System.out.println("in server...Error");
		}
		finally{
			q.closeAll();
			pm.close();
		}	
		return list;
	}

	public String deleteImage(Long key,int type){
		String output="";
		PersistenceManager pm = PMF.get().getPersistenceManager();	
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		
		try{
			switch(type){
			case 0://if a general image
				ImageData entity= pm.getObjectById(ImageData.class, key);
				if(entity!=null){
					//get the image id:
					String imageKey = entity.getImageUrl();
					 BlobKey blobKey = new BlobKey(imageKey);
					 try{
						 blobstoreService.delete(blobKey);
						 output+="Image blob deleted. ";
					 }catch(Exception e){
						 output+="Error in deleting image blob. ";
								 
					 }
					 //finally deleting the image info:
					 try{
						 pm.deletePersistent(entity);
						 output+="Image info deleted. ";
					 }catch(Exception e){
						 output+="Error in deleting image info. ";
					 }
				}
				break;
			case 1://if a slider image
				SliderImage sliderImageentity = pm.getObjectById(SliderImage.class, key);
				if(sliderImageentity!=null){
					String imageKey = sliderImageentity.getImageUrl(); 
					BlobKey blobKey = new BlobKey(imageKey);
					//deleting the image
					 try{
						 blobstoreService.delete(blobKey);
						 output+="Image blob deleted. ";
					 }catch(Exception e){
						 output+="Error in deleting image blob. ";
								 
					 }
					//finally deleting the image info:
					 try{
						 pm.deletePersistent(sliderImageentity);
						 output+="Slider Image info deleted. ";
					 }catch(Exception e){
						 output+="Error in deleting slider image info. ";
					 }
				}
				break;
			}
			
			
		}catch(Exception e){
			output+="Some error in getting the image info entity. ";
		}finally{
			pm.close();
		}
		
		return output;
	}
	public String updateProductImage(Long productKey,String imgKey){
		String strOutput="";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			ChemicalInfo productFromDb= pm.getObjectById(ChemicalInfo.class,productKey);
			if(productFromDb!=null){
				productFromDb.setImageUrl(imgKey);
				strOutput="product image key updated successfully";
				Long keyDetailsInfo=productFromDb.getDetailInfoKey();
				try{
					ChemicalDetailsInfo info=pm.getObjectById(ChemicalDetailsInfo.class,keyDetailsInfo);
					if(info!=null){ 

						info.setImageUrl(imgKey);
						
					}else strOutput+="Product details was not found. ";
				}catch(javax.jdo.JDOObjectNotFoundException e){
					strOutput+="But product detail was not found ";
				}
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			strOutput="Error in updating the product.";
			
		}finally{
			pm.close();
		}		
		return strOutput;	
	}
	public String updateProductData(Long key,ChemicalData updatedProductData){
		String strOutput="";

		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			ChemicalInfo productFromDb= pm.getObjectById(ChemicalInfo.class,key);
			if(productFromDb!=null){
				//storing the data for manipulating the index data :
				//String nameOld=productFromDb.getName().toLowerCase();
				String nameOld=productFromDb.getName();
				//compare the above name with the new name:
				String nameNew=updatedProductData.getName();
				//update the product data:
				productFromDb.setName(updatedProductData.getName());
				productFromDb.setDescription(updatedProductData.getDescription());
				productFromDb.setIsInStock(updatedProductData.getIsInStock());
				
				//productFromDb.setImageUrl(updatedProductData.getImageUrl());
				strOutput="product updated successfully";
				//now updata the product details name:
				Long keyDetailsInfo=productFromDb.getDetailInfoKey();
				try{
					ChemicalDetailsInfo info=pm.getObjectById(ChemicalDetailsInfo.class,keyDetailsInfo);
					if(info!=null){ 
						info.setName(productFromDb.getName());
						info.setIsInStock(updatedProductData.getIsInStock());
						
					}else strOutput+="Product details was not found. ";
				}catch(javax.jdo.JDOObjectNotFoundException e){
					strOutput+="But product detail was not found ";
					log.info(e.getMessage());
				}
				//index updating:
				//if does not match.. delete the indexes:
				if(!nameOld.equals(nameNew)){
					//first delete the keys in the current indexes:
					int i=0;
					String keyword="";
					ProductIndex index = null;
					ArrayList<Long> keys;
					nameOld=nameOld.toLowerCase();
				    for(i=0;i<nameOld.length();++i){
				          keyword = nameOld.substring(0, i+1);
				          try{
				              index = pm.getObjectById(ProductIndex.class, keyword);
				              if (index!=null){
				            	  //get the keys of this index
				            	  //and remove the key of the current product
				            	 index.removeKey(key);
				            	 //now see if the index has zero keys storage for the products
				            	 //if so delete the index:
				            	 if(index.isEmpty()){
				            		 try{
				            			 pm.deletePersistent(index); 
				            		 }catch(Exception e){
				            			// System.out.println("Index could not be deleted.");
				            		 }
				            	 }
				              }
				          }catch(JDOObjectNotFoundException e){
				             
				          }

				     }//manipulating the old indexes loop
				    //creating the indexes with the new name:
				    nameNew=nameNew.toLowerCase();
				    for(i=0;i<nameNew.length();++i){
				          keyword = nameNew.substring(0, i+1);
				          try{
				              index = pm.getObjectById(ProductIndex.class, keyword);
				              if (index==null) index = new ProductIndex(keyword);
				          }catch(JDOObjectNotFoundException e){
				             index =  new ProductIndex(keyword);
				          }
				          index.getKeys().add(key);
				          pm.makePersistent(index);
				     }
				}
				
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			strOutput="Error in updating the product.";
			
		}finally{
			pm.close();
		}		
		return strOutput;
	}
	public String updateProductDetailsPart(Long key,List<String> strData,
			int codeSection){
		String output="";
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		ChemicalDetailsInfo detailsInfo;
		try {
			detailsInfo = pm.getObjectById(ChemicalDetailsInfo.class, key);
			if(detailsInfo!=null){
				switch(codeSection){
				//specific gravity update:
				case 0:detailsInfo.setSpGrav1(strData.get(0));
				    detailsInfo.setSpGrav2(strData.get(1));
				    detailsInfo.setTempSpGrav(Float.valueOf(strData.get(2)));
				    detailsInfo.setTempUnitSpGrav(Integer.valueOf(strData.get(3)));
				    detailsInfo.setIsSpGravRange((strData.get(4).equals("true"))?1:0);
				    detailsInfo.setIsSpGravDspl((strData.get(5).equals("true"))?1:0);
				    
				    break;
				  //refractive index update:
				case 1:
					detailsInfo.setRfrInd1(strData.get(0));
					detailsInfo.setRfrInd2(strData.get(1));
					detailsInfo.setTempRefracIndex(Float.valueOf(strData.get(2)));
					detailsInfo.setTempUnitRfrIndex(Integer.valueOf(strData.get(3)));
					detailsInfo.setIsRfrIndexRange((strData.get(4).equals("true"))?1:0);
					detailsInfo.setIsRfrIndexDspl((strData.get(5).equals("true"))?1:0);
					break;
				case 2://optical rotaion update:
					detailsInfo.setOptRot1(strData.get(0));
					detailsInfo.setOptRot2(strData.get(1));
					detailsInfo.setIsOptRotRange((strData.get(4).equals("true"))?1:0);
					detailsInfo.setIsOptRotDspl((strData.get(5).equals("true"))?1:0);
					break;
				case 3://flash point update:
					detailsInfo.setFlshPnt1(strData.get(0));
					detailsInfo.setFlshPnt2(strData.get(1));
					detailsInfo.setTempUnitFlashPoint(Integer.valueOf(strData.get(3)));
					detailsInfo.setIsFlshPntRange((strData.get(4).equals("true"))?1:0);
					detailsInfo.setIsFlashPointDspl((strData.get(5).equals("true"))?1:0);
					
					break;
				case 4://update description
					detailsInfo.setDescription(strData.get(0));
					break;
				case 5://update general information totalling 6 properties:
					//setting the properties:
					detailsInfo.setCommonName(strData.get(0));//0
					detailsInfo.setBotanicalName(strData.get(1));//1
					detailsInfo.setCountryOfOrigin(strData.get(2));//2
					detailsInfo.setChemicalConstituent(strData.get(3));//3
					detailsInfo.setPlantPart(strData.get(4));//4
					detailsInfo.setSaparationMethod(strData.get(5));//5
					//updating the disply/hide info from 0 to 5:
					List<String> strInfo=strData.subList(6, strData.size());
					int[] listDisplay = detailsInfo.getListDisplay();
					int id=0;
					for(String info:strInfo){
						listDisplay[id]=(info.equals("1"))?1:0;
						id++;
					}
					detailsInfo.setListDisplay(listDisplay);
					
					break;
				case 6://updating the non-numerical part of the specification section
					detailsInfo.setColorAndAppearance(strData.get(0));
					detailsInfo.setOrder(strData.get(1));
					detailsInfo.setSolubility(strData.get(2));
					//show/hide the whole specification section:
					detailsInfo.setIsSection2Display(Integer.valueOf(strData.get(3)));
					//show/hide 'color' 'order' 'solubility'
					int[] listDisplay2 = detailsInfo.getListDisplay();
					int id2=6;
					List<String> strInfo2=strData.subList(4, strData.size());
					for(String info:strInfo2){
						listDisplay2[id2]=(info.equals("1"))?1:0;
						id2++;
					}
					detailsInfo.setListDisplay(listDisplay2);
					break;
				}
			}
			
			output="Updated successfully.";
		} catch (javax.jdo.JDOObjectNotFoundException e) {
			//detailsInfo = null;
			output="Error in updating.";
		}finally{
			pm.close();
		}
	
		return output;
	}
	public String updateProductUses(Long key, String uses){
		String output="";

		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		ChemicalDetailsInfo detailsInfo;
		try {
			detailsInfo = pm.getObjectById(ChemicalDetailsInfo.class, key);
			detailsInfo.setUses(uses);
			
			output="Updated sucessfully.";
		} catch (javax.jdo.JDOObjectNotFoundException e) {
			
			output="Some error in update.";
		}finally{
			pm.close();
		}		
		return output;
	}
	
	public String addSliderFilm(SliderFilmDTO dto){
		String output="";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			SliderFilm entity = new SliderFilm(dto.getTitle());
			
			entity.setImageInfos(dto.getImageItems());
			entity.setLinkedProducts(dto.getProductIds());
			pm.makePersistent(entity);
			output+="Slider film created successfully with Key:'"+entity.getKey();
			
			
		}catch(Exception e){
			output="Error in creating the slider film.";
			
		}finally{
			pm.close();
		}
		return output;
	}
	public String updateSliderFilm(SliderFilmDTO dto,Long key){
		String output="";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			SliderFilm entity= pm.getObjectById(SliderFilm.class,key);
			if(entity!=null){
				entity.setImageInfos(dto.getImageItems());
				entity.setLinkedProducts(dto.getProductIds());
				entity.setDateEdit();
				output="Slider film updated successfully.";
			}else{
				output="Slider film was found in the data store.";
			}
			}catch(Exception e){
				output="Exception in getting the Slider Film entity.";
			}finally{
				pm.close();
			}
		return output;
	}
	public SliderFilmDTO getSliderFilm(Long key){
		SliderFilmDTO filmDTO=null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		SliderFilm entity=null;
		try{
			entity= pm.getObjectById(SliderFilm.class,key);
				if(entity!=null){
					filmDTO = new SliderFilmDTO(entity.getTitle());
					filmDTO.setKey(key);
           			Long[] imageItems=entity.getImageInfos();
        			Long[] productKeys=entity.getLinkedProducts();
        			List<String> imageUrls=new ArrayList<String>();
        			List<String> names=new ArrayList<String>();
        			//getting the urls of the images in the blob store:
        			 SliderImage sliderImage;
        			for(Long imageItemKey:imageItems){
           				try{
        					sliderImage = pm.getObjectById(SliderImage.class,imageItemKey);
        					if(sliderImage!=null){
        						       						
        						imageUrls.add(sliderImage.getImageUrl());
        						
        						//imageItemJson.put("linkedProduct",String.valueOf(productKeys[i]));
        						
        					}else{
        						imageUrls.add("");
        					}
        				}catch(Exception e){
        					imageUrls.add("");
        				}
        			}
        			//getting the names of the linked products:
        			for(Long productKey:productKeys){
            			try{
            				ChemicalInfo productFromDb= pm.getObjectById(ChemicalInfo.class,productKey);
            				if(productFromDb!=null){
            					names.add(productFromDb.getName());
            					
            				}else{
            					names.add("");
            				}
            			}catch(Exception e){
            				names.add("");
            			}	       				
        			}
        			//four type of data will be set:
        			//1
        			filmDTO.setImageUrls(imageUrls);
        			//2
        			filmDTO.setLinkedItemsNames(names);
        			//3
        			filmDTO.setProductIds(productKeys);
        			//4
        			filmDTO.setImageItems(imageItems);
				
				}
			}catch(Exception e){
				
			}finally{
				pm.close();
			}
			
		return filmDTO;
	}
	@SuppressWarnings("unchecked")
	public List<SliderFilmDTO> getSliderFilms(int iPage, int itemsPerPage){
		List<SliderFilmDTO> list = new ArrayList<SliderFilmDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(SliderFilm.class);
		q.setRange(0, 1000);
		try{
			List<SliderFilm>  listFromDb = (List<SliderFilm>) q.execute();
			SliderFilmDTO dto;
			
			if(!listFromDb.isEmpty()){
				 int i=0;
				 for(SliderFilm entity:listFromDb){
					 dto = new SliderFilmDTO(entity.getTitle());
					 //setting the key of the entity
					 dto.setKey(entity.getKey());
					 //images infos
					 dto.setItems(entity.getImageInfos());
					 //date
					 //dto.setDateCreation(entity.getDateCreation());
					 //dto.setDateEdit(entity.getDateEdit());
					
					 //entity.get
					 list.add(dto);
				 }
			}
				 

				 
		}catch(Exception e){
			// System.out.println("in server...Error");
		}
		finally{
			q.closeAll();
			pm.close();
		}		
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<SliderFilmDTO> deleteSliderFilm(Long key){
		
		List<SliderFilmDTO> list = new ArrayList<SliderFilmDTO>();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		SliderFilm entity=null;
		
		try{
			entity= pm.getObjectById(SliderFilm.class,key);
			if(entity!=null){
				//deleting the object:
				pm.deletePersistent(entity);
				
				
			}
			//after deleting again get the list:
			Query q = pm.newQuery(SliderFilm.class);
			q.setRange(0, 1000);
			List<SliderFilm>  listFromDb = (List<SliderFilm>) q.execute();
			SliderFilmDTO dto;
			
			if(!listFromDb.isEmpty()){
				 int i=0;
				 for(SliderFilm item:listFromDb){
					 dto = new SliderFilmDTO(item.getTitle());
					 dto.setKey(item.getKey());
					 dto.setItems(item.getImageInfos());
					 list.add(dto);
				 }
			}

		}catch(Exception e){
			
		}finally{
			pm.close();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public String displaySliderFilm(Long key){
		
		String out="";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(AppData.class);
		try{
			List<AppData> entities = (List<AppData>) q.execute();
			if(!entities.isEmpty()){
				entities.get(0).setSliderFilmKey(key);
				
				out="Slider Film is set for display";
			}else out="First Create an AppData object from settings section.";
			
		}catch(Exception e){
			out="Error, try again.";
		}
		finally{
			q.closeAll();
			pm.close();
		}
		
		return out;
		
	}
	@SuppressWarnings("unchecked")
	public String createAppData(){
		String out="";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(AppData.class);	
		try{
			List<AppData> entities = (List<AppData>) q.execute();
			if(entities.isEmpty()){
				AppData entity=new AppData();
				pm.makePersistent(entity);
				out="AppData created successfully";
			}else out="AppData object is already created.";
		}catch(Exception e){
			out="Error try again.";
		}
		finally{
			q.closeAll();
			pm.close();
		}
		return out;
	}
	public JSONObject getUserInfoJson(String requestUri,UserService userService){
		JSONObject userJson=null;
		//get the user:
		 //UserService userService = UserServiceFactory.getUserService();
		 User user = userService.getCurrentUser();
		
		
		    	//detecting if the user is valid or not
			 try {
				 if (user != null) {//user is logged in the gmail service
					 userJson = new JSONObject();
					 userJson.put("name", user.getNickname());
					 userJson.put("email", user.getEmail());
					 userJson.put("isLoggedin", "true");
					 //user is logged in, so create the log out uri
					 userJson.put("logoutUri", userService.createLogoutURL(requestUri));
					 //now see if the user is the creater of the site itself:
					 if(userService.isUserAdmin()){
						 //give all privilego al la user:
						 userJson.put("isAdmin", "true");
						 userJson.put("adminLevel", String.valueOf(UserSettings.ADMIN_LEVEL_ROOT));
					 }else{
						 // it means user is sb else not the creater of the site:
						 //we need to find out if he is in the company user db:
						 userJson.put("isAdmin", "false");// user is not the creater of the site
						 //here the user is got from the db:
						 //now we will take the UserInfo data:
						 //get the local database user by email:
						 UserDTO userDto = getUserInfo(user.getEmail());
						 if(userDto!=null){

							 // set the admin level:
							 userJson.put("adminLevel", String.valueOf(userDto.getAdminLevel()));
							 userJson.put("exists", "true");
							 // setting the access areas of the user:
							 //JSONArray accessAreas=new JSONArray();
							 int[] areas=userDto.getAccessSections();
							 String accessAreas="";
							 for(int item:areas){
								 if(item==1) accessAreas+="1,";
								 else if(item==0) accessAreas+="0,";
							 }
							 //put the above array in the json:
							 userJson.put("accessAreas",accessAreas);
						 }else{
							 //means user is in gmail but not exists in the company database:
							 
							 userJson.put("exists", "false");
							 userJson.put("adminLevel", String.valueOf(UserSettings.ADMIN_LEVEL_NULL));
							 //this will give the user the choice to register himself for company admin group
						 }

					 }
					 

					 /*
					 AdminInfoDTO dto = getAdminInfo();
					 if(dto!=null){

						 if(userService.isUserAdmin()||user.getEmail().equals(dto.email)){
					
							 userJson.put("isAdmin", "true");
						 
						 }else{
							 userJson.put("isAdmin", "false");
							 //userJson.put("isAdmin", "false");	
						 }
					 }else{
						 if(userService.isUserAdmin()){
							 userJson.put("isAdmin", "true"); 
						 }else{
							 userJson.put("isAdmin", "false");
						 }
					 }
					 */
				 }else{//user is not logged in
					 //return a null user;
				 }
			} catch (JSONException e) {
				userJson=null;
				System.err.println("AdminDAO::getUserInfoJson() exception.");
			}


		return userJson;
	}
	/*
	 * Users registered with the site:
	 */
	@SuppressWarnings("unchecked")
	public UserDTO getUserInfo(String email){
		UserDTO dto=null;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		//Query q = pm.newQuery(AdminInfo.class);
		
		try{
			
			UserInfo entity= pm.getObjectById(UserInfo.class,email);
			if(entity!=null){
				dto=new UserDTO();
				dto.setEmail(entity.getEmail());
				dto.setName(entity.getName());
				dto.setAdminLevel(entity.getAdminLevel());
				dto.setDateCreation(entity.getDateCreation());
				dto.setDateEdit(entity.getDateEdit());
				dto.setAccessSections(entity.getAccessSections());
			}
		}catch(Exception e){
			dto=null;
		}
		finally{
			//q.closeAll();
			pm.close();
		}
		return dto;
	}
	public boolean hasUserPrivilego(String email){
		boolean hasPrivilego=false;
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		//Query q = pm.newQuery(AdminInfo.class);
		
		try{
			
			UserInfo entity= pm.getObjectById(UserInfo.class,email);
			if(entity!=null){
				log.log(Level.INFO,"User asked permission email:"+email+", adminlevel:"+entity.getAdminLevel());
				//only user admin level 0 or 1 can update other users:
				if(entity.getAdminLevel()<=UserSettings.ADMIN_LEVEL_SECOND){
					log.log(Level.INFO,"...and was granted permission.");
					hasPrivilego=true;
				}else{
					log.log(Level.INFO,"...and was denied permission.");
					hasPrivilego=false;
				}
			}
		}catch(Exception e){
			
		}
		finally{
			//q.closeAll();
			pm.close();
		}
		return hasPrivilego;
	}
	@SuppressWarnings("unchecked")
	public List<UserDTO> getUsers(String adminEmail){
		
		List<UserDTO> list = new ArrayList<UserDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(UserInfo.class);
		
		try{
			
			List<UserInfo> entities = (List<UserInfo>) q.execute();
		
			if(!entities.isEmpty()){
				for(UserInfo item:entities){
					// do not include the user who is making the request to get users
					if(adminEmail.equals(item.getEmail())) continue;
					UserDTO dto = new UserDTO();
					dto.setEmail(item.getEmail());
					dto.setName(item.getName());
					dto.setAdminLevel(item.getAdminLevel());
					dto.setAccessSections(item.getAccessSections());
					//dto.setDateCreation(item.getDateCreation());
					//dto.setDateEdit(item.getDateEdit());
					list.add(dto);
				}
			}
		}catch(Exception e){
			System.out.println("some error: "+e.toString());
		}
		finally{
			q.closeAll();
			pm.close();
		}
		return list;
		
	}
	public String updateUser(UserDTO user){
		String output="";
		PersistenceManager pm = PMF.get().getPersistenceManager();

			try{
				
				UserInfo entity= pm.getObjectById(UserInfo.class,user.getEmail());
				if(entity!=null){
					//entity.setName(user.getNickname());
					//get the access sections:
					entity.setAccessSections(user.getAccessSections());
					//set the admin level:
					entity.setAdminLevel(user.getAdminLevel());
					//set date edit:
					entity.setDateEdit();
				
					
					output+="User updated with email:"+user.getEmail();
				}else{
					output+="No user found in the database with name:"+user.getEmail();
				}
				
			}catch(IllegalArgumentException e){
				output="Error: Name can not be null or Empty.";
			}catch(Exception e){
				output="Error in updating the user. "+e.toString();
				
			}finally{
				pm.close();
			}

		return output;	
	}
	public String saveEmployee(EmployeeDTO employeeDTO){
		String output = "";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Employee entity = new Employee(employeeDTO.getName(), employeeDTO.getDesignation());
		
		try{
			pm.makePersistent(entity);
			Long keyOwner = entity.getKey();
			//now save mobiles:
			List<MobileDTO> lstMobiles = employeeDTO.getMobiles();
			for(MobileDTO mobile:lstMobiles){
				Mobile entityMobile = new Mobile(mobile.getNumber(), keyOwner);
				try{
					pm.makePersistent(entityMobile);
				}catch(Exception e){
					
				}
			}
			//save phones:
			List<EmployeePhoneDTO> lstPhones = employeeDTO.getPhones();
			for(EmployeePhoneDTO phone:lstPhones){
				EmployeePhone entityPhone = new EmployeePhone(phone.getNumber(),
						phone.getCountryCode(),phone.getAreaCode(), keyOwner);
				try{
					pm.makePersistent(entityPhone);
				}catch(Exception e){
					
				}
			}
			//save Emails:
			List<EmployeeEmailDTO> lstEmails = employeeDTO.getEmails();
			for(EmployeeEmailDTO email:lstEmails){
				EmployeeEMail entityEmail = new EmployeeEMail(email.getAddress(),keyOwner);
				try{
					pm.makePersistent(entityEmail);
				}catch(Exception e){
					
				}
			}
			//now save Addresses:
			List<AddressDTO> addresses = employeeDTO.getAddresses();
			//EmployeeAddress[] addressItems = new EmployeeAddress[addresses.size()];
			for(AddressDTO address:addresses){
				List<String> strLines = address.getAddressLines();
				String[] lines = new String[strLines.size()];
				int i = 0;
				for(String item:strLines){
					lines[i] = item;
					i++;
					
				}
				EmployeeAddress addressEntity = new EmployeeAddress(keyOwner, address.getCity(),
						address.getState(), address.getCountry(), lines);
				try{
					pm.makePersistent(addressEntity);
				}catch(Exception e){
					
				}
				
			}
			output+="Employee saved successfully";
		   		
			
		}catch(Exception e){
			System.out.println("error:"+e.toString());
			//log.
			output="Error in saving employee.";
		}finally{
			pm.close();
		}
		return output;
		
	}
	@SuppressWarnings("unchecked")
	public List<EmployeeDTO> getEmployees(){
		List<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Employee.class);
		q.setRange(0, 1000);
		
		try{
			List<Employee>  listFromDb = (List<Employee>) q.execute();
			for(Employee entity:listFromDb){
				
				EmployeeDTO dto = getEmployeeDTO(entity);
				
				list.add(dto);
			}
			
				 

				 
		}catch(Exception e){
			// System.out.println("in server...Error");
		}
		finally{
			q.closeAll();
			pm.close();
		}	
		return list;
		
	}
	@SuppressWarnings("unchecked")
	public EmployeeDTO getEmployeeByKey(Long key){
		EmployeeDTO employeeDTO = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Employee entity=null;
		try{
			entity= pm.getObjectById(Employee.class,key);
				if(entity!=null){
					employeeDTO = getEmployeeDTO(entity);
					//get employee's mobiles:
					List<MobileDTO> mobiles = new ArrayList<MobileDTO>();
					Query q = pm.newQuery(Mobile.class);
					q.setRange(0, 100);
					//Query q =pm.newQuery("SELECT c FROM ChemicalInfo c WHERE c.name == 'Tej Pat ka Patta'");
					//q.setFilter("keyOwner == '"+keyOwner+"'");//this is not working;
					List<Mobile>  lstMobilesFromDb = (List<Mobile>) q.execute();
					//String[] arrMobiles = entity.getMobiles();
					//System.out.println("size:"+lstMobilesFromDb.size()+", keyOwner:"+keyOwner);
					for(Mobile mobile:lstMobilesFromDb){
						if(mobile.getKeyOwner()==key){
							MobileDTO dto = new MobileDTO(mobile.getNumber(), mobile.getKeyOwner());
							dto.setKey(mobile.getKey());
							mobiles.add(dto);
						}
					}
					//putting the list in employee dto:
					employeeDTO.setMobiles(mobiles);
					//getting mobile codes upto here>
					//get Phones:
					q = pm.newQuery(EmployeePhone.class);
					q.setRange(0, 100);
					List<EmployeePhoneDTO> phones = new ArrayList<EmployeePhoneDTO>();
					try{
						List<EmployeePhone>  lstPhonesFromDb = (List<EmployeePhone>) q.execute();
						for(EmployeePhone phoneEntity:lstPhonesFromDb){
							if(phoneEntity.getKeyOwner()==key){
								EmployeePhoneDTO phoneDTO = new EmployeePhoneDTO(
										phoneEntity.getCountryCode(),
										phoneEntity.getAreaCode(),
										phoneEntity.getNumber());
								phoneDTO.setKey(phoneEntity.getKey());
								phoneDTO.setKeyOwner(phoneEntity.getKeyOwner());
								phones.add(phoneDTO);
							}
						}
						
					}catch(Exception e){
						System.out.println("AdminDAO::getEmployee by key exception in getting phones");
					}
					employeeDTO.setPhones(phones);
					//get emails:
					q = pm.newQuery(EmployeeEMail.class);
					q.setRange(0, 100);
					List<EmployeeEmailDTO> emails = new ArrayList<EmployeeEmailDTO>();
					try{
						List<EmployeeEMail>  lstEmailsFromDb = (List<EmployeeEMail>) q.execute();
						for(EmployeeEMail emailEntity:lstEmailsFromDb){
							if(emailEntity.getKeyOwner()==key){
								EmployeeEmailDTO emailDTO = new EmployeeEmailDTO(
										emailEntity.getAddress());
										
								emailDTO.setKey(emailEntity.getKey());
								emailDTO.setKeyOwner(emailEntity.getKeyOwner());
								emails.add(emailDTO);
							}
						}
						
					}catch(Exception e){
						System.out.println("AdminDAO::getEmployee by key exception in getting emails");
					}
					employeeDTO.setEmails(emails);
					//get the addresses:
					List<AddressDTO> addresses = new ArrayList<AddressDTO>();
					q = pm.newQuery(EmployeeAddress.class);
					q.setRange(0, 100);
					
					
					List<EmployeeAddress> addressesEntities = (List<EmployeeAddress>) q.execute();
					
					for(EmployeeAddress a:addressesEntities){
						if(a.getKeyOwner().equals(key)){
							AddressDTO addressDTO = new AddressDTO();
							addressDTO.setKey(a.getKey());
							addressDTO.setCity(a.getCity());
							addressDTO.setState(a.getState());
							addressDTO.setCountry(a.getCountry());
							//get addresses:
							String[] lines = a.getLines();
							List<String> lstLines = new ArrayList<String>();
							for(String line:lines){
								lstLines.add(line);
								
							}
							addressDTO.setAddressLines(lstLines);
							addresses.add(addressDTO);
						}
					}
					employeeDTO.setAddresses(addresses);
				
				}
			}catch(Exception e){
				
			}finally{
				pm.close();
			}
		return employeeDTO;
	}
	public String updateEmployee(Long key, EmployeeDTO dto, int code){
		String response = "";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Employee entity= pm.getObjectById(Employee.class,key);
			if(entity!=null){
				switch(code){
				case 0://all data are to be updated
					entity.setName(dto.getName());
					entity.setDesignation(dto.getDesignation());
					break;
				case 1://only name is to be updated:
					entity.setName(dto.getName());break;
				case 2: //only designation is to be updated:
					entity.setDesignation(dto.getDesignation());break;
				case 3: //only mobiles are to be updated:
					break;
				case 4: //only emails are to be updated:
					break;
					default:
						//nothing
					
				}
				response="Employee updated successfully";
				
				
				
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			response="Error in updating the employee.";
			
		}finally{
			pm.close();
		}
		return response;
	}
	public String updateEmployeeAddress(AddressDTO dto, int code){
		String response = "";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Long key = dto.getKey();
		if(key==null) return "303";
		try{
			EmployeeAddress entity= pm.getObjectById(EmployeeAddress.class, dto.getKey());
			if(entity!=null){
				switch(code){
				case 0://all data are to be updated
					//entity.setName(dto.getName());
					//entity.setDesignation(dto.getDesignation());
					break;
				case 1://only city name is to be updated:
					entity.setCity(dto.getCity());break;
				case 2: //only state is to be updated:
					entity.setState(dto.getState());break;
				case 3: //only country are to be updated:
					entity.setCountry(dto.getCountry());break;
					
				case 4: //only emails are to be updated:
					break;
				case 5: //address lines update:
					List<String> strLines = dto.getAddressLines();
					String[] lines = new String[strLines.size()];
					int i = 0;
					for(String item:strLines){
						lines[i] = item;
						i++;
						
					}
					entity.setLines(lines);
					break;
				default:
						//nothing
					
				}
				//response="Employee Address updated successfully";
				response="202";//for success
				
				
				
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			response="Error in updating the employee address.";
			
		}finally{
			pm.close();
		}
		return response;
	}
	@SuppressWarnings("unchecked")
	public List<AddressDTO> getEmployeeAddresses(){
		List<AddressDTO> addresses = new ArrayList<AddressDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			//get the mobiles:
			Query q = pm.newQuery(EmployeeAddress.class);
			q.setRange(0, 100);
			
			List<EmployeeAddress>  lstMobilesFromDb = (List<EmployeeAddress>) q.execute();
			//String[] arrMobiles = entity.getMobiles();
			//System.out.println("size:"+lstMobilesFromDb.size()+", keyOwner:"+keyOwner);
			for(EmployeeAddress a:lstMobilesFromDb){
				
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setKey(a.getKey());
				addressDTO.setCity(a.getCity());
				addressDTO.setState(a.getState());
				addressDTO.setCountry(a.getCountry());
				//get addresses:
				String[] lines = a.getLines();
				List<String> lstLines = new ArrayList<String>();
				for(String line:lines){
					lstLines.add(line);
					
				}
				addressDTO.setAddressLines(lstLines);
				
				addresses.add(addressDTO);
				
			}
			
		
	}catch(Exception e){
			
	}finally{
		pm.close();
	}
	return addresses;
	}
	@SuppressWarnings("unchecked")
	public List<MobileDTO> getEmployeeMobiles(){
		List<MobileDTO> mobiles = new ArrayList<MobileDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
				//get the mobiles:
				Query q = pm.newQuery(Mobile.class);
				q.setRange(0, 100);
				
				List<Mobile>  lstMobilesFromDb = (List<Mobile>) q.execute();
				//String[] arrMobiles = entity.getMobiles();
				//System.out.println("size:"+lstMobilesFromDb.size()+", keyOwner:"+keyOwner);
				for(Mobile mobile:lstMobilesFromDb){
					
						MobileDTO dto = new MobileDTO(mobile.getNumber(), mobile.getKeyOwner());
						dto.setKey(mobile.getKey());
						mobiles.add(dto);
					
				}
				
			
		}catch(Exception e){
				
		}finally{
			pm.close();
		}
		return mobiles;
	}
	@SuppressWarnings("unchecked")
	public List<MobileDTO> getEmployeeMobilesByKey(Long keyOwner){
		List<MobileDTO> mobiles = new ArrayList<MobileDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Employee entity=null;
		try{
			entity= pm.getObjectById(Employee.class,keyOwner);
			if(entity!=null){
				//get the mobiles:
				Query q = pm.newQuery(Mobile.class);
				q.setRange(0, 100);
				//Query q =pm.newQuery("SELECT c FROM ChemicalInfo c WHERE c.name == 'Tej Pat ka Patta'");
				//q.setFilter("keyOwner == '"+keyOwner+"'");//this is not working;
				List<Mobile>  lstMobilesFromDb = (List<Mobile>) q.execute();
				//String[] arrMobiles = entity.getMobiles();
				//System.out.println("size:"+lstMobilesFromDb.size()+", keyOwner:"+keyOwner);
				for(Mobile mobile:lstMobilesFromDb){
					if(mobile.getKeyOwner()==keyOwner){
						MobileDTO dto = new MobileDTO(mobile.getNumber(), mobile.getKeyOwner());
						dto.setKey(mobile.getKey());
						mobiles.add(dto);
					}
				}
				
			}
		}catch(Exception e){
				
		}finally{
			pm.close();
		}
		return mobiles;
	}
	@SuppressWarnings("unchecked")
	public MobileUpdateDTO addEmployeeMobile(Long keyOwner, String mobileNumber){
		String response = "";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		MobileUpdateDTO output = new MobileUpdateDTO();
		try{
			Employee entity= pm.getObjectById(Employee.class, keyOwner);
			if(entity!=null){
				//check if the given mobile number is already present:
				//Pankaj Note: June 20 2015 this query does not work find out why:
				Query q = pm.newQuery(Mobile.class);
				q.setRange(0, 100);
				//Query q =pm.newQuery("SELECT mobile FROM Mobile mobile WHERE mobile.number == '"+mobileNumber+"'");
				//This query works:
				
				q.setFilter("number == '"+mobileNumber+"'");
				List<Mobile>  lstMobilesFromDb = (List<Mobile>) q.execute();
				if(lstMobilesFromDb.isEmpty()){
					Mobile entityMobile = new Mobile(mobileNumber, keyOwner);
					pm.makePersistent(entityMobile);
					//Employee moible added successfully
					response="mobile-add-success";
					MobileDTO updatedMobile = new MobileDTO();
					updatedMobile.setKey(entityMobile.getKey());
					output.setUpdatedMobile(updatedMobile);
				}else{
					response="mobile-add-error-duplicate-entry";
				}
				
				
				

			}else{
				response="mobile-add-error-no-employee";
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			response="Error in adding the employee mobile.";
			
		}finally{
			pm.close();
		}
		output.setResponse(response);
		return output;
	}
	/*
	 * code = 3 for deleting the mobile
	 */
	public String updateMobile(MobileDTO dto, int code){
		String strResponse = "";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(dto.getKey()==null){
			strResponse = "mobile-key-null";
			return strResponse;
		}
		try{
			Mobile entity= pm.getObjectById(Mobile.class, dto.getKey());
			if(entity!=null){
				switch(code){
				case 0://all data are to be updated
					entity.setNumber(dto.getNumber());
					entity.setKeyOwner(dto.getKeyOwner());
					strResponse="Mobile-update-success";
					break;
				case 1://only number is to be updated:
					entity.setNumber(dto.getNumber());
					strResponse="Mobile-number-update-success";
					break;
				case 2: //only owner is to be updated:
					entity.setKeyOwner(dto.getKeyOwner());
					strResponse="Mobile-owner-update-success";
					break;
				case 3: //to delete the current mobile:
					pm.deletePersistent(entity);
					strResponse="Mobile-delete-success";
					break;
				case 4: //only emails are to be updated:
					break;
					default:
						//nothing
					
				}
				
				
				
				
			}
		}catch(javax.jdo.JDOObjectNotFoundException e){
			strResponse="Mobile-update-error";
			
		}finally{
			pm.close();
		}
		return strResponse;
	}
	@SuppressWarnings("unchecked")
	public String deleteEmployee(EmployeeDTO dto){
		String response = "";
		Long key = dto.getKey();
		if(key==null){
			return response="got a null key";
			
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Employee entity= pm.getObjectById(Employee.class,key);
			if(entity!=null){
				
			    
				pm.deletePersistent(entity);
				response+="Employee deleted successfully";
				//now delete the mobiles:
				//get the mobiles:
				//But from the client it is mobiles can not be got
				//so we need to have it here :
				Query q = pm.newQuery(Mobile.class);
				q.setRange(0, 100);
				try{
					List<Mobile>  lstMobilesFromDb = (List<Mobile>) q.execute();
					List<Long> mobilesKeys = new ArrayList<Long>();
					//System.out.println("size:"+lstMobilesFromDb.size()+", keyOwner:"+keyOwner);
					//log.log(Level.INFO, "In mobile list...");
					for(Mobile mobile:lstMobilesFromDb){
						//log.log(Level.INFO, "In mobile list...2, key owner:"+mobile.getKeyOwner()+", key:"+key);
						if(mobile.getKeyOwner().equals(key)) mobilesKeys.add(mobile.getKey());


					}
				
					for(Long mKey:mobilesKeys){

						try{
							Mobile mEntity=pm.getObjectById(Mobile.class,mKey);
							if(mEntity!=null){ 
								pm.deletePersistent(mEntity);
								response+=" Mobile deleted. ";
							}else response+=" mobile not found. ";
						}catch(javax.jdo.JDOObjectNotFoundException e){
							response+=" Error in getting mobile ";
						}
					}
				}catch(Exception e){
					
				}//for deleting mobiles
				//now delete phones:
				q = pm.newQuery(EmployeePhone.class);
				q.setRange(0, 100);
				try{
					List<EmployeePhone>  lstPhonesFromDb = (List<EmployeePhone>) q.execute();
					List<Long> phonesKeys = new ArrayList<Long>();
					//System.out.println("size:"+lstMobilesFromDb.size()+", keyOwner:"+keyOwner);
					for(EmployeePhone phone:lstPhonesFromDb){
						if(phone.getKeyOwner().equals(key)) phonesKeys.add(phone.getKey());


					}
				
					for(Long pKey:phonesKeys){

						try{
							EmployeePhone pEntity=pm.getObjectById(EmployeePhone.class,pKey);
							if(pEntity!=null){ 
								pm.deletePersistent(pEntity);
								response+=" Phone deleted. ";
							}else response+=" Phone not found. ";
						}catch(javax.jdo.JDOObjectNotFoundException e){
							response+=" Error in getting Phone ";
						}
					}
				}catch(Exception e){
					
				}//for deleting phones
				//now delete emails:
				q = pm.newQuery(EmployeeEMail.class);
				q.setRange(0, 100);
				try{
					List<EmployeeEMail>  lstEmailsFromDb = (List<EmployeeEMail>) q.execute();
					List<Long> emailsKeys = new ArrayList<Long>();
					//System.out.println("size:"+lstMobilesFromDb.size()+", keyOwner:"+keyOwner);
					for(EmployeeEMail email:lstEmailsFromDb){
						if(email.getKeyOwner().equals(key)) emailsKeys.add(email.getKey());


					}
				
					for(Long eKey:emailsKeys){

						try{
							EmployeeEMail eEntity=pm.getObjectById(EmployeeEMail.class,eKey);
							if(eEntity!=null){ 
								pm.deletePersistent(eEntity);
								response+=" Email deleted. ";
							}else response+=" Email not found. ";
						}catch(javax.jdo.JDOObjectNotFoundException e){
							response+=" Error in getting Email ";
						}
					}
				}catch(Exception e){
					
				}//for deleting emails
				//now delete addresses:
				
				q = pm.newQuery(EmployeeAddress.class);
				q.setRange(0, 100);
				try{
					List<EmployeeAddress>  lstAddressesFromDb = (List<EmployeeAddress>) q.execute();
					List<Long> addressesKeys = new ArrayList<Long>();
					//System.out.println("size:"+lstMobilesFromDb.size()+", keyOwner:"+keyOwner);
					for(EmployeeAddress addressEntity:lstAddressesFromDb){
						if(addressEntity.getKeyOwner().equals(key)) addressesKeys.add(addressEntity.getKey());


					}
				
					for(Long addressKey:addressesKeys){

						try{
							EmployeeAddress addressEntity=pm.getObjectById(EmployeeAddress.class,addressKey);
							if(addressEntity!=null){ 
								pm.deletePersistent(addressEntity);
								response+=" Address deleted. ";
							}else response+=" Address not found. ";
						}catch(javax.jdo.JDOObjectNotFoundException e){
							response+=" Error in getting Address ";
						}
					}
				}catch(Exception e){
					
				}//for deleting addresses
				 
			}else response+="No Such employee was found.";
		}catch(javax.jdo.JDOObjectNotFoundException e){
			response="Employee was not found";
		}finally{
			pm.close();
		}	
		return response;
	}
	private EmployeeDTO getEmployeeDTO(Employee entity){
		EmployeeDTO dto = new EmployeeDTO(entity.getName(), entity.getDesignation());
		dto.setKey(entity.getKey());
		return dto;
	}
	public String  saveUser(UserDTO userDto){
		String output="";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		//get the user:
		UserService userService = UserServiceFactory.getUserService();
		
			    
		User user = userService.getCurrentUser();
		if(user!=null){
			// it means the user is logged in:
			// get the email address and create its data
			try{
				UserInfo entity = new UserInfo(user.getEmail());
				entity.setName(user.getNickname());
				if(user.getEmail().equals("ayuroma07@gmail.com")){
					entity.setAdminLevel(UserSettings.ADMIN_LEVEL_ONE);
					int[] access=userDto.getAccessSections();
					//make all accessible:
					for(int i=0;i<access.length;i++) access[i]=1;
					entity.setAccessSections(access);
				}else{
					
					entity.setAccessSections(userDto.getAccessSections());
				}
				//get the access sections:
				//entity.setAccessSections(user.g)
				
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
		}else{
			System.out.println("AdminDAO:: user got from user service is NULL. ");
		}

		return output;
	}
	public List<UserDTO> deleteUser(String email,String doerEmail){
		
		
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
				
				UserInfo entity= pm.getObjectById(UserInfo.class,email);
				if(entity!=null){
					pm.deletePersistent(entity);
					
				}
				
			}catch(Exception e){
				
			}
			finally{
				//q.closeAll();
				pm.close();
			}
		
		
		return getUsers(doerEmail);
	}
	public boolean hasDoerPrivilego(String email,int minPrivilegoLevel){
		boolean hasPrivilego=false;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			
			UserInfo entity= pm.getObjectById(UserInfo.class,email);
			if(entity!=null){
				//check if the doer has minimum level of privilego:
				
				//only user admin level 0 or 1 can update other users:
				if(entity.getAdminLevel()<=minPrivilegoLevel){
					
					hasPrivilego=true;
				}else{
					
					hasPrivilego=false;
				}
			}
		}catch(Exception e){
			
		}
		finally{
			//q.closeAll();
			pm.close();
		}
		return hasPrivilego;
	}

}
