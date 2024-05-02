package com.example.yara.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "teachers")
@RequiredArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons = new ArrayList<>();
    public Teacher(User user){
        this.user = user;
    }
}
