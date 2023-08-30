package com.blog.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
  
}
