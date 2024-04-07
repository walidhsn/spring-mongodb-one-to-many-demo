package edu.esprit.tn.example.mongodb_homework.Repository;

import edu.esprit.tn.example.mongodb_homework.entities.Book;
import edu.esprit.tn.example.mongodb_homework.entities.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review,String> {
    List<Review> findByBook(Book book);
}
