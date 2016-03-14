package com.ayurma.ayuromaweb.client.admin.util;

import java.util.ArrayList;
import java.util.List;

public class UserLevelData {
	private List<String> levelNames=new ArrayList<String>();
	private List<Integer> levelValues=new ArrayList<Integer>();
	public void addItem(String name,int value){
		levelNames.add(name);
		levelValues.add(value);
	}
	public int size(){
		return levelNames.size();
	}
	public String getName(int iItem){
		if(iItem<levelNames.size()) return levelNames.get(iItem);
		else return "size out of bound";
	}
	public int getValue(int iItem){
		if(iItem<levelValues.size()) return levelValues.get(iItem);
		else return -100;
	}
	public int getAdminLevelIndex(int adminLevel){
		int indexSelected=-1;
		
		for(int value:levelValues){
			if(adminLevel==value){
				
				indexSelected=levelValues.indexOf(value);
				break;
			}
		}
		return indexSelected;
		
	}
	
	public List<String> getLevelNames() {
		return levelNames;
	}
	public List<Integer> getLevelValues() {
		return levelValues;
	}
	@Override
	public String toString() {
		return "UserLevelData [levelNames=" + levelNames + ", levelValues="
				+ levelValues + "]";
	}
	

}
