package dev.group12.books;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface wishlistRepository extends MongoRepository<wish, String> {
}
