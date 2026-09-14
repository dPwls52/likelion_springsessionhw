package com.likelion.springsessionhw.guestbook.repository;

import com.likelion.springsessionhw.guestbook.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {
}