package edu.esprit.tn.example.mongodb_homework.controller;

import edu.esprit.tn.example.mongodb_homework.entities.Book;
import edu.esprit.tn.example.mongodb_homework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable("id") String id){
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book result = bookService.create(book);
        return result!=null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") String id,@RequestBody Book book){
        Book result = bookService.update(id,book);
        return result!=null ? ResponseEntity.ok("Book Updated successfully") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") String id){
        Optional<Book> book = bookService.findById(id);
        if(book.isPresent()){
            bookService.delete(book.get());
            return ResponseEntity.ok("Book Deleted successfully");
        }
        return  ResponseEntity.notFound().build();
    }

}
