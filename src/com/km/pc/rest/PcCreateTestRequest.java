package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class PcCreateTestRequest {
	@SuppressWarnings("unused")
	private String xmlns = "http://www.hp.com/PC/REST/API";
	private String Name;
	private String TestFolderPath;
	private Content Content;

	public PcCreateTestRequest() {

	}

	public PcCreateTestRequest(String testName, String testFolderPath,
			com.km.pc.rest.Content content) {
		super();
		Name = testName;
		TestFolderPath = testFolderPath;
		Content = content;
	}

	public String objectToXML() {
		XStream xstream = new XStream();
		xstream.alias("Test", PcCreateTestRequest.class);
		xstream.alias("Content", Content.class);
		xstream.useAttributeFor(PcCreateTestRequest.class, "xmlns");

		xstream.alias("Groups", PcGroupResponse.class);

		// For handling the Array of Groups.
		xstream.addImplicitArray(PcGroupResponse.class, "Group");
		xstream.addImplicitCollection(PcGroupResponse.class, "Gropus");
		xstream.alias("Group", Group.class);
		xstream.alias("Script", Script.class);
		xstream.alias("RTS", RTS.class);
		// xstream.alias("Pacing", Pacing.class);
		// xstream.alias("ThinkTime", ThinkTime.class);
		// xstream.alias("Log", Log.class);
		// xstream.alias("LogOptions", LogOptions.class);

		// xstream.useAttributeFor(Log.class, "Type");
		// xstream.registerConverter(new TypeConverter());
		// xstream.useAttributeFor(StartNewIteration.class, "Type");
		// xstream.useAttributeFor(ThinkTime.class, "Type");
		// xstream.useAttributeFor(LogOptions.class, "Type");

		xstream.alias("Initialize", Initialize.class);
		xstream.alias("StartVusers", StartVusers.class);
		xstream.alias("Duration", Duration.class);
		xstream.alias("StopVusers", StopVusers.class);

		return xstream.toXML(this);
	}

	public String getTestName() {
		return Name;
	}

	public void setTestName(String testName) {
		Name = testName;
	}

	public String getTestFolderPath() {
		return TestFolderPath;
	}

	public void setTestFolderPath(String testFolderPath) {
		TestFolderPath = testFolderPath;
	}

	public Content getContent() {
		return Content;
	}

	public void setContent(Content content) {
		Content = content;
	}

}
