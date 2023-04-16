package dev.group12.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;
import java.util.stream.Collectors;
// import java.util.Array;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired //instantiates the class for us
    private AuthorRepository authorRepository; //reference to authorRepository

    public Author singleAuthor(String authorId){
        return authorRepository.findAuthorById(authorId);
    }
    public void createAuthor(Author author) {
        authorRepository.save(author);
    }
}
