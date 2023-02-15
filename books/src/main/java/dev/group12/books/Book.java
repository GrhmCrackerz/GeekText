package dev.group12.books;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private ObjectId id;
    private String bookISBN;
    private String title;
    private String author;
    private List<String> genres;
    private String copiesSold;
    private String publisher;
    private String discount;
    private String yearPublished;
    private String bookDescription;
    //private List<String> rating;

    private List<Review> rating;
}
