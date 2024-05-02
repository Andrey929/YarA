package com.example.yara.Controllers;

import com.example.yara.DTO.LessonDTO;
import com.example.yara.Service.LessonService;
import com.example.yara.Service.TeacherService;
import com.example.yara.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@PreAuthorize("hasAuthority('ROLE_TEACHER')")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    LessonService lessonService;
    @GetMapping("/teacherPanel")
    public String getTeacherPanel(Model model){
        model.addAttribute("lessonDTO",new LessonDTO());
        model.addAttribute("studentsList",teacherService.getStudents());
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("teacherInf",user);
        return "teacherPanel";
    }
    @PostMapping("/teacherPanel/addLesson")
    public String addLesson(@ModelAttribute("lessonDTO") LessonDTO lessonDTO){
        lessonService.creatLesson(lessonDTO);
        return "redirect:/teacherPanel";
    }
    @GetMapping("/teacherPanel/addLesson")
    public String addLesson(Model model){
        //User teacher =(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "redirect:/teacherPanel";
    }
    @GetMapping("/teacherPanel/myStudents")
    public String getStudents(Model model){
        model.addAttribute("studentsList",teacherService.getStudents());
        return "myStudents";
    }

}
