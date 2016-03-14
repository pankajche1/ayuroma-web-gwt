package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.mobile.model.HomeMenuItem;
import com.ayurma.ayuromaweb.client.mobile.view.header.HeaderMobileView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;

public class HomeView extends Composite implements IHomeView{

	private static HomeViewUiBinder uiBinder = GWT
			.create(HomeViewUiBinder.class);

	interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
	}
	@UiField protected HeaderMobileView headerView;
	@UiField protected FlowPanel container;
	private CellList<HomeMenuItem> cellList;
	public HomeView() {
		initWidget(uiBinder.createAndBindUi(this));
		//headerView.setButtonRightLabel("Home");
		cellList = new CellList<HomeMenuItem>(new BasicCell<HomeMenuItem>() {

			@Override
			public String getDisplayString(HomeMenuItem model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(HomeMenuItem model) {
				return true;
			}
		});


    
    container.add(cellList);
	headerView.setHeaderTitleText("Ayuroma Centre");
	headerView.setButtonRightVisible(false);
	}

	@Override
	public HasTapHandlers getAboutButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasCellSelectedHandler getCellSelectedHandler() {

		return cellList;
	}

	@Override
	public void setMenuItems(List<HomeMenuItem> lstMenuItems) {
		cellList.render(lstMenuItems);
		
	}

}
