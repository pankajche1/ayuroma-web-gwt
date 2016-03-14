package com.ayurma.ayuromaweb.client.admin.view;

import com.ayurma.ayuromaweb.client.admin.activity.SliderImagesBrowseActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SliderImageUnitImpl extends Composite {

	private static SliderImageUnitImplUiBinder uiBinder = GWT
			.create(SliderImageUnitImplUiBinder.class);

	interface SliderImageUnitImplUiBinder extends
			UiBinder<Widget, SliderImageUnitImpl> {
	}
	interface MyCss extends CssResource{
		String selection();
		String normal();
	}
	
	@UiField Button btnDelete;
	@UiField CheckBox chkSelect;//this is ticked for selecting the image
	@UiField HTMLPanel imagePanel,bottomBtnsPanel,rootPanel;
	@UiField Label lblImageInfo;
	//@UiField FocusPanel clickablePanel;
	@UiField FocusPanel hasClickHandlerPanel;
	@UiField MyCss style;
	private String imageUrl;
	private Long imageInfoKey;
	private ISliderImageView.Presenter presenter;
	//private int displayMode=0;
	private boolean isSelected=false;
	private int id;
	public SliderImageUnitImpl(int id) {
		initWidget(uiBinder.createAndBindUi(this));
		this.id=id;
	}
	public SliderImageUnitImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	public void setPresenter(ISliderImageView.Presenter presenter){
		this.presenter=presenter;
	}
	
	public void setDisplayMode(int displayMode){
		//this.displayMode=displayMode;
		switch(displayMode){
		case SliderImagesBrowseActivity.STANDALONE:
			bottomBtnsPanel.setVisible(true);
			btnDelete.setVisible(true);
			chkSelect.setVisible(false);
			break;
		case SliderImagesBrowseActivity.MULTISELECTION_DIALOG:
			bottomBtnsPanel.setVisible(true);
			btnDelete.setVisible(false);
			chkSelect.setVisible(true);
			break;
		case SliderImagesBrowseActivity.MOUSECLICK_SINGLESELECTION_DIALOG://for showing an image selected by mouse click
			bottomBtnsPanel.setVisible(false);
			break;
		case 3:	//for showing a checkbox with the image for multiple image selection
			bottomBtnsPanel.setVisible(true);
			//btnCopy.setVisible(false);
			btnDelete.setVisible(false);
			break;
		}
		
	}
	public void setImageInfoKey(Long imageInfoKey) {
		this.imageInfoKey = imageInfoKey;
	}
	public void setImageInfo(String info){
		lblImageInfo.setText(info);
	}
	public void setImageUrl(String servletUrl,String ImageUrl){
		imageUrl=ImageUrl;
		imagePanel.getElement().setInnerHTML("<img src='"+servletUrl+ImageUrl+"' alt='image' width='200px'></img>");
	}
	@UiHandler("btnDelete")
	void onDelete(ClickEvent event){
		confirmDelete();
	}
	@UiHandler("chkSelect")
	void onCheckSelect(ClickEvent event){
		isSelected = chkSelect.getValue();
		setSelected(isSelected);
		presenter.setSelected(id,isSelected);
		
	}
	
	private void confirmDelete(){
		boolean option=Window.confirm("Do you want to delete the image?");
		if(option){
			presenter.deleteImage(imageInfoKey);
			
		}
	}
	public HasClickHandlers getSelectionPanel(){
		return hasClickHandlerPanel;
	}
	public void setSelected(boolean isSelected){
		if(isSelected){
			hasClickHandlerPanel.removeStyleName(style.normal());
			hasClickHandlerPanel.addStyleName(style.selection());
		}else{
			hasClickHandlerPanel.removeStyleName(style.selection());
			hasClickHandlerPanel.addStyleName(style.normal());			
		}
	}


}
