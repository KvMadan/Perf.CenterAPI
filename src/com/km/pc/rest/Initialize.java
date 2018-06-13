package com.km.pc.rest;

public class Initialize implements Action {
	private Type Type;
	private int Vusers;

	public Initialize(com.km.pc.rest.Type type, int vusers) {
		super();
		Type = type;
		Vusers = vusers;
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

}
