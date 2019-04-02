package com.twx.user.repository;

import com.twx.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

     //查询 名字 密码 用于登录
     List<User>findByUnameAndUpass(String uname, String upass);
     //通过名字查询id
     User findByUname(String uname);
     //模糊查询
     List<User> findByUnameLike(String uname);
}

