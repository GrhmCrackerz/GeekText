package dev.group12.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Wishlist")
public class wishController {

    @Autowired
    private wishlistRepository wishlistRepository;

    public wishController (wishlistRepository wishlistRepository) {this.wishlistRepository = wishlistRepository;}
    @PostMapping("/lists")
    public ResponseEntity<wish> createList(@RequestBody wish wish) {
        try {
            wish _wish = wishlistRepository.save(new wish(wish.getUserId(),wish.getWishlistName(), wish.getbookId(), wish.getwishlistId()));
            return new ResponseEntity<>(_wish, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("lists/{wishlistId}")
    public ResponseEntity<HttpStatus> deleteList(@PathVariable("wishlistId") String wishlistId) {
        try {
            wishlistRepository.deleteById(wishlistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
