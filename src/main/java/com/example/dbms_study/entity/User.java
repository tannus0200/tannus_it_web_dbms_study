package com.example.dbms_study.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Integer userId;
    private String username;
    private String email;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;


}
