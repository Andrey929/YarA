package com.example.yara.Service;

import com.example.yara.model.Lesson;
import com.example.yara.model.Student;
import com.example.yara.model.Teacher;
import com.example.yara.model.User;
import com.example.yara.repositories.LessonRepository;
import com.example.yara.repositories.StudentRepository;
import com.example.yara.repositories.TeacherRepository;
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
    @Transactional
    public void createTeacher(User user){
        Teacher teacher = new Teacher(user);
        teacherRepository.save(teacher);
        log.info("Учитель с email: "+ user.getEmail()+" был сохранен");
    }
    public List<Student> getStudents(){
        return studentRepository.findByTeacherId(getActiveUser().getId()).orElseThrow();
    }

//    public Long getTeacherId(){
//        User teacher = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return teacher.getId();
//    }
}
