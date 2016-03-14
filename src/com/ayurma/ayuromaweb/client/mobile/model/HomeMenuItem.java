package com.ayurma.ayuromaweb.client.mobile.model;

import java.io.Serializable;

public class HomeMenuItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -134211444131752658L;

	private String name;

	private int count;

	public HomeMenuItem() {

	}

	public HomeMenuItem(String name, int count) {
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
