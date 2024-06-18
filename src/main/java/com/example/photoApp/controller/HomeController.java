package com.example.photoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private static String UPLOADED_FOLDER = "src/main/resources/static/uploaded/";

    @GetMapping("/")
    public String home(Model model) {
        File folder = new File(UPLOADED_FOLDER);
        List<String> photos = Arrays.stream(folder.listFiles())
                .map(File::getName)
                .collect(Collectors.toList());
        model.addAttribute("photos", photos);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
