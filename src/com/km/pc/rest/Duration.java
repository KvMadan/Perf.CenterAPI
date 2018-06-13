package com.km.pc.rest;

public class Duration implements Action {
	private Type Type;
	private TimeInterval TimeInterval;

	public Duration(com.km.pc.rest.Type type,
			com.km.pc.rest.TimeInterval timeInterval) {
		super();
		Type = type;
		TimeInterval = timeInterval;
	}

	public Type getType() {
		return Type;
	}

	public void setType(Type type) {
		Type = type;
	}

	public TimeInterval getTimeInterval() {
		return TimeInterval;
	}

	public void setTimeInterval(TimeInterval timeInterval) {
		TimeInterval = timeInterval;
	}

}
