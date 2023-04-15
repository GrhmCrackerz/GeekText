package dev.group12.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    //In this class we'll have the access methods
    @Autowired //instantiates the class for us
    private BookRepository bookRepository; //reference to bookRepository

    public List<Book> allBooks(){
        return bookRepository.findAll(); //returns ALL Book objects

    }

    public Optional<Book> singleBook(String bookISBN){
        return bookRepository.findBookByBookISBN(bookISBN);
    }

    public List<Book> getBooksByGenre(List<String> genre){
        return bookRepository.findByGenreIn(genre);
    }

    public List<Book> getTopSellingBooks(){
        Sort sortBySalesCountDesc = Sort.by(Sort.Direction.DESC, "salesCount");
        PageRequest pageRequest = PageRequest.of(0,10, sortBySalesCountDesc);
        return bookRepository.findAll(pageRequest).getContent();
    }

    public List<Book> getBooksByRating(double rating){
        Sort sortByRatingDesc = Sort.by(Sort.Direction.DESC, "rating");
        return bookRepository.findByRatingGreaterThanEqual(rating, sortByRatingDesc);
    }

    public void updatePricesByPublisher(String publisher, double discount) {
        List<Book> books = bookRepository.findByPublisher(publisher);
        for (Book book : books) {
            double currentPrice = book.getPrice();
            double discountedPrice = currentPrice - (currentPrice * (discount / 100));
            book.setPrice(currentPrice, discount);
            book.setDiscountedPrice(discountedPrice);
        }
        bookRepository.saveAll(books);
    }

    public void createBook(BookDetails book) {
    }

    public Book findByIsbn(String isbn) {
        return null;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
    
    private List<BookDTO> books;

//    public Book applyDiscount(String bookISBN, double discount){
//        Book book = bookRepository.findBookByBookISBN(bookISBN).orElseThrow(() -> new BookNotFoundException(bookISBN));
//        double currentPrince = book.getPrice();
//        double discountedPrice = currentPrince - (currentPrice * (discount / 100));
//        book.setPrice(discountedPrice);
//        return bookRepository.save(book);
//    }


    public List<Book> getBooksByPublisher(String publisher){
        return bookRepository.findByPublisher(publisher);
    }

    public int applyDiscountToAllBooks(double discount){
        List<Book> books = bookRepository.findAll();
        for(Book book : books){
            double currentPrice = book.getPrice();
            double discountedPrice = currentPrice - (currentPrice * (discount / 100));
            book.setPrice(discountedPrice);
        }
        bookRepository.saveAll(books);
        return books.size();
    }

    public List<Book> getBooksByAuthor(List<String> author){
        return bookRepository.findByAuthorIn(author);
    }

    public List<String> getBookDescriptions() {
        return bookRepository.findAll().stream() 
                .map(Book::getBookDescription)
                .collect(Collectors.toList());
    }
// This method works by parsing the JSON and finding instances of the given authors names in the auhtor field. 
//     public List<Book> getBooksByAuthorLastName(String lastName) {
//     List<Book> books = bookRepository.getBooks();
//     List<Book> booksByAuthor = new ArrayList<>();
//     for (Book book : books) {
//         String author = book.getAuthor();
//         if (author.toLowerCase().contains(lastName.toLowerCase())) {
//             booksByAuthor.add(book);
//         }
//     }
//     return booksByAuthor;
// }
}
