package dev.group12.books;

import org.bson.types.ObjectId;
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
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<List<Book>>(bookService.allBooks(), HttpStatus.OK);
    }

    @GetMapping("/{ISBN}")
    public ResponseEntity<Optional<Book>> getSingleBook(@PathVariable String ISBN){
        //The PathVariable converts the ObjectId into an id that we'll use to find a single book
        return new ResponseEntity<Optional<Book>>(bookService.singleBook(ISBN), HttpStatus.OK);

    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<Optional<Book>> getBooksByGenre(@PathVariable("genre") String genre){
        return new ResponseEntity<Optional<Book>>(bookService.getBooksByGenre(genre), HttpStatus.OK);
    }
}
