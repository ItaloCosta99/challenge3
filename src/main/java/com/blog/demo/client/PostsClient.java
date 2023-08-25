package com.blog.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.demo.model.Post;

@FeignClient(name = "posts", url = "https://jsonplaceholder.typicode.com/posts")
public interface PostsClient {
  // Get all posts
  @GetMapping(consumes = "application/json")
  List<Post> getAllPosts();

  // Get post by id
  @GetMapping(value = "/{id}", consumes = "application/json")
  Post getPostById(@PathVariable("id") Long id);

}
