package com.blog.demo.model;

import java.util.Date;

import com.blog.demo.enums.PostStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "history")
public class History {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date date;
  private PostStatus status;

  @ManyToOne
  private Post post;

  public History() {
  }

  public History(Long id, Date date, PostStatus status, Post post) {
    this.id = id;
    this.date = date;
    this.status = status;
    this.post = post;
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

  public void setPost(Post post) {
    this.post = post;
  }

  @Override
  public String toString() {
    return "History [id=" + id + ", date=" + date + ", status=" + status + "]";
  }

}
