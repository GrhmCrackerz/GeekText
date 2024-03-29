package dev.group12.books;

import dev.group12.books.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;

@RestController
    @RequestMapping("/api/users")
    public class UserController {
        @Autowired
        private UserRepository userRepository;

        public UserController(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @PostMapping
        public ResponseEntity<User> createUser(@RequestBody User user) {
            User savedUser = userRepository.save(user);
            return ResponseEntity.created(URI.create("/users/" + savedUser.getId())).body(savedUser);
        }

        @GetMapping("/{username}")
        public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException(username));
            return ResponseEntity.ok().body(user);
        }
        @PutMapping("/{username}")
        public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User updatedUser) {
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setUsername(updatedUser.getUsername());
                user.setPassword(updatedUser.getPassword());
                user.setName(updatedUser.getName());
                user.setHomeAddress(updatedUser.getHomeAddress());
                User savedUser = userRepository.save(user);
                return ResponseEntity.ok().body(savedUser);
            } else {
                throw new UserNotFoundException(username);
            }
        }
    @PostMapping("/{username}/creditcard")
    public ResponseEntity<User> addCreditCard(@PathVariable String username, @RequestBody CreditCard creditCard) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setCreditCard(creditCard);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

}




