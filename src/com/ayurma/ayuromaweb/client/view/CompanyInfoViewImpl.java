package com.ayurma.ayuromaweb.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CompanyInfoViewImpl extends Composite {

	private static CompanyInfoViewImplUiBinder uiBinder = GWT
			.create(CompanyInfoViewImplUiBinder.class);

	interface CompanyInfoViewImplUiBinder extends
			UiBinder<Widget, CompanyInfoViewImpl> {
	}
	public interface MyStyle extends CssResource{
		String phoneNumber();
		
	}
	private boolean isInfoSet=false;
	
	public CompanyInfoViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	@UiField Label lblCompanyHead;
	@UiField HTMLPanel addressPanel,phonesPanel,mobilesPanel,emailsPanel;
	@UiField MyStyle style;
	public void setCompanyInfo(String companyHead,List<String> addressLines,
			String city, String pin, String country,
			List<String> mobiles, List<String> phones,List<String> emails){
		if(!isInfoSet){
			//TODO set the info here
			//1 company head
			lblCompanyHead.setText(companyHead);
			//2 address
			setAddress(addressLines,city,pin,country);
			//3 phones:
			setPhones(phones);
			//4 mobiles:
			setMobiles(mobiles);
			//5 emails:
			setEmails(emails);
			
			isInfoSet=true;
		}
		
	}
	private void setAddress(List<String> addressLines,String city,String pin,String country){
		addressPanel.clear();
		//make the html string:
		String str="";
		for(String line:addressLines){
			str+=line+"<br/>";
		}
		str+=city+" "+pin+"<br/>";
		str+=country;
		addressPanel.getElement().setInnerHTML(str);
	
	}
	private void setPhones(List<String> phones){
		phonesPanel.clear();
		if(phones==null){
			return;
		}else{
			for(String phone:phones){
				Label lblPhone = new Label(phone);
				lblPhone.setStyleName(style.phoneNumber());
				phonesPanel.add(lblPhone);
			}
		}
	}
	private void setMobiles(List<String> mobiles){
		mobilesPanel.clear();
		if(mobiles==null){
			return;
		}else{
			for(String phone:mobiles){
				Label lblPhone = new Label(phone);
				lblPhone.setStyleName(style.phoneNumber());
				mobilesPanel.add(lblPhone);
			}
		}
	}
	private void setEmails(List<String> emails){
		emailsPanel.clear();
		if(emails==null){
			return;
		}else{
			for(String email:emails){
				Label lblEmail = new Label(email);
				lblEmail.setStyleName(style.phoneNumber());
				emailsPanel.add(lblEmail);
			}
		}
	}	
}
