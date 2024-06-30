package com.softeem.springbootdemo.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class User {

    private Integer id; //int - integer
    private String username;
    private String password;
    private LocalDateTime regtime;
    private String phone;
    private String img;
    private LocalDate birth;
    private Integer status;
    private String salt;

}
