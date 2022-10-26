package com.example.junk.Controllers;

import com.example.junk.Models.Message;
import com.example.junk.Models.User;
import com.example.junk.Repositories.MessageRepository;
import com.example.junk.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")//Стартовая страница с сообщениями
    public Iterable<Message> main() {
        Iterable<Message> messages = messageRepository.findAll();
        return messages;
    }

    @PostMapping("/{userId}")//Страница пользователя
    public Message add(@PathVariable Long userId, @RequestBody Message message)  {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        message.setUsername(user.getUsername());
        messageRepository.save(message);
        return message;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error(e.getMessage());
        return ":(";
    }
}
