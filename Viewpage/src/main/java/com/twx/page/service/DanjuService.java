package com.twx.page.service;

import com.twx.page.Model.Danju;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("danju-data-Sql")
public interface DanjuService {

    //借书功能
    @RequestMapping("/jiebook")
    String Jiebook(Danju danju);

    //查询用户已借书籍
    @RequestMapping("/jiebooklist")
    List jiebooklist(@RequestParam("uid") Long uid);

    //归还图书
    @RequestMapping("/guihuan")
    String Guihuan(@RequestParam("uid") Long uid, @RequestParam("bid") Long bid);

    //续借图书
    @RequestMapping("/xujie")
    String Xujie(@RequestParam("uid") Long uid, @RequestParam("bid") Long bid);

    //管理员归还
    @RequestMapping("/guanlihuan")
    String Guanlihuan(@RequestParam("uid") Long uid, @RequestParam("bid") Long bid);

    //管理员续借
    @RequestMapping("/guanlixu")
    String Guanlixu(@RequestParam("uid") Long uid, @RequestParam("bid") Long bid);
}
