package com.ayurma.ayuromaweb.client.mobile.layout;

import java.util.ArrayList;
import java.util.Map;

import com.ayurma.ayuromaweb.client.layout.ProductDetailsLayout;
import com.ayurma.ayuromaweb.client.layout.ProductViewLayout;
import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.ayurma.ayuromaweb.shared.ProductDetails;
import com.google.inject.Inject;
import com.google.inject.name.Named;

@SuppressWarnings("serial")
public class ProductMobileViewLayoutImpl extends ArrayList<ProductViewLayout<ChemicalData>>{
	private String imageServletUrl;
	@Inject
	public ProductMobileViewLayoutImpl(@Named("imageServletUrl") String imageServletUrl0){
		this.imageServletUrl=imageServletUrl0;
		//0 
		add(new ProductViewLayout<ChemicalData>(){

			@Override
			public void render(ChemicalData product, StringBuilder sb, Map<String,String> styleList) {
				sb.append(product.getName());
				
			}});
		//1 get the image url:
		add(new ProductViewLayout<ChemicalData>(){

			@Override
			public void render(ChemicalData product, StringBuilder sb, Map<String,String> styleList) {
				if(product.getImageUrl().equals("")){
					sb.append("");
					return;
				}
				sb.append(imageServletUrl+product.getImageUrl());
						
		}});	
		//1
		add(new ProductViewLayout<ChemicalData>(){

			@Override
			public void render(ChemicalData product, StringBuilder sb, Map<String,String> styles) {
				//String description=product.getDescription();
				//0 image-box,1 image,2 link-enquiry,3 link-more,4 table
			      String strHtml="";
			      sb.append(product.getDescription());
			      
			    //don't put this div if there is no image specified:
			      /*
			      if(!product.getImageUrl().equals("")){
			    	strHtml+="<div class='"+styles.get("image-box")+"'>";
			    	strHtml+="<img class='"+styles.get("image")+"' src='"+imageServletUrl+product.getImageUrl()+"' alt='image:"+imageServletUrl+product.getName()+"'></img>";
			    	strHtml+="</div>";
			    	
			      }
			      */
				//first give the description:
				//strHtml += "<div class='"+styles.get("product-description")+"'>"+product.getDescription()+"</div>";
			    
			      //info about in the stock:
			      /*
			      strHtml+="<p> In Stock: "
			      			+"<span style='color:"+(product.getIsInStock()?"rgb(57, 114, 73)":"rgb(150, 57, 73)")+";font-weight:bold;'>"
			      			+(product.getIsInStock()?"Yes":"No")
			      			+"</span></p>";
		    		  */   
			     
		    	  //sb.append(strHtml);  
				
		}});
	}
}
