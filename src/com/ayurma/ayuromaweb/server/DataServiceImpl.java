package com.ayurma.ayuromaweb.server;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.ayurma.ayuromaweb.client.service.DataService;
import com.ayurma.ayuromaweb.server.dao.AdminDAO;
import com.ayurma.ayuromaweb.server.dao.DAO;
import com.ayurma.ayuromaweb.server.dao.PMF;
import com.ayurma.ayuromaweb.server.model.ChemicalInfo;
import com.ayurma.ayuromaweb.server.model.ProductIndex;
import com.ayurma.ayuromaweb.shared.AdminInfoDTO;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ContactUsDTO;
import com.ayurma.ayuromaweb.shared.NameSuggestDTO;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.ayurma.ayuromaweb.shared.ProductEnquiryDTO;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.ayurma.ayuromaweb.shared.SliderFilmItemsDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class DataServiceImpl extends RemoteServiceServlet implements DataService {
	private static final Logger log = Logger.getLogger(DataServiceImpl.class.getName());

	@Override
	public NameSuggestDTO suggest(String queryString) {
		
		NameSuggestDTO dto = new NameSuggestDTO();
		List<String> names = new ArrayList<String>();
		List<Long> keysProducts = new ArrayList<Long>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String keyword=queryString.toLowerCase();
		try{
			ProductIndex index = pm.getObjectById(ProductIndex.class, keyword);
			if(index!=null){
				List<Long> keys = index.getKeys();
				ChemicalInfo product = null;
				for(int i=0;i<keys.size();i++){
					try{
						product=pm.getObjectById(ChemicalInfo.class, keys.get(i));
						if(product!=null){
							names.add(product.getName());
							keysProducts.add(keys.get(i));
							//suggestions.add(product.getName());
						}
					}catch(Exception e){
						
					}
				}
			
				//names.add("Pankaj");keysProducts.add(1L);
				//names.add("Amit");keysProducts.add(2L);
				//names.add("Rohit");keysProducts.add(3L);
				dto = new NameSuggestDTO(names,keysProducts);
			}
					
		}catch(Exception e){
			
		}finally{
			pm.close();
		}
		return dto;
	}

	@Override
	public ChemicalData getProductByKey(Long key) {
		
		return DAO.get().getProductByKey(key);
	}

	@Override
	public ChemicalData getProductByName(String name) {
		
		return DAO.get().getProductByName(name);
	}

	@Override
	public ProductGroupItemsData getProductGroupsItemsData(Long key) {
		
		return DAO.get().getProductGroupsItemsData(key);
	}
	private String sendMail(String mailSubject,String mailBody){
		//getting the admin email:
		AdminInfoDTO dto = AdminDAO.get().getAdminInfo();
		String receiverName=dto.name;
		String receiverEmail=dto.email;
		//String receiverName="Pankaj Kumar Lodhi";
		//String receiverEmail="pankajche1@gmail.com";
		Properties props = new Properties();
	     Session session = Session.getDefaultInstance(props, null);
	     try
	     {
	         Message msg = new MimeMessage(session);
	         msg.setFrom(new InternetAddress("ayuroma.kanpur@gmail.com", "Ayuroma Centre, Kanpur"));
	         msg.addRecipient(Message.RecipientType.TO,
	                          new InternetAddress(receiverEmail, "Mr. "+receiverName));
	         //msg.addRecipient(Message.RecipientType.CC, new InternetAddress("pankajche1@gmail.com", "Mr. "+"Pankaj Kumar"));
	         msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("pankajche1@gmail.com", "Mr. "+"Pankaj Kumar"));
	         //subject of the mail:
	         msg.setSubject(mailSubject);
	         
	         //body of the mail:
	         msg.setText(mailBody);
	         //msg.set
	         //msg.setContent(mailBody, "text/html");       
	         //msg.set
	         Transport.send(msg);
	        // System.out.println("Mail sent with body:"+mailBody);
	         log.info(mailBody);
	     }
	     catch (AddressException e)
	     {
	      //e.printStackTrace();
	      //return e.getLocalizedMessage();
	      return "Error in seding mail";
	     }
	     catch (MessagingException e)
	     {
	     // e.printStackTrace();
	     // return e.getLocalizedMessage();
	      return "Error in seding mail";
	     }
	     catch (UnsupportedEncodingException e)
	     {
	      // e.printStackTrace();
	      // return e.getLocalizedMessage();
	       return "Error in seding mail";
	     }
	     return "Mail sent successfully";
	}

	@Override
	public String submitContactEnquiry(ContactUsDTO data) {
		//checking for the spam bot:
		String strHoneyPot = data.getTextHidden();
		if(!strHoneyPot.equals("")) return "";//return "Possible spam bot";
		//preparation for the email send:
		String mailBody="\n\nTo,\nThe Administrator,\nAyuroma Centre\n\n";
		mailBody+="SUBJECT: Enquiry\n\n";
		
		mailBody+="SENDER INFORMATION:\n---------------------------\n";
		
		mailBody+="Name: "+data.getNameUser()+"\n";
		mailBody+="Email: "+data.getEmail()+"\n";
		mailBody+="Phone: "+data.getIsdCode()+"  "+data.getAreaCodePhone()+"  "+data.getPhone()+"\n";
		mailBody+="Mobile: "+data.getCountryCallingCode()+"  "+data.getMobile()+"\n";
		mailBody+="Company Name: "+data.getCompanyName()+"\n";
		mailBody+="City: "+data.getCityName()+"\n";
		mailBody+="Country: "+data.getCountry()+"\n\n";
		mailBody+="MESSAGE:\n---------------------------\n";
		mailBody+=data.getMessage();
		mailBody+="\n---------------------------\n\n";
		mailBody+="Thanks";
		//sending the mail:
		String message = sendMail("Contact us enquiry", mailBody);
		return message;
	}

	@Override
	public String submitProductEnquiry(ProductEnquiryDTO data) {
		
		//checking for the spam bot:
		String strHoneyPot = data.getTextHidden();
		if(!strHoneyPot.equals("")) return "";//return "Possible spam bot";
		//preparation for the email send:
		String mailBody="\n\nTo,\nThe Administrator,\nAyuroma Centre\n\n";
		mailBody+="SUBJECT: Request for the enquiry about the product '"+data.getProductName()+"'\n\n";
		mailBody+="SENDER INFORMATION:\n---------------------------\n";
		mailBody+="Name: "+data.getNameUser()+"\n";
		mailBody+="Email: "+data.getEmail()+"\n";
		mailBody+="Phone: "+data.getIsdCode()+"  "+data.getAreaCodePhone()+"  "+data.getPhone()+"\n";
		mailBody+="Mobile: "+data.getCountryCallingCode()+"  "+data.getMobile()+"\n";
		mailBody+="Company Name: "+data.getCompanyName()+"\n";
		mailBody+="City: "+data.getCityName()+"\n";
		mailBody+="Country: "+data.getCountry()+"\n\n";
		mailBody+="MESSAGE:\n---------------------------\n";
		mailBody+=data.getMessage();
		mailBody+="\n---------------------------\n\n";
		mailBody+="Thanks";
		//sending the mail to the administrator
		String messageToClient=sendMail("Equiry for "+data.getProductName(),mailBody);
		//now saving the enquiry:
		//DAO.get().addEnquiry(data);//the second item in the array will take the message to the user
		return messageToClient;
	}

	@Override
	public String submitProductReportsRequest(ProductEnquiryDTO data) {
		//checking for the spam bot:
		String strHoneyPot = data.getTextHidden();
		if(!strHoneyPot.equals("")) return "";//return "Possible spam bot";

		String mailBody="\n\nTo,\nThe Administrator,\nAyuroma Centre\n\n";
		mailBody+="Subject: request for the product report/reports\n\n";
		mailBody+="SENDER INFORMATION:\n---------------------------\n";
		mailBody+="Name: "+data.getNameUser()+"\n";
		mailBody+="Email: "+data.getEmail()+"\n";
		mailBody+="Phone: "+data.getIsdCode()+"  "+data.getAreaCodePhone()+"  "+data.getPhone()+"\n";
		mailBody+="Mobile: "+data.getCountryCallingCode()+"  "+data.getMobile()+"\n";
		mailBody+="Company Name: "+data.getCompanyName()+"\n";
		mailBody+="City: "+data.getCityName()+"\n";
		mailBody+="Country: "+data.getCountry()+"\n\n";
		mailBody+="MESSAGE:\n---------------------------\n";
		//get the reports equest message:
		mailBody+="The above user has requested for these reports:\n\n";
		mailBody+="Product Name: "+data.getProductName()+"\n";
		mailBody+=data.getMessage();
		mailBody+="\n---------------------------\n\n";
		mailBody+="Thanks";
		String messageToClient=sendMail("Reports request for "+data.getProductName(),mailBody);
		return messageToClient;
	}

	@Override
	public ProductDetails getProductDetails(Long key) {
		
		return DAO.get().getProductDetailsByKey(key);
	}

	@Override
	public SliderFilmItemsDTO getSliderFilmItems(Long key, int iStart,int nItems) {
		
		return DAO.get().getSliderFilmItems(key, iStart,nItems);
	}
}
