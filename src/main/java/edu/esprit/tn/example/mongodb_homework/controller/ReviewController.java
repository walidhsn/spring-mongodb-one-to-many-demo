package edu.esprit.tn.example.mongodb_homework.controller;
import edu.esprit.tn.example.mongodb_homework.entities.Review;
import edu.esprit.tn.example.mongodb_homework.service.BookService;
import edu.esprit.tn.example.mongodb_homework.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllreviews(){
        List<Review> reviews = reviewService.getAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findReviewById(@PathVariable("id") String id){
        Optional<Review> review = reviewService.findById(id);
        return review.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{book_id}")
    public ResponseEntity<Review> createReview(@PathVariable("book_id") String bookId,@RequestBody Review review){
        if(bookId == null) {
            return ResponseEntity.badRequest().build();
        }
        Review result = reviewService.create(bookId,review);
        return result!=null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable("id") String id,@RequestBody Review review){
        Review result = reviewService.update(id,review);
        return result!=null ? ResponseEntity.ok("review Updated successfully") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable("id") String id){
        Optional<Review> review = reviewService.findById(id);
        if(review.isPresent()){
            reviewService.delete(review.get());
            return ResponseEntity.ok("review Deleted successfully");
        }
        return  ResponseEntity.notFound().build();
    }
}
