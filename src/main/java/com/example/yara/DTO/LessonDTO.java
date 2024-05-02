package com.example.yara.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
@Component
public class LessonDTO {
    private Long studentID;
    private LocalDateTime dataTime;
    private Integer duration;
}
