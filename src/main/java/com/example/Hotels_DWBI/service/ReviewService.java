package com.example.Hotels_DWBI.service;

import com.example.Hotels_DWBI.model.Review;
import com.example.Hotels_DWBI.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> findAll() {
        return repository.findAll();
    }

    public Review findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    }

    public Review save(Review review) {
        return repository.save(review);
    }

    public Review update(Integer id, Review updated) {
        Review existing = findById(id);

        existing.setReservation(updated.getReservation());
        existing.setRating(updated.getRating());
        existing.setComment(updated.getComment());
        existing.setCreatedAt(updated.getCreatedAt());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
