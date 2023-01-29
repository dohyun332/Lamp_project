package com.lamp.app.dao;

import com.lamp.app.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public int signin(Map map) {
        return sqlSession.selectOne("userMapper.signin", map);
    }

    @Override
    public int idDuplicationSearch(String u_id) {
        return sqlSession.selectOne("userMapper.idSearch", u_id);
    }

    @Override
    public int emailDuplicationSearch(String u_email) {
        return sqlSession.selectOne("userMapper.emailSearch", u_email);
    }

    @Override
    public int signupInsert(UserDto userDto) {
        return sqlSession.insert("userMapper.signup", userDto);
    }

    @Override
    public int lastLoginUpdate(Map map) {
        return sqlSession.update("userMapper.lastLogin", map);
    }

}
