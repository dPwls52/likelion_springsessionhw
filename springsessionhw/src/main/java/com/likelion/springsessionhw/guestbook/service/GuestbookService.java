package com.likelion.springsessionhw.guestbook.service;

import com.likelion.springsessionhw.guestbook.dto.GuestbookSummaryResponse;
import com.likelion.springsessionhw.guestbook.entity.Guestbook;
import com.likelion.springsessionhw.guestbook.repository.GuestbookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestbookService {

    private final GuestbookRepository guestbookRepository;

    public GuestbookService(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    public List<GuestbookSummaryResponse> getGuestbookSummaries() {
        List<Guestbook> guestbooks = guestbookRepository.findAll();
        List<GuestbookSummaryResponse> responses = new ArrayList<>();

        for (Guestbook guestbook : guestbooks) {
            GuestbookSummaryResponse response = new GuestbookSummaryResponse(
                    guestbook.getTitle(),
                    guestbook.getWriter(),
                    guestbook.getPs()
            );
            responses.add(response);
        }

        return responses;
    }
}