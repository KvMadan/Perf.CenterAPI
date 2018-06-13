package com.km.pc.rest;

public class StartNewIteration {
	private Type Type;
	private int DelayAtRangeOfSeconds;
	private int DelayAtRangeToSeconds;

	public StartNewIteration() {

	}

	public StartNewIteration(Type Type) {
		this.Type = Type;
	}

	public StartNewIteration(com.km.pc.rest.Type type,
			int delayAtRangeOfSeconds, int delayAtRangeToSeconds) {
		super();
		Type = type;
		DelayAtRangeOfSeconds = delayAtRangeOfSeconds;
		DelayAtRangeToSeconds = delayAtRangeToSeconds;
	}

	public int getDelayAtRangeOfSeconds() {
		return DelayAtRangeOfSeconds;
	}

	public void setDelayAtRangeOfSeconds(int delayAtRangeOfSeconds) {
		DelayAtRangeOfSeconds = delayAtRangeOfSeconds;
	}

	public int getDelayAtRangeToSeconds() {
		return DelayAtRangeToSeconds;
	}

	public void setDelayAtRangeToSeconds(int delayAtRangeToSeconds) {
		DelayAtRangeToSeconds = delayAtRangeToSeconds;
	}

	public void setType(Type type) {
		Type = type;
	}

	public Type getType() {
		return Type;
	}
}
