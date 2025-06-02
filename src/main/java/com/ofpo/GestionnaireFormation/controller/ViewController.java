package com.ofpo.GestionnaireFormation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/roles")
    public String roles() {
        return "roles";
    }

    @GetMapping("/modules")
    public String modules() {
        return "modules";
    }

    @GetMapping("/sequences")
    public String sequences() {
        return "sequences";
    }

    @GetMapping("/inscription")
    public String inscription() {
        return "inscription";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
} 