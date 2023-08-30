package com.blog.demo.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.blog.demo.enums.PostStatus;
import com.blog.demo.model.History;
import com.blog.demo.model.Post;
import com.blog.demo.repositories.HistoryRepository;

@Service
public class HistoryService {
  private final HistoryRepository historyRepository;
  public HistoryService(HistoryRepository historyRepository) {
    this.historyRepository = historyRepository;
  }
  // save history
  public void saveHistory(PostStatus postStatus, Post post) {
    History history = new History();
    history.setStatus(postStatus);
    history.setPost(post);
    history.setDate(new Date());
    historyRepository.save(history);
  }

}