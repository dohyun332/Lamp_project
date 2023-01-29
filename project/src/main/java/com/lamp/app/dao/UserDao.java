package com.lamp.app.dao;

import com.lamp.app.domain.UserDto;

import java.util.Map;

public interface UserDao {
    int signin(Map map);

    int idDuplicationSearch(String u_id);

    int emailDuplicationSearch(String u_email);

    int signupInsert(UserDto userDto);

    int lastLoginUpdate(Map map);
}
