package com.ayurma.ayuromaweb.server.model;


import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ProductIndex {
    @PrimaryKey
    @Persistent
    private String key;
    @Persistent
    private List<Long> keys=new ArrayList<Long>();
    
	public List<Long> getKeys() {
		return keys;
	}
	public void setKeys(List<Long> keys) {
		this.keys = keys;
	}
	public void addKey(Long keyIn){
		for(Long item:keys){
			if(item.equals(keyIn)) return;
		}
		keys.add(keyIn);
	}
	public String getKey() {
		return key;
	}
	public ProductIndex(String key) {
		
		this.key = key;
	}
	public void removeKey(Long keyToRemove){
		//search the key in the keys list:
		
		int i=0;
		for(i=0;i<keys.size();i++){
			if(keys.get(i).equals(keyToRemove)){
				//remove the key
				keys.remove(i);
				//now the size has changed so 
				//make the list to go without changing the i
				continue;
			}			
		}
		
	}
	public String toString(){
		String str="Letter '"+key+"', Items keys: [";
		
		if(keys.isEmpty())
		{
			str+="Empty]";
			return str;
		}
		
		for(Long itemKey:keys){
			str+=itemKey+" ";
		}
		str+="]";
		return str;
	}
	public Boolean isEmpty(){
		
		if(keys.size()<=0) return true;
		else return false;
	}
    
}
