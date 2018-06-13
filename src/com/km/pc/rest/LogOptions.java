package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class LogOptions {

	private Type Type;
	private int CacheSize;

	public LogOptions() {
	}
	
	public LogOptions(Type Type, int CacheSize)
	{
		this.Type = Type;
		this.CacheSize = CacheSize;
	}

	public static LogOptions xmlToObject(String xml) {
		XStream xstream = new XStream();
		xstream.alias("LogOptions", LogOptions.class);
		return (LogOptions) xstream.fromXML(xml);
	}

	public Type getType() {
		return Type;
	}

	public int getCacheSize() {
		return CacheSize;
	}

}
