package com.ayurma.ayuromaweb.client.layout;

import java.util.Map;

public abstract class ProductDetailsLayout<T> {
	public abstract void render(T t, StringBuilder sb,Map<String,String> styleList);
}
