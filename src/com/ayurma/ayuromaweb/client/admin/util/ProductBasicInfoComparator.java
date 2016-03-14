package com.ayurma.ayuromaweb.client.admin.util;

import java.util.Comparator;

public class ProductBasicInfoComparator implements Comparator<ProductBasicInfoComparable>{

	@Override
	public int compare(ProductBasicInfoComparable a,
			ProductBasicInfoComparable b) {
		int i = a.getSource().getName().compareTo(b.getSource().getName());
        
        return i;

	}

}
