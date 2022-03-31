package com.nengguoqieguo.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private int id;
    private String username;
    private Date birthday;
    private char sex;
    private String constellation;
    private Education education;
    private String location;
    private String physique;
}
