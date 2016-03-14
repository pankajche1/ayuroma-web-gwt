package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.mobile.model.ProductItem;
import com.ayurma.ayuromaweb.client.mobile.model.ProductsGroupMenuItem;
import com.ayurma.ayuromaweb.shared.ProductGroupItemsData;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
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
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;

public class ProductGroupViewTemp implements IProductGroupMobileView {
	private Logger logger = Logger.getLogger("");
	private CellList<ProductItem> list;
	private RootFlexPanel main;
	private AboutImageButton aboutButton;
	private HeaderPanel headerPanel;
	private HeaderTitle headerPanelTitle = new HeaderTitle();
	private FlowPanel content;
	private ScrollPanel scrollPanel;
	private ProgressIndicator progressIndicator;
	

	public ProductGroupViewTemp() {
		//main Panel:
		main = new RootFlexPanel();
		//headerPanle on the main Panel:
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
		//now the scrollPanel for showing the data:
		scrollPanel = new ScrollPanel();
		//FlowPanel basePanel = new FlowPanel();
		content = new FlowPanel();
		//content.getElement().getStyle().setMarginTop(20, Unit.PX);
		//logger.log(Level.INFO, "I am here product group view");
		//content.add(new ProgressBar());

		progressIndicator = new ProgressIndicator();
		progressIndicator.getElement().setAttribute("style", "margin:auto; margin-top: 50px");

		//content.add(progressIndicator);

		//scrollPanel.setWidget(content);


		list = new CellList<ProductItem>(new BasicCell<ProductItem>() {

			@Override
			public String getDisplayString(ProductItem model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(ProductItem model) {
				return true;
			}
		});
		

		//FlowPanel container = new FlowPanel();
		//container.add(list);
		//content.add(list);
		//content.add(list);
		scrollPanel.setWidget(content);
		//basePanel.add(content);
		//scrollPanel.setWidget(container);
		scrollPanel.setScrollingEnabledX(false);
		//scrollPanel.setShowVerticalScrollBar(true);
		//scrollPanel.setSnap(true);
		//scrollPanel.setScrollLock(false);

		main.add(scrollPanel);
		//main.add(basePanel);
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
	public HasCellSelectedHandler getCellSelectedHandler() {
		return list;
	}

	@Override
	public void setProducts(List<ProductItem> listProductItems) {
		
		list.render(listProductItems);
		scrollPanel.refresh();
		

	}

	@Override
	public void clearList(List<ProductItem> listProductItems) {
		list.render(listProductItems);
		
	}

	@Override
	public void showProgressIndicator() {
		
		content.clear();
		content.add(progressIndicator);
		scrollPanel.refresh();
		
	}

	@Override
	public void showListView() {
		content.clear();
		content.add(list);
		
		
		
		
	}

	@Override
	public HasTapHandlers getHeaderRightButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAjaxAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showData(ProductGroupItemsData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}

}
