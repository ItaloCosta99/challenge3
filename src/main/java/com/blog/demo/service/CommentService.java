package com.blog.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blog.demo.client.PostsClient;
import com.blog.demo.model.Comment;
import com.blog.demo.model.Post;
import com.blog.demo.repositories.CommentRepository;
import com.blog.demo.repositories.PostRepository;

@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final PostRepository postRepository;
  private final PostsClient postsClient;
  private ModelMapper modelMapper;

  public CommentService(CommentRepository commentRepository, PostRepository postRepository, PostsClient postsClient,
      ModelMapper modelMapper) {
    this.commentRepository = commentRepository;
    this.postRepository = postRepository;
    this.postsClient = postsClient;
    this.modelMapper = modelMapper;
  }

  public List<Comment> save(Long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found."));
    List<Comment> comments = postsClient.getComments(id);
    List<Comment> commentsToSave = comments.stream().map(comment -> modelMapper.map(comment, Comment.class))
        .collect(Collectors.toList());
    commentRepository.saveAll(commentsToSave);
    return commentsToSave;
  }
}
