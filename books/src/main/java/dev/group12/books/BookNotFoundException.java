package dev.group12.books;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String bookISBN){
        super("Book not found with ISBN: " + bookISBN);
    }
}
