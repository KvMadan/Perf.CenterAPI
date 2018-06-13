package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class WorkloadType {
	private String Type;
	private String SubType;
	private String VusersDistributionMode;

	public WorkloadType(String type, String subType,
			String vusersDistributionMode) {
		super();
		Type = type;
		SubType = subType;
		VusersDistributionMode = vusersDistributionMode;
	}

	public String objectToXML() {
		XStream obj = new XStream();
		obj.alias("WorkloadType", WorkloadType.class);
		return obj.toXML(this);
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getSubType() {
		return SubType;
	}

	public void setSubType(String subType) {
		SubType = subType;
	}

	public String getVusersDistributionMode() {
		return VusersDistributionMode;
	}

	public void setVusersDistributionMode(String vusersDistributionMode) {
		VusersDistributionMode = vusersDistributionMode;
	}

}
