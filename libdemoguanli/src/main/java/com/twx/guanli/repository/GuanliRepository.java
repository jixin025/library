package com.twx.guanli.repository;

import com.twx.guanli.model.Guanli;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GuanliRepository extends CrudRepository <Guanli,Long> {

    //查询名字和密码 用于登录
    List<Guanli> findByGnameAndGpass(String gname, String gpass);
}
