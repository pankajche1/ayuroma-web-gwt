package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.mobile.model.ProductsGroupMenuItem;
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




public class ProductsViewTemp implements IProductsView{
	private CellList<ProductsGroupMenuItem> list;
	private RootFlexPanel main;
	private AboutImageButton aboutButton;
	private HeaderPanel headerPanel;
	private HeaderTitle headerPanelTitle = new HeaderTitle();
	public ProductsViewTemp(){
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

		ScrollPanel scrollPanel = new ScrollPanel();

		list = new CellList<ProductsGroupMenuItem>(new BasicCell<ProductsGroupMenuItem>() {

			@Override
			public String getDisplayString(ProductsGroupMenuItem model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(ProductsGroupMenuItem model) {
				return true;
			}
		});

		FlowPanel container = new FlowPanel();
		container.add(list);

		scrollPanel.setWidget(container);
		scrollPanel.setScrollingEnabledX(false);

		main.add(scrollPanel);
		
		
	}
	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void setHeaderTitle(String text) {
		headerPanelTitle.setText(text);
		
	}



	@Override
	public HasCellSelectedHandler getCellSelectedHandler() {
		
		return list;
	}
	@Override
	public void setGroups(List<ProductsGroupMenuItem> groups) {
		list.render(groups);
		
	}
	@Override
	public HasTapHandlers getHeaderRightButton() {
		// TODO Auto-generated method stub
		return null;
	}


}
