package edu.esprit.tn.example.mongodb_homework.entities;

import edu.esprit.tn.example.mongodb_homework.enums.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(collection = "Review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review{
    @Id
    private String reviewId;
    private String content;
    private Rating rating;
    @DBRef
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(reviewId, review.reviewId) &&
                Objects.equals(book.getBookId(), review.book.getBookId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, book.getBookId());
    }
}
