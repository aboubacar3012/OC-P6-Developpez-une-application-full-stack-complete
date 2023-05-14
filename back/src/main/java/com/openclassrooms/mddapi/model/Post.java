package com.openclassrooms.mddapi.model;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}
