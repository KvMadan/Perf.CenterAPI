package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class RTS {
	private String xmlns = "http://www.hp.com/PC/REST/API";
	private Pacing Pacing;
	private ThinkTime ThinkTime;
	private Log Log;

	public RTS() {

	}

	public RTS(Pacing pacing, ThinkTime thinkTime, Log log) {
		this.Pacing = pacing;
		this.ThinkTime = thinkTime;
		this.Log = log;
	}

	public static RTS xmlToObject(String xml) {
		XStream xstream = new XStream();
		xstream.alias("RTS", RTS.class);
		return (RTS) xstream.fromXML(xml);
	}

	public Pacing getPacing() {
		return Pacing;
	}

	public ThinkTime getThinkTime() {
		return ThinkTime;
	}

	public Log getLog() {
		return Log;
	}

	public void setPacing(Pacing pacing) {
		Pacing = pacing;
	}

	public void setThinkTime(ThinkTime thinkTime) {
		ThinkTime = thinkTime;
	}

	public void setLog(Log log) {
		Log = log;
	}

}
