package com.lamp.app.service;

import com.lamp.app.dao.UserDao;
import com.lamp.app.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public int signin(Map map) {
        return userDao.signin(map);
    }

    @Override
    public int idDuplicationChk(String u_id) {
        return userDao.idDuplicationSearch(u_id);
    }

    @Override
    public int emailDuplicationChk(String u_email) {
        return userDao.emailDuplicationSearch(u_email);
    }

    @Override
    public int signup(UserDto userDto) {
        return userDao.signupInsert(userDto);
    }

    @Override
    public int lastLoginRenew(Map map) {
        return userDao.lastLoginUpdate(map);
    }
}
