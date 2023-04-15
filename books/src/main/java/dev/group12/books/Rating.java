package dev.group12.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Rating")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Rating {
    @Id
    private ObjectId id;

    private String body;


}