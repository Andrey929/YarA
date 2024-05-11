package com.example.yara.Service;

import com.example.yara.DTO.EvaluationDTO;
import com.example.yara.model.*;
import com.example.yara.repositories.LessonRepository;
import com.example.yara.repositories.StudentRepository;
import com.example.yara.repositories.TeacherRepository;
import com.example.yara.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.yara.Utils.ModelUtils.getActiveUser;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    UserRepository userRepository;

    public void createTeacher(User user){
        Teacher teacher = new Teacher(user);
        teacherRepository.save(teacher);
        log.info("Учитель с email: "+ user.getEmail()+" был сохранен");
    }
    @Transactional
    public void addStudent(String email){
        Student student = new Student(userRepository.findByEmail(email).orElseThrow());
        Teacher teacher = getActiveUser().getTeacher();
        System.out.println(student.getEmail());
        System.out.println(teacher.getUser().getEmail());
        teacher.getStudents().add(student);
        student.getTeachers().add(teacher);


        studentRepository.save(student);
        teacherRepository.save(teacher);
        log.info("Add new student with email: "+ student.getEmail()+ "for teacher : "+ teacher.getUser().getLastName());
    }
    public List<Student> getStudents(){
        return userRepository.findByEmail(getActiveUser().getEmail()).orElseThrow().getTeacher().getStudents();
    }

    public void setEvaluation(EvaluationDTO evaluationDTO){
        System.out.println(evaluationDTO.getLessonID());
        Lesson lesson = lessonRepository.findById(evaluationDTO.getLessonID()).orElseThrow();
        Evaluation evaluation = new Evaluation(lesson,evaluationDTO.getComment(),evaluationDTO.getEvaluation());
        lesson.setEvaluation(evaluation);
        lessonRepository.save(lesson);
        log.info("Teacher "+ lesson.getTeacher().getUser().getLastName()+"set new evaluation for student with email: "+ lesson.getStudent().getLastName());
    }


}
