package com.ayurma.ayuromaweb.client.admin.view;

import java.util.ArrayList;
import java.util.List;

//import com.ayurma.ayuromaweb.client.admin.template.ProductDetailsEditViewTemplate;

//import com.ayurma.ayuromaweb.client.resource.Resources;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.dom.client.FocusEvent;
//import com.google.gwt.event.dom.client.FocusHandler;
//import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
//import com.google.gwt.user.client.ui.Image;
//import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
//import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TabPanel;
//import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.RichTextArea.Formatter;
//import com.google.gwt.user.client.ui.RichTextArea.Justification;
import com.pankajche1.rtt.client.RichTextEditor;

public class ProductDetailsEditViewImpl extends Composite 
								implements IProductDetailsEditView{
	@UiTemplate("ProductDetailsEditView.ui.xml")
	interface ProductDetailsEditViewUiBinder extends UiBinder<Widget, ProductDetailsEditViewImpl> {
	}
	private static ProductDetailsEditViewUiBinder uiBinder = GWT.create(ProductDetailsEditViewUiBinder.class);
	
	private Presenter presenter;
	//private List<ProductDetailsEditViewTemplate<T>> template;
	//private Image imgLoading = new Image(Resources.INSTANCE.getLoadingAnimation());
	//private Image closeIcon= new Image(Resources.INSTANCE.getCloseIcon());
	//private Image tickIcon= new Image(Resources.INSTANCE.getTick());
	//private boolean isInStock=false;
	@UiField
	HTMLPanel rootPanel;
	@UiField
	HTMLPanel editorPanel;//this contains the user interface for editing
	//@UiField
	//InlineLabel ajaxPanel;
	@UiField
	InlineLabel txtName;	
	@UiField
	HTMLPanel dataPanel,desUpdatePanel,tabPanelContainer,usesUpdatePanel,
	basicInfoPanel,propertiesPanel,specificationPanel;//,usesPanel;
	//[description edit section:
	
	
	//on this panel new parawidgets or list will be put:
	//@UiField
	//HTMLPanel desPanel;
	//description edit section ends here]
	@UiField
	Button btnUpdateUse,btnUpdateSpGrav,
	    btnUpdateRfrInd,btnUpdateOptRot,btnUpdateFlsPnt,btnUpdateDes,btnUpdateGenInfo,btnUpdateSpcf;
	//@UiField
	//Button btnSave;
	//@UiField
	//HTMLPanel rta1;
	RichTextEditor dscrpEditor;
	RichTextEditor usesEditor;
	Formatter formatter;

	@UiField
	HTMLPanel rtaUses;
	
	//Formatter formatterUses;	
	//HandlerRegistration hr;
	//@UiField
	//TextArea taDescription;


	//@UiField
	//CheckBox chkInStock;
	//@UiField
	//TextBox txtImageUrl;	
	@UiField
	TextBox txtExtractionMethods;
	@UiField
	TextBox txtBotName;
	@UiField
	TextBox txtColorApperance;
	@UiField
	TextBox txtPlantParts;
	
	@UiField
	TextBox txtCommonName;
	@UiField
	TextBox txtCountryOrigin;
	@UiField
	TextBox txtChemConstituents;
	@UiField
	TextBox txtOrder;
	@UiField
	TextBox txtSolubility;
	//properties section display:
	@UiField
	CheckBox chkSec2Dspl;
	//specific gravity values:
	@UiField
	CheckBox chkDsplSpGrav;
	@UiField
	CheckBox chk0,chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8;
	
	//@UiField
	//CheckBox chk1;
	//@UiField
	//CheckBox chk2;
	/*
	@UiField
	CheckBox chk3;
	@UiField
	CheckBox chk4;
	@UiField
	CheckBox chk5; 
	@UiField
	CheckBox chk6; 
	@UiField
	CheckBox chk7;
	@UiField
	CheckBox chk8;	*/
	@UiField
	RadioButton rdSpGrSingle;
	@UiField
	RadioButton rdSpGrRange;
	@UiField
	TextBox txtSpGrav;
	@UiField
	TextBox tbSpGravFrom;
	@UiField
	TextBox tbSpGravTo;
	@UiField
	InlineLabel msgSpGrav,msgSpc,msgGenInfo,msgDes;
	@UiField
	TextBox tbTempSpGrav;    
	@UiField
	ListBox lbTempUnitSpGrav;
	//refractive index:
	@UiField
	CheckBox chkRfrIndexDspl; 
	@UiField
	RadioButton rdRfrIndexSingle;
	@UiField
	RadioButton rdRfrIndexRange; 
	@UiField
	TextBox tbRfrIndexFrom;  
	@UiField
	TextBox tbRfrIndexTo;
	@UiField
	TextBox txtRfrIndex;
	@UiField
	InlineLabel msgRfrIndex;	
	@UiField
	TextBox tbTempRfrIndex;
	@UiField
	ListBox lbTempUnitRfrIndex;
	//optical rotation:
	@UiField
	CheckBox chkOptRotDspl;
	@UiField
	RadioButton rdOptRotSingle;
	@UiField
	RadioButton rdOptRotRange; 
	@UiField
	TextBox tbOptRotFrom; 
	@UiField
	TextBox tbOptRotTo;
	@UiField
	TextBox txtOptRot;
	@UiField
	InlineLabel msgOptRot; 
	@UiField
	InlineLabel msgUpdateUses;
	//flash point:
	@UiField
	CheckBox chkFlshPntDspl;
	@UiField
	RadioButton rdFlashPntSingle; 
	@UiField
	RadioButton rdFlashPntRange; 
	@UiField
	TextBox tbFlashPntFrom; 
	@UiField
	TextBox tbFlashPntTo;
	@UiField
	TextBox txtFlashPoint;
	@UiField
	InlineLabel msgFlashPoint;
	@UiField
	ListBox lbTempUnitFlashPoint;
	@UiField
	Label lblMasterMessage;//this shows main messages
	//isRange variables:
	List<Boolean> isRanges=new ArrayList<Boolean>();
	//display properties on the page:
	List<Boolean> isDisplayList = new ArrayList<Boolean>();
	List<CheckBox> checkBoxes=new ArrayList<CheckBox>();
	//List<TextArea> listTaUses=new ArrayList<TextArea>();
	//List<Button> listUsesRemoveButtons = new ArrayList<Button>();
	//List<ProductUseEditView> useEditView;
	//List<ParagraphWidget> listParas;
	
	private TabPanel tabPanel;
	//List<String> strsUses;
	//for storing the description data:
	//List<String> strsDes;
	//display various data on the page:
	//@UiHandler("chkInStock")  
	//public void inStockCheckClick(ClickEvent e){
		//CheckBox chk = (CheckBox) e.getSource();
		//if(chk.getValue()) isInStock=1;
		//else isInStock=0;
	//}
	//check box handlers for displaying various things:
	//@UiHandler("chk0")  
	//public void display0(ClickEvent e){
		//CheckBox chk = (CheckBox) e.getSource();
		//isDisplayList.set(5, chk.getValue());
	//}
	@UiHandler("btnUpdateSpGrav")
	public void updateSpGrav(ClickEvent e){
		if(presenter!=null)
			presenter.updateNumProperty(txtSpGrav.getText(),
					tbSpGravFrom.getText(), tbSpGravTo.getText(),
					tbTempSpGrav.getText(),
					lbTempUnitSpGrav.getSelectedIndex(), 0);

	}
	@UiHandler("btnUpdateRfrInd")
	public void updateRfrInd(ClickEvent e){
		if(presenter!=null)
			presenter.updateNumProperty(txtRfrIndex.getText(),
					tbRfrIndexFrom.getText(), tbRfrIndexTo.getText(),
					tbTempRfrIndex.getText(),
					lbTempUnitRfrIndex.getSelectedIndex(), 1);


	}
	@UiHandler("btnUpdateOptRot")
	public void updateOptRot(ClickEvent e){
		if(presenter!=null)
			presenter.updateNumProperty(txtOptRot.getText(),
					tbOptRotFrom.getText(), tbOptRotTo.getText(),
					"0",//no need of ref temp setting so give any data
					0,//no need to give code for ref temp unit so give any data
					2);
		

	}
	@UiHandler("btnUpdateFlsPnt")
	public void updateFlsPnt(ClickEvent e){
		if(presenter!=null)
			presenter.updateNumProperty(txtFlashPoint.getText(),
					tbFlashPntFrom.getText(), tbFlashPntTo.getText(),
					"0",//give any number format string this will not be processed or used
					lbTempUnitFlashPoint.getSelectedIndex(), 3);


	}
	/*
	@UiHandler("btnAddUse")
	public void addUse(ClickEvent e){
		ProductUseEditView useView = new ProductUseEditView();
		useEditView.add(useView);
		usesPanel.add(useView);
		RemoveUseClickHander handler = new RemoveUseClickHander();
		useView.btnRemove.addClickHandler(handler);
		useView.btnRemove.getElement()
						.setId(String.valueOf(useEditView.size()-1));
	}
	*/
	@UiHandler("btnUpdateUse")
	public void updateUses(ClickEvent e){
		/*
		strsUses = new ArrayList<String>();
		
		for(ProductUseEditView v:useEditView){
			strsUses.add(v.ta0.getText());
		}
		*/
		if(presenter!=null) presenter.updateUses(usesEditor.getHtml());
	}
	
	@UiHandler("btnUpdateGenInfo")
	public void updateGenInfo(ClickEvent e){
		if(presenter!=null) presenter.updateGenInfoData(
				txtCommonName.getText(),//0
				txtBotName.getText(),//1
				txtCountryOrigin.getText(),//2
				txtChemConstituents.getText(),//3
				txtPlantParts.getText(),//4
				txtExtractionMethods.getText()//5
		);
		
		
		
		
		
		
	}
	@UiHandler("btnUpdateDes")
	public void updateDescription(ClickEvent e){
		/*
		//getting the description data:
		strsDes = new ArrayList<String>();
		for(ParagraphWidget para:listParas){
			strsDes.add(para.ta0.getText());
		}
		*/
		if(presenter!=null) presenter.updateDescription(dscrpEditor.getHtml());
	}	
	
	@UiHandler("btnUpdateSpcf")
	public void updateSpcf(ClickEvent e){
		if(presenter!=null) presenter.updateSpecifications(
				txtColorApperance.getText(), 
				txtOrder.getText(),
				txtSolubility.getText());
		
	}
	/*
	 * String commonName,//0
				String botanicalName,//1
				String countryOfOrigin,//2
				String chemConstituents,//3
				String plantPart,//4
				String separationMethods//5
	 * 
	 * 
	 */
	@UiHandler({"chk0","chk1","chk2","chk3","chk4","chk5","chk6","chk7","chk8"})
	public void checkBoxClick(ClickEvent e){
		CheckBox chk = (CheckBox) e.getSource();
		//from 5 to 13 index:
		isDisplayList.set(Integer.valueOf(chk.getName()), chk.getValue());
		
	}
	
	//showing the properties secton:
	@UiHandler("chkSec2Dspl")  
	public void displaySec2(ClickEvent e){
		CheckBox chk = (CheckBox) e.getSource();
		isDisplayList.set(4, chk.getValue());
		if(chk.getValue()){
			
		}else{
			
		}
	}
	//specific gravity handlers:
	
	@UiHandler("chkDsplSpGrav")  
	public void displaySpGravClick(ClickEvent e){
		CheckBox chk = (CheckBox) e.getSource();
		isDisplayList.set(0, chk.getValue());
	}
	@UiHandler("rdSpGrSingle")
	public void radioSpGrSingleClick(ClickEvent e){
		//RadioButton rb=(RadioButton) e.getSource();
		isRanges.set(0, false);
		txtSpGrav.setEnabled(true);
		tbSpGravFrom.setEnabled(false);
		tbSpGravTo.setEnabled(false);
		//System.out.println("specific gravity single radio clicked.");
	}	
	@UiHandler("rdSpGrRange")
	public void radioSpGrRangeClick(ClickEvent e){
		//RadioButton rb=(RadioButton) e.getSource();
		isRanges.set(0, true);
		txtSpGrav.setEnabled(false);
		tbSpGravFrom.setEnabled(true);
		tbSpGravTo.setEnabled(true);
		
		//System.out.println("specific gravity range radio clicked.");
	}
	//specific refractive index:
	@UiHandler("chkRfrIndexDspl")
	public void displayRfrIndexClick(ClickEvent e){
		CheckBox chk = (CheckBox) e.getSource();
		isDisplayList.set(1, chk.getValue());
	}
	@UiHandler("rdRfrIndexSingle")
	public void radioRfrIndexSingleClick(ClickEvent e){
		//RadioButton rb=(RadioButton) e.getSource();
		isRanges.set(1, false);
		txtRfrIndex.setEnabled(true);
		tbRfrIndexFrom.setEnabled(false);
		tbRfrIndexTo.setEnabled(false);
	}	
	@UiHandler("rdRfrIndexRange")
	public void radioRfrIndexClick(ClickEvent e){
		//RadioButton rb=(RadioButton) e.getSource();
		isRanges.set(1, true);
		txtRfrIndex.setEnabled(false);
		tbRfrIndexFrom.setEnabled(true);
		tbRfrIndexTo.setEnabled(true);
		
	}
	//optical rotation radio buttons:
	@UiHandler("chkOptRotDspl")
	public void displayOptRotClick(ClickEvent e){
		CheckBox chk = (CheckBox) e.getSource();
		isDisplayList.set(2, chk.getValue());
	}
	@UiHandler("rdOptRotSingle")
	public void radioOptRotSingleClick(ClickEvent e){
		//RadioButton rb=(RadioButton) e.getSource();
		isRanges.set(2, false);
		txtOptRot.setEnabled(true);
		tbOptRotFrom.setEnabled(false); 
		tbOptRotTo.setEnabled(false);
	}	
	@UiHandler("rdOptRotRange")
	public void radioOptRotClick(ClickEvent e){
		//RadioButton rb=(RadioButton) e.getSource();
		isRanges.set(2, true);
		txtOptRot.setEnabled(false);
		tbOptRotFrom.setEnabled(true); 
		tbOptRotTo.setEnabled(true);
		
	}	
	//flash point radio buttons:
	@UiHandler("chkFlshPntDspl")
	public void displayFlashPointClick(ClickEvent e){
		CheckBox chk = (CheckBox) e.getSource();
		isDisplayList.set(3, chk.getValue());
	}
	@UiHandler("rdFlashPntSingle")
	public void radioFlashPointSingleClick(ClickEvent e){
		//RadioButton rb=(RadioButton) e.getSource();
		isRanges.set(3, false);
		txtFlashPoint.setEnabled(true);
		tbFlashPntFrom.setEnabled(false);
		tbFlashPntTo.setEnabled(false);
	}	
	@UiHandler("rdFlashPntRange")
	public void radioFlashPointClick(ClickEvent e){
		//RadioButton rb=(RadioButton) e.getSource();
		isRanges.set(3, true);
		txtFlashPoint.setEnabled(false);
		tbFlashPntFrom.setEnabled(true);
		tbFlashPntTo.setEnabled(true);
		
	}	
	/*
	@UiHandler("btnSave")
	public void onBtnSaveClick(ClickEvent e){
	*/
		/*
		//getting the uses data:
		strsUses = new ArrayList<String>();
		for(ProductUseEditView v:useEditView){
			strsUses.add(v.ta0.getText());
		}
		//getting the description data:
		strsDes = new ArrayList<String>();
		for(ParagraphWidget para:listParas){
			strsDes.add(para.ta0.getText());
		}
		*/
	/*
		if(presenter!=null) presenter.onBtnSaveClick(txtName.getText(),
				dscrpEditor.getHtml(),//rta1.getHTML(),
				//isInStock,
				txtExtractionMethods.getText(),
				txtCommonName.getText(),
				txtBotName.getText(),
				txtCountryOrigin.getText(),
				txtPlantParts.getText(),
				txtColorApperance.getText(),
				txtChemConstituents.getText(),
				txtOrder.getText(),
				txtSolubility.getText(),
				
				txtSpGrav.getText(),
				tbSpGravFrom.getText(),
				tbSpGravTo.getText(),
				tbTempSpGrav.getText(),
				lbTempUnitSpGrav.getSelectedIndex(),
				txtRfrIndex.getText(),
				tbRfrIndexFrom.getText(),
				tbRfrIndexTo.getText(),
				tbTempRfrIndex.getText(),
				lbTempUnitRfrIndex.getSelectedIndex(),
				
				txtOptRot.getText(),
				tbOptRotFrom.getText(),
				tbOptRotTo.getText(),
				//flash point:
				txtFlashPoint.getText(),	
				tbFlashPntFrom.getText(),		
				tbFlashPntTo.getText(),
				lbTempUnitFlashPoint.getSelectedIndex(),
				usesEditor.getHtml()//uses of the product
				//strsDes//description paras
				);
		;   
		    

	}	*/
	public ProductDetailsEditViewImpl(){
		
		 initWidget(uiBinder.createAndBindUi(this));
		
		 tabPanel = new TabPanel();
		 //System.out.println("here.");
		 tabPanel.setWidth("100%");
		 //adding basic info panel:
		 tabPanel.add(basicInfoPanel,"Basic Info");
		 //styling of rich text area:
		 HTMLPanel dscrpPanel = new HTMLPanel("");
		 dscrpEditor=new RichTextEditor("500px","500px");
		//1308190927 rta1.add(dscrpEditor);
		 HTMLPanel usesPanel = new HTMLPanel("");
		 usesEditor=new RichTextEditor("100%","200px");
		// rtaUses.add(usesEditor);

		 dscrpPanel.add(dscrpEditor);
		 dscrpPanel.add(desUpdatePanel);
		 tabPanel.add(dscrpPanel, "description");
		
		 usesPanel.add(usesEditor);
		 usesPanel.add(usesUpdatePanel);
		 tabPanel.add(usesPanel, "Uses");
		 //properties:
		 tabPanel.add(propertiesPanel, "Properties");
		 //specifications:
		 tabPanel.add(specificationPanel, "Specification");
		 tabPanel.selectTab(0);
		 //adding the tab panel on the interface:
		 tabPanelContainer.add(tabPanel);
		 //description edit section:
		// formatter=rta1.getFormatter();
		 //uses edit section:
		// formatterUses=rtaUses.getFormatter();
		// System.out.println("ProductDetailsEditView::cons:got formator");
		 //txtName.setReadOnly(true);
		 txtName.setText("Loading...");
		 //isRanges values:
		 isRanges.add(false);//specific gravity
		 isRanges.add(false);//refractive index
		 isRanges.add(false);//optical rotation
		 isRanges.add(false);//flash point
		 //display list:
		 isDisplayList.add(true);//0
		 isDisplayList.add(true);//1
		 isDisplayList.add(true);//2
		 isDisplayList.add(true);//3
		 isDisplayList.add(true);//4 for displaying the properties section
		 isDisplayList.add(true);//5
		 isDisplayList.add(true);//6
		 isDisplayList.add(true);//7
		 isDisplayList.add(true);//8
		 isDisplayList.add(true);//9
		 isDisplayList.add(true);//10
		 isDisplayList.add(true);//11
		 isDisplayList.add(true);//12
		 isDisplayList.add(true);//13
		 //adding the checkboxes:
		 checkBoxes.add(chk0);
		 checkBoxes.add(chk1);
		 checkBoxes.add(chk2);
		 checkBoxes.add(chk3);
		 checkBoxes.add(chk4);
		 checkBoxes.add(chk5);
		 checkBoxes.add(chk6);
		 checkBoxes.add(chk7);
		 checkBoxes.add(chk8);
		 //init values:
		 tbSpGravFrom.setEnabled(false);
		 tbSpGravTo.setEnabled(false);
		tbRfrIndexFrom.setEnabled(false);
		tbRfrIndexTo.setEnabled(false);
		tbOptRotFrom.setEnabled(false); 
		tbOptRotTo.setEnabled(false);
		tbFlashPntFrom.setEnabled(false);
		tbFlashPntTo.setEnabled(false);
		//temperature unit listbox data:
		lbTempUnitSpGrav.addItem("degree C", "0");
		lbTempUnitSpGrav.addItem("degree F", "1");
		lbTempUnitRfrIndex.addItem("degree C", "0");
		lbTempUnitRfrIndex.addItem("degree F", "1");
		lbTempUnitFlashPoint.addItem("degree C", "0");
		lbTempUnitFlashPoint.addItem("degree F", "1");
		//now hiding the editing area:
		editorPanel.setVisible(false);
		 //taDescription.addStyleName(Resources.INSTANCE.getMainCss().textDescription());
		// RadioButton rb =  new RadioButton("refractiveIndex","singleValue");
		 
		 //txtExtractionMethods.setWidth("300px");
		// System.out.println("ProductDetailsEditView::cons:got Complete");
	}	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
/*
	@Override
	public void showData(T data) {
		dataPanel.clear();
		StringBuilder sb=new StringBuilder();
		String strHtml="";
		//template.get(0).render(data, sb);
		strHtml+=sb.toString();
		dataPanel.add(new HTML(strHtml));	
		
		
	}
	*/
/*
	@Override
	public void showAjaxLoading() {
		///dataPanel.add(imgLoading);
		//ajaxPanel.clear();
		
		ajaxPanel.setText("Updating...");
		
	}
*/
	//@Override
	//public void stopAjaxLoading() {
		//ajaxPanel.setText("");
		
	//}

	@Override
	public void showError(String strError) {
		rootPanel.add(new HTML(strError));
		
	}
/*
	@Override
	public void setLayoutTemplate(
			List<ProductDetailsEditViewTemplate<T>> template) {
		this.template=template;
		
	}
	*/
	@Override
	public void showMessage(String message) {
		
		
		//ajaxPanel.setText(message);
		
	}
	@Override
	public void info(String message,Integer id){
		switch(id){
		case 0:msgSpGrav.setText(message);
			break;
		case 1:msgRfrIndex.setText(message);
			break;
		case 2:msgOptRot.setText(message);
			break;
		case 3:msgFlashPoint.setText(message);
			break;
		case 4://description message
			//desUpdatePanel.add(tickIcon);
			msgDes.setText(message);
			
		break;
		case 5:msgGenInfo.setText(message);
			break;
		case 6:msgSpc.setText(message);
		break;
		case 7:msgUpdateUses.setText(message);
		break;
		case 8:lblMasterMessage.setText(message);
		break;
		}
		
	}
	
	public List<Boolean> getIsRanges() {
		return isRanges;
	}
	@Override
	public void setData(String name, String description,//boolean isInStock,
			String separationMethods, String commonName, String botName,
			String countryOfOrigin, String plantParts,
			String colorAndApperance, String chemConstituents, String order,
			String solubility,
			int isSec2Dspl,
			int isSpGravDspl,
			int isSpGravRange,// Float spGrav,
			String spGravFrom, String spGravTo,
			float tempSpGrav,
			int tempUnitSpGrav,
			int isRfrIndexDspl,
			int isRfrIndexRange,
			//Float refrIndex,
			String refrIndexFrom, String refrIndexTo,
			float tempRfrIndex,
			int tempUnitRfrIndex,
			int  isOptRotDspl,
			int isOptRotRange,
			//Float optRot,
			String optRotFrom, String optRotTo,
			int  isFlashPointDspl,
			int isFlashPointRange, 
			//Float flashPoint,
			String flashPointFrom,
			String flashPointTo,
			int tempUnitFlashPoint,
			
			List<Boolean> listDisplay,
			//List<String> listUses,
			String uses
			//List<String> listParasIn
			) {
		//the editor panel is not visible before the loading of the data
		//so make it visible:
		editorPanel.setVisible(true);
		//now populate the data:
		 tabPanel.selectTab(0);
		txtName.setText(name);
		//setting the in stock value:
		//this.isInStock=isInStock;
		//if(isInStock==1) chkInStock.setValue(true);
		//else chkInStock.setValue(false);
		//taDescription.setText(description);
		
		//130816 rta1.setHTML(description);
		dscrpEditor.setHtml(description);
		// System.out.println("ProductDetailsEditView::setData() setting uses data");
		//setting the uses data:
		
		usesEditor.setHtml(uses);
		//System.out.println("ProductDetailsEditView::setData() SET");
		//txtImageUrl.setText(imageUrl);
		txtExtractionMethods.setText(separationMethods);
		txtBotName.setText(botName);
		txtColorApperance.setText(colorAndApperance);
		txtPlantParts.setText(plantParts);
		
		
		txtCommonName.setText(commonName);
		
		txtCountryOrigin.setText(countryOfOrigin);
		
		
		txtChemConstituents.setText(chemConstituents);
		txtOrder.setText(order);
		txtSolubility.setText(solubility);
		//setting the display list:
		int i=5;
		for(Boolean val:listDisplay){
			isDisplayList.set(i,val);
			checkBoxes.get(i-5).setValue(val);
			i++;
		}
		//display properties section:
		if(isSec2Dspl==1){ 
			chkSec2Dspl.setValue(true);
			isDisplayList.set(4, true);
		}
		else {
			chkSec2Dspl.setValue(false);
			isDisplayList.set(4, false);
		}
		
		//if single value of specific gravity:
		if(isSpGravDspl==0){
			chkDsplSpGrav.setValue(false);
			isDisplayList.set(0, false);
			
		}else{
			chkDsplSpGrav.setValue(true);
			isDisplayList.set(0, true);
		}
		if(isSpGravRange==0){
			//txtSpGrav.setText(String.valueOf(spGrav));
			txtSpGrav.setText(spGravFrom);
			
			rdSpGrSingle.setValue(true);
			rdSpGrRange.setValue(false);
			 tbSpGravFrom.setEnabled(false);
			 tbSpGravTo.setEnabled(false);
			 isRanges.set(0, false);
			
		}else{
			txtSpGrav.setEnabled(false);
			rdSpGrSingle.setValue(false);
			rdSpGrRange.setValue(true);
			 tbSpGravFrom.setEnabled(true);
			 tbSpGravTo.setEnabled(true);
			 //tbSpGravFrom.setText(String.valueOf(spGravFrom));
			 tbSpGravFrom.setText( spGravFrom);
			 //tbSpGravTo.setText(String.valueOf(spGravTo));
			 tbSpGravTo.setText(spGravTo);
			 isRanges.set(0, true);
			 
			

			
		}
		tbTempSpGrav.setText(String.valueOf(tempSpGrav));
		lbTempUnitSpGrav.setSelectedIndex(tempUnitSpGrav);
		//if range of specific gravity:
		//from
		//to
		if(isRfrIndexDspl==0){
		 chkRfrIndexDspl.setValue(false);
		 isDisplayList.set(1, false);
		}else{
			chkRfrIndexDspl.setValue(true);
			isDisplayList.set(1, true);
		}
		if(isRfrIndexRange==0){
			txtRfrIndex.setEnabled(true);
			tbRfrIndexFrom.setEnabled(false);
			tbRfrIndexTo.setEnabled(false);
			
			//txtRfrIndex.setText(String.valueOf(refrIndex));
			txtRfrIndex.setText(refrIndexFrom);
			rdRfrIndexSingle.setValue(true);
			rdRfrIndexRange.setValue(false);
			 isRanges.set(1, false);
			
		}else{
			txtRfrIndex.setEnabled(false);
			tbRfrIndexFrom.setEnabled(true);
			tbRfrIndexTo.setEnabled(true);
			rdRfrIndexSingle.setValue(false);
			rdRfrIndexRange.setValue(true);
			//tbRfrIndexFrom.setEnabled(false);
			//tbRfrIndexTo.setEnabled(false);
			//tbRfrIndexFrom.setText(String.valueOf(refrIndexFrom));
			tbRfrIndexFrom.setText(refrIndexFrom);
			
			//tbRfrIndexTo.setText(String.valueOf(refrIndexTo));
			tbRfrIndexTo.setText(refrIndexTo);
			isRanges.set(1, true);
		}
		tbTempRfrIndex.setText(String.valueOf(tempRfrIndex));
		lbTempUnitRfrIndex.setSelectedIndex(tempUnitRfrIndex);
		if(isOptRotDspl==0){
		 chkOptRotDspl.setValue(false);
		 isDisplayList.set(2, false);
		}else{
			chkOptRotDspl.setValue(true);
			isDisplayList.set(2, true);
		}
		if(isOptRotRange==0){
			txtOptRot.setEnabled(true);
			tbOptRotFrom.setEnabled(false);
			tbOptRotTo.setEnabled(false);
			//txtOptRot.setText(String.valueOf(optRot));
			txtOptRot.setText(optRotFrom);
			rdOptRotRange.setValue(false);
			rdOptRotSingle.setValue(true);
			isRanges.set(2, false);
		}else{
			txtOptRot.setEnabled(false);
			tbOptRotFrom.setEnabled(true);
			tbOptRotTo.setEnabled(true);
			//tbOptRotFrom.setEnabled(false); 
			//tbOptRotTo.setEnabled(false);
			rdOptRotRange.setValue(true);
			rdOptRotSingle.setValue(false);
			//tbOptRotFrom.setText(String.valueOf(optRotFrom));
			tbOptRotFrom.setText(optRotFrom);
			//tbOptRotTo.setText(String.valueOf(optRotTo));
			tbOptRotTo.setText(optRotTo);
			isRanges.set(2, true);
			
		}
		//System.out.println("value:"+isFlashPointDspl);
		if(isFlashPointDspl==0){
			chkFlshPntDspl.setValue(false);
			isDisplayList.set(3, false);
		}else{
			chkFlshPntDspl.setValue(true);
			isDisplayList.set(3, true);
		}
		if(isFlashPointRange==0){
			txtFlashPoint.setEnabled(true);
			tbFlashPntFrom.setEnabled(false);
			tbFlashPntTo.setEnabled(false);
			//txtFlashPoint.setText(String.valueOf(flashPoint));	
			txtFlashPoint.setText(flashPointFrom);	
			rdFlashPntSingle.setValue(true);
			rdFlashPntRange.setValue(false);
			isRanges.set(3, false);
		}else{
			txtFlashPoint.setEnabled(false);
			tbFlashPntFrom.setEnabled(true);
			tbFlashPntTo.setEnabled(true);
			//tbFlashPntFrom.setEnabled(false);
			//tbFlashPntTo.setEnabled(false);	
			rdFlashPntSingle.setValue(false);
			rdFlashPntRange.setValue(true);
			
			//tbFlashPntFrom.setText(String.valueOf(flashPointFrom));
			tbFlashPntFrom.setText(flashPointFrom);
			//tbFlashPntTo.setText(String.valueOf(flashPointTo));
			tbFlashPntTo.setText(flashPointTo);
			isRanges.set(3, true);
		}
		lbTempUnitFlashPoint.setSelectedIndex(tempUnitFlashPoint);
		// 0 1 spgrav 2 3 refractive index  4 5 opt rot 6 7 flash point
		/*
		//processing the uses strings and putting them in the place:
		//TextArea ta;
		//Button btn;
		ProductUseEditView useView;
		RemoveUseClickHander handler = new RemoveUseClickHander();
		i=0;
		//listTaUses=new ArrayList<TextArea>();
		//listUsesRemoveButtons = new ArrayList<Button>();
		useEditView=new ArrayList<ProductUseEditView>() ;
		//strsUses = new ArrayList<String>();
		for(String use:listUses){
			useView = new ProductUseEditView();
			useEditView.add(useView);
			//ta=useView.ta0;
			//listTaUses.add(ta);
			useView.ta0.setText(use);
			//strsUses.add(use);
			//ta.setText(use);
			//btn=useView.btnRemove;
			//listUsesRemoveButtons.add(btn);
			useView.btnRemove.addClickHandler(handler);
			useView.btnRemove.getElement().setId(String.valueOf(i));
			//ta = new TextArea();
			//ta.setWidth("95%");
			//ta.setHeight("60px");
			//listTaUses.add(ta);
			//usesPanel.add(new Label("Use ("+listTaUses.size()+")"));
			//usesPanel.add(ta);
			//btn = new Button("Remove");
			
			//usesPanel.add(useView);
			//usesPanel.
			//ta.setText(use);
			i++;
		}
		//description section paragraphs:
		
		listParas=new ArrayList<ParagraphWidget>() ;*/
		/*
		ParagraphWidget para;
		RemoveParaClickHander handler2 = new RemoveParaClickHander();
		for(String strPara:listParasIn){
			para = new ParagraphWidget();
			desPanel.add(para);
			listParas.add(para);
			para.ta0.setText(strPara);
			para.btnRemove.addClickHandler(handler2);
			para.btnRemove.getElement()
							.setId(String.valueOf(listParas.size()-1));
		}
		
		*/
		
		
		

	}
	/*
	private class RemoveUseClickHander implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Button btn = (Button) event.getSource();
			try{
				String strId= btn.getElement().getId();
				int id=Integer.valueOf(strId);
				//remove the useedit view interface:
				useEditView.get(id).removeFromParent();
				useEditView.remove(id);
				for(int i=id;i<useEditView.size();i++){
					useEditView.get(i).btnRemove.getElement().setId(String.valueOf(i));
				}

				//remove the data model object:
				//strsUses.remove(id);
				
				
				//if(presenter!=null) presenter.createFullRestore(id);
			}catch(Exception e){
				//info("Error in getting the id.");
			}
			
		}
		
	}
	*/
	@Override
	public List<Boolean> getIsDisplay() {
		
		return isDisplayList;
	}
/*
	private class RemoveParaClickHander implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Button btn = (Button) event.getSource();
			try{
				String strId= btn.getElement().getId();
				int id=Integer.valueOf(strId);
				//remove the useedit view interface:
				listParas.get(id).removeFromParent();
				listParas.remove(id);
				for(int i=id;i<listParas.size();i++){
					listParas.get(i).btnRemove.getElement().setId(String.valueOf(i));
				}

				//remove the data model object:
				//strsUses.remove(id);
				
				
				//if(presenter!=null) presenter.createFullRestore(id);
			}catch(Exception e){
				//info("Error in getting the id.");
			}
			
		}
		
	}
	*/
	@Override
	public void showAjaxLoading() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stopAjaxLoading() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reset() {
		resetMessageBoxes();
		editorPanel.setVisible(false);
		
	}
	private void resetMessageBoxes(){
		msgSpGrav.setText("");
		msgSpc.setText("");
		msgGenInfo.setText("");
		msgDes.setText("");
		msgRfrIndex.setText("");
		msgOptRot.setText(""); 
		msgUpdateUses.setText(""); 
		msgFlashPoint.setText(""); 
		lblMasterMessage.setText("");
	}
	
	
}
