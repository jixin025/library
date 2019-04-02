package com.twx.page.service;

import com.twx.page.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("user-data-Sql")
public interface UserService {
    //调用远程保存服务
    @RequestMapping("/savauser")
    String savaUser(User user);

    //调用远程名字密码查询服务
    @RequestMapping("/findByUNameAndUpass")
    String findByUnameAndUpass(@RequestParam("uname") String uname, @RequestParam("upass") String upass);

    //调用远程分页查询用户
    @RequestMapping("/userpage")
    List<User> findpage(int pageNum);

    @RequestMapping("/findByUname")
    String findByUname(@RequestParam("uname") String uname);
    //查找用户
    @RequestMapping("/finduser")
    User finduser(@RequestParam("uid") Long uid);

    //删除用户
    @RequestMapping("/deleteuser")
    String deleteuser(@RequestParam("uid") Long uid);

    //修改用户
    @RequestMapping("/updatauser")
    String updatauser(User user);

    //模糊查询
    @RequestMapping("/likeuserbyname")
    List<User> likeuserByname(@RequestParam("uname") String uname);
}
