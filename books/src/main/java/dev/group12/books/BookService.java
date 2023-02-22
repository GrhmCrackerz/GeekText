package dev.group12.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

}
