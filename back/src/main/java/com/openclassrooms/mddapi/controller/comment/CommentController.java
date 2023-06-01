package com.openclassrooms.mddapi.controller.comment;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> comments(@PathVariable Long id){
        return ResponseEntity.ok().body(this.commentService.getComments(id));
    }

    @PostMapping()
    public ResponseEntity<?> subscribe(@RequestBody Comment newComment){
        return ResponseEntity.ok().body(this.commentService.addComment(newComment));
    }
}
