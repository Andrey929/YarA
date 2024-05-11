package com.example.yara.repositories;

import com.example.yara.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    //Optional<List<Student>> findByTeacherId(Long id);
}
