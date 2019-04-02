package com.twx.book.repository;

import com.twx.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long>{
    //通过名字查找书
    List<Book> findByBnameLike(String bname);
}
