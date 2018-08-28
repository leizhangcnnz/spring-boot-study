/*
 * Copyright 2018, Kiwiplan (NZ) Ltd
 * User: lei.zhang
 * Created on: 2018-08-28
 * Time: 13:59:00
 */
package com.nzleyuan.persistance.com.nzleyuan.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TODO Replace with suitable description of this class.
 * <p>
 * Copyright 2018, Kiwiplan (NZ) Ltd.
 *
 * @author lei.zhang
 */
@Entity
@Table(name = "grouporder")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GroupOrder {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long objid;
  private String title;
  private long seller;
  private long category;
  private String content;

  /**
   * @return the {@link #objid}
   */
  public long getObjid() {
    return objid;
  }

  /**
   * @param objid the value to set to the {@link #objid}
   */
  public GroupOrder setObjid(long objid) {
    this.objid = objid;
    return this;
  }

  /**
   * @return the {@link #title}
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the value to set to the {@link #title}
   */
  public GroupOrder setTitle(String title) {
    this.title = title;
    return this;
  }

  /**
   * @return the {@link #seller}
   */
  public long getSeller() {
    return seller;
  }

  /**
   * @param seller the value to set to the {@link #seller}
   */
  public GroupOrder setSeller(long seller) {
    this.seller = seller;
    return this;
  }

  /**
   * @return the {@link #category}
   */
  public long getCategory() {
    return category;
  }

  /**
   * @param category the value to set to the {@link #category}
   */
  public GroupOrder setCategory(long category) {
    this.category = category;
    return this;
  }

  /**
   * @return the {@link #content}
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content the value to set to the {@link #content}
   */
  public GroupOrder setContent(String content) {
    this.content = content;
    return this;
  }
}