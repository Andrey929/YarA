package com.example.yara.Controllers;

import com.example.yara.Service.LessonService;
import com.example.yara.Service.StudentService;
import com.example.yara.Service.UserService;
import com.example.yara.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    StudentService studentService;

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model){
        if (!userService.createUser(user)){
            model.addAttribute("errorMessage", "Пользователь с почтой:" + user.getEmail() + " уже существует");
            return "regPage";
        }
        return "redirect:LoginPage";
    }
    @GetMapping("/study/myLessons")
    public String getLessons(Model model){
        model.addAttribute("lessonList",lessonService.getLessons());
        //studentService.getEvaluations();
        return "myLessons";
    }

    @GetMapping("/study/HistoryMyLessons")
    public String getHistoryLessons(Model model){
        model.addAttribute("lessonList",lessonService.getHistoryLessons());
        //studentService.getEvaluations();
        return "myLessons";
    }
}
