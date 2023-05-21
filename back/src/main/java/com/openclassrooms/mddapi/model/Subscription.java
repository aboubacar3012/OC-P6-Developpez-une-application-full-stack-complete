package com.openclassrooms.mddapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
