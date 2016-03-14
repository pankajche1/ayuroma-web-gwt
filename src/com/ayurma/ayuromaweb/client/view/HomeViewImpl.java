package com.ayurma.ayuromaweb.client.view;




import java.util.List;

import com.ayurma.ayuromaweb.client.view.widgets.ItemsEntryView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.Widget;

public class HomeViewImpl extends Composite implements IHomeView{

	private static HomeViewImplUiBinder uiBinder = GWT
			.create(HomeViewImplUiBinder.class);

	interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
	}
	interface MyCss extends CssResource{
		 String intro();
		 String div1();
		 String productTypeDiv();
		 String clearBoth();
		 String adPanel1();	
		 //the 4 product catagory ad for links:
		 String productCatPanel();
		 String productCatLink();
	}
	private Presenter presenter;
	@UiField
	HTMLPanel rootPanel,sliderPanel;//,introductionPanel;
	@UiField HTMLPanel certificatePanel;
	//shows 100% customer satisfaction stuff
	@UiField HTMLPanel adPanel1;
	//shows ayuroma welcome stuff
	@UiField HTMLPanel introPanel;
	//shows four product catagories ad:
	@UiField HTMLPanel productCatPanel;
	@UiField
	MyCss style;
	private boolean isContentSliderSet=false;
	private ItemsEntryView productsGroupEntry;
	public HomeViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		//Image img=new Image();
		productsGroupEntry = new ItemsEntryView();
		productCatPanel.add(productsGroupEntry);
		
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		productsGroupEntry.setPresenter(presenter);
		
	}
	@Override
	public HasWidgets get(String id){
		if(id.equals("slider")){
			return sliderPanel;
		}else if(id.equals("intro")){
			return introPanel;
		}else if(id.equals("root")){
			return rootPanel;
		}
		return rootPanel;
	}
	@Override
	public boolean isContentSliderPut() {
		return isContentSliderSet;
	}
	@Override
	public void setContentSliderPut(boolean isContentSliderSet) {
		this.isContentSliderSet = isContentSliderSet;
	}
	@Override
	public void setProductGroupData(List<String> headTexts,List<String> bodyTexts,List<String> imageUrls,
			List<String> linkUrls){
		productsGroupEntry.setData(headTexts, bodyTexts, imageUrls, linkUrls);
	}
	@Override
	public void scrollTo(int left,int top){
		Window.scrollTo(left, top);
	}

}
