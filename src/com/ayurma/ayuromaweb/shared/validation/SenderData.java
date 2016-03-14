package com.ayurma.ayuromaweb.shared.validation;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
public class SenderData implements Serializable  {
	@NotEmpty(message="Please fill your name")
	@Size(max=500,message="Name too long!")
	private String name;
	
	@NotEmpty(message="Please fill your email address")
	@Size(max=500,message="Email address too long!")
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	               message="Please give a valid email address.")
	private String email;

	
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

	public SenderData() {
		
	}

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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaCodeMobile() {
		return areaCodeMobile;
	}

	public void setAreaCodeMobile(String areaCodeMobile) {
		this.areaCodeMobile = areaCodeMobile;
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
	
}
