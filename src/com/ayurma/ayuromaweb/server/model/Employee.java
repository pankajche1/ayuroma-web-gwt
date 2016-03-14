package com.ayurma.ayuromaweb.server.model;



import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Employee {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long key;
    
	@Persistent
	private String name;
	@Persistent
	private String designation; 
	@Persistent
	private String[] mobiles = new String[0];
	//@Persistent
	//private List<String> emails=new ArrayList<String>();
	
	//@Persistent
   // private List<String> phones=new ArrayList<String>();

	public Employee(String name, String designation) {
		
		this.name = name;
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	

	public Long getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void addMobile(String mobile){
		//create a temporary array:
		String[] temp = new String[mobiles.length];
		//now put the numbers here:
		int i = 0;
		for(String item:mobiles){
			//check if the mobile already exists:
			if(item.equals(mobile)) return;
			temp[i] = item;
			
			
			i++;
		}
		//now create a new array:
		mobiles = new String[temp.length+1];
		i = 0;
		for(String item:temp){
			
			mobiles[i] = item;
			
			
			i++;
		}
		//now add the new mobile:
		mobiles[i] = mobile;
		
		
	}
	public String[] getMobiles(){
		return mobiles;
	}

	

}
