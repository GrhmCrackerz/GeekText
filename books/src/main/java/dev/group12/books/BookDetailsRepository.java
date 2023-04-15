package dev.group12.books;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepository extends MongoRepository<BookDetails, ObjectId> {
    static void save(Book book) {
    }

    //@Query("{ 'isbn' : ?0 }")
    String findBookDetailsByBookISBN(String bookISBN);
}

