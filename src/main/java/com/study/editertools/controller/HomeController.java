package com.study.editertools.controller;

import com.study.editertools.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/editer")
public class HomeController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String homePage() {
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        byte[] bytes = file.getBytes();
        Path path = Paths.get(newFileName);
        Files.write(path, bytes);
        File dest = path.toFile();
    }
    @GetMapping("/addUser")
    public void addUser() {
        studentService.addStudent();
    }

}
