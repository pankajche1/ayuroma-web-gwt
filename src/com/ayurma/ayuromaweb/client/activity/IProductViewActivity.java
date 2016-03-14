package com.ayurma.ayuromaweb.client.activity;

import com.ayurma.ayuromaweb.shared.ChemicalData;

public interface IProductViewActivity {
	void processDataFromServer(ChemicalData chemical);
	void showData(ChemicalData chemical);
	void info(String msg, int id, int type);
	void stopAjaxAnim();
	void startAjaxAnim();
	
	
}
