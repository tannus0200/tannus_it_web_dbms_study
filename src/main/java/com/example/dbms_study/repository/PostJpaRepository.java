package com.example.dbms_study.repository;

import com.example.dbms_study.entity.JpaPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<JpaPost, Integer> {
}