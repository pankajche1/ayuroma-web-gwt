package com.ayurma.ayuromaweb.client.layout;

import java.util.ArrayList;
import java.util.Map;

import com.ayurma.ayuromaweb.shared.ChemicalData;
import com.google.inject.Inject;
import com.google.inject.name.Named;


@SuppressWarnings("serial")
public class ProductViewLayoutImpl extends ArrayList<ProductViewLayout<ChemicalData>>{
	private String imageServletUrl;
	@Inject
	public ProductViewLayoutImpl(@Named("imageServletUrl") String imageServletUrl0){
		this.imageServletUrl=imageServletUrl0;
		//0 
		add(new ProductViewLayout<ChemicalData>(){

			@Override
			public void render(ChemicalData product, StringBuilder sb, Map<String,String> styleList) {
				sb.append(product.getName());
				
			}});
		//1
		add(new ProductViewLayout<ChemicalData>(){

			@Override
			public void render(ChemicalData product, StringBuilder sb, Map<String,String> styles) {
				 String description=product.getDescription();
					//0 image-box,1 image,2 link-enquiry,3 link-more,4 table
			      String strHtml="<table class='"+styles.get("table")+"'><tbody><tr>";
			     // strHtml+="<div class='chemical-list-item'>"
			    		 // strHtml +="<div class='product-wrapper'>";
		    			  // +"<h2>"+c.getName()+"</h2>";
			    //don't put this div if there is no image specified:
			      if(!product.getImageUrl().equals("")){
			    	  strHtml+="<td style='vertical-align:top;'>";
			    	  strHtml+= "<div class='"+styles.get("image-box")+"'><img class='"+styles.get("image")+"' src='"+imageServletUrl+product.getImageUrl()+"' alt='image'></img></div>";
			    	  strHtml+="</td>";
			      }else{
			    	  
			      }
			      //info about in the stock:
			      
			      strHtml+="<td style='vertical-align:top;'>"
		    		       
		    		        +description
		    		        +"<p> In Stock: "
			      			+"<span style='color:"+(product.getIsInStock()?"rgb(57, 114, 73)":"rgb(150, 57, 73)")+";font-weight:bold;'>"
			      			+(product.getIsInStock()?"Yes":"No")
			      			+"</span></p></td>"   		    
		    		        +"</tr></tbody></table>";
		    		     //the next line is commented on 131028 1218
		    		       /// +" <div class='clear'></div>";
		    		       // +"<a class='link-more' href='#products/productDetails/source-list/"+c.getStrIdDetailsInfo()+"'>more ></a>"
		    		       // +"<a class='link-enquiry' href='#products/enquiry/chemicals/list&"+c.getStrId()+"'>Enquiry</a>"
		    		       //  +" <div class='clear'>" ;
		    		         //"</div>";
		    		       // +"</div>";
			     
		    	  sb.append(strHtml);  
				
		}});
	}
}
