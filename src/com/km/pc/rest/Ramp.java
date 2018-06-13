package com.km.pc.rest;

public class Ramp {
	private TimeInterval TimeInterval;
	private int Vusers;

	public Ramp(com.km.pc.rest.TimeInterval timeInterval, int vusers) {
		super();
		TimeInterval = timeInterval;
		Vusers = vusers;
	}

	public TimeInterval getTimeInterval() {
		return TimeInterval;
	}

	public void setTimeInterval(TimeInterval timeInterval) {
		TimeInterval = timeInterval;
	}

	public int getVusers() {
		return Vusers;
	}

	public void setVusers(int vusers) {
		Vusers = vusers;
	}

}
