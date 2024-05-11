package com.example.yara.Service;

import com.example.yara.DTO.LessonDTO;
import com.example.yara.enums.Roles;
import com.example.yara.model.*;
import com.example.yara.repositories.LessonRepository;
import com.example.yara.repositories.StudentRepository;
import com.example.yara.repositories.TeacherRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.yara.Utils.ModelUtils.getActiveUser;

@Data
@Service
@RequiredArgsConstructor
@Slf4j
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;


    public void creatLesson(LessonDTO lessonDTO){
        Teacher teacher = getActiveUser().getTeacher();
        Student student = studentRepository.findById(lessonDTO.getStudentID()).orElseThrow();
        Evaluation evaluation = new Evaluation();
        Lesson lesson = new Lesson(teacher,student,lessonDTO.getDataTime(),lessonDTO.getDuration(),evaluation);
        lessonRepository.save(lesson);
        log.info("Create lesson for Student "+ lesson.getStudent().getLastName() +" with teacher "+ lesson.getTeacher().getUser().getLastName());
    }
    public List<Lesson> getLessons(){
        return getActiveUserLessons().stream()
                .filter(lesson -> lesson.getTimeOfStart()
                .isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Lesson::getTimeOfStart)).toList();
    }
    public List<Lesson> getHistoryLessons(){
        return getActiveUserLessons().stream()
                .filter(lesson -> lesson.getTimeOfStart()
                .isBefore(LocalDateTime.now()))
                .sorted(Comparator.comparing(Lesson::getTimeOfStart)).toList();
    }
    private List<Lesson> getActiveUserLessons(){
        User user = getActiveUser();
        List<Lesson> lessons;
        if (user.getRoles().contains(Roles.ROLE_TEACHER)){
            lessons = lessonRepository.findByTeacherId(user.getTeacher().getId()).orElseThrow();
        }else {
            System.out.println();
            lessons = lessonRepository.findByStudentId((user.getStudent().getId())).orElseThrow();
        }
        return lessons;
    }
}
