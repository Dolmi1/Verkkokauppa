package com.verkkokauppa.verkkokauppa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index"; // Palauttaa index.html -sivun
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // Palauttaa about.html -sivun
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Palauttaa login.html -sivun
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Palauttaa register.html -sivun
    }
}
