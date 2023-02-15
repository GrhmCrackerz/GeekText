package dev.group12.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @GetMapping
    public ResponseEntity<String> allBooks(){
        return new ResponseEntity<String>("All Movies!", HttpStatus.OK);
    }
}
