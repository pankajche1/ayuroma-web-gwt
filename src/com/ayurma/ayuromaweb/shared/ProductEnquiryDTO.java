package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ProductEnquiryDTO  implements Serializable {
	private String productName="";
	private String message;//message from the user
	private String nameUser;
	private String email;
	private Date date;
	private String companyName;
	private String cityName;
	//mobile data
	private String countryCallingCode;
	private String mobile;
	//phone data
	private String isdCode;
	private String areaCodePhone;
	private String phone;
	private String country;
	//honey pot text:
	private String textHidden;
	//reCaptcha challenge:
	private String challenge;
	//reCaptcha response:
	private String response;
	//products reports mess
	public ProductEnquiryDTO() {
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryCallingCode() {
		return countryCallingCode;
	}
	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIsdCode() {
		return isdCode;
	}
	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}
	public String getAreaCodePhone() {
		return areaCodePhone;
	}
	public void setAreaCodePhone(String areaCodePhone) {
		this.areaCodePhone = areaCodePhone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getChallenge() {
		return challenge;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getTextHidden() {
		return textHidden;
	}
	public void setTextHidden(String textHidden) {
		this.textHidden = textHidden;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
