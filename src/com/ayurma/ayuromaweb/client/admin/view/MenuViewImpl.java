package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuViewImpl extends Composite implements IMenu,ClickHandler{

	private static MenuViewImplUiBinder uiBinder = GWT
			.create(MenuViewImplUiBinder.class);

	interface MenuViewImplUiBinder extends UiBinder<Widget, MenuViewImpl> {
	}
	private Presenter presenter;
	@UiField HTMLPanel menuPanel;
	//@UiField Button btnHome, btnProducts, btnProductGroups, btnImages, btnSlider, btnTools, btnUsers;
    @UiField
    Styles style;
    private List<Widget> listMenu;
	private List<Button> buttons;
	interface Styles extends CssResource{
		String buttonContainer();
	}
	public MenuViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//mapMenus();
	}
	@Override
	public void createMenu(List<String> menus){
		menuPanel.clear();
		listMenu = new ArrayList<Widget>(); 
		for(String menu:menus){
			HTMLPanel panel = new HTMLPanel("");
			Button btnMenu = new Button(menu);
			btnMenu.addClickHandler(this);
			listMenu.add(btnMenu);
			panel.add(btnMenu);
			panel.setStyleName(style.buttonContainer());
			menuPanel.add(panel);
			
		}
		
	}
	/*
	public void mapMenus(){
		buttons = new ArrayList<Button>();
		buttons.add(btnHome);
		buttons.add(btnProducts);
		buttons.add(btnProductGroups);
		buttons.add(btnImages);
		buttons.add(btnSlider);
		buttons.add(btnTools);
		buttons.add(btnUsers);
		
	}
	*/
	public void setMenuVisible(int idMenu,boolean value){
		if(idMenu<buttons.size())
		buttons.get(idMenu).setVisible(value);
	}
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	/*
	@UiHandler("btnHome")
	public void gotoHome(ClickEvent e){
		presenter.gotoPlace(0);
	}
	
	@UiHandler("btnProducts")
	public void gotoPlace1(ClickEvent e){
		presenter.gotoPlace(1);
	}
	@UiHandler("btnProductGroups")
	public void gotoPlace2(ClickEvent e){
		presenter.gotoPlace(2);
	}
	@UiHandler("btnImages")
	public void gotoPlace3(ClickEvent e){
		presenter.gotoPlace(3);
	}
	@UiHandler("btnTools")
	public void gotoPlace4(ClickEvent e){
		presenter.gotoPlace(4);
	}
	@UiHandler("btnSlider")
	public void gotoPlace5(ClickEvent e){
		presenter.gotoPlace(5);
	}
	@UiHandler("btnUsers")
	public void gotoPlace6(ClickEvent e){
		presenter.gotoPlace(6);
	}
	*/
	@Override
	public void onClick(ClickEvent event) {
		Widget sender = (Widget) event.getSource();
		
		presenter.gotoPlace(listMenu.indexOf(sender));
		
	}



}
