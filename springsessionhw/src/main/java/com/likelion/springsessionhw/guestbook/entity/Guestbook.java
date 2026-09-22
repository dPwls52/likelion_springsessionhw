package com.likelion.springsessionhw.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "guestbooks")
@Getter
@NoArgsConstructor
public class Guestbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false, length = 20)
    private String writer;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 200)
    private String ps;

    public Guestbook(String title, String content, String writer, String ps) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.ps = ps;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String title, String content, String ps) {
        this.title = title;
        this.content = content;
        this.ps = ps;
    }
}