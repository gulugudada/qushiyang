package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> findAllUser();
    public User findUserByUsername(String username);
    public int findEducationId(String username);
    public int addUser(User user);
    public int updateUser(User user);
    public int updatePhysique(String physique,String username);
}
