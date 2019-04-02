package com.twx.page.service;

import com.twx.page.Model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("book-data-Sql")
public interface BookService {
    //远程调用保存
    @RequestMapping("/savabook")
    String sava(Book book);

    //分页查询服务
    @RequestMapping("/bookpage")
    List findpage(int pageNum);

    //查找书籍
    @RequestMapping("/findbook")
    Book findbook(@RequestParam("bid") Long bid);

    //删除书籍
    @RequestMapping("/deletebook")
    String deletebook(@RequestParam("bid") Long bid);

    //修改书籍
    @RequestMapping("/updatabook")
    String updatabook(Book book);

    //借书出库
    @RequestMapping("/jiannum")
    String jiannum(@RequestParam("bid") Long bid);

    //还书入库
    @RequestMapping("/addnum")
    String addnum(@RequestParam("bid") Long bid);

    //模糊查询
    @RequestMapping("/likebookbyname")
    List<Book> likebookByname(@RequestParam("bname") String bname);

}
