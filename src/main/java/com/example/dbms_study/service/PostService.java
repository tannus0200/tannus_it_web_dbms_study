package com.example.dbms_study.service;

import com.example.dbms_study.dto.AddPostReqDto;
import com.example.dbms_study.dto.ApiRespDto;
import com.example.dbms_study.dto.EditPostReqDto;
import com.example.dbms_study.entity.Post;
import com.example.dbms_study.entity.User;
import com.example.dbms_study.repository.PostRepository;
import com.example.dbms_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public ApiRespDto<?> addPost(AddPostReqDto addPostReqDto) {
        try {
            Optional<User> optionalUser = userRepository.getUserByUserId(addPostReqDto.getUserId());
            if(optionalUser.isEmpty()){
                return new ApiRespDto<>("존재하지 않는 유저입니다.", null);
            }
            int result = postRepository.addPost(addPostReqDto.toEntity());

            if (result == 0) {
                return new ApiRespDto<>("문제가 발생했습니다.", null);
            }
            return new ApiRespDto<>("정상적으로 게시하였습니다.", null);
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }

    public ApiRespDto<Object> getPostByPostId(Integer postId) {
        try {
            Optional<Post> optionalPost = postRepository.getPostByPostId(postId);
            if(optionalPost.isEmpty()) {
                return new ApiRespDto<>("해당 게시물이 없습니다.", null);
            }
            return new ApiRespDto<>("조회 성공", optionalPost.get());
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다", e.getMessage());
        }
    }

    public ApiRespDto<?> getPostList() {
        try {
            List<Post> postList = postRepository.getPostList();
            return new ApiRespDto<>("조회 완료", postList);
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }

    public ApiRespDto<?> editPost(EditPostReqDto editPostReqDto) {
        try {
            Optional<Post> optionalPost = postRepository.getPostByPostId(editPostReqDto.getPostId());
            if(optionalPost.isEmpty()){
                return new ApiRespDto<>("해당 게시물은 존재하지 않습니다.", null);
            }
            int result = postRepository.editPost(editPostReqDto.toEntity());
            if(result != 1) {
                return new ApiRespDto<>("문제가 발생했습니다.", result);
            }
            return new ApiRespDto<>("수정 성공", result);
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }

    public ApiRespDto<?> removePost(Integer postId) {
        try {
            Optional<Post> optionalPost = postRepository.getPostByPostId(postId);
            if(optionalPost.isEmpty()){
                return new ApiRespDto<>("해당 게시물은 존재하지 않습니다.", null);
            }
            int result = postRepository.removePost(postId);
            if(result != 1) {
                return new ApiRespDto<>("문제가 발생했습니다.", result);
            }
            return new ApiRespDto<>("삭제 성공", result);
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }

}