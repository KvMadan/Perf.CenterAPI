package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;

public class PcRunResults
{
  private ArrayList<PcRunResult> ResultsList;

  public PcRunResults()
  {
    this.ResultsList = new ArrayList();
  }

  public static PcRunResults xmlToObject(String xml)
  {
    XStream xstream = new XStream();
    xstream.alias("RunResult", PcRunResult.class);
    xstream.alias("RunResults", PcRunResults.class);
    xstream.addImplicitCollection(PcRunResults.class, "ResultsList");
    xstream.setClassLoader(PcRunResults.class.getClassLoader());
    return (PcRunResults)xstream.fromXML(xml);
  }

  public ArrayList<PcRunResult> getResultsList() {
    return this.ResultsList;
  }
}