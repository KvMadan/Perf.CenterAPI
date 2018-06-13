package com.km.pc.rest;

public enum StartNewIterationType {
	IMMEDIATELY("IMMEDIATELY"), FIXED_DELAY("fixed delay"), RANDOM_DELAY(
			"random delay"), FIXED_INTERVAL("fixed interval"), RANDOM_INTERVAL(
			"random interval");

	private String value;

	private StartNewIterationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
