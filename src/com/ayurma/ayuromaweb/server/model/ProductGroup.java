package com.ayurma.ayuromaweb.server.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ProductGroup {
	   @PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Long key;
	    @Persistent
	    private String name;
	    //@Persistent
	   // private String description;
	    @Persistent
	    private Text description=new Text("");
	    @Persistent
	    private Long keyItems=null;
	    @Persistent
	    private Text imageUrl=new Text("");
		public Long getKeyItems() {
			return keyItems;
		}
		public void setKeyItems(Long keyItems) {
			this.keyItems = keyItems;
		}
		public ProductGroup(String name, String description) {
			
			this.name = name;
			this.description = new Text(description);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description.getValue();
		}
		public void setDescription(String description) {
			this.description = new Text(description);
		}
		public Long getKey() {
			return key;
		}
		public String getImageUrl() {
			return imageUrl.getValue();
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = new Text(imageUrl);
		}
}
