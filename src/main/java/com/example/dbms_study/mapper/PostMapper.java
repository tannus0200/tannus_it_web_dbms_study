package com.example.dbms_study.mapper;

import com.example.dbms_study.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    int addPost(Post post);
    Optional<Post> getPostByPostId(Integer postId);
    List<Post> getPostList();
    int editPost(Post post);
    int removePost(Integer postId);
}