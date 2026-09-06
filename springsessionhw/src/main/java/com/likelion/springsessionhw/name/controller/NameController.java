package com.likelion.springsessionhw.name.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {

    @GetMapping("/my-name")
    public String getMyName() {
        return "임예진";
    }
}