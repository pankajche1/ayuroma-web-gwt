package com.ayurma.ayuromaweb.shared;

public class DataFields implements Comparable<DataFields>{
	public String source;
    public String destination;
    public Long keyProduct;
    public Long keyDetails;

	@Override
	public int compareTo(DataFields o) {
	       
        int i = this.source.compareTo(o.source);
        
        return i;

	
	}

	public DataFields(String source, String destination, Long keyProduct,
			Long keyDetails) {
		
		this.source = source;
		this.destination = destination;
		this.keyProduct = keyProduct;
		this.keyDetails = keyDetails;
	}

	public DataFields() {
		
	}

}
