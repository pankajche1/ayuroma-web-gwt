package com.ayurma.ayuromaweb.client.admin.util;

import com.ayurma.ayuromaweb.shared.ProductBasicInfo;

public class ProductBasicInfoComparable implements Comparable<ProductBasicInfoComparable>{
	private ProductBasicInfo source;
	@Override
	public int compareTo(ProductBasicInfoComparable o) {
		int i = source.getName().compareTo(o.source.getName());
	        
	    return i;
	}
	public ProductBasicInfo getSource() {
		return source;
	}
	public ProductBasicInfoComparable(ProductBasicInfo source) {
		
		this.source = source;
	}
	
	
}
