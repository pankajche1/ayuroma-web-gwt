package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.mobile.model.HomeMenuItem;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.image.AboutImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;


public class HomeViewTemp implements IHomeView{
	private RootFlexPanel main;
	private AboutImageButton aboutButton;
	private HeaderPanel headerPanel;
	private HeaderTitle headerPanelTitle = new HeaderTitle();
	private CellList<HomeMenuItem> cellList;
	public HomeViewTemp(){
		main = new RootFlexPanel();

		headerPanel = new HeaderPanel();

		headerPanel.add(new FixedSpacer());
		headerPanel.add(new FlexSpacer());
		headerPanel.add(headerPanelTitle);
		headerPanel.add(new FlexSpacer());

		aboutButton = new AboutImageButton();
		if (MGWT.getFormFactor().isPhone()) {
			headerPanel.add(aboutButton);
		} else {
			headerPanel.add(new FixedSpacer());
		}



		main.add(headerPanel);
		
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


    FlowPanel container = new FlowPanel();
    container.add(cellList);


		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setWidget(container);
		scrollPanel.setScrollingEnabledX(false);
		main.add(scrollPanel);
		
		
	}

	@Override
	public Widget asWidget() {
		
		return main;
	}

	@Override
	public void setTitle(String text) {
		headerPanelTitle.setText(text);
		
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
