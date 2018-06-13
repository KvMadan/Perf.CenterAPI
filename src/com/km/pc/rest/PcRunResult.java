package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class PcRunResult
{
  private int ID;
  private String Name;
  private int RunID;
  private String Type;

  public static PcRunResult xmlToObject(String xml)
  {
    XStream xstream = new XStream();
    xstream.alias("RunResult", PcRunResult.class);
    return (PcRunResult)xstream.fromXML(xml);
  }

  public int getID() {
    return this.ID;
  }

  public String getName() {
    return this.Name;
  }

  public int getRunID() {
    return this.RunID;
  }

  public String getType() {
    return this.Type;
  }
}