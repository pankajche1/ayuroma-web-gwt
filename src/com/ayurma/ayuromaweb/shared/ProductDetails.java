package com.ayurma.ayuromaweb.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductDetails implements Serializable {
	private String strId="";
	private Long keyChemical;//the key of the chemical whose details it is.	
	private byte isInStock=0;
    public String getStrId() {
		return strId;
	}
	public void setStrId(String strId) {
		this.strId = strId;
	}
	private String name="";
   
    private String description="";
    private String uses="";
    private int[] listDisplay;
	private int isSection2Displayed=0;//this is the Properties section at this time
    private String botanicalName="";
    private String commonName="-";
    private String countryOfOrigin="-";
    private String plantPart="";
   
    public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	private String saparationMethod="Distillation";
   
    private String colorAndAppearance="";
    private String order="-";
    private String solubility="-";
    public String getSolubility() {
		return solubility;
	}
	public void setSolubility(String solubility) {
		this.solubility = solubility;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	//specific gravity data:
	 private int isSpGravDspl=0;
	private int isSpGravRange=0;
	//private float specificGravity=0;
	//private float specificGravity1=0;
	//private float specificGravity2=0;
	 private float tempSpGrav=0;
	 private int tempUnitSpGrav=0;//0 for degree C and 1 for degF

	//optical rotation data:
	 private int isOptRotDspl=0;
	private int isOptRotRange=0;
    //private float opticalRotation;
    //private float opticalRotation1;
    //private float opticalRotation2;
    //refractive index data:
    private int isRfrIndexDspl=0;
    private int isRfrIndexRange=0;
    //private float refractiveIndex=0;
    //private float refractiveIndex1=0;
    //private float refractiveIndex2=0;
	private float tempRefracIndex=0;
	 private int tempUnitRfrIndex=0;//0 for degC and 1 for degF
    //flash point data:
    private int isFlashPointDspl=0;
    private int isFlshPntRange=0;
    
    //private float flashPoint=0f;
    //private float flashPoint1=0f;
    //private float flashPoint2=0f;
    private int tempUnitFlashPoint=0;//0 for degreeC and 1 for degreeF
    private String chemicalConstituent="";
	private String imageUrl="";   
	 private String[] strValues={"","","","","","","",""};
    public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBotanicalName() {
		return botanicalName;
	}
	public void setBotanicalName(String botanicalName) {
		this.botanicalName = botanicalName;
	}
	public String getPlantPart() {
		return plantPart;
	}
	public void setPlantPart(String plantPart) {
		this.plantPart = plantPart;
	}

	public String getSaparationMethod() {
		return saparationMethod;
	}
	public void setSaparationMethod(String saparationMethod) {
		this.saparationMethod = saparationMethod;
	}
	public String getColorAndAppearance() {
		return colorAndAppearance;
	}
	public void setColorAndAppearance(String colorAndAppearance) {
		this.colorAndAppearance = colorAndAppearance;
	}
	/*
	public float getSpecificGravity() {
		return specificGravity;
	}
	public void setSpecificGravity(float specificGravity) {
		this.specificGravity = specificGravity;
	}
	public float getRefractiveIndex() {
		return refractiveIndex;
	}
	public void setRefractiveIndex(float refractiveIndex) {
		this.refractiveIndex = refractiveIndex;
	}
	*/
	public String getChemicalConstituent() {
		return chemicalConstituent;
	}
	public void setChemicalConstituent(String chemicalConstituent) {
		this.chemicalConstituent = chemicalConstituent;
	}
	/*
	public float getOpticalRotation() {
		return opticalRotation;
	}
	public void setOpticalRotation(float opticalRotation) {
		this.opticalRotation = opticalRotation;
	}
	

	public float getFlashPoint() {
		return flashPoint;
	}
	public void setFlashPoint(float flashPoint) {
		this.flashPoint = flashPoint;
	}
	*/
	public ProductDetails() {
		listDisplay=new int[9];
	}
	
	public ProductDetails(String name, String description) {
		
		this.name = name;
		this.description = description;
		listDisplay=new int[9];
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public Long getKeyChemical() {
		return keyChemical;
	}
	public void setKeyChemical(Long keyChemical) {
		this.keyChemical = keyChemical;
	}
	public int getIsSpGravRange() {
		return isSpGravRange;
	}
	public void setIsSpGravRange(int isSpGravRange) {
		this.isSpGravRange = isSpGravRange;
	}
	/*
	public float getSpecificGravity1() {
		return specificGravity1;
	}
	public void setSpecificGravity1(float specificGravity1) {
		this.specificGravity1 = specificGravity1;
	}
	public float getSpecificGravity2() {
		return specificGravity2;
	}
	public void setSpecificGravity2(float specificGravity2) {
		this.specificGravity2 = specificGravity2;
	}
	*/
	public int getIsOptRotRange() {
		return isOptRotRange;
	}
	public void setIsOptRotRange(int isOptRotRange) {
		this.isOptRotRange = isOptRotRange;
	}
	/*
	public float getOpticalRotation1() {
		return opticalRotation1;
	}
	public void setOpticalRotation1(float opticalRotation1) {
		this.opticalRotation1 = opticalRotation1;
	}
	public float getOpticalRotation2() {
		return opticalRotation2;
	}
	public void setOpticalRotation2(float opticalRotation2) {
		this.opticalRotation2 = opticalRotation2;
	}
	*/
	public int getIsRfrIndexRange() {
		return isRfrIndexRange;
	}
	public void setIsRfrIndexRange(int isRfrIndexRange) {
		this.isRfrIndexRange = isRfrIndexRange;
	}
	/*
	public float getRefractiveIndex1() {
		return refractiveIndex1;
	}
	public void setRefractiveIndex1(float refractiveIndex1) {
		this.refractiveIndex1 = refractiveIndex1;
	}
	public float getRefractiveIndex2() {
		return refractiveIndex2;
	}
	public void setRefractiveIndex2(float refractiveIndex2) {
		this.refractiveIndex2 = refractiveIndex2;
	}
	*/
	public int getIsFlshPntRange() {
		return isFlshPntRange;
	}
	public void setIsFlshPntRange(int isFlshPntRange) {
		this.isFlshPntRange = isFlshPntRange;
	}
	/*
	public float getFlashPoint1() {
		return flashPoint1;
	}
	public void setFlashPoint1(float flashPoint1) {
		this.flashPoint1 = flashPoint1;
	}
	public float getFlashPoint2() {
		return flashPoint2;
	}
	public void setFlashPoint2(float flashPoint2) {
		this.flashPoint2 = flashPoint2;
	}
	*/
	public int getIsSpGravDspl() {
		return isSpGravDspl;
	}
	public void setIsSpGravDspl(int isSpGravDspl) {
		this.isSpGravDspl = isSpGravDspl;
	}
	public int getIsOptRotDspl() {
		return isOptRotDspl;
	}
	public void setIsOptRotDspl(int isOptRotDspl) {
		this.isOptRotDspl = isOptRotDspl;
	}
	public int getIsRfrIndexDspl() {
		return isRfrIndexDspl;
	}
	public void setIsRfrIndexDspl(int isRfrIndexDspl) {
		this.isRfrIndexDspl = isRfrIndexDspl;
	}
	public int getIsFlashPointDspl() {
		return isFlashPointDspl;
	}
	public void setIsFlashPointDspl(int isFlashPointDspl) {
		this.isFlashPointDspl = isFlashPointDspl;
	}
	public float getTempSpGrav() {
		return tempSpGrav;
	}
	public void setTempSpGrav(float tempSpGrav) {
		this.tempSpGrav = tempSpGrav;
	}
	public int getTempUnitSpGrav() {
		return tempUnitSpGrav;
	}
	public void setTempUnitSpGrav(int tempUnitSpGrav) {
		this.tempUnitSpGrav = tempUnitSpGrav;
	}
	public float getTempRefracIndex() {
		return tempRefracIndex;
	}
	public void setTempRefracIndex(float tempRefracIndex) {
		this.tempRefracIndex = tempRefracIndex;
	}
	public int getTempUnitRfrIndex() {
		return tempUnitRfrIndex;
	}
	public void setTempUnitRfrIndex(int tempUnitRfrIndex) {
		this.tempUnitRfrIndex = tempUnitRfrIndex;
	}
	public int getTempUnitFlashPoint() {
		return tempUnitFlashPoint;
	}
	public void setTempUnitFlashPoint(int tempUnitFlashPoint) {
		this.tempUnitFlashPoint = tempUnitFlashPoint;
	}
	public int getIsSection2Displayed() {
		return isSection2Displayed;
	}
	public void setIsSection2Displayed(int isSection2Displayed) {
		this.isSection2Displayed = isSection2Displayed;
	}
	public int[] getListDisplay() {
		return listDisplay;
	}
	public void setListDisplay(int[] listDisplay) {
		this.listDisplay = listDisplay;
	}
	public boolean getIsInStock() {
		return (isInStock==1)?true:false;
	}
	public void setIsInStock(boolean isInStock) {
		this.isInStock = (byte) ((isInStock)?1:0);
	}
	public String[] getStrValues() {
		return strValues;
	}
	public void setStrValues(String[] strValues) {
		this.strValues = strValues;
	}
	public String getUses() {
		return uses;
	}
	public void setUses(String uses) {
		this.uses = uses;
	}  
	
}
