package edu.esprit.tn.example.mongodb_homework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book{
    @Id
    private String bookId;
    private String title;
    private String author;
    @DBRef
    @JsonIgnore
    private List<Review> reviews;
}
