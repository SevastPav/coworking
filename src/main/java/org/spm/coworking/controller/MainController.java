package org.spm.coworking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.spm.coworking.entity.Rle;
import org.spm.coworking.entity.UserProfile;
import org.spm.coworking.repository.UserProfileRepository;

import java.util.Collections;
import java.util.Optional;

@Controller
public class MainController {

    private final UserProfileRepository userProfileRepository;

    public MainController(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @GetMapping(value = {"/", "/index, /home"})
    public String index() {
        return "/welcomePage.xhtml";
    }

    @GetMapping("/login")
    public String login() {
        return "/loginPage.xhtml";
    }

    @GetMapping("/add")
    public String add() {
        return "/addPage.xhtml";
    }

    @GetMapping("/acc")
    public String acc() {
        return "/accPage.xhtml";
    }

    @GetMapping("/orders")
    public String orders() {
        return "/ordersPage.xhtml";
    }

    @GetMapping("/pay")
    public String pay() {
        return "/payPage.xhtml";
    }

    @GetMapping("/search")
    public String searchGet() {
        return "/searchPage.xhtml";
    }

    @GetMapping("/office")
    public String officeGet() {
        return "/officePage.xhtml";
    }

    @GetMapping("/registration")
    public String registtration() {
        return "/registrationPage.xhtml";
    }

    @PostMapping("/registration")
    public String addUser(UserProfile user) {
        user.setFio("test");
        user.setRoles(Collections.singleton(Rle.USER));
        userProfileRepository.save(user);
        return "/registrationPage.xhtml";
    }

}