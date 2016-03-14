package com.ayurma.ayuromaweb.client.admin.view;





import java.util.List;



import com.google.gwt.user.client.ui.Widget;

public interface IProductDetailsEditView {
	public interface Presenter{
		void onBtnSaveClick(String name, String description,//boolean isInStock,
				String separationMethods,
				String commonName,
				
				String botanicalName,
				String countryOfOrigin,
				String plantPart,
				String colorAndAppearance, 
				String chemConstituents,
				String order,
				String solubility,
				String spGrav,
				String spGrav0,
				String spGrav1,
				String tempSpGrav,
				int tempUnitSpGrav,
				String refrIndex,
				String rfrIndex1,
				String rfrIndex2,
				String tempRfrIndex,
				int tempUnitRfrIndex,
				String optRot,
				String optRot1,
				String optRot2,
				String flashPoint,
				String flashPoint1,
				String flashPoint2,
				int tempUnitFlashPoint,
				String strUses
				//List<String> strsDes
			
				);
		void updateUses(String strUses);
		void updateNumProperty(String str0,String str1,String str2,String strTemp,int tempUnit,int code);
		void updateGenInfoData(
				String commonName,//0
				String botanicalName,//1
				String countryOfOrigin,//2
				String chemConstituents,//3
				String plantPart,//4
				String separationMethods//5
				);
		//void updateDescription(List<String> listDes);
		void updateDescription(String strDes);
		void updateSpecifications(
				String colorAndAppearance, //0
				String order,//1
				String solubility//2
				);
	}
	void setPresenter(Presenter presenter);
	//void showData(List<T> list);
	//void showData(T data);
	void setData(String name,//0
			String description,//1
			//boolean isInStock,//2
			String separationMethods,//3
			String commonName,//4
			String botName,//5
			String countryOfOrigin,//6
			String plantParts,//7
			String colorAndApperance,//8
			String chemConstituents,//9
			String order,//10
			String solubility,//11
			int isSec2Dspl,//12
			int isSpGravDspl,//13
			int isSpGravRange,//14
			//Float spGrav,
			String spGravFrom,//15
			String spGravTo,//16
			float tempSpGrav,//17
			int tempUnitSpGrav,//18
			int isRfrIndexDspl,//19
			int isRfrIndexRange,//20
			//Float refrIndex,
			String refrIndexFrom,//21
			String refrIndexTo,//22
			float tempRfrIndex,//23
			int tempUnitRfrIndex,//24
			int isOptRotDspl,//25
			int isOptRotRange,//26
			//Float optRot,
			String optRotFrom,//27
			String optRotTo,//28
			int isFlashPointDspl,//29
			int isFlashPointRange,//30
			//Float flashPoint,
			String flashPointFrom,//31
			String flashPointTo,//32
			int tempUnitFlashPoint,//33
			
			List<Boolean> listDisplay,//34
			//List<String> listUse,//35
			String uses
			//List<String> listParas//paras list
			);
	
	
	void showAjaxLoading();
	void stopAjaxLoading();
	void showError(String strError);
	 void showMessage(String message);
	void info(String info,Integer id);
	List<Boolean> getIsRanges();
	List<Boolean> getIsDisplay();
	//void setData(String title, String text);
	//void setText(String text);
	//void setLayoutTemplate(List<ProductDetailsEditViewTemplate<T>> template);
	Widget asWidget();
	void reset();
}
