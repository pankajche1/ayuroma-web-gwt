package com.ayurma.ayuromaweb.server.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class SliderImage {
	   @PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Long key;
	    @Persistent
	    private String imageInfo="";

		@Persistent
	    private Text imageUrl=new Text("");

		public SliderImage(String imageInfo, String imageUrl) {
			
			this.imageInfo = imageInfo;
			this.imageUrl = new Text(imageUrl);
		}

		public Long getKey() {
			return key;
		}

		public String getImageInfo() {
			return imageInfo;
		}

		public String getImageUrl() {
			return imageUrl.getValue();
		}


}
