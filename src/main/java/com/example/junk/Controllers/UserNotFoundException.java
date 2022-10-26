package com.example.junk.Controllers;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}