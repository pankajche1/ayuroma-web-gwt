package com.ayurma.ayuromaweb.client.admin.view;

import java.util.List;


import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;

public interface IImagesView {
	public interface Presenter{
		void loadImages(String strNPage,String strItemsPerPage);
		void getImageUploadUrl();
		void copyImageUrl(String url);
		void deleteImage(Long key);
		void showView(int viewId);
	}
	Widget asWidget();
	void info(String message,int id);
	void setPresenter(Presenter presenter);
	void setImageData(List<ImageDataDTO> images);
	void setImageUploadUrl(String url);
	AcceptsOneWidget get(String id);
	void init();
	void updateLayout(int displayMode);
}
