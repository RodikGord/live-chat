package com.example.junk.Repositories;

import com.example.junk.Models.Message;
import com.example.junk.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
