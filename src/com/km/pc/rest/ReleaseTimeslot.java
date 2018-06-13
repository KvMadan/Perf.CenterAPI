package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class ReleaseTimeslot
{
  private String xmlns = "http://www.hp.com/PC/REST/API";
  private boolean ReleaseTimeslot;
  private String PostRunAction;

  public ReleaseTimeslot(boolean releaseTimeslot, String postRunAction)
  {
    this.ReleaseTimeslot = releaseTimeslot;
    this.PostRunAction = postRunAction;
  }

  public String objectToXML() {
    XStream obj = new XStream();
    obj.alias("PostRunActions", ReleaseTimeslot.class);
    obj.useAttributeFor(ReleaseTimeslot.class, "xmlns");
    return obj.toXML(this);
  }

  public boolean isReleaseTimeslot() {
    return this.ReleaseTimeslot;
  }

  public String getPostRunAction() {
    return this.PostRunAction;
  }
}