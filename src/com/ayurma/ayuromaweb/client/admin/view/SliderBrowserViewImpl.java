package com.ayurma.ayuromaweb.client.admin.view;

import java.util.List;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class SliderBrowserViewImpl extends Composite implements ISliderBrowserView{

	private static SliderBrowserViewImplUiBinder uiBinder = GWT
			.create(SliderBrowserViewImplUiBinder.class);

	interface SliderBrowserViewImplUiBinder extends
			UiBinder<Widget, SliderBrowserViewImpl> {
	}
	interface MyStyle extends CssResource{
		String floatRight();
		String film();
		String clearBoth();
	}
	@UiField
	HTMLPanel dataPanel;
	@UiField
	MyStyle style;
	@UiField InlineLabel lblMainMessage;
	private Presenter presenter;
	public SliderBrowserViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	
	@UiHandler("btnLoadFilms")
	void onLoadFilms(ClickEvent event){
		presenter.loadFilms(1,100);
	}
	/*
	private class DeleteHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Button btn = (Button) event.getSource();
			String strId = btn.getElement().getId();
			int id = Integer.valueOf(strId);
			
			confirmDelete(id);
			
		}
		
	}
	private void confirmDelete(int id){
		boolean option=Window.confirm("Do you want to delete the film?");
		if(option){
			presenter.deleteFilm(id);
			
		}
	}
	private class DisplayButtonHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Button btn = (Button) event.getSource();
			String strId = btn.getElement().getId();
			int id = Integer.valueOf(strId);
			presenter.displayFilm(id);
			
		}
		
	}
	private class EditButtonHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Button btn = (Button) event.getSource();
			String strId = btn.getElement().getId();
			int id = Integer.valueOf(strId);
			presenter.showEditSliderView(id);
			
		}
		
	}
	*/
	@Override
	public void clear(){
		dataPanel.clear();
	}
	@Override
	public void info(String message,int type){
		lblMainMessage.setText(message);
	}
	@Override
	public void setFilms(List<String> titles, List<String> datesCreation,
			List<String> datesEdit) {
		int i=0;
		dataPanel.clear();
		//HTMLPanel film;
		//Button btn;
		//DisplayButtonHandler displayHandler = new DisplayButtonHandler();
		//DeleteHandler deleteHandler = new DeleteHandler();
		//EditButtonHandler editHandler = new EditButtonHandler();
		for(String title:titles){
			SliderFilmUi ui=new SliderFilmUi(title,i);
			/*
			film = new HTMLPanel(title);
			film.setStyleName(style.film());
			btn = new Button("Display");
			btn.getElement().setId(String.valueOf(i));
			btn.addClickHandler(displayHandler);
			btn.addStyleName(style.floatRight());
			film.add(btn);
			btn = new Button("Delete");
			btn.getElement().setId(String.valueOf(i));
			btn.addClickHandler(deleteHandler);
			btn.addStyleName(style.floatRight());
			film.add(btn);
			btn = new Button("Edit");
			btn.getElement().setId(String.valueOf(i));
			btn.addClickHandler(editHandler);
			btn.addStyleName(style.floatRight());
			film.add(btn);
			*/
			dataPanel.add(ui);
			i++;
		}
		
		
	}
	private class SliderFilmUi extends HTMLPanel implements ClickHandler{
		Button btnDefault;
		Button btnEdit;
		Button btnDelete;
		private int id;
		public SliderFilmUi(String title,int id){
			super(title);
			this.id=id;
			setStyleName(style.film());
			//to set the film to default to show on the site
			btnDefault = new Button("Default");
			btnDefault.addClickHandler(this);
			btnDefault.addStyleName(style.floatRight());
			add(btnDefault);
			//to edit the film:
			btnEdit = new Button("Edit");
			btnEdit.addClickHandler(this);
			btnEdit.addStyleName(style.floatRight());
			add(btnEdit);
			//to delete the film:
			btnDelete = new Button("Delete");
			btnDelete.addClickHandler(this);
			btnDelete.addStyleName(style.floatRight());
			add(btnDelete);
		}

		@Override
		public void onClick(ClickEvent event) {
			Widget widget = (Widget) event.getSource();
			if(widget==btnDefault){
				presenter.displayFilm(id);
			}else if(widget==btnEdit){
				presenter.showEditSliderView(id);
			}else if(widget==btnDelete){
				confirmDelete(id);
			}
			
		}
		private void confirmDelete(int id){
			boolean option=Window.confirm("Do you want to delete the film?");
			if(option){
				presenter.deleteFilm(id);
				
			}
		}
	}
}
