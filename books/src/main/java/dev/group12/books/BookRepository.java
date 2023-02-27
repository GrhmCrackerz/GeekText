package dev.group12.books;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //This lets the framework know that this is an interface
//MongoRepository is the generic type, the type of data we are using is Book, and the id used is ObjectId
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findBookByBookISBN(String bookISBN);
    List<Book> findByGenreIn(List<String> genre);
    List<Book> findByRatingGreaterThanEqual(double rating, Sort sortByRatingDesc);
    List<Book> findByPublisher(String publisher);

}


