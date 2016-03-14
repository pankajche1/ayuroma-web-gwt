package com.ayurma.ayuromaweb.client.mobile.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ayurma.ayuromaweb.client.layout.ProductViewLayout;
import com.ayurma.ayuromaweb.client.mobile.view.header.HeaderMobileView;
import com.ayurma.ayuromaweb.client.mobile.view.resource.MainClientBundleMobile;
import com.ayurma.ayuromaweb.client.mobile.view.resource.MainClientBundleMobile.ProductStyle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.panel.Panel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;
//import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;


public class ProductMobileView<T> extends Composite implements IProductMobileView<T>{

	private static ProductViewUiBinder uiBinder = GWT
			.create(ProductViewUiBinder.class);
	@SuppressWarnings("rawtypes")
	interface ProductViewUiBinder extends UiBinder<Widget, ProductMobileView> {
	}
	private List<ProductViewLayout<T>> layouts;
	private Logger logger = Logger.getLogger("");
	@UiField protected HeaderMobileView headerView;
	
	@UiField protected Label lblProductName;
	@UiField protected HTMLPanel productBody;
	@UiField protected HTMLPanel imagePanel;
	@UiField protected HTMLPanel descriptionPanel;
	@UiField protected Button btnSendEnquiry;
	@UiField protected Button btnGetReports;
	@UiField protected Button btnShowDetails;
	@UiField protected ProgressIndicator progressIndicator;
	@UiField protected HTMLPanel contentPanel;
	@UiField protected ScrollPanel scrollPanel;
	
	private Map<String,String> styles;
	private Image image;
	private HandlerRegistration imgHandler;
	//private ProgressIndicatorWidget progressIndicator;
	//private ProductInfoWidget productInfoWidget;
	
	public ProductMobileView() {
		initWidget(uiBinder.createAndBindUi(this));
		scrollPanel.setScrollLock(false); //THIS IS IMPORTANT AND DONE IN THE UI BINDER FILE
		ProductStyle style = MainClientBundleMobile.INSTANCE.productStyle();
		style.ensureInjected();
		//setting the style list:
		styles = new HashMap<String,String>();
		styles.put("image-box",style.productImageBox());//0
		styles.put("image",style.productImage());//1
		styles.put("product-description",style.productDescription());//2
		headerView.setButtonRightLabel("Home");
		headerView.setHeaderTitleText("Ayuroma Centre");
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
	public void showData(T data) {
		
		contentPanel.setVisible(true);
		StringBuilder sb = new StringBuilder();
		layouts.get(0).render(data,sb,styles);
		
		//0 product Name:
		lblProductName.setText(sb.toString());
		//1 get the image url:
		sb = new StringBuilder();
		layouts.get(1).render(data, sb,styles);
		String imageUrl=sb.toString();
		if(!imageUrl.equals("")){
			image = new Image(imageUrl);
			imgHandler = image.addLoadHandler(new LoadHandler(){

				@Override
				public void onLoad(LoadEvent event) {
					//logger.log(Level.INFO, "Image Loaded");
					image.setAltText("Image");
					image.setStyleName(styles.get("image"));
					imgHandler.removeHandler();
					scrollPanel.refresh();
					
					
					
			
					
				}});
			//Image.prefetch(imageUrl);
			
			//image.
			imagePanel.setStyleName(styles.get("image-box"));
			imagePanel.add(image);
			
		}else imagePanel.setStyleName(styles.get("image-box"));
		//3 get the description:
		sb = new StringBuilder();
		layouts.get(2).render(data,sb,styles);
		descriptionPanel.getElement().setInnerHTML(sb.toString());
		//TODO
		
		//productBody.getElement().setInnerHTML(sb.toString());
		
		
		
		scrollPanel.refresh();
		
	}
	@Override
	public void setLayouts(List<ProductViewLayout<T>> layouts) {
		this.layouts = layouts;
		
	}
	@Override
	public void setPresenter(
			Presenter<T> presenter) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showAjaxAnim() {
		
		
		progressIndicator.setVisible(true);
		contentPanel.setVisible(false);

		//scrollPanel.refresh();
		
	}
	@Override
	public void stopAjaxAnim() {
		
		progressIndicator.setVisible(false);
		
		
	}
	@Override
	public void reset() {
		contentPanel.setVisible(false);
		//clearing the already existing data:
		imagePanel.clear();

	}
	@Override
	public void scrollTo(int left, int top) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void info(String msg, int id, int type) {
		System.out.println("ProductMobileView::info(), msg"+msg+", id:"+id+", type:"+type);
		
	}
	@Override
	public HasTapHandlers getButtonSendEnquiry() {
		
		return btnSendEnquiry;
		//return new TapHandlersMock();
	}
	@Override
	public HasTapHandlers getButtonGetReports() {
		
		return btnGetReports;
		//return new TapHandlersMock();
	}
	@Override
	public HasTapHandlers getButtonShowDetails() {
		
		return btnShowDetails;
		//return new TapHandlersMock();
	}
	/*
	private class TapHandlersMock implements HasTapHandlers{

		@Override
		public HandlerRegistration addTapHandler(TapHandler handler) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	*/

}
