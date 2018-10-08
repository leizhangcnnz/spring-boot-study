package com.nzleyuan.persistance.com.nzleyuan.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "photos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Photos {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long objid;
  private long referenceId;
  private short sequence;
  private String path;

  public long getObjid() {
    return objid;
  }

  public Photos setObjid(long objid) {
    this.objid = objid;
    return this;
  }

  public long getReferenceId() {
    return referenceId;
  }

  public Photos setReferenceId(long referenceId) {
    this.referenceId = referenceId;
    return this;
  }

  public short getSequence() {
    return sequence;
  }

  public Photos setSequence(short sequence) {
    this.sequence = sequence;
    return this;
  }

  public String getPath() {
    return path;
  }

  public Photos setPath(String path) {
    this.path = path;
    return this;
  }
}
