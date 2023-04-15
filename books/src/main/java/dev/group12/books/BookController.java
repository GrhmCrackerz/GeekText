package dev.group12.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.bson.types.ObjectId;
import org.json.JSONException;
import java.util.List;
import java.util.Arrays;
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

    // An alternate method to allow the user to get books by the ISBN. 
    @GetMapping("/isbn/{ISBN}")
    public ResponseEntity<Optional<Book>> getSingleBookAgain(@PathVariable String ISBN){ 
        return getSingleBook(ISBN);
    }

    //This mehtod WORKS by using /author/{author} to searched. 
     @GetMapping("/author/{author}") // endpoint to retrieve author by name.  
     public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable List<String> author) {
          List<Book> books = bookService.getBooksByAuthor(author);
          return new ResponseEntity<>(books, HttpStatus.OK);
    }
    // This method works by retrieving a book by an authors last name as an alternate method. 
    // @GetMapping("/author/{lastName}")
    // public ResponseEntity<List<Book>> authorsBooks(@PathVariable String lastName) {
    //     List<String> authors = Arrays.asList(authors.split("\\s+"));
    //     List<Book> books = bookService.getBooksByAuthorLastName(lastName);
    //     return new ResponseEntity<>(books, HttpStatus.OK);
    // }

    

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
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Book>> getSingleBookById(@PathVariable String id) {
        return new ResponseEntity<Optional<Book>>(bookService.singleBook(id), HttpStatus.OK);
    }


    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    //Allows the admin to create and save a book. 
    @PostMapping 
    public ResponseEntity<Void> createBook(@RequestBody Book book) {
        bookRepository.save(book);
        return ResponseEntity.ok().build();
    }
    // An alternate method to allow the user to get books by the ISBN. 
    //  @GetMapping("/isbn/{isbn}")
    //  public ResponseEntity<BookDetails> getBookByIsbn(@PathVariable String isbn) {
    //      Optional<Book> bookJson = bookRepository.findBookByBookISBN(isbn);
    //      org.json.JSONObject bookObj = null;
    //      try {
    //          bookObj = new org.json.JSONObject(bookJson);
    //      } catch (JSONException e) {
    //          throw new RuntimeException(e);
    //      }
    //      BookDetails book = new BookDetails(bookObj);
    //      return ResponseEntity.ok(book);
    //  }

    //This method is an API endpoint that handles HTTP GET requests to 
    //"/book". When the endpoint is called, it retrieves all books by calling 
    //the getBooks() method from the bookService.
    // @GetMapping("/book")
    // public ResponseEntity<List<BookDTO>> getBooks() {
    //     List<BookDTO> books = bookService.getBooks();
    //     if (books.isEmpty()) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    //     return new ResponseEntity<>(books, HttpStatus.OK);
    // }

    //This method is an API endpoint that parses the JSON file and Outputs Book descriptions.  
    @GetMapping("/descriptions") 
    public ResponseEntity<List<String>> getBookDescriptions () {
        List<String> books = bookService.getBookDescriptions();
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

}




