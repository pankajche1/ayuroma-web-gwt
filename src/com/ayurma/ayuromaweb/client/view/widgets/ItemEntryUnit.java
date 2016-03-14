package com.ayurma.ayuromaweb.client.view.widgets;

import com.ayurma.ayuromaweb.client.view.IHomeView;
import com.ayurma.ayuromaweb.client.view.IHomeView.Presenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ItemEntryUnit extends Composite implements ClickHandler{

	private static ItemEntryUnitUiBinder uiBinder = GWT
			.create(ItemEntryUnitUiBinder.class);

	interface ItemEntryUnitUiBinder extends UiBinder<Widget, ItemEntryUnit> {
	}
	
    @UiField Image img;
    @UiField Label lblHeading;
    @UiField HTMLPanel contentPanel;
    @UiField FocusPanel rootPanel;
    private String keyGroup="";
    public String getKeyGroup() {
		return keyGroup;
	}
	public void setKeyGroup(String keyGroup) {
		this.keyGroup = keyGroup;
	}
	private IHomeView.Presenter presenter;
	public ItemEntryUnit() {
		initWidget(uiBinder.createAndBindUi(this));
		rootPanel.addClickHandler(this);
	}
	public void setImageUrl(String url){
		if(url!=null) img.setUrl(url);
	}
	public void setHeading(String heading){
		lblHeading.setText(heading);
	}
	public void setContentText(String text){
		contentPanel.getElement().setInnerHTML(text);
	}
	public void setPresenter(IHomeView.Presenter presenter){
		this.presenter=presenter;
	}
	@Override
	public void onClick(ClickEvent event) {
		presenter.gotoProductGroup(keyGroup);
		
	}
	

}
