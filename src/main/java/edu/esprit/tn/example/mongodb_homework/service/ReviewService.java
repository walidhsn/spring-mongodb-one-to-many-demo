package edu.esprit.tn.example.mongodb_homework.service;

import edu.esprit.tn.example.mongodb_homework.entities.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getAll();
    Optional<Review> findById(String ID);

    Review create(String ID,Review review);
    Review update(String ID,Review review);
    void delete(Review review);
}
