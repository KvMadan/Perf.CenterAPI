package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class Log {

	private Type Type;
	private boolean ParametersSubstituion;
	private boolean DataReturnedByServer;
	private boolean AdvanceTrace;
	private LogOptions LogOptions; 
		
	public Log(){
		
	}
	
	public Log(Type Type){
		this.Type = Type;
	}
	
	public static Log xmlToObject(String xml) {
		XStream xstream = new XStream();
		xstream.alias("Log", Log.class);
		xstream.useAttributeFor(Log.class, "Type");
		xstream.registerConverter(new TypeConverter());
		return (Log) xstream.fromXML(xml);
	}
	
	public Type getType(){
		return Type;
	}
	
	public boolean getParametersSubstitution(){
		return ParametersSubstituion;
	}
	
	public boolean getDataReturnedByServer(){
		return DataReturnedByServer;
	}
	
	public boolean getAdvanceTrace(){
		return AdvanceTrace;
	}
	
	public LogOptions getLogOptions() {
		return LogOptions;
	}

	public boolean isParametersSubstituion() {
		return ParametersSubstituion;
	}

	public void setParametersSubstituion(boolean parametersSubstituion) {
		ParametersSubstituion = parametersSubstituion;
	}

	public void setType(Type type) {
		Type = type;
	}

	public void setDataReturnedByServer(boolean dataReturnedByServer) {
		DataReturnedByServer = dataReturnedByServer;
	}

	public void setAdvanceTrace(boolean advanceTrace) {
		AdvanceTrace = advanceTrace;
	}

	public void setLogOptions(LogOptions logOptions) {
		LogOptions = logOptions;
	}
	
}
