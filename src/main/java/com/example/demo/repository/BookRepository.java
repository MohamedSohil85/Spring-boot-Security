package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Borrower;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "select TITLE ,AUTHOR,GENRE from Book  where AUTHOR =:AUTHOR", nativeQuery = true)
    public List getBookByAuthor(@Param("AUTHOR") String author);

    @Query(value = "select AUTHOR  from Book where TITLE =:TITLE", nativeQuery = true)
    public Book getAuthorByTitle(@Param("TITLE") String title);

    @Query(value = "select BORROWER.NAME , BORROWER.ADDRESS from Book inner join BORROWER on BORROWER.ID=BOOK.BORROWER_ID where TITLE =:TITLE", nativeQuery = true)
    public List getBorrowerByBookName(@Param("TITLE") String title);
//@Query("SELECT b FROM City c WHERE c.name LIKE CONCAT('%',:ending, '%') AND c.population < :num")

}