package com.example.photoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PhotoController {
    private static String UPLOADED_FOLDER = "src/main/resources/static/uploaded/";

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "upload";
        }

        try {
            // Создаем директорию, если её нет
            File uploadDir = new File(UPLOADED_FOLDER);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Сохраняем файл на сервере
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload '" + file.getOriginalFilename() + "'");
        }

        return "upload";
    }
}
