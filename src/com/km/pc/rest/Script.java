package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class Script {

	private int ID;

	public static Script xmlToObject(String xml) {
		XStream xstream = new XStream();
		xstream.alias("Script", Script.class);
		return (Script) xstream.fromXML(xml);
	}

	public Script() {

	}

	public Script(int id) {
		this.ID = id;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
