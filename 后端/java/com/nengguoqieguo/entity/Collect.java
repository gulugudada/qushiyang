package com.nengguoqieguo.entity;

import lombok.Data;

@Data
public class Collect {

    public Collect(){

    }

    public Collect(String username,String dishname,String type){
        this.username = username;
        this.dishname = dishname;
        this.type = type;
    }

    private int id;
    private String username;
    private String dishname;
    private String image;
    private String type;
}
