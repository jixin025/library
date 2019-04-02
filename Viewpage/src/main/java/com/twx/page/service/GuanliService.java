package com.twx.page.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("guanli-data-Sql")
public interface GuanliService {
    //调用管理查询名字密码
    @RequestMapping("/findByGNameAndGpass")
    String findByGnameAndGpass(@RequestParam("gname") String uname, @RequestParam("gpass") String upass);
}
