package com.likelion.springsessionhw.guestbook.controller;

import com.likelion.springsessionhw.guestbook.dto.GuestbookCreateRequest;
import com.likelion.springsessionhw.guestbook.dto.GuestbookDetailResponse;
import com.likelion.springsessionhw.guestbook.dto.GuestbookSummaryResponse;
import com.likelion.springsessionhw.guestbook.dto.GuestbookUpdateRequest;
import com.likelion.springsessionhw.guestbook.service.GuestbookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/guestbooks")
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping
    public List<GuestbookSummaryResponse> getGuestbooks() {
        return guestbookService.getGuestbookSummaries();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuestbookDetailResponse create(@Valid @RequestBody GuestbookCreateRequest request) {
        return guestbookService.createGuestbook(request);
    }

    @GetMapping("/{guestbookId}")
    public GuestbookDetailResponse getGuestbook(@PathVariable Long guestbookId) {
        return guestbookService.getGuestbook(guestbookId);
    }

    @PutMapping("/{guestbookId}")
    public GuestbookDetailResponse update(@PathVariable Long guestbookId, @Valid @RequestBody GuestbookUpdateRequest request) {
        return guestbookService.updateGuestbook(guestbookId, request);
    }

    @DeleteMapping("/{guestbookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long guestbookId) {
        guestbookService.deleteGuestbook(guestbookId);
    }
}