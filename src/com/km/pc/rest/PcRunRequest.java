package com.km.pc.rest;

import com.km.pc.rest.TimeslotDuration;
import com.thoughtworks.xstream.XStream;

public class PcRunRequest
{
  private String xmlns = "http://www.hp.com/PC/REST/API";
  private int TestID;
  private int TestInstanceID;
  private int TimeslotID;
  private TimeslotDuration TimeslotDuration;
  private String PostRunAction;
  private boolean VudsMode;

  public PcRunRequest(int testID, int testInstanceID, int timeslotID, TimeslotDuration timeslotDuration, String postRunAction, boolean vudsMode)
  {
    this.TestID = testID;
    this.TestInstanceID = testInstanceID;
    this.TimeslotID = timeslotID;
    this.TimeslotDuration = timeslotDuration;
    this.PostRunAction = postRunAction;
    this.VudsMode = vudsMode;
  }
  public PcRunRequest() {
  }

  public String objectToXML() {
    XStream obj = new XStream();
    obj.alias("Run", PcRunRequest.class);
    obj.alias("TimeslotDuration", TimeslotDuration.class);
    obj.useAttributeFor(PcRunRequest.class, "xmlns");
    return obj.toXML(this);
  }

  public int getTestID() {
    return this.TestID;
  }

  public int getTestInstanceID() {
    return this.TestInstanceID;
  }

  public int getTimeslotID() {
    return this.TimeslotID;
  }

  public TimeslotDuration getTimeslotDuration() {
    return this.TimeslotDuration;
  }

  public String getPostRunAction() {
    return this.PostRunAction;
  }

  public boolean isVudsMode() {
    return this.VudsMode;
  }
}