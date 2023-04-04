package dev.group12.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{username}")
    public double getSubtotal(@PathVariable String username) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartService.findByUsername(username);
        if (optionalShoppingCart.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        ShoppingCart shoppingCart = optionalShoppingCart.get();

        return shoppingCart.getSubtotal();
    }

    @GetMapping("/items/{username}")
    public List<Book> getCartItems(@PathVariable String username) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartService.findByUsername(username);
        if (optionalShoppingCart.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        ShoppingCart shoppingCart = optionalShoppingCart.get();

        return shoppingCart.getItems();
    }

    @PostMapping("/add/{username}/{ISBN}")
    public void addItem(@PathVariable String username, @PathVariable String ISBN) {
        shoppingCartService.addItem(username, ISBN);
    }

    @DeleteMapping("/delete/{username}/{ISBN}")
    public void removeItem(@PathVariable String username, @PathVariable String ISBN) {
        shoppingCartService.removeItem(username, ISBN);
        double subtotal = getSubtotal(username);
        if (subtotal < 0) {
            throw new IllegalStateException("Subtotal cannot be negative.");
        }
    }
}