package com.ayurma.ayuromaweb.server.model;



import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ProductGroupItems {
	   @PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Long key;
	   @Persistent
	   private Long keyParent;
	    @Persistent
	    private String name;
	    @Persistent
	    private Long[] itemsIds;
	    @Persistent
	    private Long[] itemsDetailsIds;
	    @Persistent
	    private String[] itemsNames;
		public ProductGroupItems() {
			itemsNames=new String[0];
			itemsIds=new Long[0];
			itemsDetailsIds=new Long[0];
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Long[] getItemsIds() {
			return itemsIds;
		}
		public void setItemsIds(Long[] itemsIds) {
			this.itemsIds = itemsIds;
		}
		public String[] getItemsNames() {
			return itemsNames;
		}
		public void setItemsNames(String[] itemsNames) {
			this.itemsNames = itemsNames;
		}
		public Long getKey() {
			return key;
		}

		public void addItemInfo(Long itemKey,String itemName,Long detailsKey){
			String[] tempNames=itemsNames;
			Long[] tempIds=itemsIds;
			Long[] tempDetailsId=itemsDetailsIds;
			
			
			int i=0;
			//find if the item is already present:
			for(i=0;i<itemsIds.length;i++){
				if(itemsIds[i].equals(itemKey)) return;
				
			}
			//increase the length of the keys array:
			itemsIds=new Long[tempIds.length+1];
			for(i=0;i<tempIds.length;i++){
				itemsIds[i]=tempIds[i];
			}
			itemsIds[itemsIds.length-1]=itemKey;	
			//increase the length of the name String array
			itemsNames=new String[tempNames.length+1];
			for(i=0;i<tempNames.length;i++){
				itemsNames[i]=tempNames[i];
			}
			itemsNames[itemsNames.length-1]=itemName;
			//increase the length of the details key array:
			itemsDetailsIds=new Long[tempIds.length+1];
			for(i=0;i<tempDetailsId.length;i++){
				itemsDetailsIds[i]=tempDetailsId[i];
			}			
			itemsDetailsIds[itemsDetailsIds.length-1]=	detailsKey;
			
		}
		public void removeItem(Long itemKey){
			//System.out.println("Inside remove got key:"+itemKey);
			//System.out.println("Before delete length:"+itemsIds.length);
			int i=0;
			int j=0;
			int iTarget=-1;
			Long[] tempIds=null;
			String[] tempNames=new String[0];
			Long[] tempDetailsId=null;
			for(i=0;i<itemsIds.length;i++){
				if(itemsIds[i].equals(itemKey)){
					tempIds=new Long[itemsIds.length-1];
					tempDetailsId=new Long[itemsIds.length-1];
					tempNames=new String[itemsIds.length-1];
					iTarget=i;
					//System.out.println("Inside got product at index:"+iTarget);
					break;
				}
				
			}
			if(tempIds!=null){
				j=0;
				for(i=0;i<itemsIds.length;i++){
					if(i!=iTarget){
						tempIds[j]=itemsIds[i];
						tempNames[j]=itemsNames[i];
						tempDetailsId[j]=itemsDetailsIds[i];
						j++;

					}
					
				}
				itemsIds=tempIds;
				itemsNames=tempNames;
				itemsDetailsIds=tempDetailsId;
			}
			//System.out.println("After delete length:"+itemsIds.length);
		}

		public Long getKeyParent() {
			return keyParent;
		}

		public void setKeyParent(Long keyParent) {
			this.keyParent = keyParent;
		}

		public Long[] getItemsDetailsIds() {
			return itemsDetailsIds;
		}

		public void setItemsDetailsIds(Long[] itemsDetailsIds) {
			this.itemsDetailsIds = itemsDetailsIds;
		}

}
