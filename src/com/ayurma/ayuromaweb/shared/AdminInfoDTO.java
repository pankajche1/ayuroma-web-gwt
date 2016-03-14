package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AdminInfoDTO implements Serializable{
public String name="";
public String email="";
public AdminInfoDTO(String name, String email) {
	super();
	this.name = name;
	this.email = email;
}
public AdminInfoDTO() {
	
}


}
