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
    private String id;
    private String bookISBN; //actual ISBN of book
    private String title; // General info
    private String author; // General info
    private List<String> genre; // ArrayList to include the genres applicable to book
    private int salesCount; //Used for BestSeller feature
    private String publisher; // Needed for feature that updates discounts by publisher
    private String discount; // variable to be updated
    private String yearPublished; // General info
    private String bookDescription; //General info
    private double price;
    private double discountedPrice;

    private double rating;

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
//
//    public String getCopiesSold() {
//        return copiesSold;
//    }
//
//    public void setCopiesSold(String copiesSold) {
//        this.copiesSold = copiesSold;
//    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price, double discount) {
        this.price = price;
        this.discountedPrice = price - (price * (discount/100));
    }

    public double getDiscountedPrice(double discountedPrice){
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice){
        this.discountedPrice = discountedPrice;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }
}
