package com.example.junk.Controllers;

import com.example.junk.Models.User;
import com.example.junk.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registration")
    User addUser(@RequestBody User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            throw new IllegalArgumentException();
        }
        return userRepository.save(user);
    }

    @GetMapping("/login")
    public String login(@RequestBody User user){
        User loginUser = userRepository.findByUsername(user.getUsername());
        if (loginUser == null)
            return "user not found!";
        if(loginUser.getUsername().equals(user.getUsername()) && loginUser.getPassword().equals(user.getPassword())) {
            return Long.toString(loginUser.getId());
        }
        else
            return "password is incorrect!";
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<User> changePass(@PathVariable long id,@RequestBody User userDetails){
        User updatedPass = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found!"));
        updatedPass.setPassword(userDetails.getPassword());
        userRepository.save(updatedPass);

        return ResponseEntity.ok(updatedPass);
    }
}
