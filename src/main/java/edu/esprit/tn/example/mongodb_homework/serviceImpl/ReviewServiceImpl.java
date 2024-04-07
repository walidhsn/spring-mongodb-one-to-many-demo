package edu.esprit.tn.example.mongodb_homework.serviceImpl;

import edu.esprit.tn.example.mongodb_homework.Repository.BookRepository;
import edu.esprit.tn.example.mongodb_homework.Repository.ReviewRepository;
import edu.esprit.tn.example.mongodb_homework.entities.Book;
import edu.esprit.tn.example.mongodb_homework.entities.Review;
import edu.esprit.tn.example.mongodb_homework.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> findById(String ID) {
        return reviewRepository.findById(ID);
    }

    @Override
    public Review create(String ID,Review review) {
        Optional<Book> optionalBook = bookRepository.findById(ID);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            review.setBook(book);
            reviewRepository.save(review);
            if(book.getReviews()==null){
                List<Review> list = new ArrayList<Review>();
                list.add(review);
                book.setReviews(list);
            }else{
                book.getReviews().add(review);
            }
            bookRepository.save(book);
            return review;
        }
        return null;
    }

    @Override
    public Review update(String ID,Review review) {
        Optional<Review> result = reviewRepository.findById(ID);
        if(result.isPresent()){
            Review updatedReview = result.get();
            updatedReview.setContent(review.getContent());
            updatedReview.setRating(review.getRating());
            Optional<Book> optionalBook = bookRepository.findById(updatedReview.getBook().getBookId());
            if(optionalBook.isPresent()){
                Book book = optionalBook.get();
                updatedReview.setBook(book);
                book.getReviews().remove(result.get());
                book.getReviews().add(updatedReview);
                reviewRepository.save(updatedReview);
                bookRepository.save(book);
                return updatedReview;
            }
        }
        return null;
    }

    @Override
    public void delete(Review review) {
        Optional<Book> optionalBook = bookRepository.findById(review.getBook().getBookId());
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.getReviews().remove(review);
            bookRepository.save(book);
        }
        reviewRepository.delete(review);
    }
}
