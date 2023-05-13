package com.openclassrooms.mddapi.controller.post;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostModelAssembler assembler;

    @GetMapping("/posts")
    public CollectionModel<EntityModel<Post>> getPosts(){
        List<EntityModel<Post>> posts =  this.postService.getPosts().stream()
                .map(post -> assembler.toModel(post))
                .collect(Collectors.toList());

        return CollectionModel.of(posts, linkTo(methodOn(PostController.class).getPosts()).withSelfRel());
    }

    @GetMapping("/posts/{id}")
    public EntityModel<Post> getPostById(@PathVariable Long id) {
        Post employee = this.postService.getPostById(id).orElseThrow(() -> new NotFoundException(id));
        return assembler.toModel(employee);
    }

    @PostMapping("/posts")
    public ResponseEntity<EntityModel<Post>> addPost(@RequestBody Post newPost){
        EntityModel<Post> entityModel = assembler.toModel(this.postService.addPost(newPost));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<EntityModel<Post>> updateEmployee(@RequestBody Post newPost, @PathVariable Long id){
        EntityModel<Post> entityModel = assembler.toModel(this.postService.replacePostById(newPost,id));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        this.postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }
}
