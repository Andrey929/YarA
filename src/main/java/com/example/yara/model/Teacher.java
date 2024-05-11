package com.example.yara.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "teachers")
@RequiredArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(mappedBy = "teachers",fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons = new ArrayList<>();

    private Integer countStudents;

    private String subject;
    public Teacher(User user){
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(user, teacher.user) && Objects.equals(students, teacher.students) && Objects.equals(lessons, teacher.lessons) && Objects.equals(countStudents, teacher.countStudents) && Objects.equals(subject, teacher.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, students, lessons, countStudents, subject);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", students=" + students.toString() +
                ", countStudents=" + countStudents +
                ", subject='" + subject + '\'' +
                '}';
    }
}
