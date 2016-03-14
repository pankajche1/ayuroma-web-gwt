package com.ayurma.ayuromaweb.server.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

//import com.ayurma.ayuromaweb.shared.util.StringFactory;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ChemicalDetailsInfo {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long key;
    @Persistent
    private Long keyChemical;//the key of the chemical whose details the present object is
	@Persistent
    private String name="";
	@Persistent
    private Text description=new Text("TODO");
	@Persistent
	private Text uses=new Text("");
	@Persistent
	private int isSection2Display=0;//this is the Properties section at this time
	@Persistent
	private int[] listDisplay;//from 0 to 5 it contains general info display info, 6 to 8 it contains specification string properties info
    @Persistent	
    private String commonName="N/A";
    @Persistent
    private String botanicalName="N/A";
	@Persistent
	private String countryOfOrigin="N/A";

	@Persistent
    private Text imageUrl=new Text("");

    //which part of the plant it is obtained:
    @Persistent
    private String plantPart="N/A";
    @Persistent
    private String saparationMethod="N/A";
    @Persistent
    private String colorAndAppearance="N/A";
    @Persistent
    private String order="-";
   // @Persistent
   // private float specificGravity=0.0f;
   // @Persistent
   // private float refractiveIndex=0.0f;
    @Persistent
    private String chemicalConstituent="N/A";
  //  @Persistent
   // private float opticalRotation=0.0f;
    @Persistent
    private String solubility="N/A";
   // @Persistent
   // private float flashPoint=0f;
    //specific gravity data:
    @Persistent
    private int isSpGravDspl=0;
    @Persistent
    private int isSpGravRange=0;
    //@Persistent
	//private float specificGravity=0;
    //@Persistent
	///private float specificGravity1=0;
   // @Persistent
	//private float specificGravity2=0;
    @Persistent
    private float tempSpGrav=25;
    @Persistent
    private int tempUnitSpGrav=0;//0 for degree C and 1 for degF
    //optical rotation data:
    @Persistent
    private int isOptRotDspl=0;
    @Persistent
	private int isOptRotRange=0;
    //@Persistent
    //private float opticalRotation;
    //@Persistent
    //private float opticalRotation1;
    //@Persistent
    //private float opticalRotation2;
    
    //refractive index data:
    @Persistent
    private int isRfrIndexDspl=0;
    @Persistent
    private int isRfrIndexRange=0;
   // @Persistent
   // private float refractiveIndex=0;
   // @Persistent
   // private float refractiveIndex1=0;
   // @Persistent
   // private float refractiveIndex2=0;
    @Persistent
    private float tempRefracIndex=25;
    @Persistent
    private int tempUnitRfrIndex=0;//0 for degC and 1 for degF
    //flash point data:
    @Persistent
    private int isFlashPointDspl=0;
    @Persistent
    private int isFlshPntRange=0;
   // @Persistent
   // private float flashPoint=0f;
   // @Persistent
   // private float flashPoint1=0f;
   // @Persistent
   // private float flashPoint2=0f;
    @Persistent
    private int tempUnitFlashPoint=0;//0 for degreeC and 1 for degreeF
    //private Float a;
    @Persistent
    private boolean isInStock=false;
    //these are 8 values:
    @Persistent
    private String[] strValues={"0","0","0","0","0","0","0","0"};
    @Persistent
    private String spGrav1="0";
    @Persistent
    private String spGrav2="0";
    @Persistent
    private String rfrInd1="0";
    @Persistent
    private String rfrInd2="0";
    @Persistent
    private String optRot1="0";
    @Persistent
    private String optRot2="0";
    @Persistent
    private String flshPnt1="0";
    @Persistent
    private String flshPnt2="0";
    public void setProperties(String[] strValues){
    	this.spGrav1=strValues[0];
    	this.spGrav2=strValues[1];
    	this.rfrInd1=strValues[2];
    	this.rfrInd2=strValues[3];
    	this.optRot1=strValues[4];
    	this.optRot2=strValues[5];
    	this.flshPnt1=strValues[6];
    	this.flshPnt2=strValues[7];
    	
    }
    public String[] getProperties(){
    	String[] values={spGrav1,spGrav2,rfrInd1,rfrInd2,optRot1,optRot2,flshPnt1,flshPnt2};
    	return values;
    }
    public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public String getSaparationMethod() {
		return saparationMethod;
	}
	public void setSaparationMethod(String saparationMethod) {
		this.saparationMethod = saparationMethod;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSolubility() {
		return solubility;
	}
	public void setSolubility(String solubility) {
		this.solubility = solubility;
	}
	/*
	public float getFlashPoint() {
		return flashPoint;
	}
	public void setFlashPoint(float flashPoint) {
		this.flashPoint = flashPoint;
	}
*/
	public String getImageUrl() {
		return imageUrl.getValue();
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl=new Text(imageUrl);
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
	*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description.getValue();
	}
	public void setDescription(String description) {
		this.description = new Text(description);
	}
	public String getUses() {
		return uses.getValue();
	}
	public void setUses(String uses) {
		this.uses = new Text(uses);
	} 	
	public Long getKey() {
		return key;
	}

	public ChemicalDetailsInfo(String name, String description) {
		
		this.name = name;
		this.description = new Text(description);
		listDisplay=new int[9];
		
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
	public float getTempRefracIndex() {
		return tempRefracIndex;
	}
	public void setTempRefracIndex(float tempRefracIndex) {
		this.tempRefracIndex = tempRefracIndex;
	}
	public int getTempUnitSpGrav() {
		return tempUnitSpGrav;
	}
	public void setTempUnitSpGrav(int tempUnitSpGrav) {
		this.tempUnitSpGrav = tempUnitSpGrav;
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
	public int getIsSection2Display() {
		return isSection2Display;
	}
	public void setIsSection2Display(int isSection2Display) {
		this.isSection2Display = isSection2Display;
	}
	public int[] getListDisplay() {
		return listDisplay;
	}
	public void setListDisplay(int[] listDisplay) {
		this.listDisplay = listDisplay;
	}
	public boolean getIsInStock() {
		return isInStock;
	}
	public void setIsInStock(boolean isInStock) {
		this.isInStock = isInStock;
	}
	public String[] getStrValues() {
		return strValues;
	}
	public void setStrValues(String[] strValues) {
		this.strValues = strValues;
	}
	public void setSpGrav1(String spGrav1) {
		this.spGrav1 = spGrav1;
	}
	public void setSpGrav2(String spGrav2) {
		this.spGrav2 = spGrav2;
	}
	public void setRfrInd1(String rfrInd1) {
		this.rfrInd1 = rfrInd1;
	}
	public void setRfrInd2(String rfrInd2) {
		this.rfrInd2 = rfrInd2;
	}
	public void setOptRot1(String optRot1) {
		this.optRot1 = optRot1;
	}
	public void setOptRot2(String optRot2) {
		this.optRot2 = optRot2;
	}
	public void setFlshPnt1(String flshPnt1) {
		this.flshPnt1 = flshPnt1;
	}
	public void setFlshPnt2(String flshPnt2) {
		this.flshPnt2 = flshPnt2;
	}


	
}
