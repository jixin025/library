package com.twx.guanli.controller;

import com.twx.guanli.model.Guanli;
import com.twx.guanli.repository.GuanliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class GuanliController {
    Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private GuanliRepository guanliRepository;

    @RequestMapping("/findByGNameAndGpass")
    @ResponseBody
    public String findByGNameAndGPass(String gname,String gpass){
        log.info("---------视图服务信息---------"+gname+gpass);
        List<Guanli> list= guanliRepository.findByGnameAndGpass(gname,gpass);
        if (list.size()==0){
            return "fail";
        }else {
            return "success";
        }
    }
}
