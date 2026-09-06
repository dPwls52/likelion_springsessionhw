package com.likelion.springsessionhw.greeting.service;

import com.likelion.springsessionhw.greeting.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GreetingService {
    private final MessageRepository messageRepository;

    public GreetingService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String greet(String name, int hour) {
        String message;

        if (hour >= 0 && hour <= 5) {
            message = "아직 안 주무셨어요? " ;
        } else {
            message = "안녕하세요, " + name + "님!";
        }

        messageRepository.save(message);
        return message;
    }

    public List<String> getAllMessages() {
        return messageRepository.findAll();
    }

    public int countMessages() {
        return messageRepository.count();
    }
}