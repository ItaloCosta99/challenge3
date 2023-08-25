package com.blog.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String body;
  private Comment comments;
  private History history;

  public Post() {
  }

  public Post(Long id, String title, String body, Comment comments, History history) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.comments = comments;
    this.history = history;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Comment getComments() {
    return comments;
  }

  public void setComments(Comment comments) {
    this.comments = comments;
  }

  public History getHistory() {
    return history;
  }

  public void setHistory(History history) {
    this.history = history;
  }

  @Override
  public String toString() {
    return "Post [id=" + id + ", title=" + title + ", body=" + body + ", comments=" + comments + ", history=" + history
        + "]";
  }

}
