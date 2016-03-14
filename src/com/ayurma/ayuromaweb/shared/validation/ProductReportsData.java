package com.ayurma.ayuromaweb.shared.validation;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

//import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
public class ProductReportsData implements Serializable {
	@NotEmpty(message="Please select at least one report.")
	@NotNull(message="Please select at least one report.")
	private String isReportSelected;

	public String getIsReportSelected() {
		return isReportSelected;
	}

	public void setIsReportSelected(String isReportSelected) {
		this.isReportSelected = isReportSelected;
	}
	
}
