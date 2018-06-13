package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class LGDistribution {
	private String Type;
	private int Amount;

	public LGDistribution(String type, int amount) {
		super();
		Type = type;
		Amount = amount;
	}

	public String objectToXML() {
		XStream obj = new XStream();
		obj.alias("LGDistribution", LGDistribution.class);
		return obj.toXML(this);
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

}
