package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class Group {
	private String Name;
	private int Vusers;
	private Script Script;
	private RTS RTS;

	public static Group xmlToObject(String xml) {
		XStream xstream = new XStream();
		xstream.alias("Group", Group.class);
		return (Group) xstream.fromXML(xml);
	}

	public static String objectToXml(Group group) {
		XStream xstream = new XStream();
		xstream.alias("Group", Group.class);
		return (String) xstream.toXML(group);
	}

	public Group() {

	}

	public Group(String name, int vusers, Script script, RTS rts) {
		this.Name = name;
		this.Vusers = vusers;
		this.Script = script;
		this.RTS = rts;
	}

	public String getName() {
		return Name;
	}

	public int getVusers() {
		return Vusers;
	}

	public Script getScript() {
		return Script;
	}

	public RTS getRTS() {
		return RTS;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setVusers(int vusers) {
		Vusers = vusers;
	}

	public void setScript(Script script) {
		Script = script;
	}

	public void setRTS(RTS rTS) {
		RTS = rTS;
	}
}
