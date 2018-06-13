package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class PcGroupResponse {
	private Group[] Group;

	public PcGroupResponse() {

	}

	public PcGroupResponse(Group[] groups) {
		this.Group = groups;
	}

	public static PcGroupResponse xmlToObject(String xml) {
		XStream xstream = new XStream();
		xstream.alias("Groups", PcGroupResponse.class);
		return (PcGroupResponse) xstream.fromXML(xml);
	}

	public Group[] getGroup() {
		return Group;
	}
}
