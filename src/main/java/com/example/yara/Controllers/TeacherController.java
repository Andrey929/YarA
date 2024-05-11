package com.example.yara.Controllers;

import com.example.yara.DTO.EvaluationDTO;
import com.example.yara.DTO.LessonDTO;
import com.example.yara.DTO.StudentDTO;
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
        model.addAttribute("studentDTO", new StudentDTO());
        model.addAttribute("studentsList",teacherService.getStudents());
        model.addAttribute("lessonList",lessonService.getLessons());
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
    @GetMapping("/teacherPanel/MyStudents")
    public String getStudents(Model model){
        model.addAttribute("studentsList",teacherService.getStudents());
        return "teacherPanel";
    }
    @GetMapping("/teacherPanel/myLessons")
    public String getLessons(Model model){
        model.addAttribute("lessonList",lessonService.getLessons());
        //studentService.getEvaluations();
        return "redirect:/teacherPanel";
    }
    @PostMapping("/teacherPanel/addStudent")
    public String addNewStudent(@ModelAttribute("studentDTO") StudentDTO studentDTO){
        teacherService.addStudent(studentDTO.getEmail());
        return "redirect:/teacherPanel";
    }
    @PostMapping("/teacherPanel/setEvaluation")
    public String setEvaluation(@ModelAttribute("evaluationDTO")EvaluationDTO evaluationDTO){
        teacherService.setEvaluation(evaluationDTO);
        return "redirect:/teacherPanel/HistoryMyLessons";
    }
    @GetMapping("/teacherPanel/HistoryMyLessons")
    public String getHistoryLessons(Model model){
        model.addAttribute("lessonList",lessonService.getHistoryLessons());
        model.addAttribute("evaluationDTO",new EvaluationDTO());
        return "myLessons";
    }


}
