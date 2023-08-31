package com.blog.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/V1/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/{postId}")
  public ResponseEntity<Post> getPost(@PathVariable Long postId) {
    return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId));
  }

  @PostMapping("/{postId}")
  public ResponseEntity<Post> processPost(@PathVariable Long postId) {
    return ResponseEntity.status(HttpStatus.CREATED).body(postService.processPost(postId));
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<Post> disablePost(@PathVariable Long postId) {
    return ResponseEntity.status(HttpStatus.OK).body(postService.disablePost(postId));
  }

  @PutMapping("/{postId}")
  public ResponseEntity<Post> reprocessPost(@PathVariable Long postId) {
    return ResponseEntity.status(HttpStatus.OK).body(postService.reprocessPost(postId));
  }

  // Get posts with pagination
  @GetMapping("pageNum/{pageNum}/size/{pageSize}")
  public ResponseEntity<Page<Post>> getPosts(@PathVariable("pageNum") Optional<Integer> pageNum,
      @PathVariable("pageSize") Optional<Integer> pageSize) {
    // default value
    if (pageNum.isEmpty() || pageNum.get() <= 0) {
      pageNum = Optional.of(0);
    }
    if (pageSize.isEmpty() || pageSize.get() <= 0) {
      pageSize = Optional.of(10);
    }

    int intValuePageNum = pageNum.get().intValue();
    int intValueToPage = pageSize.get().intValue();
    Page<Post> posts = postService.getPostsWithPageList(intValuePageNum, intValueToPage);
    return ResponseEntity.ok(posts);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Post>> getAllPosts() {
    List<Post> posts = postService.getAllPosts();
    return ResponseEntity.ok(posts);
  }

}
