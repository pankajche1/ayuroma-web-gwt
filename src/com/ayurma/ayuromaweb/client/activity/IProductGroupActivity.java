package com.ayurma.ayuromaweb.client.activity;

import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;



public interface IProductGroupActivity {
	void processDataFromServer(ProductGroupItemsData result);
	void showData(ProductGroupItemsData targetData);
	void info(String msg, int id, int type);
	void stopAjaxAnim();
	void startAjaxAnim();
	void setTargetData(ProductGroupItemsData targetData);
	int getDisplayMode();
	void setGroupData(String[] namesProducts, Long[] idProducts, Long[] idDetails);
	
}
