package com.example.yara.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class StudentDTO {
    private String email;
}
