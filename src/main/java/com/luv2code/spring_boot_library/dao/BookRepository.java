package com.luv2code.spring_boot_library.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.spring_boot_library.entity.Book;
@CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
public interface BookRepository extends JpaRepository<Book,Long>{
    
    Page<Book> findByTitleContaining(@RequestParam("title") String title,Pageable pageable);
     Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);

//     @Query("select o from Book o where id in :book_ids")
//     List<Book> findBooksByBookIds (@Param("book_ids") List<Long> bookId);
}
