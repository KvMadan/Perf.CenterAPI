package com.km.pc.rest;

public class TimeslotDuration {
	private int Hours;
	private int Minutes;

	public TimeslotDuration(int hours, int minutes) {
		this.Hours = (hours + minutes / 60);
		this.Minutes = (minutes % 60);
	}

	public TimeslotDuration(String hours, String minutes) {
		try {
			int m = Integer.parseInt(minutes);
			int h = Integer.parseInt(hours) + m / 60;
			if (h < 480) {
				this.Hours = h;
				this.Minutes = (m % 60);
			} else {
				this.Hours = 480;
				this.Minutes = 0;
			}
		} catch (Exception e) {
			this.Hours = 0;
			this.Minutes = 0;
		}
	}

	public TimeslotDuration(int minutes) {
		this(0, minutes);
	}

	public int getHours() {
		return this.Hours;
	}

	public int getMinutes() {
		return this.Minutes;
	}

	public int toMinutes() {
		return this.Hours * 60 + this.Minutes;
	}

	public String toString() {
		return String.format(
				"%d:%02d(h:mm)",
				new Object[] { Integer.valueOf(this.Hours),
						Integer.valueOf(this.Minutes) });
	}
}