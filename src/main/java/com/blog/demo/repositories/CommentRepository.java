package com.blog.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  
}
