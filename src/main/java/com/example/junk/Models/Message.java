package com.example.junk.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String text;

    public Message() {}
}
