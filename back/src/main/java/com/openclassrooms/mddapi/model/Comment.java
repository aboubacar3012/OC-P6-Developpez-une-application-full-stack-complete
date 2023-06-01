package com.openclassrooms.mddapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "post_id")
    private Long postId;

    private String content;
}
