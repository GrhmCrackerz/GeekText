package dev.group12.books;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository //This lets the framework know that this is an interface
//MongoRepository is the generic type, the type of data we are using is Book, and the id used is ObjectId
public interface AuthorRepository extends MongoRepository<Author, String> {
    
    Author findAuthorById(String id);
    
    
}
