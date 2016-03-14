package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MobileUpdateDTO  implements Serializable{
	private List<MobileDTO> mobiles = new ArrayList<MobileDTO>();
	private MobileDTO updatedMobile;
	private String response;
	

	public List<MobileDTO> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<MobileDTO> mobiles) {
		this.mobiles = mobiles;
	}
	public MobileDTO getUpdatedMobile() {
		return updatedMobile;
	}
	public void setUpdatedMobile(MobileDTO updatedMobile) {
		this.updatedMobile = updatedMobile;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	

}
