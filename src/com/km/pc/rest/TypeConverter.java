package com.km.pc.rest;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class TypeConverter implements SingleValueConverter {
	public String toString(Object obj) {
		return ((Type) obj).getType();
	}

	public Object fromString(String type) {
		return new Type(type);
	}

	public boolean canConvert(Class type) {
		return type.equals(Type.class);
	}
}
