package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class Pacing {

	private int NumberOfIterations;
	private StartNewIteration StartNewIteration;

	public Pacing() {

	}
	
	public static Pacing xmlToObject(String xml) {
		XStream xstream = new XStream();
		xstream.alias("Pacing", Pacing.class);
		return (Pacing) xstream.fromXML(xml);
	}
	

	public int getNumberOfIterations() {
		return NumberOfIterations;
	}

	public StartNewIteration getStartNewIteration() {
		return StartNewIteration;
	}
}
