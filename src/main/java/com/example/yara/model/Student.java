package com.example.yara.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "students")
@RequiredArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "student")
    private Set<Lesson> lessons = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers = new HashSet<>();

    public Student(User user){
        this.user = user;
    }

    public String getName(){
        return user.getName();
    }
    public String getLastName(){
        return user.getLastName();
    }
    public String getEmail(){
        return user.getEmail();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                // Other simple properties, avoid calling toString() on related entities
                '}';
    }

}
