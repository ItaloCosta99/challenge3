package com.blog.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  
}
