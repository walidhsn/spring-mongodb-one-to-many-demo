package edu.esprit.tn.example.mongodb_homework.Repository;

import edu.esprit.tn.example.mongodb_homework.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}
