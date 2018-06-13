package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class Scheduler {

	private Action[] Actions;

	public Scheduler(Action[] actions) {
		super();
		Actions = actions;
	}

	public String objectToXML() {
		XStream obj = new XStream();
		obj.alias("Scheduler", Scheduler.class);
		return obj.toXML(this);
	}

	public Action[] getActions() {
		return Actions;
	}

	public void setActions(Action[] actions) {
		Actions = actions;
	}

}
