package com.ayurma.ayuromaweb.client.admin.view;


import java.util.List;

import com.ayurma.ayuromaweb.client.admin.activity.ISliderImageBrowserConnector;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.ayurma.ayuromaweb.shared.SliderImageDTO;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface ISliderImageView {
	public interface Presenter{
		void loadSliderImages(String strNPage, String strItemsPerPage);
		void deleteImage(Long imageInfoKey);
		
		void setSelected(int id, boolean isSelected);
		void clearImageCacheData();
	}
	Widget asWidget();
	void info(String message,int id);
	void setPresenter(Presenter presenter);
	
	
	void showData(List<SliderImageDTO> images, int displayMode);
	
	HasClickHandlers getExportButton();
	List<Boolean> getSelectedImages();
	void setDataPresenter(ISliderImageBrowserConnector imgDataReceiver);
	void setDisplayMode(int id);
	void clear();
	void init(int displayMode);
	
}
