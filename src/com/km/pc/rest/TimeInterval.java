package com.km.pc.rest;

public class TimeInterval {
	private int Days;
	private int Hours;
	private int Minutes;
	private int Seconds;

	public TimeInterval(int days, int hours, int minutes, int seconds) {
		Days = days;
		Hours = hours;
		Minutes = minutes;
		Seconds = seconds;
	}

	public int getDays() {
		return Days;
	}

	public void setDays(int days) {
		Days = days;
	}

	public int getHours() {
		return Hours;
	}

	public void setHours(int hours) {
		Hours = hours;
	}

	public int getMinutes() {
		return Minutes;
	}

	public void setMinutes(int minutes) {
		Minutes = minutes;
	}

	public int getSeconds() {
		return Seconds;
	}

	public void setSeconds(int seconds) {
		Seconds = seconds;
	}

}
