package com.likelion.springsessionhw.guestbook.service;

import com.likelion.springsessionhw.guestbook.dto.GuestbookCreateRequest;
import com.likelion.springsessionhw.guestbook.dto.GuestbookDetailResponse;
import com.likelion.springsessionhw.guestbook.dto.GuestbookSummaryResponse;
import com.likelion.springsessionhw.guestbook.dto.GuestbookUpdateRequest;
import com.likelion.springsessionhw.guestbook.entity.Guestbook;
import com.likelion.springsessionhw.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestbookService {

    private final GuestbookRepository guestbookRepository;

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

    public GuestbookDetailResponse createGuestbook(GuestbookCreateRequest request) {
        Guestbook guestbook = new Guestbook(
                request.getTitle(),
                request.getContent(),
                request.getWriter(),
                request.getPs()
        );

        Guestbook savedGuestbook = guestbookRepository.save(guestbook);

        return toDetailResponse(savedGuestbook);
    }

    public GuestbookDetailResponse getGuestbook(Long id) {
        Guestbook guestbook = guestbookRepository.findById(id).orElseThrow();
        return toDetailResponse(guestbook);
    }

    @Transactional
    public GuestbookDetailResponse updateGuestbook(Long id, GuestbookUpdateRequest request) {
        Guestbook guestbook = guestbookRepository.findById(id).orElseThrow();
        guestbook.update(request.getTitle(), request.getContent(), request.getPs());
        return toDetailResponse(guestbook);
    }

    @Transactional
    public void deleteGuestbook(Long id) {
        Guestbook guestbook = guestbookRepository.findById(id).orElseThrow();
        guestbookRepository.delete(guestbook);
    }

    private GuestbookDetailResponse toDetailResponse(Guestbook guestbook) {
        return new GuestbookDetailResponse(
                guestbook.getId(),
                guestbook.getTitle(),
                guestbook.getContent(),
                guestbook.getWriter(),
                guestbook.getCreatedAt(),
                guestbook.getPs()
        );
    }
}