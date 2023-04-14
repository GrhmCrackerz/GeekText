package dev.group12.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping("/get/{wishlistId}")
    public ResponseEntity<wish> getWishlistBook(@PathVariable ("wishlistId") String wishlistId) {
        Optional<wish> optionalWish =  wishlistRepository.findById(wishlistId);
        if (optionalWish.isPresent()) {
            return new ResponseEntity<>(optionalWish.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
