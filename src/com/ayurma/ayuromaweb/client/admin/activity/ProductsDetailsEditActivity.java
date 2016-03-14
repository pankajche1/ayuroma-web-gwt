package com.ayurma.ayuromaweb.client.admin.activity;

import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.client.admin.place.ProductsDetailsEditPlace;
import com.ayurma.ayuromaweb.client.admin.services.AdminDataServiceAsync;
import com.ayurma.ayuromaweb.client.admin.view.IProductDetailsEditView;
import com.ayurma.ayuromaweb.shared.FieldVerifier;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ProductsDetailsEditActivity extends AbstractActivity
		implements IProductDetailsEditView.Presenter{
	private IProductDetailsEditView view;
	private PlaceController placeController;
	private final AdminDataServiceAsync rpcService;
	private ProductsDetailsEditPlace place;
	private ProductDetails targetData;
	private List<ProductDetails> listTarget;
	private int iSectionTarget;//which section of the details is being updated to the server.
	private String strDetailsId="0";
	private Long key;
	private List<Boolean> listDisplay=new ArrayList<Boolean>();

	@Inject
	public ProductsDetailsEditActivity(IProductDetailsEditView view,
			PlaceController placeController, AdminDataServiceAsync rpcService) {
		
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
		this.rpcService = rpcService;
	}
	public void init(ProductsDetailsEditPlace place){
		this.place=place;
		view.reset();//this hides the already existing data view
		view.info("Loading...", 8);//main message at the top
		try{
			Long detailsKey = Long.valueOf(place.getPlaceName());
			fetchData(detailsKey);
		}catch(NumberFormatException e){
			
		}
	}
	private void fetchData(Long detailsKey){
		// AdminDataServiceAsync adminRpcService = GWT.create(AdminDataService.class);
				 rpcService.getProductDetails(detailsKey, new AsyncCallback<ProductDetails>(){

					@Override
					public void onFailure(Throwable caught) {
					
						view.info("Rpc failed.", 8);
						
						
					}

					@Override
					public void onSuccess(ProductDetails result) {
						processResponse(result);
							
					}});
	}
	private void processResponse(ProductDetails result){
		if(result!=null){
			view.info("", 8);//this message goes to the master message
			targetData=result;
			strDetailsId=result.getStrId();
		}else{
			view.info("No data was found on the server.", 8);//master message
			return;
		}
		//String[] subStr=null;
	   // String description=targetData.getDescription();
	    int[] aryDisplay = targetData.getListDisplay();
	    for(int val:aryDisplay){
	    	if(val==0)listDisplay.add(false);
	    	else listDisplay.add(true);
	    }
	    String[] strValues=targetData.getStrValues();
	    String des=targetData.getDescription();
	    if(des.equals("")) des="<p></p>";
	    String uses=targetData.getUses();
	    if(uses.equals("")) uses="<p></p>";
	    //sets the data on the view interface:
		view.setData(targetData.getName(),
				des,
				//targetData.getIsInStock(),
				targetData.getSaparationMethod(),
				targetData.getCommonName(),
				targetData.getBotanicalName(),
				targetData.getCountryOfOrigin(),
				targetData.getPlantPart(),
				targetData.getColorAndAppearance(),
				targetData.getChemicalConstituent(),
				targetData.getOrder(),
				targetData.getSolubility(),
				targetData.getIsSection2Displayed(),
				targetData.getIsSpGravDspl(),
				targetData.getIsSpGravRange(),
				//targetData.getSpecificGravity(),
				//Float.valueOf(strValues[0]),
				strValues[0],
				//Float.valueOf(strValues[0]),
				strValues[1],
				//Float.valueOf(strValues[1]),
				targetData.getTempSpGrav(),
				targetData.getTempUnitSpGrav(),
				targetData.getIsRfrIndexDspl(),
				targetData.getIsRfrIndexRange(),
				//targetData.getRefractiveIndex(),
				//Float.valueOf(strValues[2]),
				strValues[2],
				//Float.valueOf(strValues[2]),
				strValues[3],
				//Float.valueOf(strValues[3]),
				targetData.getTempRefracIndex(),
				targetData.getTempUnitRfrIndex(),
				targetData.getIsOptRotDspl(),
				targetData.getIsOptRotRange(),
				//targetData.getOpticalRotation(),
				//Float.valueOf(strValues[4]),
				strValues[4],
				//Float.valueOf(strValues[4]),
				strValues[5],
				//Float.valueOf(strValues[5]),
				targetData.getIsFlashPointDspl(),
				targetData.getIsFlshPntRange(),
				//targetData.getFlashPoint(),
				//Float.valueOf(strValues[6]),
				strValues[6],
				//Float.valueOf(strValues[6]),
				strValues[7],
				//Float.valueOf(strValues[7]),
				targetData.getTempUnitFlashPoint(),
				
				listDisplay,
				uses
				//listParas
				);
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
	}

	@Override
	public void onBtnSaveClick(String name, String description,
			String separationMethods, String commonName, String botanicalName,
			String countryOfOrigin, String plantPart,
			String colorAndAppearance, String chemConstituents, String order,
			String solubility, String spGrav, String spGrav0, String spGrav1,
			String tempSpGrav, int tempUnitSpGrav, String refrIndex,
			String rfrIndex1, String rfrIndex2, String tempRfrIndex,
			int tempUnitRfrIndex, String optRot, String optRot1,
			String optRot2, String flashPoint, String flashPoint1,
			String flashPoint2, int tempUnitFlashPoint, String strUses) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUses(String strUses) {
		if(strUses.equals("<p><br></p>")||strUses.equals("<br>")) strUses="";
		updateUseToServer(strUses);
		
	}
	private void updateUseToServer(String uses){
		view.info("Updating...", 7);
		Long key = Long.valueOf(targetData.getStrId());
		rpcService.updateProductUses(key, uses, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("RPC failure...", 7);
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, 7);
				
			}});
	}	

	@Override
	public void updateNumProperty(String str0, String str1, String str2,
			String strTemp, int tempUnit, int code) {
		//two values:
				String strVal1;
				String strVal2;
				String isRange;
				String isDisplay;
				//code for updating the specific gravity section:
				iSectionTarget=code;
				//String strTemp;
				String strTempUnit=String.valueOf(tempUnit);
				//getting the is range data or single data info:
				List<Boolean> isRanges = view.getIsRanges();
				//display list info:
				    //0: for specific gravity display info
				List<Boolean> isDisplayList = view.getIsDisplay();
				//ist will decided by the code:
				isDisplay = (isDisplayList.get(iSectionTarget))?"true":"false";
				isRange = (isRanges.get(iSectionTarget))?"true":"false";
				if (!FieldVerifier.isFloatFormat(strTemp)) {
					view.info("Temperature is not Number format", 0);
					return;
				}
				//making a list of strings:
				List<String> listToServer = new ArrayList<String>();
				//values
				if(isRanges.get(iSectionTarget)){//if values are in ranges
					//listToServer.add("");
					
					if(iSectionTarget==2){//optical rotation
					    if (FieldVerifier.isFloatFormat(str1)) {
						    if(Float.valueOf(str1)>0){
							    if(str1.startsWith("+")) listToServer.add(str1);//index 0
							    else listToServer.add("+"+str1);
						    }else listToServer.add(str1);
					    }else listToServer.add(str1);
					    if (FieldVerifier.isFloatFormat(str2)) {
						    if(Float.valueOf(str2)>0){
							    if(str2.startsWith("+")) listToServer.add(str2);//index 1
							    else listToServer.add("+"+str2);
						    }else listToServer.add(str2);
					   }else listToServer.add(str2);
					}else{//specific graivity, refractive index
						listToServer.add(str1);//index 0
						listToServer.add(str2);//index 1
					}
				}else{//if values are single
					if(iSectionTarget==2){//optical rotation
						if (FieldVerifier.isFloatFormat(str0)) {
						    if(Float.valueOf(str0)>0){
							    if(str0.startsWith("+")) listToServer.add(str0);//index 0
							    else listToServer.add("+"+str0);
						    }else listToServer.add(str0);
					    }else listToServer.add(str0);
						listToServer.add("");//1 just put any data in the second value
					}else{
					    listToServer.add(str0);//0
					    listToServer.add("");//1
					}
					//listToServer.add("");
				}
				//temperature data:
				listToServer.add(strTemp);//2
				listToServer.add(strTempUnit);//3
				//display data:
				listToServer.add(isRange);//4
				listToServer.add(isDisplay);//5

				updateProductDetailsPart(listToServer);
		
	}

	@Override
	public void updateGenInfoData(String commonName, String botanicalName,
			String countryOfOrigin, String chemConstituents, String plantPart,
			String separationMethods) {
		List<String> listToServer = new ArrayList<String>();
		// upto index 5 this listToServer contains properties values
		//from index 6 to 11 it contains display/hide info as 1 and 0
		//so its size is 12
		listToServer.add(commonName);//0
		listToServer.add(botanicalName);//1
		listToServer.add(countryOfOrigin);//2
		listToServer.add(chemConstituents);//3
		listToServer.add(plantPart);//4
		listToServer.add(separationMethods);//5
	
		List<Boolean> subListDisplay=view.getIsDisplay().subList(5, 11);
		for(Boolean val:subListDisplay){
			listToServer.add((val)?"1":"0");
		}
		iSectionTarget=5;
		updateProductDetailsPart(listToServer);
		
	}

	@Override
	public void updateDescription(String strDes) {
		List<String> listToServer = new ArrayList<String>();
		listToServer.add(strDes);
		iSectionTarget=4;
		updateProductDetailsPart(listToServer);
		
	}
	private void updateProductDetailsPart(List<String> listToServer){
		view.info("Updating...", iSectionTarget);
		Long key = Long.valueOf(targetData.getStrId());
		rpcService.updateProductDetailsPart(key, listToServer, iSectionTarget, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				view.info("Rpc failed.", iSectionTarget);
				
			}

			@Override
			public void onSuccess(String result) {
				view.info(result, iSectionTarget);
				
			}});
	}

	@Override
	public void updateSpecifications(String colorAndAppearance, String order,
			String solubility) {
		List<String> listToServer = new ArrayList<String>();
		listToServer.add(colorAndAppearance);//0
		listToServer.add(order);//1
		listToServer.add(solubility);//2
		//display/hide info:
		List<Boolean> subListDisplay=view.getIsDisplay().subList(11, 14);
		//index 4 data is for showing/hiding the whole specification section:
		listToServer.add(view.getIsDisplay().get(4)?"1":"0");
		//show/hide the three string properties:
		for(Boolean val:subListDisplay){
			listToServer.add((val)?"1":"0");
		}
		//sending to server:
		iSectionTarget=6;//code for the section
		updateProductDetailsPart(listToServer);
		
	}

}
