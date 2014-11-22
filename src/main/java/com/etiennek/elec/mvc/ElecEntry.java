package com.etiennek.elec.mvc;

import javax.validation.constraints.Pattern;

public class ElecEntry {

  @Pattern(regexp = "(^\\d+\\.?\\d*$)", message = "Decimal values only.")
  private String reading;

  public String getReading() {
    return this.reading;
  }

  public void setReading(String reading) {
    this.reading = reading;
  }

}
