package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class PcErrorResponse
{
  private String xmlns = "http://www.hp.com/PC/REST/API";
  public String ExceptionMessage;
  public int ErrorCode;

  public PcErrorResponse(String exceptionMessage, int errorCode)
  {
    this.ExceptionMessage = exceptionMessage;
    this.ErrorCode = errorCode;
  }

  public static PcErrorResponse xmlToObject(String xml) {
    XStream xstream = new XStream();
    xstream.setClassLoader(PcErrorResponse.class.getClassLoader());
    xstream.alias("Exception", PcErrorResponse.class);
    return (PcErrorResponse)xstream.fromXML(xml);
  }
}