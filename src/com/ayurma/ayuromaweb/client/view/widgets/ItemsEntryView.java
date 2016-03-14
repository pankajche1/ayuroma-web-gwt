package com.ayurma.ayuromaweb.client.view.widgets;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.view.IHomeView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class ItemsEntryView extends Composite implements IItemsEntryView{

	private static ItemsEntryViewUiBinder uiBinder = GWT
			.create(ItemsEntryViewUiBinder.class);

	interface ItemsEntryViewUiBinder extends UiBinder<Widget, ItemsEntryView> {
	}
	private IHomeView.Presenter presenter;
	interface MyCss extends CssResource{
		 //the 4 product catagory ad for links:
		 String productCatPanel();
		 String productCatLink();
	}
	@UiField HTMLPanel rootPanel;
	@UiField MyCss style;
	private List<ItemEntryUnit> listItems;
	public ItemsEntryView() {
		initWidget(uiBinder.createAndBindUi(this));
		/*
		String[] linkKeys={"43","43","43","43"};
		String[] images={"images/productGroups/ac.jpg",
				"images/productGroups/ao.jpg",
				"images/productGroups/co.jpg",
				"images/productGroups/neo.jpg"};
		String[] headings={"Aromatic Chemicals","Aromatherampy Oils","Carrier Oils",
				"Natural Essential Oils"};
		String text="hello, this is a general text to be filled here. this will be" +
				"updated soon. Thanks.";
		listItems = new ArrayList<ItemEntryUnit>() ;
		for(int i=0;i<images.length;i++){
			ItemEntryUnit unit = new ItemEntryUnit();
			unit.setContentText(text);
			unit.setHeading(headings[i]);
			unit.setImageUrl(images[i]);
			unit.setKeyGroup(linkKeys[i]);
			unit.setStyleName(style.productCatLink());
			listItems.add(unit);
			rootPanel.add(unit);
		}
		*/
	}
	@Override
	public void setPresenter(IHomeView.Presenter presenter) {
		this.presenter=presenter;
		//for(ItemEntryUnit unit:listItems){
			//unit.setPresenter(this.presenter);
		//}
	}
	public void setData(List<String> headTexts,List<String> bodyTexts,List<String> imageUrls,
			List<String> linkUrls){
		if(listItems==null){
			listItems = new ArrayList<ItemEntryUnit>() ;
			rootPanel.clear();
			for(int i=0;i<headTexts.size();i++){
				ItemEntryUnit unit = new ItemEntryUnit();
				unit.setContentText(bodyTexts.get(i));
				unit.setHeading(headTexts.get(i));
				unit.setImageUrl(imageUrls.get(i));
				unit.setKeyGroup(linkUrls.get(i));
				unit.setStyleName(style.productCatLink());
				unit.setPresenter(this.presenter);
				listItems.add(unit);
				rootPanel.add(unit);
			}
		}
	}


}
