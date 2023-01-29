package com.lamp.app.service;

import com.lamp.app.domain.UserDto;

import java.util.Map;

public interface UserService {
    int signin(Map map);

    int idDuplicationChk(String u_id);

    int emailDuplicationChk(String u_email);

    int signup(UserDto userDto);

    int lastLoginRenew(Map map);
}
