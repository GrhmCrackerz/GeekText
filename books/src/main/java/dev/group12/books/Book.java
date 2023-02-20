package dev.group12.books;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private ObjectId id; //object id created with MongoDB
    private String bookISBN; //actual ISBN of book
    private String title; // General info
    private String author; // General info
    private List<String> genre; // ArrayList to include the genres applicable to book
    //private String genre;
    private String copiesSold; // Needed for BestSeller feature
    private String publisher; // Needed for feature that updates discounts by publisher
    private String discount; // variable to be updated
    private String yearPublished; // General info
    private String bookDescription; //General info
    //private List<String> rating;

    @DocumentReference
    private List<Review> rating;
}
