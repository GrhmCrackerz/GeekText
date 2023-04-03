package dev.group12.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public Optional<ShoppingCart> findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(User::getShoppingCart);
    }

    public void addItem(String username, String ISBN) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Book> optionalBook = bookRepository.findById(ISBN);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        if (optionalBook.isEmpty()) {
            throw new BookNotFoundException("Book not found with ISBN: " + ISBN);
        }

        User user = optionalUser.get();
        Book book = optionalBook.get();

        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.addItem(book);

        shoppingCartRepository.save(shoppingCart);
        userRepository.save(user);
    }

    public void removeItem(String username, String ISBN) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Book> optionalBook = bookRepository.findById(ISBN);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        if (optionalBook.isEmpty()) {
            throw new BookNotFoundException("Book not found with ISBN: " + ISBN);
        }

        User user = optionalUser.get();
        Book book = optionalBook.get();

        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.removeItem(book);

        if (shoppingCart.getSubtotal() < 0) {
            shoppingCart.reset();
            throw new SubtotalBelowZeroException("Subtotal cannot be below 0");
        }

        shoppingCartRepository.save(shoppingCart);
        userRepository.save(user);
    }
}