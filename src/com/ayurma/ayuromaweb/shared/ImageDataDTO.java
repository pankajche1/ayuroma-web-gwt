package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ImageDataDTO implements Serializable{
	private Long key;
	private String imageInfo;
	private String imageKey;
	public ImageDataDTO() {
		
	}
	public ImageDataDTO(Long key, String imageInfo, String imageKey) {
		
		this.key = key;
		this.imageInfo = imageInfo;
		this.imageKey = imageKey;
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
	public String getImageKey() {
		return imageKey;
	}
	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}
	
}
