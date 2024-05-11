package com.example.yara.Service;

import com.example.yara.model.Lesson;
import com.example.yara.model.User;
import com.example.yara.repositories.LessonRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.yara.Utils.ModelUtils.getActiveUser;

@Data
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    @Autowired
    LessonRepository lessonRepository;

//    public List<Integer>getEvaluations(){
//        List<Lesson> lessons = lessonRepository.findByStudentIdAndEvaluationIsNotNull(getActiveUser().getStudent().getId()).orElseThrow();
//        List<Integer> res = new ArrayList<>();
//        lessons.forEach(lesson -> {
//           res.add(lesson.getEvaluation());
//        });
//        return res;
//    }

}
