package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;




import com.allen_sauer.gwt.dnd.client.DragEndEvent;
import com.allen_sauer.gwt.dnd.client.DragHandler;
import com.allen_sauer.gwt.dnd.client.DragStartEvent;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.VerticalPanelDropController;

import com.ayurma.ayuromaweb.client.admin.activity.SliderEditViewActivity;
import com.ayurma.ayuromaweb.client.admin.view.sldieredit.FilmItemInterface;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SliderEditViewImpl extends Composite implements  ISliderEditView{

	private static SliderEditViewImplUiBinder uiBinder = GWT
			.create(SliderEditViewImplUiBinder.class);

	interface SliderEditViewImplUiBinder extends
			UiBinder<Widget, SliderEditViewImpl> {
	}
	interface MyCss extends CssResource{
		String selectedImage();
		String normalImage();
		String item();
	}
	@UiField MyCss style;
	@UiField
	HTMLPanel leftPanel,btnsPanel,mainPanel;//,rightPanel;
	@UiField HorizontalPanel selectedImagesPanel;
	//@UiField
	//SimplePanel rightDataPanel;
	@UiField AbsolutePanel boundaryPanel;
	
	@UiField VerticalPanel columnCompositePanel;
	@UiField VerticalPanel filmItemsUiPanel;
	@UiField
	SimplePanel externalInjectionPanel;
	@UiField InlineLabel lblMainMessage;
	private VerticalPanel verticalPanel;
	
	private Presenter presenter;
	private List<ImageUnit> imageUnits;
	private ImageUnit selectedImageUnit;
	private PickupDragController widgetDragController;
	private VerticalPanelDropController widgetDropController;
	public SliderEditViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		 //verticalPanel = new VerticalPanelWithSpacer();
		 verticalPanel = new VerticalPanel();
		 columnCompositePanel.add(verticalPanel);
		
		 // initialize our widget drag controller
		 widgetDragController = new PickupDragController(boundaryPanel, false);
		 widgetDragController.setBehaviorMultipleSelection(false);
	     widgetDropController = new VerticalPanelDropController(
		            verticalPanel);
	     widgetDragController.registerDropController(widgetDropController);
		MyDragHandler dragHandler = new MyDragHandler();
		widgetDragController.addDragHandler(dragHandler);

	}
	@UiHandler("btnUpdateFilm")
	public void updateFilm(ClickEvent e){
		presenter.updateFilm();
	}
	@UiHandler("btnAddImages")
	public void addImages(ClickEvent e){
		presenter.onAddImagesClicked();
	}
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	@Override
	public void info(String string, int id) {
		switch(id){
		case 0:lblMainMessage.setText(string);
			break;
		}
		
	}
	@Override
	public void showFilmItems(List<String> imageUrls, List<String> names){
		ImageUnit item;
		verticalPanel.clear();
		filmItemsUiPanel.clear();
		imageUnits=new ArrayList<ImageUnit>();
		for(int i=0;i<imageUrls.size();i++){
			
			item = new ImageUnit(i,imageUrls.get(i),names.get(i));
			imageUnits.add(item);
			verticalPanel.add(item);
			FilmItemInterface ui= new FilmItemInterface(i);
			ui.setPresenter(presenter);
			filmItemsUiPanel.add(ui);
			widgetDragController.makeDraggable(item);
		}
	}
	@Override
	public void showImageBrowser(){
		leftPanel.removeFromParent();
	}
	@Override
	public AcceptsOneWidget getExternalPanel() {
		
		return externalInjectionPanel;
	}
	private class ImageUnit extends FocusPanel implements ClickHandler{
		private String imageUrl;
		private int id;
		private HTMLPanel rootPanel;
		private Label lblLinkedProduct;
		public ImageUnit(int id,String imageUrl,String name){
			
			this.imageUrl=imageUrl;
			this.id=id;
			Image img = new Image(imageUrl);
			img.setAltText("Image");
			img.setWidth("100px");
			rootPanel=new HTMLPanel("");
			rootPanel.add(img);
			//label for showing the name of the linked product:
			lblLinkedProduct = new Label(name);
			rootPanel.add(lblLinkedProduct);
			add(rootPanel);
			this.setStyleName(style.item());
			this.addClickHandler(this);
		}
		@Override
		public void onClick(ClickEvent event) {
			
			for(ImageUnit item:imageUnits){
				item.setStyleName(style.normalImage());
			}
			this.setStyleName(style.selectedImage());
			selectedImageUnit=this;
			//presenter.selectImage(id);
		}
		public int getId(){
			return this.id;
		}
		public void setLinkedProductName(String name){
			lblLinkedProduct.setText(name);
		}
	}
	private class MyDragHandler implements DragHandler{

		@Override
		public void onDragEnd(DragEndEvent event) {
			IsWidget item=(IsWidget) event.getSource();
		
			
			presenter.onDragEnd(verticalPanel.getWidgetIndex(item));
			
		}

		@Override
		public void onDragStart(DragStartEvent event) {
			IsWidget item=(IsWidget) event.getSource();
			
			
			presenter.onDragStart(verticalPanel.getWidgetIndex(item));
			
		}

		@Override
		public void onPreviewDragEnd(DragEndEvent event)
				throws VetoDragException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPreviewDragStart(DragStartEvent event)
				throws VetoDragException {
			// TODO Auto-generated method stub
			
		}
		
	}
	@Override
	public void clearExternalPanel() {
		externalInjectionPanel.clear();
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.ayurma.ayuromaweb.client.admin.view.ISliderEditView#reset(int)
	 * this method sets the layout of the view for various purposes
	 * 
	 */
	@Override
	public void reset(int code) {
		switch(code){
		case SliderEditViewActivity.DIALOG_LAYOUT://prepare the view interface for showing the slider image browser:
			//and also for showing the product browser:
			//1 hide the data Panel which shows the current slider film items:
			//selectedImagesPanel.setVisible(false);
			mainPanel.setVisible(false);
			externalInjectionPanel.setVisible(true);
			//btnsPanel.setVisible(false);
			break;
		case SliderEditViewActivity.MAIN_LAYOUT:
			//now for showing the default view of the interface
			//when the browse slider images dialog box is removed:
			//selectedImagesPanel.setVisible(true);
			mainPanel.setVisible(true);
			externalInjectionPanel.setVisible(false);
			//btnsPanel.setVisible(true);
			break;
		}
		
	}

}
