package com.blog.demo.model;

import java.util.List;

import com.blog.demo.enums.PostStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
  @Id
  private Long id;
  private Long userId;
  private String title;
  private String body;
  private PostStatus status;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
  private List<Comment> comments;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
  private List<History> history;

  public Post() {
  }

  public Post(Long id, Long userId, String title, String body, PostStatus status, List<Comment> comments,
      List<History> history) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.body = body;
    this.status = status;
    this.comments = comments;
    this.history = history;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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

  public PostStatus getStatus() {
    return status;
  }

  public void setStatus(PostStatus status) {
    this.status = status;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<History> getHistory() {
    return history;
  }

  public void setHistory(List<History> history) {
    this.history = history;
  }

  @Override
  public String toString() {
    return "Post [id=" + id + ", title=" + title + ", body=" + body + ", comments=" + comments + ", history=" + history
        + "]";
  }

}
