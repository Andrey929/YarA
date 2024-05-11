package com.example.yara.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@Entity
@Table(name = "evaluations")
@RequiredArgsConstructor
@AllArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy ="evaluation")
    private Lesson lesson;

    private String comment;

    private Integer evaluationPoint;

    public Evaluation(Lesson lesson, String comment, Integer evaluation) {
        this.lesson = lesson;
        this.comment = comment;
        this.evaluationPoint = evaluation;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", evaluation=" + evaluationPoint +
                '}';
    }
}
