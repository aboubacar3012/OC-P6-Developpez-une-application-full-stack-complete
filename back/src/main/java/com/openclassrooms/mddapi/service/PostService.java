package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts() {
        return this.postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return this.postRepository.findById(id);
    }

    public Post addPost(Post post) {
        return this.postRepository.save(post);
    }

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

    public void deletePostById(Long id){
        this.postRepository.deleteById(id);
    }
}
