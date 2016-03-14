package com.ayurma.ayuromaweb.server.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ImageData {
	   @PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Long key;
	    @Persistent
	    private String imageInfo="";
		@Persistent
		private Date dateCreation;
		@Persistent
		private Date dateEdit;
		@Persistent
	    private Text imageUrl=new Text("");

		public ImageData(String imageInfo, String imageUrl) {
			
			this.imageInfo = imageInfo;
			this.imageUrl = new Text(imageUrl);
			dateCreation=new Date();
		}

		public Long getKey() {
			return key;
		}

		public void setKey(Long key) {
			this.key = key;
		}

		public String getImageInfo() {
			return imageInfo;
		}

		public void setImageInfo(String imageInfo) {
			this.imageInfo = imageInfo;
		}

		public String getImageUrl() {
			return imageUrl.getValue();
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = new Text(imageUrl);
		}
		public Date getDateEdit() {
			return dateEdit;
		}
		public void setDateEdit() {
			dateEdit = new Date();
		}

		public Date getDateCreation() {
			return dateCreation;
		}	
		
}
