package com.ayurma.ayuromaweb.client.utils;


import com.ayurma.ayuromaweb.client.AppInitData;
//import com.ayurma.ayuromaweb.client.gin.AyuromaGinjector;
import com.ayurma.ayuromaweb.client.json.IAppInitData;
import com.ayurma.ayuromaweb.client.json.JSAppInitData;
import com.ayurma.ayuromaweb.client.json.JSCompanyInfo;
import com.ayurma.ayuromaweb.client.json.JSProductGroup;
import com.ayurma.ayuromaweb.client.json.JsAddress;
import com.ayurma.ayuromaweb.client.json.JsEmail;
import com.ayurma.ayuromaweb.client.json.JsMobile;
import com.ayurma.ayuromaweb.client.json.JsTelephone;
import com.ayurma.ayuromaweb.shared.CompanyInfoDTO;
//import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;

public class AppInitDataManager {
	//private AyuromaGinjector injector = GWT.create(AyuromaGinjector.class);
	public AppInitData createData(){
		//getting the app init data from javascript:
        IAppInitData appInitDataJs = getAppInitData();
        //getting the product groups from the above:
        JsArray<JSProductGroup> jsProductGroupsArr=appInitDataJs.getProductGroups();
        //AppInitData appInitData = injector.getAppInitData();
        //AppInitData appInitData = injector.getAppInitData();
        AppInitData appInitData = new AppInitData();
        for(int i=0;i<jsProductGroupsArr.length();i++){
        	appInitData.addProductGroup(jsProductGroupsArr.get(i).getName(),
        			jsProductGroupsArr.get(i).getGroupItemsId(),
        			jsProductGroupsArr.get(i).getImageUrl(),
        			jsProductGroupsArr.get(i).getGroupDisplayText());
        	//System.out.println("Name:"+jsProductGroupsArr.get(i).getName()
        			//+" Key:"+jsProductGroupsArr.get(i).getGroupItemsId());
        }
      //getting the company info address mobiles etc:
        JSCompanyInfo jsCompanyInfo = appInitDataJs.getCompanyInfo();
        CompanyInfoDTO companyInfo = new CompanyInfoDTO(jsCompanyInfo.getAddressHead(),
        		jsCompanyInfo.getPin(), jsCompanyInfo.getCity(),jsCompanyInfo.getCountry());
        JsArray<JsMobile> jsMobArr=jsCompanyInfo.getMobiles();
        JsArray<JsTelephone> jsTelPhoneArr=jsCompanyInfo.getTelephones();
        JsArray<JsAddress> jsAddressLinesArr=jsCompanyInfo.getAddressLines();
        JsArray<JsEmail> jsEmailsArr=jsCompanyInfo.getEmails();
        for(int i=0;i<jsAddressLinesArr.length();i++){
        	companyInfo.addAddressLine(jsAddressLinesArr.get(i).getLine());
        	
        }      
        for(int i=0;i<jsMobArr.length();i++){
        	companyInfo.addMobile(jsMobArr.get(i).getNumber());
        	
        }
        for(int i=0;i<jsTelPhoneArr.length();i++){
        	companyInfo.addTelephone(jsTelPhoneArr.get(i).getNumber());
        	
        }
        for(int i=0;i<jsEmailsArr.length();i++){
        	companyInfo.addEmail(jsEmailsArr.get(i).getEmail());
        	
        }
        appInitData.setCompanyInfo(companyInfo);
        return appInitData;
		
		
	}//createData
	private native JSAppInitData getAppInitData()/*-{
		return $wnd.appInitData;
	}-*/;
}
