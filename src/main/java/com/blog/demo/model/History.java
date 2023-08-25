package com.blog.demo.model;

import java.util.Date;

import com.blog.demo.enums.PostStatus;

public class History {
  private Long id;
  private Date date;
  private PostStatus status;

  public History() {
  }

  public History(Long id, Date date, PostStatus status) {
    this.id = id;
    this.date = date;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public PostStatus getStatus() {
    return status;
  }

  public void setStatus(PostStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "History [id=" + id + ", date=" + date + ", status=" + status + "]";
  }

}
