package com.km.pc.rest;

public class StopVusers implements Action {

	private Type Type;
	private int Vusers;
	private Ramp Ramp;

	public StopVusers(com.km.pc.rest.Type type, int vusers,
			com.km.pc.rest.Ramp ramp) {
		super();
		Type = type;
		Vusers = vusers;
		Ramp = ramp; 
	}

	public Type getType() {
		return Type;
	}

	public void setType(Type type) {
		Type = type;
	}

	public int getVusers() {
		return Vusers;
	}

	public void setVusers(int vusers) {
		Vusers = vusers;
	}

	public Ramp getRamp() {
		return Ramp;
	}

	public void setRamp(Ramp ramp) {
		Ramp = ramp;
	}

}
