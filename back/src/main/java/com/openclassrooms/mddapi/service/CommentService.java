package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing comments.
 */
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * Constructs a new CommentService with the provided CommentRepository.
     *
     * @param commentRepository the repository for managing comments
     */
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Retrieves the list of comments for a given post.
     *
     * @param postId the ID of the post
     * @return the list of comments for the post
     */
    public List<Comment> getComments(Long postId) {
        return this.commentRepository.findCommentsByPostId(postId);
    }

    /**
     * Adds a new comment to a post.
     *
     * @param comment the comment to add
     * @return the added comment
     */
    public Comment addComment(Comment comment) {
        return this.commentRepository.save(comment);
    }
}
