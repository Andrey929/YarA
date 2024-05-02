package com.example.yara.repositories;

import com.example.yara.model.Lesson;
import com.example.yara.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    Optional<List<Lesson>> findByTeacherId(Long id);
    Optional<List<Lesson>> findByStudentId(Long id);
    Optional<List<Lesson>> findByStudentIdAndEvaluationIsNotNull(Long id);
}
