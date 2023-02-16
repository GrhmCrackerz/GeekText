package dev.group12.books;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
        THIS CLASS IS A TESTER TO CREATE THE DISCOUNTS
 */

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
