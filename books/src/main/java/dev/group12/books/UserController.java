package dev.group12.books;

import dev.group12.books.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    }
