package com.km.pc.rest;

public enum PostRunAction
{
  COLLATE("Collate Results"), 
  COLLATE_AND_ANALYZE("Collate And Analyze"), 
  DO_NOTHING("Do Not Collate");

  private String value;

  private PostRunAction(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}