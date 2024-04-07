package edu.esprit.tn.example.mongodb_homework.service;

import edu.esprit.tn.example.mongodb_homework.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService{
    List<Book> getAll();
    Optional<Book> findById(String ID);
    Book create(Book book);
    Book update(String ID,Book book);
    void delete(Book book);

}
