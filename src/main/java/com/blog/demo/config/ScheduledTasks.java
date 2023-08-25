package com.blog.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.blog.demo.service.PostService;

@Configuration
@EnableScheduling
public class ScheduledTasks {
  private final PostService postService;

  public ScheduledTasks(PostService postService) {
    this.postService = postService;
  }

  @Scheduled(fixedRate = 60000)
  public void fetchPosts() {
    postService.getAllPosts();
  }
}
