package com.study.editertools.controller;

import com.study.editertools.entity.AnalysisResultDO;
import com.study.editertools.entity.TaskDO;
import com.study.editertools.service.StudentService;
import com.study.editertools.service.TaskService;
import com.study.editertools.utils.PdfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/editer")
public class HomeController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/home")
    public String homePage() {
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public void upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
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
        TaskDO taskDO = new TaskDO();
        taskDO.setFileName(fileName);
        taskDO.setAddress(dest.getAbsolutePath());
        taskDO.setStatus(0);
        taskService.addStudent(taskDO);

//        List<TaskDO> tasks = taskService.listTask();
//
//        modelAndView.addObject("tasks", tasks);
//        modelAndView.setViewName("/fileList");
        response.sendRedirect("/editer/fileList");

    }

    @GetMapping("/fileList")
    @ResponseBody
    public ModelAndView fileList(ModelAndView modelAndView) {
        List<TaskDO> tasks = taskService.listTask();

        modelAndView.addObject("tasks", tasks);
        modelAndView.setViewName("/fileList");

        return modelAndView;
    }

    @GetMapping("/analysis")
    public ModelAndView analysis(@RequestParam Integer id, ModelAndView modelAndView) throws IOException {

        TaskDO task = taskService.findById(id);
        List<AnalysisResultDO> analysis = PdfUtil.analysis(task.getAddress());
        for (AnalysisResultDO analysisResultDO : analysis) {
            analysisResultDO.setTaskId(id);
        }

        //todo 入库

        modelAndView.addObject("analys", analysis);
        modelAndView.setViewName("/resultList");

        return modelAndView;
    }

}
