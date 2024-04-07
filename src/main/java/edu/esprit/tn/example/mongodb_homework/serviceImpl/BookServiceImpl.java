package edu.esprit.tn.example.mongodb_homework.serviceImpl;

import edu.esprit.tn.example.mongodb_homework.Repository.BookRepository;
import edu.esprit.tn.example.mongodb_homework.entities.Book;
import edu.esprit.tn.example.mongodb_homework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(String ID) {
        return bookRepository.findById(ID);
    }

    @Override
    public Book create(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public Book update(String ID,Book book) {
        Optional<Book> result = bookRepository.findById(ID);
        if(result.isPresent()){
            Book updateBook = result.get();
            updateBook.setAuthor(book.getAuthor());
            updateBook.setTitle(book.getTitle());
            if(!updateBook.getReviews().isEmpty() && !book.getReviews().isEmpty()){
                updateBook.setReviews(book.getReviews());
            }else if(!book.getReviews().isEmpty()){
                updateBook.setReviews(book.getReviews());
            }
            return bookRepository.save(updateBook);
        }
        return null;
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
