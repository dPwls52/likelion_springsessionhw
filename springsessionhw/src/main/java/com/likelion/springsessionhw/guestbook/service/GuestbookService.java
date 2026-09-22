package com.likelion.springsessionhw.guestbook.service;

import com.likelion.springsessionhw.guestbook.dto.GuestbookCreateRequest;
import com.likelion.springsessionhw.guestbook.dto.GuestbookDetailResponse;
import com.likelion.springsessionhw.guestbook.dto.GuestbookSummaryResponse;
import com.likelion.springsessionhw.guestbook.dto.GuestbookUpdateRequest;
import com.likelion.springsessionhw.guestbook.entity.Guestbook;
import com.likelion.springsessionhw.guestbook.repository.GuestbookRepository;
import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

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

    public GuestbookDetailResponse getGuestbook(Long guestbookId) {
        Guestbook guestbook = findGuestbookById(guestbookId);
        return toDetailResponse(guestbook);
    }

    private Guestbook findGuestbookById(Long guestbookId) {
        return guestbookRepository.findById(guestbookId)
                .orElseThrow();
    }

    @Transactional
    public GuestbookDetailResponse updateGuestbook(
            Long guestbookId,
            GuestbookUpdateRequest request
    ) {
        Guestbook guestbook = findGuestbookById(guestbookId);

        guestbook.update(
                request.getTitle(),
                request.getContent(),
                request.getPs()
        );
        return toDetailResponse(guestbook);
    }

    @Transactional
    public void deleteGuestbook(Long guestbookId) {
        Guestbook guestbook = findGuestbookById(guestbookId);
        guestbookRepository.delete(guestbook);
    }
}