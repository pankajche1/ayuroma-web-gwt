package com.ayurma.ayuromaweb.client.admin.view;




import java.util.List;

import com.ayurma.ayuromaweb.client.admin.view.IBrowseImagesView.IDataLinker;
import com.ayurma.ayuromaweb.shared.ImageDataDTO;
import com.google.gwt.user.client.ui.Widget;

public interface IBrowseImagesView {
	public interface Presenter{

		void loadImages(String text, String text2);

		void copyImageUrl(String imageUrl);

		void deleteImage(Long imageInfoKey);

		int getDisplayMode();

		void clearImageCacheData();
		
	}
	public interface IDataLinker{
		void onOkClick(int iSelectedImage);
		void onCancelClick();
	}

	void setPresenter(Presenter presenter);
	void info(String message,int id);
	
	Widget asWidget();
	
	void setDisplayMode(int displayMode);
	
	void setDataLinker(IDataLinker linker);
	void clear();
	void init(int displayMode);
	void showData(List<ImageDataDTO> images, int displayMode);

}
