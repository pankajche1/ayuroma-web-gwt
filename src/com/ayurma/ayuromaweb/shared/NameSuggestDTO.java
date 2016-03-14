package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class NameSuggestDTO  implements Serializable{
	String name;
	Long key;
	private List<String> names = new ArrayList<String>();
	private List<Long> keys = new ArrayList<Long>();
	public NameSuggestDTO() {
	
	}
	
	public NameSuggestDTO(List<String> names, List<Long> keys) {
	
		this.names = names;
		this.keys = keys;
	}

	public NameSuggestDTO(String name,Long key) {
		this.name=name;
		this.key=key;
	}
	
	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<Long> getKeys() {
		return keys;
	}

	public void setKeys(List<Long> keys) {
		this.keys = keys;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}

}
