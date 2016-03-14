package com.ayurma.ayuromaweb.client.activity;

import com.ayurma.ayuromaweb.shared.ProductDetails;

public interface IProductDetailsActivity {
	void setData(ProductDetails data);
	void showAjaxLoading();
	//void reset();
	void stopAjaxLoading();
	ProductDetails getTargetData();

	void setTargetData(ProductDetails targetData);
		
}
