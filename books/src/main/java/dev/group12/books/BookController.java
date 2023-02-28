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

    //finds individual book by /{using unique isbn}
    @GetMapping("/{ISBN}") //Endpoint to retrieve a single book by using its ISBN
    public ResponseEntity<Optional<Book>> getSingleBook(@PathVariable String ISBN){
        //The PathVariable converts the ObjectId into an id that we'll use to find a single book
        return new ResponseEntity<Optional<Book>>(bookService.singleBook(ISBN), HttpStatus.OK);

    }

    //this method WORKS by using /genre/{name of genre to be searched}
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

    //This method is Working by using /rating/{a double for rating}
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Book>> getBooksByRating(@PathVariable double rating){
        List<Book> books = bookService.getBooksByRating(rating);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    //This method is WORKING by using /publisher/{insert name of publisher in this spot}
    @GetMapping("/publisher/{publisher}")
    public ResponseEntity<List<Book>> getBooksByPublisher(@PathVariable String publisher){
        List<Book> books = bookService.getBooksByPublisher(publisher);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @PutMapping("/publisher/{publisher}/{discount}")
    public ResponseEntity<String> updatePricesByPublisher(@PathVariable String publisher, @PathVariable double discount) {
        bookService.updatePricesByPublisher(publisher, discount);
        return new ResponseEntity<>("Prices updated for publisher: " + publisher, HttpStatus.OK);
    }

//    @PutMapping("/{ISBN}/{discount}")
//    public ResponseEntity<Book> applyDiscount(@PathVariable String bookISBN, @RequestParam double discount){
//        Book book = bookService.applyDiscount(bookISBN, discount);
//        return new ResponseEntity<>(book, HttpStatus.OK);
//    }

    @PutMapping("/discount/{discount}")
    public ResponseEntity<String> applyDiscountToAllBooks(@PathVariable double discount){
        int count = bookService.applyDiscountToAllBooks(discount);
        String response = count + " books were updated with discount of " + discount;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
