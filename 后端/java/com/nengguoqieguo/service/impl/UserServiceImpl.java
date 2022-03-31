package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.EducationMapper;
import com.nengguoqieguo.dao.UserMapper;
import com.nengguoqieguo.entity.Education;
import com.nengguoqieguo.entity.User;
import com.nengguoqieguo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    EducationMapper educationMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public int findEducationId(String username) {
        return userMapper.findEducationId(username);
    }

    @Transactional
    @Override
    public int addUser(User user) {
        educationMapper.addEducation(user.getEducation());
        return userMapper.addUser(user);
    }

    @Transactional
    @Override
    public int addUser2(User user) {
//        先添加educaion表中的数据  再回填到user表中educationid
        Education education = new Education();
        educationMapper.addEducation(education);
        user.setEducation(education);
        return userMapper.addUser(user);
    }

    @Transactional
    @Override
    public int updateUser(User user) {
//        先更新education  再更新user
        Education education = user.getEducation();
        education.setId(userMapper.findEducationId(user.getUsername()));
        educationMapper.updateEducation(education);
        return userMapper.updateUser(user);
    }

    @Override
    public int updatePhysique(String physique, String username) {
        return userMapper.updatePhysique(physique,username);
    }
}
