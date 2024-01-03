package com.codewithbhanuka.blog.services;

import com.codewithbhanuka.blog.payloads.CommentDto;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
