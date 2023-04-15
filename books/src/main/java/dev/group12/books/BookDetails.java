package dev.group12.books;

import java.util.ArrayList;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class BookDetails {

    public String bookISBN;
    public String title;
    public String bookDescription;
    public double price;
    public String author;
    public ArrayList genre;
    public String publisher;
    public int yearPublished;
    public int copiesSold;

    // public BookDetails(Book bookDTO) {
    // }

    public BookDetails(org.json.JSONObject json) {
    }
    // public BookDetails() {
    // }
}