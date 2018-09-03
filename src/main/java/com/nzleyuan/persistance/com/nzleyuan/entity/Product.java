package com.nzleyuan.persistance.com.nzleyuan.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long objid;
  private String name;
  private BigDecimal inventory;
  private String unit;
  private BigDecimal price;
  private String detail;

  public long getObjid() {
    return objid;
  }

  public Product setObjid(long objid) {
    this.objid = objid;
    return this;
  }

  public String getName() {
    return name;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public BigDecimal getInventory() {
    return inventory;
  }

  public Product setInventory(BigDecimal inventory) {
    this.inventory = inventory;
    return this;
  }

  public String getUnit() {
    return unit;
  }

  public Product setUnit(String unit) {
    this.unit = unit;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Product setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public String getDetail() {
    return detail;
  }

  public Product setDetail(String detail) {
    this.detail = detail;
    return this;
  }
}
