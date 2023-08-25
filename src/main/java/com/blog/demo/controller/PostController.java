package com.blog.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.model.Post;
import com.blog.demo.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping("/{postId}")
  public ResponseEntity<String> processPost(@PathVariable Long postId) {
    try {
      postService.processPost(postId);
      return ResponseEntity.ok("Post processing initiated.");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<String> disablePost(@PathVariable Long postId) {
    try {
      postService.disablePost(postId);
      return ResponseEntity.ok("Post disabled successfully.");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{postId}")
  public ResponseEntity<String> reprocessPost(@PathVariable Long postId) {
    try {
      postService.reprocessPost(postId);
      return ResponseEntity.ok("Post reprocessing initiated.");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<List<Post>> getAllPosts() {
    List<Post> posts = postService.getAllPosts();
    return ResponseEntity.ok(posts);
  }
}
