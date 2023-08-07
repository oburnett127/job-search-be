package com.oburnett127.jobsearch.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CsrfController {

    @GetMapping("/csrf-token")
    public String getCsrfToken(HttpServletResponse response, HttpServletRequest request) {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    
        if (csrf != null) {
            Cookie cookie = new Cookie("XSRF-TOKEN", csrf.getToken());
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return "CSRF token set";
    }
}