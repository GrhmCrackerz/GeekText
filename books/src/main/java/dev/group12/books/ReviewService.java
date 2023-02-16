package dev.group12.books;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/*
    THIS CLASS IS A TESTER TO CREATE THE DISCOUNTS
 */

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReviewBy(String reviewBody, String bookISBN){
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Book.class)
                .matching(Criteria.where("bookISBN").is(bookISBN))
                .apply(new Update().push("reviewIDs").value(review))
                .first();

        return review;
    }
}
