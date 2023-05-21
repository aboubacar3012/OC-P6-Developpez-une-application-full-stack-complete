package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(Long postId){
        return this.commentRepository.findCommentsByPostId(postId);
    }

    public Comment addComment(Comment comment){
        return this.commentRepository.save(comment);
    }
}
