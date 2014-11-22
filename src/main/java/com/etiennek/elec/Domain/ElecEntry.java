package com.etiennek.elec.Domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ElecEntry {

  @Id
  @GeneratedValue
  private Long id;
  @Column
  private BigDecimal reading;
  @Column
  private Date created;

  protected ElecEntry() {
    super();
  }

  public ElecEntry(BigDecimal reading) {
    this.reading = reading;
    this.created = new Date();
  }

  public BigDecimal getReading() {
    return reading;
  }

  public Long getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }



}
