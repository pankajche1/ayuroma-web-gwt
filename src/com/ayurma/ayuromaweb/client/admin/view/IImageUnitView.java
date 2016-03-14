package com.ayurma.ayuromaweb.client.admin.view;




import com.google.gwt.user.client.ui.Widget;

public interface IImageUnitView {
	public interface Presenter{

		void copyImageUrl(String imageUrl);

		void deleteImage(Long imageInfoKey);
		
	}
	Widget asWidget();
	void info(String message,int id);
	void setPresenter(Presenter presenter);
	//void setMainPresenter(BrowseImagesActivity mainPresenter);
	void setMainPresenter(IBrowseImagesView.Presenter mainPresenter);
}
