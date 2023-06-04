package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing posts.
 */
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    /**
     * Retrieves all posts.
     *
     * @return the list of all posts
     */
    public List<Post> getPosts() {
        return this.postRepository.findAll();
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id the ID of the post
     * @return an optional containing the post if found, or an empty optional if not found
     */
    public Optional<Post> getPostById(Long id) {
        return this.postRepository.findById(id);
    }

    /**
     * Adds a new post.
     *
     * @param post the post to be added
     * @return the added post
     */
    public Post addPost(Post post) {
        return this.postRepository.save(post);
    }

    /**
     * Replaces a post with the given ID with a new post.
     * If the post with the given ID exists, its fields are updated with the fields from the new post.
     * If the post with the given ID doesn't exist, a new post is created with the ID and fields from the new post.
     *
     * @param newPost the new post to replace the existing post
     * @param id      the ID of the post to be replaced
     * @return the replaced post
     */
    public Post replacePostById(Post newPost, Long id) {
        return this.postRepository.findById(id)
                .map(post -> {
                    if (newPost.getTitle() != null) post.setTitle(newPost.getTitle());
                    if (newPost.getContent() != null) post.setContent(newPost.getContent());
                    if (newPost.getSubjectId() != null) post.setSubjectId(newPost.getSubjectId());
                    if (newPost.getAuthorId() != null) post.setAuthorId(newPost.getAuthorId());
                    return postRepository.save(post);
                })
                .orElseGet(() -> {
                    newPost.setId(id);
                    return postRepository.save(newPost);
                });
    }

    /**
     * Deletes a post by its ID.
     *
     * @param id the ID of the post to be deleted
     */
    public void deletePostById(Long id) {
        this.postRepository.deleteById(id);
    }
}