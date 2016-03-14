package com.ayurma.ayuromaweb.client.mobile.layout;

import java.util.ArrayList;
import java.util.Map;

import com.ayurma.ayuromaweb.client.layout.ProductDetailsLayout;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.google.inject.Inject;
import com.google.inject.name.Named;

@SuppressWarnings("serial")
public class ProductDetailsMobileLayoutImpl extends ArrayList<ProductDetailsLayout<ProductDetails>>{
	private String imageServletUrl;
	@Inject
	public ProductDetailsMobileLayoutImpl(@Named("imageServletUrl") String imageServletUrl0){
		this.imageServletUrl=imageServletUrl0;
		//0 get the name of the product:
		add(new ProductDetailsLayout<ProductDetails>(){

			@Override
			public void render(ProductDetails t, StringBuilder sb,Map<String,String> styleList) {
				sb.append(t.getName());
				
			}});
		//1 get the image url:
		add(new ProductDetailsLayout<ProductDetails>(){

			@Override
			public void render(ProductDetails t, StringBuilder sb,Map<String,String> styleList) {
				if(t.getImageUrl().equals("")){
					sb.append("");
					return;
				}
				sb.append(imageServletUrl+t.getImageUrl());
				
			}});	
		//2 description:
		add(new ProductDetailsLayout<ProductDetails>(){

			@Override
			public void render(ProductDetails t, StringBuilder sb,Map<String,String> styleList) {
				sb.append(t.getDescription());
				
			}});	
		//3 basic info table:
		add(new ProductDetailsLayout<ProductDetails>(){

			@Override
			public void render(ProductDetails t, StringBuilder sb,Map<String,String> styles) {
				//get the info which to display or which not to:
				int[] listDisplay=t.getListDisplay();
				int iStyle;
				
				String[] rowStyles={"background-color:rgb(238, 255, 238);","background-color:rgb(230, 250, 230);"};
				String str=	"<table class='"+styles.get("alignCenter")+" "+styles.get("width_100")+"' style='font-family:arial;'>"
				 +"<caption class='"+styles.get("tableCaption")+"'><h3 class='"+styles.get("h3")+"'>Basic Information</h3></caption>"
				 +"<tbody>";
				 //0 common name
				iStyle=0;
				if(listDisplay[0]==1){//common name
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Common Name </td><td  class='"+styles.get("value")+"'>"+t.getCommonName()+"</td></tr>";
				iStyle=(iStyle>0)?0:1;
				}
				//1 bot name:
				if(listDisplay[1]==1){
					 str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Botanical Name: </td><td  class='"+styles.get("value")+"' style='font-style:italic;'>"+t.getBotanicalName()+"</td></tr>";
					 iStyle=(iStyle>0)?0:1;
				}
				//2 country of origin
				if(listDisplay[2]==1){
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Country of Origin: </td><td  class='"+styles.get("value")+"'>"+t.getCountryOfOrigin()+"</td></tr>";
				iStyle=(iStyle>0)?0:1;
				}
				//3 chemical constituents
				if(listDisplay[3]==1){
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Major Chemical constituents:</td><td  class='"+styles.get("value")+"'>"+t.getChemicalConstituent()+"</td></tr>";
				iStyle=(iStyle>0)?0:1;
				}
				//4 source
				if(listDisplay[4]==1){
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Obtained from:</td><td  class='"+styles.get("value")+"'>"+t.getPlantPart()+"</td></tr>";
				iStyle=(iStyle>0)?0:1;
				}
				//5 separation method
				if(listDisplay[5]==1){
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Method of separation:</td><td  class='"+styles.get("value")+"'>"+t.getSaparationMethod()+"</td></tr>";
				iStyle=(iStyle>0)?0:1;
				}
				//6 color and appearance
				if(listDisplay[6]==1){
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Color and appearance:</td><td class='"+styles.get("value")+"'>"+t.getColorAndAppearance()+"</td></tr>";
				iStyle=(iStyle>0)?0:1;
				}
				//7 order and smell
				if(listDisplay[7]==1){
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Order </td><td  class='"+styles.get("value")+"'>"+t.getOrder()+"</td></tr>";
				iStyle=(iStyle>0)?0:1;
				}
				//8 solubility
				if(listDisplay[8]==1){
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Solubility</td><td  class='"+styles.get("value")+"'>"+t.getSolubility()+"</td></tr>";;
				iStyle=(iStyle>0)?0:1;
				}

				 str+="</tbody>"
				 //+"<tfoot>"
				 //+"<tr><td colspan='2'>Info: no info here to be shown in this way</td></tr>"
				// +"</tfoot>"
				 +"</table>";
				 sb.append(str);
				
		}});
		//4 the specification like sp gravity, refra index data etc:
		add(new ProductDetailsLayout<ProductDetails>(){

			@Override
			public void render(ProductDetails t, StringBuilder sb,Map<String,String> styles) {
				if(t.getIsSection2Displayed()==1){
					
					int iStyle=0;
					String str="";
					String[] rowStyles={"background-color:rgb(238, 255, 238);","background-color:rgb(230, 250, 230);"};
					String[] strValues=t.getStrValues();//these are the values of sp grav,refrec index, opt rot and flash point each two values;
					str+="<table class='"+styles.get("alignCenter")+" "+styles.get("width_100")+"' style='font-family:arial;'>"
							+"<caption class='"+styles.get("tableCaption")+"'><h3 class='"+styles.get("h3")+"'>Specification</h3></caption>";
					
					//numerical data:
					//if refractive index is single value or it is in a range:
					if(t.getIsRfrIndexDspl()==1){
						
						
						str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Refractive Index at "+t.getTempRefracIndex();
						
						if(t.getTempUnitRfrIndex()==0) str+="&#176;C";
						else str+="&#176;F";
						str+="</td><td  class='"+styles.get("value")+"'>";
						
						if(t.getIsRfrIndexRange()==0){
							//str+=t.getRefractiveIndex();
							str+=strValues[2];
						}else{
							//str+=t.getRefractiveIndex1()+" to "+t.getRefractiveIndex2();
							str+=strValues[2]+" to "+strValues[3];
						}
						str+="</td></tr>";
						iStyle=(iStyle>0)?0:1;
					}
					//optical rotation:
					if(t.getIsOptRotDspl()==1){
						str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Optical Rotation</td><td  class='value'>";
						if(t.getIsOptRotRange()==0){
							str+=strValues[4]+"&#176;";
							//if(t.getOpticalRotation()>0){
								//str+="+"+t.getOpticalRotation()+"&#176;";
								//str+="+"+strValues[4]+"&#176;";
							//}else{ 
								//str+=t.getOpticalRotation()+"&#176;";
								//str+="-"+strValues[4]+"&#176;";
							//}
						}else{
							str+=strValues[4]+"&#176;";
							//if(t.getOpticalRotation1()>0){
								//str+="+"+t.getOpticalRotation1()+"&#176;";
							//}else{ str+=t.getOpticalRotation1()+"&#176;";}
							str+=" to ";
							str+=strValues[5]+"&#176;";
							//if(t.getOpticalRotation2()>0){
								//str+="+"+t.getOpticalRotation2()+"&#176;";
							//}else{ str+=t.getOpticalRotation2()+"&#176;";}
						}

						str+="</td></tr>";
						iStyle=(iStyle>0)?0:1;
					}
				//specific gravity:
				if(t.getIsSpGravDspl()==1){
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Specific Gravity at "+t.getTempSpGrav();
				if(t.getTempUnitSpGrav()==0) str+="&#176;C";
				else str+="&#176;F";
				str+="</td><td  class='"+styles.get("value")+"'>";
				if(t.getIsSpGravRange()==0){
					//str+=t.getSpecificGravity();
					str+=strValues[0];
				}else{
					str+=strValues[0];
					//str+=t.getSpecificGravity1();
					str+=" to ";
					str+=strValues[1];
					//str+=t.getSpecificGravity2();
				}
				str+="</td></tr>";
				iStyle=(iStyle>0)?0:1;
				}

				//flash point:
				if(t.getIsFlashPointDspl()==1){
				str+="<tr style='"+rowStyles[iStyle]+"'><td class='"+styles.get("width_50")+" "+styles.get("valueName")+"'>Flash Point (";
				if(t.getTempUnitFlashPoint()==0) str+="&#176;C";
				else str+="&#176;F";
				str+=")</td><td  class='"+styles.get("value")+"'>";
				//System.out.println("isFlashPointRange="+t.getIsFlshPntRange());
				if(t.getIsFlshPntRange()==0){
					//str+=t.getFlashPoint();
					str+=strValues[6];
				}else{
					str+=strValues[6];
					//str+=t.getFlashPoint1();
					str+=" to ";
					//str+=t.getFlashPoint2();
					str+=strValues[7];
				}

				str+="</td></tr>";
				iStyle=(iStyle>0)?0:1;
				}

				str+="</table>";
				 sb.append(str);
				}//if property data table is to be shown	
				
			}});
		//5 the uses of the product
		add(new ProductDetailsLayout<ProductDetails>(){

			@Override
			public void render(ProductDetails t, StringBuilder sb,Map<String,String> styles) {
				String str="";
				if(!t.getUses().equals("")){
				    str+="<h3 class='"+styles.get("h3")+"'>Uses</h3>";
				    str+=t.getUses();
				}
				 sb.append(str);
		}});	
		
		
	}
}
