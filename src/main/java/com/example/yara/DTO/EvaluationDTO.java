package com.example.yara.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class EvaluationDTO {
    Long studentID;
    Long lessonID;
    Integer evaluation;
    String comment;
}
