package com.nengguoqieguo.entity;

import lombok.Data;

@Data
public class Account {

    public Account(){

    }

    public Account(String account,String username,String password){
        this.account = account;
        this.username = username;
        this.password = password;
    }

    private int id;
    private String username;
    private String account;
    private String password;
}
