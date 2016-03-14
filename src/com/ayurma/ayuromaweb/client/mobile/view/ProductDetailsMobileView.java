package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ayurma.ayuromaweb.client.layout.ProductDetailsLayout;
import com.ayurma.ayuromaweb.client.mobile.view.header.HeaderMobileView;

import com.ayurma.ayuromaweb.client.mobile.view.resource.MainClientBundleMobile;
import com.ayurma.ayuromaweb.client.mobile.view.resource.MainClientBundleMobile.ProductDetailsStyle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;
import com.googlecode.mgwt.ui.client.widget.tabbar.MostViewedTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;

public class ProductDetailsMobileView<T> extends Composite implements IProductDetailsMobileView<T>{

	private static ProductDetailsMobileViewUiBinder uiBinder = GWT
			.create(ProductDetailsMobileViewUiBinder.class);

	@SuppressWarnings("rawtypes")
	interface ProductDetailsMobileViewUiBinder extends
			UiBinder<Widget, ProductDetailsMobileView> {
	}
	private List<ProductDetailsLayout<T>> listLayouts; 
	@UiField protected HeaderMobileView headerView;
	//@UiField protected HeaderMobileView2 headerView;
	@UiField protected FlowPanel container;
	@UiField protected ScrollPanel scrollPanel;
	@UiField protected HTMLPanel contentPanel;
	@UiField protected Label lblHeading;
	@UiField protected HTMLPanel imagePanel;
	@UiField protected HTMLPanel descriptionPanel;
	@UiField protected HTMLPanel basicInfoPanel;
	@UiField protected HTMLPanel specPanel;
	@UiField protected HTMLPanel usesPanel;
	@UiField protected Button btnSendEnquiry;
	@UiField protected Button btnGetReports;
	
	@UiField protected ProgressIndicator progressIndicator;
	//@UiField protected TabPanel tabPanel;
	private Map<String,String> styles;
	
	public ProductDetailsMobileView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		ProductDetailsStyle style = MainClientBundleMobile.INSTANCE.productDetailsStyle();
		style.ensureInjected();
		//setting the style list:
		
		styles = new HashMap<String,String>();
		styles.put("alignCenter",style.alignCenter());//6
		styles.put("width_100",style.width_100());//6
		styles.put("width_50",style.width_50());//6
		styles.put("spacer",style.spacer());//6
		styles.put("rootPanel",style.rootPanel());//6
		styles.put("usesPanel",style.usesPanel());//6
		styles.put("heading2",style.heading2());//6
		styles.put("heading1",style.heading1());//6
		styles.put("h2",style.h2());//6
		styles.put("h3",style.h3());//6
		styles.put("button",style.button());//6
		styles.put("imagePanel",style.imagePanel());//0
		styles.put("productName",style.productName());//1
		styles.put("productImage",style.productImage());//1
		styles.put("productImageBox",style.productImageBox());//1
		styles.put("productDescription",style.productDescription());//2
		styles.put("valueName",style.valueName());//4
		styles.put("value",style.value());//5
		styles.put("tableTh1",style.tableTh1());//5
		styles.put("tableCaption",style.tableCaption());//5
		headerView.setButtonRightLabel("Home");
		//scrollPanel = new ScrollPanel();
		headerView.setHeaderTitleText("Ayuroma Centre");
		//tab:
		//tabPanel.add(new MostViewedTabBarButton(), new Label("Basic Info"));
		//tabPanel.add(new MostViewedTabBarButton(), new Label("Specifications"));
		//tabPanel.add(new MostViewedTabBarButton(), new Label("Uses"));

	}

	@Override
	public HasTapHandlers getHeaderRightButton() {

		return headerView.getButtonRight();
	}
	@Override
	public void setHeaderTitle(String text) {
		headerView.setHeaderTitleText(text);
		
	}
	@Override
	public void setPresenter(
			com.ayurma.ayuromaweb.client.view.IProductDetailsView.Presenter<T> presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListLayouts(List<ProductDetailsLayout<T>> listLayouts) {
		this.listLayouts = listLayouts;
		
	}

	@Override
	public void setData(T data) {
		contentPanel.setVisible(true);
		StringBuilder sb = new StringBuilder();
		//0 get the name:
		listLayouts.get(0).render(data, sb, styles);
		lblHeading.setText(sb.toString());
		//1 get the image url:
		sb = new StringBuilder();
		listLayouts.get(1).render(data, sb,styles);
		String imageUrl=sb.toString();
		if(!imageUrl.equals("")){
			Image image = new Image(imageUrl);
			image.setAltText("Image");
			image.setStyleName(styles.get("imageProduct"));
			imagePanel.add(image);
			imagePanel.setStyleName(styles.get("imagePanel"));
		}else imagePanel.setStyleName(styles.get("imagePanel"));
		sb = new StringBuilder();
		listLayouts.get(2).render(data, sb, styles);
		descriptionPanel.getElement().setInnerHTML(sb.toString());
		//3 setting the basic info (common name, bot name, country of origin etc)
		sb = new StringBuilder();
		listLayouts.get(3).render(data, sb, styles);
		basicInfoPanel.getElement().setInnerHTML(sb.toString());
		//4 getting the specification table which contains refractive index data etc.
		sb = new StringBuilder();
		listLayouts.get(4).render(data, sb,styles);
		specPanel.getElement().setInnerHTML(sb.toString());
		//5 getting the uses of the product:
		sb = new StringBuilder();
		listLayouts.get(5).render(data, sb,styles);
		
		usesPanel.getElement().setInnerHTML(sb.toString());
		
		
	}

	@Override
	public void showAjaxLoading() {
		progressIndicator.setVisible(true);
		contentPanel.setVisible(false);
		
	}

	@Override
	public void reset() {
		contentPanel.setVisible(false);
		//clearing the already existing data:
		imagePanel.clear();
		
	}

	@Override
	public void stopAjaxLoading() {
		progressIndicator.setVisible(false);
		
		
	}
	@Override
	public HasTapHandlers getButtonSendEnquiry() {
		
		return btnSendEnquiry;
	}
	@Override
	public HasTapHandlers getButtonGetReports() {
		
		return btnGetReports;
	}

}
