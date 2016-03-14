package com.ayurma.ayuromaweb.client.admin.view.sldieredit;

import com.ayurma.ayuromaweb.client.admin.view.ISliderEditView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FilmItemInterface extends Composite {

	private static FilmItemInterfaceUiBinder uiBinder = GWT
			.create(FilmItemInterfaceUiBinder.class);

	interface FilmItemInterfaceUiBinder extends
			UiBinder<Widget, FilmItemInterface> {
	}
	private ISliderEditView.Presenter presenter;
	private int id;
	public FilmItemInterface(int id) {
		initWidget(uiBinder.createAndBindUi(this));
		this.id=id;
	}
	public void setPresenter(ISliderEditView.Presenter presenter){
		this.presenter=presenter;
		
	}
	@UiHandler("btnChangeImage")
	public void changeImage(ClickEvent e){
		presenter.changeImage(id);
		
	}
	@UiHandler("btnEditLink")
	public void editLink(ClickEvent e){
		presenter.changeLink(id);
		
	}
	@UiHandler("btnRemove")
	public void removeItem(ClickEvent e){
		confirmDelete();
		
	}
	private void confirmDelete(){
		boolean option=Window.confirm("Do you want to delete the item?");
		if(option){
		 presenter.removeItem(id);	
		}
	}
}
