package dev.group12.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class wish {

    @Id
    private String userId;
    private String wishlistName;

    private String bookId;

    private String wishlistId;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getWishlistName() {
        return wishlistName;
    }
    public void setWishlistName(String wishlistName){
        this.wishlistName = wishlistName;
    }

    public String getbookId() {
        return bookId;
    }
    public void setbookId(String bookId) {this.bookId = bookId;}

    public String getwishlistId() {
        return wishlistId;
    }
    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    @Override
    public String toString() {
        return "Wishlist [User ID=" + userId + ", Wishlist Name=" + wishlistName + ", Book ID=" + bookId + ", Wishlist ID=" + wishlistId + "]";
    }


}
