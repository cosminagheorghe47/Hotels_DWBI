package com.example.Hotels_DWBI.controller;

import com.example.Hotels_DWBI.model.Review;
import com.example.Hotels_DWBI.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Review getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Review create(@RequestBody Review review) {
        return service.save(review);
    }

    @PutMapping("/{id}")
    public Review update(@PathVariable Integer id, @RequestBody Review review) {
        return service.update(id, review);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
