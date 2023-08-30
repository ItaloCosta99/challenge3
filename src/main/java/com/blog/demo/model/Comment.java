package com.blog.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
  @Id
  private Long id;
  private String name;
  private String email;
  private String body;

  @ManyToOne
  private Post post;

  public Comment() {
  }

  public Comment(Long id, String name, String email, String body, Post post) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.body = body;
    this.post = post;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long commentId) {
    this.id = commentId;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Comment [id=" + id + ", body=" + body + "]";
  }

}
