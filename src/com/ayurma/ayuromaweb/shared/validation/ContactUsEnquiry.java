package com.ayurma.ayuromaweb.shared.validation;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
public class ContactUsEnquiry implements Serializable{
	@NotEmpty(message="Please fill your name")
	@Size(max=500,message="Name too long!")
	private String name;
	
	@NotEmpty(message="Please fill your email address")
	@Size(max=500,message="Email address too long!")
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	               message="Please give a valid email address.")
	private String email;
	/*
	@NotEmpty(message="Please fill the codes")
	@Size(max=500,message="Codes too long!")
	private String codeCaptcha;
	*/
	@NotEmpty(message="Please write a message")
	@Size(max=500,message="Message must be less than 500 letters.")
	private String message;
	
	@Size(max=500,message="Company name too long!")
	private String companyName;

	//city name
	@Size(max=100,message="City name too long!")
	private String cityName;
	
	//mobile data
	@Size(max=5,message="Country code is not valid!")
	private String areaCodeMobile;
	
	@Size(max=100,message="Mobile number too long!")
	private String mobile;
	
	//phone data
	@Size(max=10,message="Country code is not valid!")
	private String isdCode;
	
	@Size(max=20,message="Area code is not valid!")
	private String areaCodePhone;
	
	@Size(max=100,message="Phone number too long!")
	private String phone;
	
	@Size(max=500,message="Country name too long!")
	private String country;
	/*
	//reCaptcha response:
	@NotEmpty(message="Please fill the varification code.")
	@Size(max=200,message="Varification code is too long!")
	@NotNull(message="Please fill the varification code.")
	private String response;
	*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/*
	public String getCodeCaptcha() {
		return codeCaptcha;
	}
	public void setCodeCaptcha(String codeCaptcha) {
		this.codeCaptcha = codeCaptcha;
	}
	*/
	public void setAreaCodeMobile(String areaCodeMobile) {
		this.areaCodeMobile = areaCodeMobile;
	}
	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}
	public void setAreaCodePhone(String areaCodePhone) {
		this.areaCodePhone = areaCodePhone;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/*public void setResponse(String response) {
		this.response = response;
	}*/
	
}
