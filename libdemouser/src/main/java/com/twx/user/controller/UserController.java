package com.twx.user.controller;

import com.twx.user.model.User;
import com.twx.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

//@RefreshScope
@RestController("/userservice")
public class UserController {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private UserRepository userRepository;


    /*
  用户功能
  登录，注册，查询全部用户，修改用户，删除用户。
  */
    //保存到数据库
    @PostMapping("/savauser")
    public String savauser(@RequestBody User user) {
        log.info("从前台控制器传值——————————————————————" + user.getUname());
        User queryuser = userRepository.findByUname(user.getUname());
        //log.info("--------newuser对象数据"+newuser.getUid()+newuser.getUname()+newuser.getUpass());
        if (null==queryuser){
            userRepository.save(user);
            return "success";
        }else {
            return "fail";
        }


    }

    //登录功能

    //查询数据库信息
    @RequestMapping("/findByUNameAndUpass")
    public String findByUNameAndUPass(String uname, String upass) {
        log.info("---------视图服务信息---------" + uname + upass);
        List<User> list = userRepository.findByUnameAndUpass(uname, upass);
        if (list.size() == 0) {
            return "fail";
        } else {
            return list.get(0).getUid().toString();
        }
    }

    @RequestMapping("/findByUname")
    public String findByUname(String uname) {
        log.info("-------调用uname值-------"+uname);
        User queryUser = userRepository.findByUname(uname);
        if (null == queryUser) {
            return "fail";
        } else {
            return queryUser.getUid().toString();
        }
    }

    //分页查询所有用户
    @RequestMapping("/userpage")
    public List<User> queryPage(@RequestBody int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "uid");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);
        List<User> userlist = page.getContent();
        return userlist;
    }

    //查找用户
    @RequestMapping("/finduser")
    public User finduser(@RequestParam Long uid) {
        User user = userRepository.findById(uid).get();
        return user;
    }

    //修改用户
    @RequestMapping("/updatauser")
    public String updatauser(@RequestBody User user) {
        User olduser = userRepository.findById(user.getUid()).get();
        if (olduser.getUid().equals("")) {
            return "fail";
        } else {
            olduser.setUname(user.getUname());
            olduser.setUpass(user.getUpass());
            userRepository.save(olduser);
            return "success";
        }
    }

    //删除用户
    @RequestMapping("/deleteuser")
    public String deletebook(@RequestParam Long uid) {
        User user = userRepository.findById(uid).get();
        if (user.getUid().equals("")) {
            return "fail";
        } else {
            userRepository.delete(user);
            return "success";
        }
    }

    //模糊查询用户
    @RequestMapping("/likeuserbyname")
    public List<User> likebookByname(@RequestParam String uname) {
        List<User> userList = userRepository.findByUnameLike("%" + uname + "%");
        log.info("====查询结果====" + userList.size());
        return userList;
    }
}
