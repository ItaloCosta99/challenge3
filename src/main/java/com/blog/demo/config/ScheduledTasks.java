package com.blog.demo.config;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blog.demo.client.PostsClient;
import com.blog.demo.enums.PostStatus;
import com.blog.demo.model.Post;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.service.HistoryService;

import jakarta.annotation.PostConstruct;

@Component
public class ScheduledTasks {
  private PostsClient postsClient;
  private PostRepository postRepository;
  private HistoryService historyService;

  public ScheduledTasks(PostsClient postsClient, PostRepository postRepository, HistoryService historyService) {
    this.postsClient = postsClient;
    this.postRepository = postRepository;
    this.historyService = historyService;
  }

  @PostConstruct()
  public void fetchPosts() {
    List<Post> posts = postsClient.getPosts();
    try {
      posts.forEach(post -> {
        post.setStatus(PostStatus.ENABLED);
        postRepository.save(post);
        historyService.saveHistory(PostStatus.ENABLED, post);
      });
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
}
