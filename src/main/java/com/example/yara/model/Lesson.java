package com.example.yara.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private Integer evaluation;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDateTime timeOfStart;
    private Integer duration;//продолжительность
    public Lesson(Teacher teacher,Student student,LocalDateTime timeOfStart,Integer duration){
        this.teacher = teacher;
        this.student = student;
        this.timeOfStart =timeOfStart;
        this.duration = duration;
    }
}
