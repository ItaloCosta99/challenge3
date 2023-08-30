package com.blog.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.demo.model.Comment;
import com.blog.demo.model.Post;

@FeignClient(name = "postsclient", url = "https://jsonplaceholder.typicode.com")
public interface PostsClient {
  // Get all posts
  @GetMapping("/posts")
  List<Post> getPosts();

  // Get post by id
  @GetMapping("/posts/{id}")
  Post getPostById(@PathVariable("id") Long id);

  // Get post comments
  @GetMapping("/posts/{id}/comments")
  List<Comment> getComments(@PathVariable("id") Long id);
}
