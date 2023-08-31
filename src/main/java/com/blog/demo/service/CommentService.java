package com.blog.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blog.demo.client.PostsClient;
import com.blog.demo.enums.PostStatus;
import com.blog.demo.model.Comment;
import com.blog.demo.repositories.CommentRepository;
import com.blog.demo.repositories.PostRepository;

@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final PostRepository postRepository;
  private final PostsClient postsClient;
  private final HistoryService historyService;
  private ModelMapper modelMapper;

  public CommentService(CommentRepository commentRepository, PostRepository postRepository, PostsClient postsClient,
      ModelMapper modelMapper, HistoryService historyService) {
    this.commentRepository = commentRepository;
    this.postRepository = postRepository;
    this.postsClient = postsClient;
    this.modelMapper = modelMapper;
    this.historyService = historyService;
  }

  public List<Comment> save(Long id) {
    historyService.saveHistory(PostStatus.COMMENT_FIND, postRepository.getReferenceById(id));
    if (!postRepository.existsById(id)) {
      historyService.saveHistory(PostStatus.FAILED, postRepository.getReferenceById(id));
      historyService.saveHistory(PostStatus.DISABLED, postRepository.getReferenceById(id));
      throw new IllegalArgumentException("Post not found.");
    }
    List<Comment> comments = postsClient.getComments(id);
    List<Comment> commentsToSave = comments.stream().map(comment -> modelMapper.map(comment, Comment.class))
        .collect(Collectors.toList());
    commentsToSave.forEach(comment -> comment.setPost(postRepository.getReferenceById(id)));
    commentRepository.saveAll(commentsToSave);
    historyService.saveHistory(PostStatus.COMMENT_OK, postRepository.getReferenceById(id));

    return commentsToSave;
  }
}
