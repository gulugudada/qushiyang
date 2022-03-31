package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.Education;
import com.nengguoqieguo.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUser();
    public User findUserByUsername(String username);
    public int findEducationId(String username);
    public int addUser(User user);
    public int addUser2(User user);
    public int updateUser(User user);
    public int updatePhysique(String physique,String username);
}
