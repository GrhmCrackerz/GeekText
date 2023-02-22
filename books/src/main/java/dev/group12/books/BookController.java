package dev.group12.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {


    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){ //Endpoint to retrieve all books
        return new ResponseEntity<List<Book>>(bookService.allBooks(), HttpStatus.OK);
    }

    @GetMapping("/{ISBN}") //Endpoint to retrieve a single book by using its ISBN
    public ResponseEntity<Optional<Book>> getSingleBook(@PathVariable String ISBN){
        //The PathVariable converts the ObjectId into an id that we'll use to find a single book
        return new ResponseEntity<Optional<Book>>(bookService.singleBook(ISBN), HttpStatus.OK);

    }

    @GetMapping("/genre/{genre}") //Endpoint to retrieve all books by specific genre, takes {genre} as parameter
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable List<String> genre) {
        List<Book> books = bookService.getBooksByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/top-sellers") //Endpoint for top-selling books
    public ResponseEntity<List<Book>> getTopSellingBooks(){
        List<Book> topSellingBooks = bookService.getTopSellingBooks();
        return new ResponseEntity<>(topSellingBooks, HttpStatus.OK);
    }

}
