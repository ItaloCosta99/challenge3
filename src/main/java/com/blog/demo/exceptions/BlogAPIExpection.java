package com.blog.demo.exceptions;

import org.springframework.http.HttpStatus;

public class BlogAPIExpection extends RuntimeException {
  private HttpStatus status;
  private String message;

  public BlogAPIExpection(String message, HttpStatus status) {
    this.status = status;
    this.message = message;
  }

  public BlogAPIExpection(HttpStatus status, String message) {
    super(message);
    this.status = status;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
