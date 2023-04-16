package dev.group12.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    private String id;
    private String authorName;
    private String biography;
    private String firstName;
    private String lastName;
    private String publisher;

    public String getAuthorName() {
        return authorName;
    }

    public void getAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBiography() {
        return biography;
    }

    public void getBiography(String biography) {
        this.biography = biography;
    }

    public String getFirstName() {
        return firstName;
    }

    public void getFirstName(String firstName ) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void getLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void getPublisher(String publisher) {
        this.publisher = publisher;
    }
}
