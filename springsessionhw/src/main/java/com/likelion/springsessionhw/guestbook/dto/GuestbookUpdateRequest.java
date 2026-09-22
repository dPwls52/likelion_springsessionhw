package com.likelion.springsessionhw.guestbook.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GuestbookUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String ps;
}