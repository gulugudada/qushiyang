package com.nengguoqieguo.utils;

import java.sql.Date;

/**
 * 算星座的工具类
 */
public class Constellation {
    public static String getConstellation(Date birthday){
//        System.out.println(birthday.getDay());
//        System.out.println(birthday.getMonth());
        String[] str = birthday.toString().split("-");
        int yue = Integer.valueOf(str[1]);
        int day = Integer.valueOf(str[2]);
//        System.out.println(yue);
//        System.out.println(day);
        switch (yue) {
            case 1:
                if (day <= 19) {
                    return "魔羯座";
                } else {
                    return "水瓶座";
                }
            case 2:
                if (day <= 18) {
                    return "水瓶座";
                } else {
                    return "双鱼座";
                }
            case 3:
                if (day <= 20) {
                    return "双鱼座";
                } else {
                    return "白羊座";
                }
            case 4:
                if (day <= 19) {
                    return "白羊座";
                } else {
                    return "金牛座";
                }
            case 5:
                if (day <= 20) {
                    return "金牛座";
                } else {
                    return "双子座";
                }
            case 6:
                if (day <= 21) {
                    return "双子座";
                } else {
                    return "巨蟹座";
                }
            case 7:
                if (day <= 22) {
                    return "巨蟹座";
                } else {
                    return "狮子座";
                }
            case 8:
                if (day <= 22) {
                    return "狮子座";
                } else {
                    return "处女座";
                }
            case 9:
                if (day <= 22) {
                    return "处女座";
                } else {
                    return "天秤座";
                }
            case 10:
                if (day <= 23) {
                    return "天秤座";
                } else {
                    return "天蝎座";
                }
            case 11:
                if (day <= 22) {
                    return "天蝎座";
                } else {
                    return "射手座";
                }
            case 12:
                if (day <= 21) {
                    return "射手座";
                } else {
                    return "魔羯座";
                }
        }
        return null;
    }
}
