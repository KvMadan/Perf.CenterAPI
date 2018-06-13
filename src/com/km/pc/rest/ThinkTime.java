package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class ThinkTime {
	private Type Type;
	private int LimitThinkTimeSeconds;
	private int MaxPercentage;
	private int MinPercentage;
	private int MultiplyFactor;

	public ThinkTime() {

	}

	public ThinkTime(Type Type) {
		this.Type = Type;
	}

	public ThinkTime(com.km.pc.rest.Type type, int limitThinkTimeSeconds,
			int maxPercentage, int minPercentage, int multiplyFactor) {
		super();
		Type = type;
		LimitThinkTimeSeconds = limitThinkTimeSeconds;
		MaxPercentage = maxPercentage;
		MinPercentage = minPercentage;
		MultiplyFactor = multiplyFactor;
	}

	public int getLimitThinkTimeSeconds() {
		return LimitThinkTimeSeconds;
	}

	public void setLimitThinkTimeSeconds(int limitThinkTimeSeconds) {
		LimitThinkTimeSeconds = limitThinkTimeSeconds;
	}

	public int getMaxPercentage() {
		return MaxPercentage;
	}

	public void setMaxPercentage(int maxPercentage) {
		MaxPercentage = maxPercentage;
	}

	public int getMinPercentage() {
		return MinPercentage;
	}

	public void setMinPercentage(int minPercentage) {
		MinPercentage = minPercentage;
	}

	public int getMultiplyFactor() {
		return MultiplyFactor;
	}

	public void setMultiplyFactor(int multiplyFactor) {
		MultiplyFactor = multiplyFactor;
	}

	public void setType(Type type) {
		Type = type;
	}

	public static ThinkTime xmlToObject(String xml) {
		XStream xstream = new XStream();
		xstream.alias("ThinkTime", ThinkTime.class);
		return (ThinkTime) xstream.fromXML(xml);
	}

	public Type getType() {
		return Type;
	}
}
