package com.blog.demo.config;

import java.util.List;

import org.springframework.stereotype.Component;

import com.blog.demo.client.PostsClient;
import com.blog.demo.enums.PostStatus;
import com.blog.demo.model.Comment;
import com.blog.demo.model.Post;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.service.CommentService;
import com.blog.demo.service.HistoryService;

import jakarta.annotation.PostConstruct;

@Component
public class ScheduledTasks {
  private PostsClient postsClient;
  private PostRepository postRepository;
  private HistoryService historyService;
  private CommentService commentService;

  public ScheduledTasks(PostsClient postsClient, PostRepository postRepository, HistoryService historyService, CommentService commentService) {
    this.postsClient = postsClient;
    this.postRepository = postRepository;
    this.historyService = historyService;
    this.commentService = commentService;
  }

  @PostConstruct()
  public void fetchPosts() {
    List<Post> posts = postsClient.getPosts();
    try {
      posts.forEach(post -> {
        post.setStatus(PostStatus.CREATED);
        postRepository.save(post);
        historyService.saveHistory(PostStatus.CREATED, post);
        historyService.saveHistory(PostStatus.POST_FIND, post);
        historyService.saveHistory(PostStatus.POST_OK, post);
        commentService.save(post.getId());
      });
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
}
