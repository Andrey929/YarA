package com.example.yara.repositories;

import com.example.yara.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface teacherStudentRepository extends JpaRepository<Student, Long> {

}
