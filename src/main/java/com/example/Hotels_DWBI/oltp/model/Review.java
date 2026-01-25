package com.example.Hotels_DWBI.oltp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "REVIEWS")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "comment_review", columnDefinition = "TEXT")
    @Lob
    private String commentReview;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
