package com.ayurma.ayuromaweb.shared;

import java.util.Comparator;

public class SortBySource implements Comparator<DataFields> {

	@Override
	public int compare(DataFields a, DataFields b) {
		int i = a.source.compareTo(b.source);
        
        return i;

	}

}
