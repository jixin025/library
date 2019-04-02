package com.twx.book.controller;

import com.twx.book.model.Book;
import com.twx.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
public class BookController {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private BookRepository bookRepository;


    /*图书服务
    查看书籍，修改书籍信息，新增书籍，删除书籍 分页查询书籍*/

    //分页查询书籍
    //分页查询所有书
    @RequestMapping("/bookpage")
    @ResponseBody
    public List<Book> queryPage(@RequestBody int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "bid");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<Book> page = bookRepository.findAll(pageable);
        List<Book> booklist = page.getContent();
        return booklist;
    }


    //保存书对象
    @PostMapping("/savabook")
    @ResponseBody
    public String savabook(@RequestBody Book book) {
        log.info("==========前台信息===========" + book.getBname() + book.getBpress() + book.getBedsc() + book.getBworks() + book.getBtime());
        bookRepository.save(book);
        return "success";
    }


    //查看书籍
    @RequestMapping("/findbook")
    public Book findbook(@RequestParam Long bid) {
        Book book = bookRepository.findById(bid).get();
        log.info("--------书籍------" + book.getBname());
        log.info("====时间格式====" + book.getBtime());
        return book;
    }

    //删除书籍
    @RequestMapping("/deletebook")
    public String deletebook(@RequestParam Long bid) {
        Book book = bookRepository.findById(bid).get();
        if (book.getBid().equals("")) {
            return "fail";
        } else {
            bookRepository.delete(book);
            return "success";
        }
    }

    //修改书籍
    @RequestMapping("/updatabook")
    public String updatabook(@RequestBody Book book) {
        log.info("————————修改书籍id" + book.getBid());
        log.info("—————书籍名称—————" + book.getBname());
        Book oldbook = bookRepository.findById(book.getBid()).get();
        if (oldbook.getBid().equals("")) {
            return "fail";
        } else {
            oldbook.setBname(book.getBname());
            oldbook.setBedsc(book.getBedsc());
            oldbook.setBnum(book.getBnum());
            oldbook.setBpress(book.getBpress());
            oldbook.setBtime(book.getBtime());
            oldbook.setBworks(book.getBworks());
            oldbook.setImgurl(book.getImgurl());
            bookRepository.save(oldbook);
            return "success";
        }
    }

    //模糊查询书
    @RequestMapping("/likebookbyname")
    public List<Book> likebookByname(@RequestParam String bname) {
        List<Book> bookList = bookRepository.findByBnameLike("%" + bname + "%");
        log.info("====查询结果====" + bookList.size());
        return bookList;
    }


    //num-
    @RequestMapping("/jiannum")
    public String jiannum(@RequestParam Long bid) {
        Book oldbook = bookRepository.findById(bid).get();
        int num = oldbook.getBnum();
        log.info("====jian num====" + num);
        if (num == 0) {
            return "fail";
        } else {
            num--;
            oldbook.setBnum(num);
            savabook(oldbook);
            log.info("====jian-if-num====" + num);
            return "success";
        }
    }

    //num+
    @RequestMapping("/addnum")
    public String addnum(@RequestParam Long bid) {
        Book oldbook = bookRepository.findById(bid).get();
        int num = oldbook.getBnum();
        num++;
        oldbook.setBnum(num);
        savabook(oldbook);
        return "success";
    }

}
